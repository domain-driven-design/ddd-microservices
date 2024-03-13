package com.ddd.base.adapter;

import com.ddd.base.TestBase;
import com.ddd.base.application.dto.OperationLogCreateCommand;
import com.ddd.base.application.dto.OperationLogResponse;
import com.ddd.base.domain.aggregate.operationlog.OperationResult;
import com.ddd.base.domain.aggregate.operationlog.OperationScene;
import com.ddd.base.domain.aggregate.operationlog.OperationType;
import com.ddd.base.infra.persistence.mapper.OperationLogMapper;
import com.ddd.base.infra.persistence.po.OperationLogPO;
import com.example.common.utils.page.PageResponse;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.example.common.utils.TestUtil.USER_CONTEXT;
import static com.example.common.utils.TestUtil.getUserContextString;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class OperationLogControllerTest extends TestBase {

    @Autowired
    private OperationLogMapper mapper;

    @Test
    void should_create_log_successfully() {
        OperationLogCreateCommand createCommand = new OperationLogCreateCommand();
        createCommand.setOperatorIp("110.1.1.1");
        createCommand.setOperationType(OperationType.READ);
        createCommand.setOperationScene(OperationScene.CALCULATION);
        createCommand.setOperationResult(OperationResult.SUCCESS);

        Response response = given()
                .header(USER_CONTEXT, getUserContextString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createCommand)
                .when()
                .post("/operation-log")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract()
                .response();

        OperationLogResponse operationLog = response.as(OperationLogResponse.class);
        assertThat(operationLog.getOperatorId(), equalTo("testId"));
        assertThat(operationLog.getOperatorIp(), equalTo("110.1.1.1"));
        assertThat(operationLog.getOperationType().getCode(), equalTo(OperationType.READ.getCode()));
        assertThat(operationLog.getOperationScene().getCode(), equalTo(OperationScene.CALCULATION.getCode()));
        assertThat(operationLog.getOperationResult().name(), equalTo(OperationResult.SUCCESS.name()));
    }

    @Test
    void should_query_log_successfully() {
        OperationLogPO operationLogPO = new OperationLogPO();
        operationLogPO.setId("1");
        operationLogPO.setOperationType(OperationType.READ);
        operationLogPO.setOperationScene(OperationScene.CALCULATION);
        operationLogPO.setOperationResult(OperationResult.SUCCESS);
        operationLogPO.setOperatorIp("110.1.1.1");
        operationLogPO.setOperatorId("testQueryMockId");
        operationLogPO.setOperatorName("testSetup");
        operationLogPO.setCreatedBy("testSetup");
        operationLogPO.setCreatedTime(OffsetDateTime.now());
        operationLogPO.setUpdatedBy("testSetup");
        operationLogPO.setUpdatedTime(OffsetDateTime.now());
        mapper.insert(operationLogPO);

        Map<String, String> params = new HashMap<>();
        params.put("operatorId", "testQueryMockId");

        Response response = given()
                .header(USER_CONTEXT, getUserContextString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .params(params)
                .when()
                .get("/operation-log")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract()
                .response();

        PageResponse<OperationLogResponse> operationLogPage = response.as(new TypeRef<PageResponse<OperationLogResponse>>() {});
        assertThat(operationLogPage.getRecords().size(), equalTo(1));
        OperationLogResponse operationLog = operationLogPage.getRecords().get(0);
        assertThat(operationLog.getOperatorId(), equalTo("testQueryMockId"));
        assertThat(operationLog.getOperatorIp(), equalTo("110.1.1.1"));
        assertThat(operationLog.getOperationType().getCode(), equalTo(OperationType.READ.getCode()));
        assertThat(operationLog.getOperationScene().getCode(), equalTo(OperationScene.CALCULATION.getCode()));
        assertThat(operationLog.getOperationResult().name(), equalTo(OperationResult.SUCCESS.name()));
    }

}
