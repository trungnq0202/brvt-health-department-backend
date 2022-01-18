package com.trungngo.brvtadminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BrvtAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrvtAdminServiceApplication.class, args);
    }

}
