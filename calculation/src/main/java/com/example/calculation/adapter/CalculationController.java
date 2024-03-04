package com.example.calculation.adapter;


import com.example.calculation.application.args.CalculationCommand;
import com.example.calculation.application.dto.CalculationNodeResult;
import com.example.calculation.application.service.CalculationAppService;
import com.example.calculation.domain.aggregate.AbstractCalculationData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculation")
public class CalculationController {

    private final CalculationAppService calculationAppService;

    @PostMapping("/calculate")
    public List<CalculationNodeResult> calculate(@RequestBody CalculationCommand<AbstractCalculationData> command) {
        return calculationAppService.calculate(command);
    }

}
