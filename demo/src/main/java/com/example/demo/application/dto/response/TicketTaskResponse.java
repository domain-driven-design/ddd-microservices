package com.example.demo.application.dto.response;

import com.example.common.domain.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketTaskResponse extends AbstractResponse {
    private String title;
    private String description;
    private String assigneeId;
    private String status;
}
