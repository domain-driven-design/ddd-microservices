package com.example.demo.domain.aggregate.ticket;

import com.example.common.datadictionary.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TicketTaskPriority implements Description {
    LOW("Low"), MIDDLE("Middle"), HIGH("High");

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
