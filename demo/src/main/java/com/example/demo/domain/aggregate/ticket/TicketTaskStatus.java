package com.example.demo.domain.aggregate.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TicketTaskStatus {
    TODO("Todo"), DOING("Doing"), DONE("Done");

    private final String name;
}
