package infrastructure.util;

import businessrule.BusinessRuleProvider;
import businessrule.IBusinessRule;
import com.example.calculation.domain.aggregate.CalculationExpression;
import com.example.calculation.domain.aggregate.CalculationMode;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public abstract class AbstractExpressionLoader {

    private final Map<CalculationMode, Map<String, CalculationExpression>> expressionCache = new HashMap<>();
    private final BusinessRuleProvider businessRuleProvider;

    protected abstract Map<CalculationMode, List<IBusinessRule>> initializeModeToBusinessRulesMap();

    @PostConstruct
    public void initiateExpressionCache() {
        initializeModeToBusinessRulesMap().forEach((mode, rules) -> expressionCache.put(mode, loadExpression(rules)));
    }

    public Map<String, CalculationExpression> loadExpression(List<IBusinessRule> rules) {
        Map<String, CalculationExpression> expressions = new HashMap<>();
        rules.forEach(r -> {
            Map<String, CalculationExpression> businessRule = businessRuleProvider.getBusinessRule(
                    r.getRuleName(), new TypeReference<>() {});
            expressions.putAll(businessRule);
        });
        return expressions;
    }

    public Map<String, CalculationExpression> getLoadedExpression(CalculationMode mode) {
        return Optional.ofNullable(expressionCache.get(mode))
                .orElseThrow(() -> new RuntimeException()); //todo
    }

}
