package com.example.demo.application.node;


import com.example.calculation.application.node.AbstractNodeComponent;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;

@LiteflowComponent(id = OriginalAllScenesFirstYearEvaNodeComponent.VARIABLE_NAME, name = "原始全情景全年")
@LiteflowCmpDefine
public class OriginalAllScenesFirstYearEvaNodeComponent extends AbstractNodeComponent {
    public static final String VARIABLE_NAME = "originalAllScenesFirstYearEva";

    @Override
    protected String getVariableName() {
        return VARIABLE_NAME;
    }

    @Override
    protected void calculate(CalculationContext context) {
        autoCalculate(context);
    }

}
