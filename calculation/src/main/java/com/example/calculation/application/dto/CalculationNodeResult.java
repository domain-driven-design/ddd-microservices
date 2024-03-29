package com.example.calculation.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculationNodeResult {

    private String name;
    private String expression;
    private BigDecimal value;
    private List<CalculationNodeResult> children = new ArrayList<>();
    private Map<String, BigDecimal> params = new HashMap<>();

}
