package com.liang.kill.controller;

import com.liang.kill.service.StorageHistoryService;
import com.liang.kill.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private StorageHistoryService storageHistoryService;

    @PostMapping("/storageHistory/add")
    public ResponseEntity<Void> addStorageHistory (
            @RequestParam("sku_id") Long sku_id,
            @RequestParam("in_quanty")  Long in_quanty,
            @RequestParam("out_quanty") Long out_quanty){
        storageHistoryService.insertStorageHistory(sku_id,in_quanty,out_quanty);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
