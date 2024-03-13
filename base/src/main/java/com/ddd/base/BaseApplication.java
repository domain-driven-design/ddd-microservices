package com.ddd.base;

import com.example.common.enabler.EnableAuthService;
import com.example.common.enabler.EnableBusinessRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAuthService
@EnableBusinessRule
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

}
