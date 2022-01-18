package com.trungngo.brvtblogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class BrvtBlogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrvtBlogServiceApplication.class, args);
    }

}
