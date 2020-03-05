package com.liang.kill.service;

import com.alibaba.fastjson.JSON;
import com.liang.kill.mapper.OrderMapper;
import com.liang.kill.pojo.Order;
import com.liang.kill.pojo.Policy;
import com.liang.kill.pojo.Sku;
import com.liang.kill.tools.KillException;
import com.liang.kill.tools.KillExceptionEnum;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private AmqpTemplate amqpTemplate;

    //redis中order的前缀
    private final String ORDER_PREFIX = "order_id:";
    //redis数据key前缀
    private final String POLICY_PREFIX = "POLICY_SKUID:";
    private final String SKU_PREFIX = "SKU_ID:";
    public void insertOrder(Long sku_id){
        //过滤前100个请求
        if (redisTemplate.opsForValue().increment("POST_NUM",100) <= 100){
            Order order = new Order();
            //判断是否为秒杀商品
            if (redisTemplate.hasKey(POLICY_PREFIX + sku_id)) {
                //从缓存中获取秒杀政策
                String p = redisTemplate.opsForValue().get(POLICY_PREFIX + sku_id);
                Policy policy = JSON.parseObject(p, Policy.class);
                //从缓存中获取商品信息
                String s = redisTemplate.opsForValue().get(SKU_PREFIX + sku_id);
                Sku sku = JSON.parseObject(s,Sku.class);
                //填写订单信息
                order.setActual_fee(policy.getPrice());
                order.setCreate_time(new SimpleDateFormat("yyyy-mm-dd HH:MM:SS").format(new Date()));
                order.setImage(sku.getImages());
                order.setNum(1);
                order.setOrder_id(System.currentTimeMillis());
                order.setOwn_spec(sku.getOwn_spec());
                order.setPayment_type(1);
                order.setPost_fee(0L);
                order.setPrice(policy.getPrice());
                order.setTotal_fee(sku.getPrice());
                order.setSku_id(sku_id);
                order.setStatus(1);
                order.setTitle(sku.getTitle());
                order.setUser_id(10000L);
                //把订单写入redis缓存
                String order_json = JSON.toJSONString(order);
                redisTemplate.opsForValue().set(ORDER_PREFIX + order.getOrder_id(),order_json);
                //向消息队列发送消息
                amqpTemplate.convertAndSend("order_queue",order_json);
            }else{
                throw new KillException(KillExceptionEnum.POLICY_NOT_FOUND);
            }
        }else{
            //提示用户商品已经被抢完
            throw new KillException(KillExceptionEnum.STOCK_NOT_ENOUGH);
        }
    }
}
