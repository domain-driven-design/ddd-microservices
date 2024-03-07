package com.example.calculation.infrastructure.util.calculationflow;

import java.util.List;
import java.util.stream.Collectors;

public class FlowUtil {

    FlowUtil() {}

    public static String generateFlow(Dag graph, String flowName) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                    "<flow>\n" +
                        getMainFlow(graph, flowName) +
                    "</flow>";
    }

    private static String getMainFlow(Dag graph, String flowName) {
        StringBuilder flowMain = new StringBuilder();
        List<NodeWithLevel> nodeWithLevels = graph.topologicalSort();
        flowMain.append("    <chain name=\"" + flowName +"\">\n");
        flowMain.append("        THEN(\n");
        List<Integer> levels = nodeWithLevels.stream()
                .map(NodeWithLevel::getLevel)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        for (Integer level : levels) {
            String nodes = nodeWithLevels.stream()
                    .filter(nodeWithLevel -> nodeWithLevel.getLevel() == level)
                    .map(nodeWithLevel -> "                " + nodeWithLevel.getNode())
                    .collect(Collectors.joining(",\n"));
            boolean isLastLevel = level < levels.get(levels.size() - 1);
            flowMain.append("            WHEN(\n")
                    .append(nodes)
                    .append("\n")
                    .append("            )")
                    .append(isLastLevel? ",\n" : "\n");
        }
        flowMain.append("        )\n")
                .append("    </chain>\n");
        return flowMain.toString();
    }
}
