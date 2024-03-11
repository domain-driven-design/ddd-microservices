package com.example.demo.application.dto.request;

import com.example.demo.domain.aggregate.ticket.TicketStatus;
import com.example.demo.domain.aggregate.ticket.TicketTask;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateTicketCommand {
    @NotNull
    private String title;
    @NotNull
    private String description;
}
