package com.example.demo.domain.aggregate.ticket;

import datadictionary.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TicketStatus implements Description {
    TODO("Todo"), DOING("Doing"), DONE("Done");

    private final String description;

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
