package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.demo.RibbonApplication;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class RibbonApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args );
    }
    
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
}
