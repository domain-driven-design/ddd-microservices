package com.example.demo.application.dto.response;

import com.example.common.domain.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TicketResponse extends AbstractResponse {
    private String title;
    private String description;
    private String assigneeId;
    private String status;
//    private List<TicketTaskResponse> tasks;
}
