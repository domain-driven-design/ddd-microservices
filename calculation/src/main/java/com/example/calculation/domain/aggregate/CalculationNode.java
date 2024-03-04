package com.example.calculation.domain.aggregate;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 节点，包含了某一节点的基础信息，包括名称、公式、计算结果、关联节点等
 */
@Data
@Builder
public class CalculationNode {

    private String name;
    private String expression;
    private BigDecimal value;
    private List<CalculationNode> children;
    private Map<String, BigDecimal> params;

}
