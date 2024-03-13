package com.example.demo.infrastructure.config;

import com.example.common.bizidgenerator.domain.BizType;
import com.example.calculation.domain.aggregate.CalculationMode;
import com.example.demo.domain.aggregate.calculation.DemoBizType;
import com.example.demo.domain.aggregate.calculation.DemoCalculationMode;
import com.example.calculation.infrastructure.util.AbstractMyBatisConfig;
import com.example.calculation.infrastructure.util.CommonEnumTypeHandler;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig extends AbstractMyBatisConfig {

    @Override
    protected void customize(org.apache.ibatis.session.Configuration configuration) {
        registerEnumTypeHandler(configuration, BizType.class, new CommonEnumTypeHandler<>(DemoBizType.class));
        registerEnumTypeHandler(configuration, CalculationMode.class, new CommonEnumTypeHandler<>(DemoCalculationMode.class));
    }
}
