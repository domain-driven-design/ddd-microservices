package com.example.demo.application.dto.request;

import com.example.demo.domain.aggregate.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.common.utils.IdUtil;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketCommand {
    @NotNull
    private String title;
    @NotNull
    private String description;

    public Ticket toEntity(String userId) {
        return Ticket.builder().id(IdUtil.uuid()).createdBy(userId).updatedBy(userId).title(this.getTitle()).description(this.getDescription()).build();
    }
}
