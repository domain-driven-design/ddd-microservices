package com.example.demo.application.calculation.node;


import com.example.calculation.application.node.AbstractNodeComponent;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;

import java.math.BigDecimal;
import java.util.Map;

@LiteflowComponent(id = PeriodicCostNodeComponent.VARIABLE_NAME, name = "周期成本")
@LiteflowCmpDefine
public class PeriodicCostNodeComponent extends AbstractNodeComponent {
    public static final String VARIABLE_NAME = "periodicCost";

    @Override
    protected String getVariableName() {
        return VARIABLE_NAME;
    }

    @Override
    protected void calculate(CalculationContext context) {
        context.saveVariables(Map.of(VARIABLE_NAME, BigDecimal.valueOf(900.00)));
    }

}
