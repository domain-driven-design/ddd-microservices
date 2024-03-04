package com.example.calculation.application.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CalculationProcessResult {

    private List<CalculationNodeResult> nodes;

}
