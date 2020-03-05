package com.liang.kill.controller;

import com.liang.kill.pojo.Policy;
import com.liang.kill.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class PolicyController {
    @Autowired
    private PolicyService polityService;

    @RequestMapping("/policy/add")
    public ResponseEntity<Object> addPolity(@RequestBody @Valid Policy policy, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining("|")));
        }
        polityService.addPolity(policy);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
