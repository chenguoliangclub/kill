package com.liang.kill.service;

import com.liang.kill.mapper.SpuMapper;
import com.liang.kill.pojo.Spu;
import com.liang.kill.tools.KillException;
import com.liang.kill.tools.KillExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpuService {
    @Autowired
    private SpuMapper spuMapper;

    public List<Spu> getSpuList() {
        List<Spu> spus = spuMapper.selectAll();
        if (CollectionUtils.isEmpty(spus)){
            throw new KillException(KillExceptionEnum.SPU_NOT_FOUND);
        }
        return spus;
    }
}
