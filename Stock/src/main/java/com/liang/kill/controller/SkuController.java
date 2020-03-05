package com.liang.kill.controller;

import com.liang.kill.pojo.Sku;
import com.liang.kill.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkuController {
    @Autowired
    private SkuService skuService;

    @RequestMapping("/sku/list")
    public ResponseEntity<List<Sku>> querySkuList(
            @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer size
    ){
        return ResponseEntity.ok(skuService.getSkuList(page,size));
    }
}
