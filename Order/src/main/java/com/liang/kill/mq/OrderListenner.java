package com.liang.kill.mq;

import com.alibaba.fastjson.JSON;
import com.liang.kill.mapper.OrderMapper;
import com.liang.kill.pojo.Order;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class OrderListenner {
    @Autowired
    private OrderMapper orderMapper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "order_queue",durable = "true"),
            exchange = @Exchange(name = "order_exchange",type = ExchangeTypes.TOPIC)
    ))
    public void listenOrderSend(String msg){
        if(StringUtils.isEmpty(msg)){
            return ;
        }
        Order order = JSON.parseObject(msg, Order.class);
        orderMapper.insert(order);
    }
}
