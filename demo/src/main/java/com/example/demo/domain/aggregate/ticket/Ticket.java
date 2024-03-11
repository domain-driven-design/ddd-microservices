package com.example.demo.domain.aggregate.ticket;

import domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Ticket extends AbstractEntity {
    private String id;
    private String title;
    private String description;
    private String creatorId;
    private String assigneeId;
    private TicketStatus status;
    private List<TicketTask> tasks;
}
