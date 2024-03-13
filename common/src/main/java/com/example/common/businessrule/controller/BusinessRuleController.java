package com.example.common.businessrule.controller;


import com.example.common.businessrule.BusinessRuleResponse;
import com.example.common.businessrule.application.BusinessRuleAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/business-rules")
public class BusinessRuleController {

    private final BusinessRuleAppService appService;

    @GetMapping("/{ruleName}")
    public Object getRule(@PathVariable String ruleName) {
        return appService.getRule(ruleName);
    }

    @GetMapping
    public List<BusinessRuleResponse> queryRuleList() {
        return appService.query();
    }

}
