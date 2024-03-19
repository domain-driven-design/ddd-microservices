package com.example.calculation.infrastructure.util.calculationflow;

import com.example.common.error.SystemException;
import com.example.common.utils.FreemarkerUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.example.calculation.domain.exception.CalculationError.GENERATE_FLOW_ERROR;

@Slf4j
public class FlowUtil {
    private static final String FLOW_TEMPLATE_PATH = "/template";
    private static final String FLOW_TEMPLATE_NAME = "flow_template.ftl";
    private static final String FLOW_NAME = "flowName";
    private static final String LEVELS = "levels";
    private static final String NODES = "nodes";

    FlowUtil() {}

    public static String generateFlow(List<NodeWithLevel> nodeWithLevels, String flowName) {
        try {
            // set up FreeMarker
            Configuration cfg = FreemarkerUtil.create(FlowUtil.class, FLOW_TEMPLATE_PATH);

            // prepare template data
            List<Map<String, Object>> levels = new ArrayList<>();
            nodeWithLevels.stream()
                    .collect(Collectors.groupingBy(NodeWithLevel::getLevel, TreeMap::new,
                            Collectors.mapping(NodeWithLevel::getNode, Collectors.toList())))
                    .forEach((level, nodes) -> levels.add(Map.of(NODES, nodes)));

            Map<String, Object> templateData = new HashMap<>();
            templateData.put(LEVELS, levels);
            templateData.put(FLOW_NAME, flowName);

            // load template and set data
            Template temp = cfg.getTemplate(FLOW_TEMPLATE_NAME);
            Writer out = new StringWriter();
            temp.process(templateData, out);

            return out.toString();
        } catch (IOException | TemplateException e) {
            log.error("[generateFlow] fail to generate flow", e);
            throw new SystemException(GENERATE_FLOW_ERROR);
        }
    }

}
