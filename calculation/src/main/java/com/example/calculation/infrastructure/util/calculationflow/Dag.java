package com.example.calculation.infrastructure.util.calculationflow;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;
import com.example.common.utils.JacksonUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Dag {

    private final Map<String, List<String>> graph = new HashMap<>();

    public void addNode(String node, String edge) {
        if (!exist(node)) {
            initNodeWithNoDependency(node);
        }
        List<String> edges = graph.get(node);
        if (edges.contains(edge)) {
            return;
        }
        edges.add(edge);
    }

    public boolean exist(String node) {
        return graph.containsKey(node);
    }

    public void initNodeWithNoDependency(String node) {
        graph.put(node, Lists.newArrayList());
    }

    public List<NodeWithLevel> topologicalSort() {
        List<NodeWithLevel> result = Lists.newArrayList();

        Map<String, List<String>> modifyGraph = new HashMap<>(graph);
        while (!modifyGraph.isEmpty()) {
            int beforeSize = modifyGraph.size();
            updateResultAndReturnGraphFilterZeroInDegreeNodes(result, modifyGraph);
            int afterSize = modifyGraph.size();
            if (beforeSize == afterSize) {
                throw new IllegalArgumentException(); //todo
            }
        }
        if (result.size() != graph.size()) {
            Set<String> resultNodes = result.stream().map(NodeWithLevel::getNode).collect(Collectors.toSet());
            Set<String> allNodes = graph.keySet();
            allNodes.removeAll(resultNodes);
            throw new IllegalArgumentException("has a cycle" + JacksonUtil.toJson(allNodes)); //todo
        }
        return result;
    }

    private void updateResultAndReturnGraphFilterZeroInDegreeNodes(List<NodeWithLevel> result,
                                                                   Map<String, List<String>> graph) {
        Map<String, Integer> inDegree = Maps.newTreeMap();
        for (String node: graph.keySet()) {
            inDegree.put(node, 0);
        }
        for (List<String> toNodes : graph.values()) {
            for (String toNode : toNodes) {
                inDegree.put(toNode, inDegree.get(toNode) + 1);
            }
        }

        int level = 0;
        if (!CollectionUtils.isEmpty(result)) {
            level = result.stream()
                    .max(Comparator.comparing(NodeWithLevel::getLevel))
                    .map(NodeWithLevel::getLevel)
                    .orElseThrow(RuntimeException::new) + 1;
        }

        for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
            String node = entry.getKey();
            Integer nodeInDegree = entry.getValue();
            if (nodeInDegree == 0) {
                result.add(new NodeWithLevel(node, level));
                graph.remove(node);
                for (Map.Entry<String, List<String>> originalGraphEntry : graph.entrySet()) {
                    originalGraphEntry.getValue().remove(node);
                }
            }
        }
    }

}
