package com.example.demo.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import domain.AbstractPO;
import lombok.Data;

@Data
@TableName("ticket")
public class TicketPO extends AbstractPO {
    private String title;
    private String description;
    private String creatorId;
    private String assigneeId;
    private String status;
}
