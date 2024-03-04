package com.example.calculation.application.service;

import com.example.calculation.application.args.CalculationCommand;
import com.example.calculation.application.assembler.CalculationAssembler;
import com.example.calculation.application.dto.CalculationNodeResult;
import com.example.calculation.domain.aggregate.AbstractCalculationData;
import com.example.calculation.domain.aggregate.CalculationContext;
import com.example.calculation.domain.aggregate.CalculationEssential;
import com.example.calculation.domain.aggregate.CalculationExpression;
import com.example.calculation.domain.aggregate.CalculationMode;
import com.example.calculation.domain.aggregate.CalculationTransaction;
import com.example.calculation.domain.repository.CalculationTransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import infrastructure.util.AbstractExpressionLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractCalculationService implements CalculationServiceInterface {

    private final AbstractExpressionLoader expressionLoader;
    private final CalculationAssembler calculationAssembler;
    private final ObjectMapper objectMapper;
    private final CalculationTransactionRepository transactionRepository;
    private final FlowExecutor flowExecutor;

    public abstract CalculationMode getMode();

    public List<CalculationNodeResult> execute(CalculationCommand<AbstractCalculationData> command) {
        try {
            // 构建测算上下文
            CalculationEssential<AbstractCalculationData> essential = prepare(command);
            CalculationMode mode = command.getMode();
            Map<String, CalculationExpression> expression = expressionLoader.getLoadedExpression(mode);
            CalculationContext context = CalculationContext.init(mode, essential, expression);
            // 记录当前事务信息
            CalculationTransaction transaction = initializeTransaction(context);
            // 执行测算
            calculate(context);
            // 结果校验和保存
            handleCalculationResult(context, transaction);
            return calculationAssembler.toProcessResult(context.getAllNodes());
        } catch (Exception e) {
            log.error("[execute] failed to execute calculation!", e);
            throw new IllegalArgumentException(e); //todo
        }
    }

    private CalculationEssential<AbstractCalculationData> prepare(CalculationCommand<AbstractCalculationData> command) {
        CalculationEssential<AbstractCalculationData> essential = calculationAssembler.toEssential(command);
        // 执行自定义测算要素
        customizeEssential(essential, command);
        return essential;
    }

    private CalculationTransaction initializeTransaction(CalculationContext context) throws JsonProcessingException {
        CalculationEssential<AbstractCalculationData> essential = context.getEssential();
        CalculationTransaction transaction = CalculationTransaction.builder()
                .id(essential.getTransactionId())
                .bizId(essential.getBizId())
                .bizType(essential.getBizType())
                .mode(context.getMode())
                .status(CalculationTransaction.Status.PROCESSING)
                .essential(objectMapper.writeValueAsString(essential))
                .build();
        transactionRepository.upsertByBizId(transaction);
        return transaction;
    }

    private void calculate(CalculationContext context) {
        LiteflowResponse response = flowExecutor.execute2Resp(context.getMode().getCode(), null, context);
        if (!response.isSuccess()) {
            log.warn("[calculate] failed to execute lite flow, error: ", response.getCause());
            context.setFailureCause(response.getCause());
        }
    }

    private void handleCalculationResult(CalculationContext context, CalculationTransaction transaction) throws Exception {
        if (context.isSuccess()) {
            transaction.success(context.getAllNodes());
            transactionRepository.upsertByBizId(transaction);
        } else {
            transaction.fail(Arrays.toString(context.getFailureCause().getStackTrace()));
            transactionRepository.upsertByBizId(transaction);
            throw context.getFailureCause(); //todo
        }
    }

}
