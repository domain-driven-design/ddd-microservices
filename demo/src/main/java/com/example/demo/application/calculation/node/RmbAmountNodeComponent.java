package com.example.demo.application.calculation.node;


import com.example.calculation.application.node.AbstractNodeComponent;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.example.demo.domain.aggregate.calculation.DemoCalculationData;
import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;

import java.util.Map;

@LiteflowComponent(id = RmbAmountNodeComponent.VARIABLE_NAME, name = "折人民币金额")
@LiteflowCmpDefine
public class RmbAmountNodeComponent extends AbstractNodeComponent {
    public static final String VARIABLE_NAME = "rmbAmount";

    @Override
    protected String getVariableName() {
        return VARIABLE_NAME;
    }

    @Override
    protected void calculate(CalculationContext context) {
        DemoCalculationData data = (DemoCalculationData) context.getEssential().getData();
        context.saveVariables(Map.of(VARIABLE_NAME, data.getOriginalAmount()));
    }

}
