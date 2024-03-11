package com.example.demo.application.calculation.node;


import com.example.calculation.application.node.AbstractNodeComponent;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;

@LiteflowComponent(id = UpperLimitInterestRateNodeComponent.VARIABLE_NAME, name = "上限利率")
@LiteflowCmpDefine
public class UpperLimitInterestRateNodeComponent extends AbstractNodeComponent {
    public static final String VARIABLE_NAME = "upperLimitInterestRate";

    @Override
    protected String getVariableName() {
        return VARIABLE_NAME;
    }

    @Override
    protected void calculate(CalculationContext context) {
        autoCalculate(context);
    }

}
