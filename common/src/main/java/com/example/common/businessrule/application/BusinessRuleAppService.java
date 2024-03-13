package com.example.common.businessrule.application;

import com.example.common.businessrule.BusinessRuleConfig;
import com.example.common.businessrule.BusinessRuleProvider;
import com.example.common.businessrule.BusinessRuleResponse;
import com.example.common.businessrule.IBusinessRule;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(BusinessRuleConfig.class)
public class BusinessRuleAppService {

    private final BusinessRuleConfig businessRuleConfig;
    private final BusinessRuleProvider businessRuleProvider;

    @SuppressWarnings("unchecked")
    public List<BusinessRuleResponse> query() {
        try {
            Class<?> clazz = Class.forName(businessRuleConfig.getClassPath());
            if (!IBusinessRule.class.isAssignableFrom(clazz)) {
                log.error("Class {} should implement IBusinessRule!", businessRuleConfig.getClassPath());
                throw new IllegalArgumentException(); //todo replace by proper exception
            }
            return ((List<? extends IBusinessRule>) Arrays.asList(clazz.getEnumConstants()))
                    .stream()
                    .map(r -> BusinessRuleResponse.of(r.getRuleName(), r.getDescription()))
                    .collect(Collectors.toList());
        } catch (ClassNotFoundException e) {
            log.error("failed to find business rule class path {}", businessRuleConfig.getClassPath(), e);
            throw new IllegalArgumentException(); //todo replace by proper exception
        }
    }

    public Object getRule(String ruleName) {
        return businessRuleProvider.getBusinessRule(ruleName, new TypeReference<>(){});
    }

}
