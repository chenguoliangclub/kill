package com.liang.kill.controller;

import com.liang.kill.pojo.Spu;
import com.liang.kill.pojo.SpuDetail;
import com.liang.kill.service.SpuDetailService;
import com.liang.kill.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpuController {
    @Autowired
    private SpuService spuService;
    @Autowired
    private SpuDetailService spuDetailService;

    @RequestMapping("/spu/list")
    public ResponseEntity<List<Spu>> getSpuList(){
        return ResponseEntity.ok(spuService.getSpuList());
    }

    @RequestMapping("/spuDetail/{spuId}")
    public ResponseEntity<SpuDetail> getSpuDetailBySpuId(@PathVariable("spuId") Long spuId){
        return ResponseEntity.ok(spuDetailService.querySpuDetailBySpuId(spuId));
    }
}
