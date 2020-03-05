package com.liang.kill.service;

import com.liang.kill.mapper.StorageHistoryMapper;
import com.liang.kill.pojo.Storage;
import com.liang.kill.pojo.StorageHistory;
import com.liang.kill.tools.KillException;
import com.liang.kill.tools.KillExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class StorageHistoryService {
    @Autowired
    private StorageHistoryMapper storageHistoryMapper;
    @Autowired
    private StorageService storageService;
    //插入库存记录
    @Transactional
    public void insertStorageHistory(Long skuId,Long in_quanty,Long out_quanty){
        //根据skuId查询库存主表中是否已经存在数据
        Storage storage = storageService.queryStorageBySkuId(skuId);
        StorageHistory storageHistory = new StorageHistory();
        storageHistory.setIn_quanty(in_quanty);
        storageHistory.setOut_quanty(out_quanty);
        //如果存在，修改库存主表的库存量并获取库存id，然后插入库存记录表
        if (storage != null){
            storage.setQuanty(storage.getQuanty() + in_quanty - out_quanty);
            storageService.updateStorage(storage);
            storageHistory.setStock_storage_id(storage.getId());
        }else{
            //如果不存在，先插入库存表，再根据库存表返回的id插入到记录表
            Storage insertStorage = storageService.insertStorage(skuId, in_quanty, out_quanty);
            storageHistory.setStock_storage_id(insertStorage.getId());
        }
        int i = storageHistoryMapper.insert(storageHistory);
        if (i != 1 ){
            throw new KillException(KillExceptionEnum.STORAGE_HISTORY_INSERT_ERROR);
        }
    }
}
