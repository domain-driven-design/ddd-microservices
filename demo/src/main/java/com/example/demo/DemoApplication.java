package com.example.demo;

import enabler.EnableBusinessRule;
import com.example.calculation.infrastructure.util.EnableCalculation;
import enabler.EnableDistributeLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBusinessRule
@EnableCalculation
@EnableDistributeLock
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
