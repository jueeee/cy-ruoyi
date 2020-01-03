package com.cy.ruoyi.user.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RuoyiUserAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiUserAppApplication.class, args);
    }

}