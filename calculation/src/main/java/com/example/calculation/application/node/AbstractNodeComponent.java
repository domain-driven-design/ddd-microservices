package com.example.calculation.application.node;

import com.example.calculation.domain.aggregate.CalculationContext;
import com.example.calculation.domain.aggregate.CalculationExpression;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import utils.NumberUtil;
import utils.PreconditionUtil;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
public abstract class AbstractNodeComponent extends NodeComponent {

    protected abstract String getVariableName();
    protected abstract void calculate(CalculationContext context);

    @Override
    public void process() {
        CalculationContext context = this.getContextBean(CalculationContext.class);
        calculate(context);
        createCalculationNode(context);
    }

    private void createCalculationNode(CalculationContext context) {
        context.insertNode(getVariableName());
    }

    public void autoCalculate(CalculationContext context) {
        CalculationExpression expression = context.getExpression().get(getVariableName());
        PreconditionUtil.begin()
                .checkNotNull(expression, IllegalArgumentException::new)
                .checkNotNull(expression.getExpression(), IllegalArgumentException::new);

        DefaultContext<String, Object> expressionContext = new DefaultContext<>();
        for (String variableName : expression.getVariables()) {
            expressionContext.put(variableName, context.retrieveValue(variableName));
        }

        try {
            ExpressRunner runner = new ExpressRunner();
            BigDecimal result = NumberUtil.toBigDecimal(
                    runner.execute(expression.getExpression(), expressionContext, null, true, false));

            log.info("[autoCalculate] Expression result for " + getVariableName() + ": " + result);
            context.saveVariables(Map.of(getVariableName(), result));
        } catch (Exception e) {
            log.error("[autoCalculate] Failed to execute expression {}", getVariableName(), e);
            throw new IllegalArgumentException(); //todo
        }
    }

}
