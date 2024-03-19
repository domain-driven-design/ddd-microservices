package com.example.calculation.infrastructure.util.calculationflow;

import com.example.common.error.SystemException;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkSource;
import guru.nidi.graphviz.model.MutableNode;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PaintUtil {

    PaintUtil() {}

    public static void printGraphImage(Dag graph, String flowPath) {
        List<LinkSource> links = new ArrayList<>();
        graph.getGraph().forEach((nodeName, edgeNames) -> {
            MutableNode node = Factory.mutNode(nodeName).addLink(edgeNames.toArray(String[]::new));
            links.add(node);
        });

        Graph graphToPaint = Factory.graph("gg")
                .directed()
                .graphAttr()
                .with(Rank.dir(Rank.RankDir.LEFT_TO_RIGHT))
                .with(links);

        try {
            Graphviz.fromGraph(graphToPaint).render(Format.PNG).toFile(new File(flowPath));
        } catch (IOException e) {
            log.error("[printGraphImage] fail to print graph", e);
            throw new SystemException();
        }
    }

}
