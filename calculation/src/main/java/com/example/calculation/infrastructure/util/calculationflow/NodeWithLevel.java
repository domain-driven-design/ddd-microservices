package com.example.calculation.infrastructure.util.calculationflow;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NodeWithLevel {

    private String node;
    private int level;

}
