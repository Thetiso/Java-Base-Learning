package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import com.demo.ESApplication;
@SpringBootApplication
@EnableEurekaServer
public class ESApplication {
    public static void main(String[] args) {
        SpringApplication.run( ESApplication.class, args );
    }
}
