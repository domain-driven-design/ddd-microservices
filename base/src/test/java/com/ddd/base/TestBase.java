package com.ddd.base;

import auth.UserContext;
import com.ddd.base.config.ResetDbListener;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import utils.JacksonUtil;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {BaseApplication.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        ResetDbListener.class
})
@ActiveProfiles("test")
public abstract class TestBase {

    public static final String USER_CONTEXT = "userContext";

    @LocalServerPort
    private int port;


    @BeforeEach
    public void setUp() {
        System.out.println("port:" + port);

        RestAssured.port = port;
        RestAssured.basePath = "/";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public String getUserContextString() {
        UserContext userContext = new UserContext();
        userContext.setUserId("testId");
        userContext.setUserName("testName");
        return JacksonUtil.toJson(userContext);
    }
}
