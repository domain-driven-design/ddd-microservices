package com.example.calculation.application.service;

import com.example.calculation.application.args.CalculationCommand;
import com.example.calculation.domain.aggregate.AbstractCalculationData;
import com.example.calculation.domain.aggregate.CalculationEssential;

public interface CalculationServiceInterface {

    default void customizeEssential(CalculationEssential<AbstractCalculationData> essential,
                                    CalculationCommand<AbstractCalculationData> calculationCommand) {}

}
