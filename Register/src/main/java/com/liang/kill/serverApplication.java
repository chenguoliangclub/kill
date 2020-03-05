package com.liang.kill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class serverApplication {
    public static void main(String[] args) {
        SpringApplication.run(serverApplication.class);
    }
}
