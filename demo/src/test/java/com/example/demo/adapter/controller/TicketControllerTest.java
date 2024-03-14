package com.example.demo.adapter.controller;

import com.example.demo.TestBase;
import com.example.demo.application.dto.request.CreateTicketCommand;
import com.example.demo.application.dto.request.UpdateTicketCommand;
import com.example.demo.fixture.TicketFixture;
import com.example.demo.infrastructure.persistence.mapper.base.TicketMapper;
import com.example.demo.infrastructure.persistence.mapper.base.TicketTaskMapper;
import com.example.demo.infrastructure.persistence.po.TicketTaskPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.example.common.utils.TestUtil.USER_CONTEXT;
import static com.example.common.utils.TestUtil.getUserContextString;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.core.Is.is;

class TicketControllerTest extends TestBase {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    TicketTaskMapper ticketTaskMapper;

    @Test
    void should_create_ticket() {
        CreateTicketCommand command = new CreateTicketCommand("Test title", "Test description");

        given()
                .header(USER_CONTEXT, getUserContextString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(command)
                .when()
                .post("/v1/tickets")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("title", is("Test title"))
                .body("description", is("Test description"))
                .body("id", any(String.class))
                .body("createdBy", is("testId"))
                .body("updatedBy", is("testId"))
                .body("createdTime", any(String.class))
                .body("updatedTime", any(String.class))
        ;
    }

    @Test
    void should_query_ticket_list() {
        TicketFixture.buildTickets().forEach(ticketMapper::insert);

        given()
                .header(USER_CONTEXT, getUserContextString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/v1/tickets")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("records[0].title", is("Ticket title"))
                .body("records[0].description", is("Ticket description"))
                .body("records[0].status", is("TODO"))
                .body("records[0].id", any(String.class))
                .body("records[0].createdBy", is("testId"))
                .body("records[0].updatedBy", is("testId"))
                .body("records[0].createdTime", any(String.class))
                .body("records[0].updatedTime", any(String.class))
        ;
    }

    @Test
    void should_read_ticket_detail() {
        TicketFixture.buildTickets().forEach(ticketMapper::insert);
        TicketFixture.buildTicketTasks().forEach(ticketTaskMapper::insert);

        given()
                .header(USER_CONTEXT, getUserContextString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/v1/tickets/ticket-id")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("title", is("Ticket title"))
                .body("description", is("Ticket description"))
                .body("status", is("TODO"))

                .body("id", any(String.class))
                .body("createdBy", is("testId"))
                .body("updatedBy", is("testId"))
                .body("createdTime", any(String.class))
                .body("updatedTime", any(String.class))
        ;
    }

    @Test
    void should_update_ticket() {
        UpdateTicketCommand command = new UpdateTicketCommand("New Test title", "New Test description");

        TicketFixture.buildTickets().forEach(ticketMapper::insert);

        given()
                .header(USER_CONTEXT, getUserContextString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(command)
                .when()
                .put("/v1/tickets/ticket-id")
                .then()
                .statusCode(HttpStatus.OK.value())
        ;
    }

    @Test
    void should_delete_ticket() {
        TicketFixture.buildTickets().forEach(ticketMapper::insert);

        given()
                .header(USER_CONTEXT, getUserContextString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/v1/tickets/ticket-id")
                .then()
                .statusCode(HttpStatus.OK.value())
        ;
    }
}
