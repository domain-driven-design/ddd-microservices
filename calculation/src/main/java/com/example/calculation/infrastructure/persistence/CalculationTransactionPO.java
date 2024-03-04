package com.example.calculation.infrastructure.persistence;

import bizidgenerator.domain.BizType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.calculation.domain.aggregate.CalculationMode;
import com.example.calculation.domain.aggregate.CalculationTransaction;
import lombok.Data;

@Data
@TableName("calculation_transaction")
public class CalculationTransactionPO {

    private String id;

    private BizType bizType;

    private String bizId;

    private CalculationMode mode;

    private String essential;

    private String error;

    private CalculationTransaction.Status status;

}
