package com.trungngo.brvtaccountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class BrvtAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrvtAccountServiceApplication.class, args);
    }

}
