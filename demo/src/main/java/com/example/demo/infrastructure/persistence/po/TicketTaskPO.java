package com.example.demo.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import domain.AbstractPO;
import lombok.Data;

@Data
@TableName("ticket_task")
public class TicketTaskPO extends AbstractPO {
    private String id;
    // Hints: In PO we have to keep associate attribute but in domain model we don't need it anymore.
    private String ticketId;
    private String name;
    private String description;
    private String assigneeId;
    // Hints: Keep original String formal for enum in PO.
    private String status;
    private String priority;
}
