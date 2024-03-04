package com.example.calculation.application.assembler;

import com.example.calculation.application.args.CalculationCommand;
import com.example.calculation.application.dto.CalculationNodeResult;
import com.example.calculation.domain.aggregate.AbstractCalculationData;
import com.example.calculation.domain.aggregate.CalculationEssential;
import com.example.calculation.domain.aggregate.CalculationNode;
import com.example.calculation.domain.aggregate.CalculationTransaction;
import com.example.calculation.infrastructure.persistence.CalculationTransactionPO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CalculationAssembler {

    default <T extends AbstractCalculationData> CalculationEssential<T> toEssential(
            CalculationCommand<T> command) {
        return CalculationEssential.<T>builder()
                .bizType(command.getBizType())
                .mode(command.getMode())
                .bizId(command.getBizId())
                .transactionId(command.getTransactionId())
                .data(command.getData())
                .build();
    }

    CalculationTransactionPO toTransactionPO(CalculationTransaction transaction);

    List<CalculationNodeResult> toProcessResult(List<CalculationNode> nodes);

}
