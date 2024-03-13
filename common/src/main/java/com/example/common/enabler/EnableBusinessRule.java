package com.example.common.enabler;

import com.example.common.businessrule.BusinessRuleProvider;
import com.example.common.businessrule.application.BusinessRuleAppService;
import com.example.common.businessrule.controller.BusinessRuleController;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({BusinessRuleAppService.class, BusinessRuleController.class, BusinessRuleProvider.class})
public @interface EnableBusinessRule {}
