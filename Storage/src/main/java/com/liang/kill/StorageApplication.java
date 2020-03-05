package com.liang.kill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.liang.kill.mapper")
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class);
    }
}
