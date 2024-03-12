package com.example.demo.domain.aggregate.ticket;

import domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
public class TicketTask extends AbstractEntity {
    private String id;
    private String name;
    private String description;
    private String assigneeId;
    private TicketTaskStatus status;
    private TicketTaskPriority priority;
}
