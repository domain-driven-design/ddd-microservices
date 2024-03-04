package com.example.calculation.domain.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.calculation.application.assembler.CalculationAssembler;
import com.example.calculation.domain.aggregate.CalculationTransaction;
import infrastructure.persistence.CalculationTransactionMapper;
import infrastructure.persistence.CalculationTransactionPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CalculationTransactionRepository {

    private final CalculationTransactionMapper calculationTransactionMapper;
    private final CalculationAssembler assembler;

    public void upsertByBizId(CalculationTransaction transaction) {
        deleteByBizId(transaction.getBizId());
        calculationTransactionMapper.insert(assembler.toTransactionPO(transaction));
    }

    public void deleteByBizId(String bizId) {
        LambdaQueryWrapper<CalculationTransactionPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CalculationTransactionPO::getBizId, bizId);
        calculationTransactionMapper.delete(wrapper);
    }

}
