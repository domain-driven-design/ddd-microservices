package com.example.calculation.adapter;


import com.example.calculation.application.args.CalculationCommand;
import com.example.calculation.application.dto.CalculationNodeResult;
import com.example.calculation.application.service.CalculationAppService;
import com.example.calculation.domain.aggregate.AbstractCalculationData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("定价测算")
@RestController
@RequiredArgsConstructor
@RequestMapping("/calculation")
public class CalculationController {

    private final CalculationAppService calculationAppService;

    @PostMapping("/calculate")
    @ApiOperation("测算")
    public ResponseEntity<List<CalculationNodeResult>> calculate(@RequestBody CalculationCommand<AbstractCalculationData> command) {
        return ResponseEntity.ok(calculationAppService.calculate(command));
    }

}
