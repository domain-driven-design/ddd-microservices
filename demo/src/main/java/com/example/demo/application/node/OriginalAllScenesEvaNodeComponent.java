package com.example.demo.application.node;


import com.example.calculation.application.node.AbstractNodeComponent;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;

import java.math.BigDecimal;
import java.util.Map;

@LiteflowComponent(id = OriginalAllScenesEvaNodeComponent.VARIABLE_NAME, name = "原始全情景")
@LiteflowCmpDefine
public class OriginalAllScenesEvaNodeComponent extends AbstractNodeComponent {
    public static final String VARIABLE_NAME = "originalAllScenesEva";

    @Override
    protected String getVariableName() {
        return VARIABLE_NAME;
    }

    @Override
    protected void calculate(CalculationContext context) {
        BigDecimal customerScene = context.retrieveValue(CustomerContributionNodeComponent.CUSTOMER_SCENE_VARIABLE_NAME);
        BigDecimal innerScene = context.retrieveValue(CustomerContributionNodeComponent.INNER_SCENE_VARIABLE_NAME);
        BigDecimal groupScene = context.retrieveValue(CustomerContributionNodeComponent.GROUP_SCENE_VARIABLE_NAME);
        BigDecimal result = customerScene.add(innerScene).add(groupScene);
        context.saveVariables(Map.of(VARIABLE_NAME, result));
    }

}
