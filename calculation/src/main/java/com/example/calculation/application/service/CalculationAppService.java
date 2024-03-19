package com.example.calculation.application.service;

import com.example.calculation.application.args.CalculationCommand;
import com.example.calculation.application.dto.CalculationNodeResult;
import com.example.calculation.domain.aggregate.AbstractCalculationData;
import com.example.calculation.domain.aggregate.CalculationMode;
import com.example.calculation.infrastructure.util.AbstractExpressionLoader;
import com.example.calculation.infrastructure.util.calculationflow.Dag;
import com.example.calculation.infrastructure.util.calculationflow.FlowUtil;
import com.example.calculation.infrastructure.util.calculationflow.GraphUtil;
import com.example.calculation.infrastructure.util.calculationflow.PaintUtil;
import com.example.common.error.ClientException;
import com.example.common.error.SystemException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.example.calculation.domain.exception.CalculationError.NO_MATCHED_CALCULATION_SERVICE;
import static com.example.calculation.domain.exception.CalculationError.WRITE_FLOW_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculationAppService {

    private final List<AbstractCalculationService> calculationServices;
    private final AbstractExpressionLoader expressionLoader;

    public List<CalculationNodeResult> calculate(CalculationCommand<AbstractCalculationData> command) {
        // 根据模式获取对应的service
        AbstractCalculationService service = calculationServices.stream()
                .filter(s -> s.getMode() == command.getMode()).findFirst()
                .orElseThrow(() -> new ClientException(NO_MATCHED_CALCULATION_SERVICE, command.getMode().getCode()));

        return service.execute(command);
    }

    /**
     * Generates a calculation flow based on the calculation mode and writes it to a file at the specified path
     * @param mode      The calculation mode enum, specifying which type of calculation flow to generate
     * @param flowPath  The path to the file where the calculation flow output will be written
     */
    public void generateFlow(CalculationMode mode, String flowPath, boolean printGraph) {
        log.info("Start generating {} calculation flow ----->", mode);
        Dag graph = GraphUtil.generateDag(mode, expressionLoader);
        String flow = FlowUtil.generateFlow(graph.topologicalSort(), mode.getCode());

        if (printGraph) {
            log.info("Start drawing calculation flow ----->");
            PaintUtil.printGraphImage(graph, flowPath);
        }

        log.info("Start writing flow to file -----> \n{}", flow);
        try {
            File file = new File(flowPath);
            FileWriter writer = new FileWriter(file);
            writer.write(flow);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            log.error("Fail to handle flow file -----> ", e);
            throw new SystemException(WRITE_FLOW_ERROR, flowPath);
        }

        log.info("calculation flow file generated successfully!");
    }

}
