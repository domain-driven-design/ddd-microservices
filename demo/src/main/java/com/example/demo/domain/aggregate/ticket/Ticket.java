package com.example.demo.domain.aggregate.ticket;

import domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public class Ticket extends AbstractEntity {
    private String title;
    private String description;
    private String assigneeId;
    private TicketStatus status;
    private List<TicketTask> tasks;
}
