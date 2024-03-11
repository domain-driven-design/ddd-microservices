package com.example.demo.infrastructure.config;

import com.example.calculation.application.args.CalculationCommand;
import com.example.demo.infrastructure.util.CalculationCommandMixin;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import utils.JacksonUtil;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = JacksonUtil.MAPPER;
        // 添加 mixin 类
        objectMapper.addMixIn(CalculationCommand.class, CalculationCommandMixin.class);
        return objectMapper;
    }
}
