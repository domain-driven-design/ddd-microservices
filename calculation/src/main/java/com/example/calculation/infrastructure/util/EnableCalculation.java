package com.example.calculation.infrastructure.util;

import com.example.calculation.adapter.CalculationController;
import com.example.calculation.application.assembler.CalculationAssemblerImpl;
import com.example.calculation.application.service.CalculationAppService;
import com.example.calculation.domain.aggregate.CalculationExpression;
import com.example.calculation.domain.repository.CalculationTransactionRepository;
import com.yomahub.liteflow.core.FlowExecutor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CalculationController.class,
        CalculationAppService.class,
        CalculationExpression.class,
        CalculationAssemblerImpl.class,
        CalculationTransactionRepository.class,
        FlowExecutor.class})
@MapperScan("com.example.calculation.infrastructure")
public @interface EnableCalculation {}
