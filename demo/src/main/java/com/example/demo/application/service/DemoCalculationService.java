package com.example.demo.application.service;

import com.example.calculation.application.assembler.CalculationAssembler;
import com.example.calculation.application.service.AbstractCalculationService;
import com.example.calculation.domain.aggregate.CalculationMode;
import com.example.calculation.domain.repository.CalculationTransactionRepository;
import com.example.demo.domain.aggregate.DemoCalculationMode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yomahub.liteflow.core.FlowExecutor;
import infrastructure.util.AbstractExpressionLoader;
import org.springframework.stereotype.Service;


@Service
public class DemoCalculationService extends AbstractCalculationService {

    public DemoCalculationService(AbstractExpressionLoader expressionLoader,
                                  CalculationAssembler calculationAssembler,
                                  ObjectMapper objectMapper,
                                  CalculationTransactionRepository transactionRepository,
                                  FlowExecutor flowExecutor) {
        super(expressionLoader, calculationAssembler, objectMapper, transactionRepository, flowExecutor);
    }

    @Override
    public CalculationMode getMode() {
        return DemoCalculationMode.DEPOSIT;
    }

}
