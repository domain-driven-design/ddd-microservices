package com.ddd.base.adapter;

import com.ddd.base.TestBase;
import com.ddd.base.application.dto.query.UserQueryDTO;
import com.ddd.base.infra.persistence.mapper.UserMapper;
import com.ddd.base.infra.persistence.po.UserPO;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.example.common.utils.IdUtil;

import java.time.OffsetDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static com.example.common.utils.TestUtil.USER_CONTEXT;
import static com.example.common.utils.TestUtil.getUserContextString;

class UserControllerTest extends TestBase {

    @Autowired
    private UserMapper userMapper;

    @Test
    void should_query_user_page_successfully() {
        UserPO userPO = new UserPO();
        userPO.setId(IdUtil.uuid());
        userPO.setName("John Doe");
        userPO.setCurrentIdentityId("identity-123");
        userPO.setStatus("Active");
        userPO.setDeleted(false);
        userPO.setMaintainBy("admin");
        userPO.setMaintainByName("Administrator");
        userPO.setMaintainTime(OffsetDateTime.now());
        userPO.setCreatedBy("testSetup");
        userPO.setCreatedTime(OffsetDateTime.now());
        userPO.setUpdatedBy("testSetup");
        userPO.setUpdatedTime(OffsetDateTime.now());
        userMapper.insert(userPO);

        UserQueryDTO userQuery = new UserQueryDTO();
        userQuery.setId("1");


        Response response = given()
                .header(USER_CONTEXT, getUserContextString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract()
                .response();

        assertThat(response.getBody().asString(), containsString("John Doe")); // Adjust according to actual response

    }
}
