package com.example.calculation.domain.aggregate;


import bizidgenerator.domain.BizType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculationEssential<T extends AbstractCalculationData> {

    private String transactionId;
    private CalculationMode mode;
    private BizType bizType;
    private String bizId;
    private T data;

}
