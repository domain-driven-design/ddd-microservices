package com.example.demo.application.calculation.node;


import com.example.calculation.application.node.AbstractNodeComponent;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;

@LiteflowComponent(id = BusinessRarocNodeComponent.VARIABLE_NAME, name = "本币业务Raroc")
@LiteflowCmpDefine
public class BusinessRarocNodeComponent extends AbstractNodeComponent {
    public static final String VARIABLE_NAME = "businessRaroc";

    @Override
    protected String getVariableName() {
        return VARIABLE_NAME;
    }

    @Override
    protected void calculate(CalculationContext context) {
        autoCalculate(context);
    }

}
