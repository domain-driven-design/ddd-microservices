package com.example.demo.infrastructure.util;

import bizidgenerator.domain.BizType;
import com.example.calculation.domain.aggregate.CalculationMode;
import com.example.demo.domain.aggregate.DemoBizType;
import com.example.demo.domain.aggregate.DemoCalculationMode;
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
