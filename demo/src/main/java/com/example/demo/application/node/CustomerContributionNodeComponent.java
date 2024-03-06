package com.example.demo.application.node;


import com.example.calculation.application.node.AbstractNodeComponent;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;

import java.math.BigDecimal;
import java.util.Map;

@LiteflowComponent(id = CustomerContributionNodeComponent.VARIABLE_NAME, name = "综合贡献")
@LiteflowCmpDefine
public class CustomerContributionNodeComponent extends AbstractNodeComponent {
    public static final String VARIABLE_NAME = "customerContribution";
    public static final String CUSTOMER_SCENE_VARIABLE_NAME = "customerSceneEva";
    public static final String INNER_SCENE_VARIABLE_NAME = "innerSceneEva";
    public static final String GROUP_SCENE_VARIABLE_NAME = "groupSceneEva";

    @Override
    protected String getVariableName() {
        return VARIABLE_NAME;
    }

    @Override
    protected void calculate(CalculationContext context) {
        context.saveVariables(Map.of(
                CUSTOMER_SCENE_VARIABLE_NAME, BigDecimal.valueOf(1.00),
                INNER_SCENE_VARIABLE_NAME, BigDecimal.valueOf(2.00),
                GROUP_SCENE_VARIABLE_NAME, BigDecimal.valueOf(3.00))
        );
    }

}
