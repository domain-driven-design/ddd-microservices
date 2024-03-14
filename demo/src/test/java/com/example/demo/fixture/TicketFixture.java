package com.example.demo.fixture;

import com.example.demo.infrastructure.persistence.po.TicketPO;
import com.example.demo.infrastructure.persistence.po.TicketTaskPO;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class TicketFixture {

    public static List<TicketPO> buildTickets() {
        return List.of(new TicketPO() {{
            setId("ticket-id");
            setCreatedTime(OffsetDateTime.now());
            setUpdatedTime(OffsetDateTime.now());
            setCreatedBy("testId");
            setUpdatedBy("testId");
            setTitle("Ticket title");
            setDescription("Ticket description");
            setAssigneeId(null);
            setStatus("TODO");
        }}, new TicketPO() {{
            setId("ticket-id-2");
            setCreatedTime(OffsetDateTime.now());
            setUpdatedTime(OffsetDateTime.now());
            setCreatedBy("testId");
            setUpdatedBy("testId");
            setTitle("Ticket title2");
            setDescription("Ticket description2");
            setAssigneeId(null);
            setStatus("TODO");
        }});
    }

    public static List<TicketTaskPO> buildTicketTasks() {
        return List.of(new TicketTaskPO() {{
            setId("ticket-task-id");
            setTicketId("ticket-id");
            setCreatedTime(OffsetDateTime.now());
            setUpdatedTime(OffsetDateTime.now());
            setCreatedBy("testId");
            setUpdatedBy("testId");
            setName("Ticket task name");
            setDescription("Ticket task description");
            setAssigneeId("");
            setStatus("TODO");
            setPriority("LOW");
        }}, new TicketTaskPO() {{
            setId("ticket-task-id2");
            setTicketId("ticket-id");
            setCreatedTime(OffsetDateTime.now());
            setUpdatedTime(OffsetDateTime.now());
            setCreatedBy("testId");
            setUpdatedBy("testId");
            setName("Ticket task name");
            setDescription("Ticket task description");
            setAssigneeId("");
            setStatus("TODO");
            setPriority("LOW");
        }});
    }
}
