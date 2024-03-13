package com.example.common.businessrule;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessRuleResponse {

    private String ruleName;
    private String description;

    public static BusinessRuleResponse of(String ruleName, String description) {
        BusinessRuleResponse response = new BusinessRuleResponse();
        response.setRuleName(ruleName);
        response.setDescription(description);
        return response;
    }

}
