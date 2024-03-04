package com.example.calculation.domain.aggregate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculationContext {

    private CalculationMode mode;
    private CalculationEssential<AbstractCalculationData> essential;
    private Map<String, CalculationExpression> expression = new HashMap<>();
    private Map<String, BigDecimal> variables = new HashMap<>();
    private Map<String, CalculationNode> nodes = new HashMap<>();
    private Exception failureCause;

    public static CalculationContext init(CalculationMode mode,
                                          CalculationEssential<AbstractCalculationData> essential,
                                          Map<String, CalculationExpression> expression) {
        CalculationContext context = new CalculationContext();
        context.setMode(mode);
        context.setEssential(essential);
        context.setExpression(expression);
        return context;
    }

    public BigDecimal retrieveValue(String variableName) {
        return variables.get(variableName);
    }

    public void saveVariables(Map<String, BigDecimal> variables) {
        this.variables.putAll(variables);
    }

    public boolean isSuccess() {
        return Objects.isNull(failureCause);
    }

    public void insertNode(String variableName) {
        CalculationExpression calculationExpression = this.getExpression().get(variableName);
        Map<String, BigDecimal> vars = new HashMap<>();
        calculationExpression.getVariables().forEach(v -> vars.put(v, this.retrieveValue(v)));
        CalculationNode node = CalculationNode.builder()
                .name(variableName)
                .expression(calculationExpression.getExpression())
                .value(this.retrieveValue(variableName))
                .params(vars)
                .build();
        if (!vars.isEmpty()) {
            node.setChildren(calculationExpression.getVariables().stream()
                    .map(v -> nodes.get(v))
                    .collect(Collectors.toList()));
        }
        nodes.put(variableName, node);
    }

    public List<CalculationNode> getAllNodes() {
        return new ArrayList<>(nodes.values());
    }

}
