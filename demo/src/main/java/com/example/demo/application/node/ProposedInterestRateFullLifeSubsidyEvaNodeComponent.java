package com.example.demo.application.node;


import com.example.calculation.application.node.AbstractNodeComponent;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;

@LiteflowComponent(id = ProposedInterestRateFullLifeSubsidyEvaNodeComponent.VARIABLE_NAME, name = "本币业务全生命周期EVA(含补贴)")
@LiteflowCmpDefine
public class ProposedInterestRateFullLifeSubsidyEvaNodeComponent extends AbstractNodeComponent {
    public static final String VARIABLE_NAME = "proposedInterestRateFullLifeSubsidyEva";

    @Override
    protected String getVariableName() {
        return VARIABLE_NAME;
    }

    @Override
    protected void calculate(CalculationContext context) {
        autoCalculate(context);
    }

}
