package com.example.demo.infrastructure.util;

import com.example.demo.domain.aggregate.DemoCalculationMode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = DemoCalculationMode.class)
public interface CalculationModeMixin {
}
