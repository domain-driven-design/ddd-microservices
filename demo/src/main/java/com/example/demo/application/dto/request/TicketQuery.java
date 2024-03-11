package com.example.demo.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.page.PageQuery;

@Data
@AllArgsConstructor
public class TicketQuery extends PageQuery{
    private String title;
}
