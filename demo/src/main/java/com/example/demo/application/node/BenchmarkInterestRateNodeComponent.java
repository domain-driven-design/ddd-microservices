package com.example.demo.application.node;


import com.example.calculation.application.node.AbstractNodeComponent;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;

@LiteflowComponent(id = BenchmarkInterestRateNodeComponent.VARIABLE_NAME, name = "基准利率")
@LiteflowCmpDefine
public class BenchmarkInterestRateNodeComponent extends AbstractNodeComponent {
    public static final String VARIABLE_NAME = "benchmarkInterestRate";

    @Override
    protected String getVariableName() {
        return VARIABLE_NAME;
    }

    @Override
    protected void calculate(CalculationContext context) {
        autoCalculate(context);
    }

}
