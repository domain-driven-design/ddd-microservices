package com.example.demo.domain.aggregate.ticket;

import com.example.common.domain.AbstractEntity;
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
