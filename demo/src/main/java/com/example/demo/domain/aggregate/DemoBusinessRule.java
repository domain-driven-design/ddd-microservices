package com.example.demo.domain.aggregate;

import businessrule.IBusinessRule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DemoBusinessRule implements IBusinessRule {

    DEPOSIT_CALCULATION_RULE("deposit_calculation_rule", "存款测算规则");

    private String ruleName;
    private String description;

}
