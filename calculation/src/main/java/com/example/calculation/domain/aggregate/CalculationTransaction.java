package com.example.calculation.domain.aggregate;

import bizidgenerator.domain.BizType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 测算事务，用于记录当前测算信息和结果
 */
@Data
@Builder
public class CalculationTransaction {

    private String id;

    private BizType bizType;

    private String bizId;

    private CalculationMode mode;

    private String essential;

    private List<CalculationNode> nodes;

    private String error;

    private Status status;

    public void success(List<CalculationNode> nodes) {
        this.nodes = nodes;
        this.status = Status.SUCCESS;
    }

    public void fail(String error) {
        this.error = error;
        this.status = Status.FAILED;
    }

    public enum Status {
        SUCCESS,
        FAILED,
        PROCESSING
    }

}
