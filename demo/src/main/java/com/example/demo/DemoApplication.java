package com.example.demo;

import com.example.calculation.infrastructure.util.EnableCalculation;
import enabler.EnableAuthService;
import enabler.EnableBusinessRule;
import enabler.EnableDataDictionary;
import enabler.EnableDistributeLock;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBusinessRule
@EnableCalculation
@EnableAuthService
@EnableDistributeLock
@EnableDataDictionary
@MapperScan(basePackages = {
        "com.example.demo.infrastructure", "com.example.calculation.infrastructure"
})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
