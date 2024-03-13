package com.example.demo;

import com.example.calculation.infrastructure.util.EnableCalculation;
import com.example.common.enabler.EnableAuthService;
import com.example.common.enabler.EnableBusinessRule;
import com.example.common.enabler.EnableCommonFeatures;
import com.example.common.enabler.EnableDataDictionary;
import com.example.common.enabler.EnableDistributeLock;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBusinessRule
@EnableCalculation
@EnableAuthService
@EnableDistributeLock
@EnableDataDictionary
@EnableCommonFeatures
@MapperScan(basePackages = {
        "com.example.demo.infrastructure.persistence",
        "com.example.calculation.infrastructure"
})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
