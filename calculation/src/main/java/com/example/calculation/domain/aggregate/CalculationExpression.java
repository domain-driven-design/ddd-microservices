package com.example.calculation.domain.aggregate;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CalculationExpression {

    private String componentName;

    private String expression;

    private List<String> variables;

}
