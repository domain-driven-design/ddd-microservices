package com.example.demo.domain.aggregate.calculation;

import com.example.calculation.domain.aggregate.AbstractCalculationData;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DemoCalculationData implements AbstractCalculationData {

    private BigDecimal basicRate;
    private BigDecimal originalAmount;
    private BigDecimal basicRefactor;

}
