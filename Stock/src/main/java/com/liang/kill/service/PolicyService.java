package com.liang.kill.service;

import com.alibaba.fastjson.JSON;
import com.liang.kill.mapper.PolicyMapper;
import com.liang.kill.pojo.Policy;
import com.liang.kill.tools.KillException;
import com.liang.kill.tools.KillExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

@Service
public class PolicyService {
    @Autowired
    private PolicyMapper policyMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SkuService skuService;
    //redis数据key前缀
    private final String POLICY_PREFIX = "POLICY_SKUID:";
    private final String SKU_PREFIX = "SKU_ID:";

    public void addPolity(Policy policy){
        int i = policyMapper.insert(policy);
        if (i == 0){
            throw new KillException(KillExceptionEnum.POLICY_INSERT_ERROR);
        }
        Long skuId = policy.getSku_id();
        //放入redis缓存
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间戳
        Long beginTime = format.parse(policy.getBegin_time(),new ParsePosition(0)).getTime();
        Long nowTime = System.currentTimeMillis();
        Long endTime = format.parse(policy.getEnd_time(),new ParsePosition(0)).getTime();
        if (beginTime <= nowTime && nowTime <= endTime){
            //计算有效时间，单位秒
            Long time = (endTime - beginTime)/1000;
            //把秒杀政策写入缓存
            redisTemplate.opsForValue().set(POLICY_PREFIX + skuId, JSON.toJSONString(policy),time, TimeUnit.SECONDS);
            //把秒杀商品写入缓存
            redisTemplate.opsForValue().set(SKU_PREFIX + skuId,JSON.toJSONString(skuService.querySkuById(skuId)));
        }else{
            throw new KillException(KillExceptionEnum.POLICY_INSERT_ERROR);
        }

    }
}
