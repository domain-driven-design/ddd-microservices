package com.example.demo.application.calculation.node;


import com.example.calculation.application.node.AbstractNodeComponent;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;

import java.math.BigDecimal;
import java.util.Map;

@LiteflowComponent(id = OperatingCostNodeComponent.VARIABLE_NAME, name = "运营成本")
@LiteflowCmpDefine
public class OperatingCostNodeComponent extends AbstractNodeComponent {
    public static final String VARIABLE_NAME = "operatingCost";

    @Override
    protected String getVariableName() {
        return VARIABLE_NAME;
    }

    @Override
    protected void calculate(CalculationContext context) {
        context.saveVariables(Map.of(VARIABLE_NAME, BigDecimal.valueOf(9.00)));
    }

}
