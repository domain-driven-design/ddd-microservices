package com.example.demo.application.calculation.node;


import com.example.calculation.application.node.AbstractNodeComponent;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.example.demo.domain.aggregate.calculation.DemoCalculationData;
import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;

import java.util.Map;

@LiteflowComponent(id = BasicRefactorNodeComponent.VARIABLE_NAME, name = "基础折算率")
@LiteflowCmpDefine
public class BasicRefactorNodeComponent extends AbstractNodeComponent {
    public static final String VARIABLE_NAME = "basicRefactor";

    @Override
    protected String getVariableName() {
        return VARIABLE_NAME;
    }

    @Override
    protected void calculate(CalculationContext context) {
        DemoCalculationData data = (DemoCalculationData) context.getEssential().getData();
        context.saveVariables(Map.of(VARIABLE_NAME, data.getBasicRate()));
    }

}
