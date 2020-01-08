package com.vkopendoh.lunchapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.vkopendoh.lunchapp"})
@SpringBootApplication
public class LunchApplication {
    public static void main(String[] args) {
        SpringApplication.run(LunchApplication.class, args);
    }
}
