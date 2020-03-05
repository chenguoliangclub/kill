package com.liang.kill.service;

import com.liang.kill.mapper.StorageHistoryMapper;
import com.liang.kill.mapper.StorageMapper;
import com.liang.kill.pojo.Storage;
import com.liang.kill.tools.KillException;
import com.liang.kill.tools.KillExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class StorageService {
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private StorageHistoryMapper storageHistoryMapper;
    /*
        插入库存表，并返回Storage
     */
    public Storage insertStorage(Long spuId,Long in_quanty,Long out_quanty){
        Storage storage = new Storage();
        storage.setSku_id(spuId);
        storage.setQuanty(in_quanty - out_quanty);
        storage.setWarehouse_id(1L);
        int i = storageMapper.insert(storage);
        if (i != 1){
            throw new KillException(KillExceptionEnum.STORAGE_INSERT_ERROR);
        }
        return storage;
    }
    /*
        根据skuId检查商品是否已经存在
        存在返回Storage
        不存在返回Null
     */
    public Storage queryStorageBySkuId(Long skuId){
        Storage storage = new Storage();
        storage.setSku_id(skuId);
        return storageMapper.selectOne(storage);
    }
    /*
        更新库存表的信息
     */
    public void updateStorage(Storage storage){
        int i = storageMapper.updateByPrimaryKey(storage);
        if (i != 1){
            throw new KillException(KillExceptionEnum.STORAGE_UPDATE_ERROR);
        }
    }
}
