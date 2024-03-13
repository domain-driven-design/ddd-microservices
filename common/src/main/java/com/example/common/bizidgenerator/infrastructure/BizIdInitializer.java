package com.example.common.bizidgenerator.infrastructure;

import com.example.common.bizidgenerator.config.BizIdGeneratorBeanCondition;
import com.example.common.bizidgenerator.config.BizIdGeneratorConfig;
import com.example.common.bizidgenerator.domain.BizIdVariable;
import com.example.common.bizidgenerator.repository.BizIdGeneratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Conditional(BizIdGeneratorBeanCondition.class)
@EnableConfigurationProperties(BizIdGeneratorConfig.class)
public class BizIdInitializer {

    private final BizIdGeneratorConfig bizIdGeneratorConfig;
    private final BizIdGeneratorRepository bizIdGeneratorRepository;

    @Bean
    public CommandLineRunner init() {
        return args -> initBizIdGenerators();
    }

    private void initBizIdGenerators() {
        List<BizIdVariable> bizIdVariables = bizIdGeneratorConfig.getBizIdVariables();
        bizIdVariables.forEach(bizIdGeneratorRepository::upsert);
    }

}
