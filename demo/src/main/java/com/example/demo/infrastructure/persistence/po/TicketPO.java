package com.example.demo.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.domain.AbstractPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ticket")
public class TicketPO extends AbstractPO {
    private String title;
    private String description;
    private String assigneeId;
    private String status;
}
