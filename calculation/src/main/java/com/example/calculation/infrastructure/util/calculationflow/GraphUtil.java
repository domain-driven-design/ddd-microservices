package com.example.calculation.infrastructure.util.calculationflow;

import com.example.calculation.domain.aggregate.CalculationExpression;
import com.example.calculation.domain.aggregate.CalculationMode;
import com.example.calculation.infrastructure.util.AbstractExpressionLoader;
import com.example.common.error.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

import static com.example.calculation.domain.exception.CalculationError.GENERATE_DAG_ERROR;

@Slf4j
public class GraphUtil {

    private GraphUtil() {}

    public static Dag generateDag(CalculationMode mode, AbstractExpressionLoader expressionLoader) {
        Dag graph = new Dag();
        try {
            Map<String, CalculationExpression> loadedExpression = expressionLoader.getLoadedExpression(mode);
            Map<String, String> variableToComponentName = loadedExpression.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getComponentName()));

            loadedExpression.forEach((node, exp) -> {
                String nodeComponentName = exp.getComponentName();
                for (String variable : exp.getVariables()) {
                    if (!StringUtils.equals(node, variable)) {
                        graph.addNode(variableToComponentName.get(variable), nodeComponentName);
                    }
                }
            });

            loadedExpression.forEach((node, exp) -> {
                if (!graph.exist(exp.getComponentName())) {
                    graph.initNodeWithNoDependency(exp.getComponentName());
                }
            });
        } catch (Exception e) {
            log.error("[generateDag] failed with error", e);
            throw new SystemException(GENERATE_DAG_ERROR);
        }
        return graph;
    }

}
