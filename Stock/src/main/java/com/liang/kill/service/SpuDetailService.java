package com.liang.kill.service;

import com.liang.kill.mapper.SpuDetailMapper;
import com.liang.kill.pojo.SpuDetail;
import com.liang.kill.tools.KillException;
import com.liang.kill.tools.KillExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpuDetailService {
    @Autowired
    private SpuDetailMapper spuDetailMapper;

    public SpuDetail querySpuDetailBySpuId(Long spuId){
        SpuDetail spuDetail = spuDetailMapper.selectByPrimaryKey(spuId);
        if (spuDetail == null){
            throw new KillException(KillExceptionEnum.SPU_NOT_FOUND);
        }
        return spuDetail;
    }
}
