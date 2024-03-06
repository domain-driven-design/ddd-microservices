package com.example.demo.application.node;


import com.example.calculation.application.node.AbstractNodeComponent;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;

import java.math.BigDecimal;
import java.util.Map;

@LiteflowComponent(id = ProposedInterestRateIncomeNodeComponent.VARIABLE_NAME, name = "拟定利率利息收入")
@LiteflowCmpDefine
public class ProposedInterestRateIncomeNodeComponent extends AbstractNodeComponent {
    public static final String VARIABLE_NAME = "proposedInterestRateIncome";

    @Override
    protected String getVariableName() {
        return VARIABLE_NAME;
    }

    @Override
    protected void calculate(CalculationContext context) {
        context.saveVariables(Map.of(VARIABLE_NAME, BigDecimal.valueOf(1000.00)));
    }

}
