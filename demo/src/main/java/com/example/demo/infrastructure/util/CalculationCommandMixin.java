package com.example.demo.infrastructure.util;

import com.example.common.bizidgenerator.domain.BizType;
import com.example.calculation.domain.aggregate.AbstractCalculationData;
import com.example.calculation.domain.aggregate.CalculationMode;
import com.example.demo.domain.aggregate.calculation.DemoBizType;
import com.example.demo.domain.aggregate.calculation.DemoCalculationData;
import com.example.demo.domain.aggregate.calculation.DemoCalculationMode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public interface CalculationCommandMixin<T extends AbstractCalculationData> {

    @JsonDeserialize(as = DemoCalculationMode.class)
    CalculationMode getMode();
    @JsonDeserialize(as = DemoBizType.class)
    BizType getBizType();
    @JsonDeserialize(as = DemoCalculationData.class)
    T getData();
}
