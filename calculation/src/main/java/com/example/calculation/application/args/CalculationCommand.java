package com.example.calculation.application.args;


import bizidgenerator.domain.BizType;
import com.example.calculation.domain.aggregate.AbstractCalculationData;
import com.example.calculation.domain.aggregate.CalculationMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculationCommand<T extends AbstractCalculationData> {

    @NotBlank
    private String transactionId;
    @NotNull
    private CalculationMode mode;
    @NotNull
    private BizType bizType;
    @NotBlank
    private String bizId;
    @NotNull
    private T data;

}
