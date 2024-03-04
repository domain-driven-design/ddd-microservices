package com.example.demo;

import enabler.EnableBusinessRule;
import infrastructure.util.EnableCalculation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBusinessRule
@EnableCalculation
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
