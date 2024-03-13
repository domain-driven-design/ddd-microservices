package com.example.demo.domain.aggregate;


import com.example.demo.domain.aggregate.ticket.TicketStatus;
import com.example.demo.domain.aggregate.ticket.TicketTaskStatus;
import com.example.common.datadictionary.DataDictionaryType;
import com.example.common.datadictionary.Description;

public enum DemoDataDictionaryType implements DataDictionaryType {
    TICKET_STATUS(TicketStatus.class, "Enum for ticket status"), TICKET_TASK_STATUS(TicketTaskStatus.class, "Ticket task status");

    private final Class<? extends Description> clazz;
    private final String desc;


    DemoDataDictionaryType(Class<? extends Description> clazz, String desc) {
        this.clazz = clazz;
        this.desc = desc;
    }

    @Override
    public Class<? extends Description> getClazz() {
        return this.clazz;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }
}
