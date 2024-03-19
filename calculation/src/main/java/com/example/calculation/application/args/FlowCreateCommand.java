package com.example.calculation.application.args;


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
public class FlowCreateCommand<T extends CalculationMode> {

    @NotNull
    private T mode;
    @NotBlank
    private String flowFilePath;

    private Boolean printGraph = false;

}
