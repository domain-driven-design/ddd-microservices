package com.example.demo.application.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketResponse {
    private String title;
    private String description;
    private String assigneeId;
    private String status;
}
