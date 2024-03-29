package com.example.demo.application.calculation.service;

import com.example.common.businessrule.BusinessRuleProvider;
import com.example.common.businessrule.IBusinessRule;
import com.example.calculation.domain.aggregate.CalculationMode;
import com.example.demo.domain.aggregate.calculation.DemoBusinessRule;
import com.example.demo.domain.aggregate.calculation.DemoCalculationMode;
import com.example.calculation.infrastructure.util.AbstractExpressionLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DemoExpressionLoader extends AbstractExpressionLoader {

    public DemoExpressionLoader(BusinessRuleProvider businessRuleProvider) {
        super(businessRuleProvider);
    }

    @Override
    protected Map<CalculationMode, List<IBusinessRule>> initializeModeToBusinessRulesMap() {
        return Map.of(DemoCalculationMode.DEPOSIT, List.of(DemoBusinessRule.DEPOSIT_CALCULATION_RULE));
    }

}
