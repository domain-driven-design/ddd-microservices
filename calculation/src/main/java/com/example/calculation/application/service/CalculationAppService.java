package com.example.calculation.application.service;

import com.example.calculation.application.args.CalculationCommand;
import com.example.calculation.application.dto.CalculationNodeResult;
import com.example.calculation.domain.aggregate.AbstractCalculationData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculationAppService {

    private final List<AbstractCalculationService> calculationServices;

    public List<CalculationNodeResult> calculate(CalculationCommand<AbstractCalculationData> command) {
        // 根据模式获取对应的service
        AbstractCalculationService service = calculationServices.stream()
                .filter(s -> s.getMode() == command.getMode()).findFirst()
                .orElseThrow(IllegalAccessError::new); // todo exception

        return service.execute(command);
    }

}
