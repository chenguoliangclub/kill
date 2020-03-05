package com.liang.kill.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liang.kill.mapper.SkuMapper;
import com.liang.kill.pojo.Policy;
import com.liang.kill.pojo.Sku;
import com.liang.kill.tools.KillException;
import com.liang.kill.tools.KillExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkuService {
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    //redis数据key前缀
    private final String POLICY_PREFIX = "POLICY_SKUID:";
    private final String SKU_PREFIX = "SKU_ID:";

    public List<Sku> getSkuList(Integer page,Integer size){
        //开启分页助手
        PageHelper.startPage(page,size);
        List<Sku> skus = skuMapper.selectAll();
        //解析分页结果
        PageInfo<Sku> info = new PageInfo<>(skus);
        if (CollectionUtils.isEmpty(skus)){
            throw new KillException(KillExceptionEnum.SPU_NOT_FOUND);
        }
        PageHelper.startPage(page,size);
        skus.stream().map(sku -> {
            if (redisTemplate.hasKey(POLICY_PREFIX + sku.getId())) {
                String json = redisTemplate.opsForValue().get(POLICY_PREFIX + sku.getId());
                Policy policy = JSON.parseObject(json, Policy.class);
                //判断时间是否已经到期
                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                Long endTime = format.parse(policy.getEnd_time(),new ParsePosition(0)).getTime();
                Long now = System.currentTimeMillis();
                //没到期，写进商品信息
                if (now < endTime)
                    sku.setPolicy(policy);
                //已到期，删除redis缓存信息
/*                else
                    redisTemplate.delete(POLICY_PREFIX + sku.getId());*/
            }
            return sku;
        }).collect(Collectors.toList());
        return skus;
    }

    public Sku querySkuById(Long sku_id){
        Sku sku = skuMapper.selectByPrimaryKey(sku_id);
        if (sku == null){
            throw new KillException(KillExceptionEnum.SKU_NOT_FOUND);
        }
        return sku;
    }
}
