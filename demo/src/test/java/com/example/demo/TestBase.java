package com.example.demo;

import com.example.demo.config.ResetDbListener;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {DemoApplication.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        ResetDbListener.class
})
@ActiveProfiles("test")
public abstract class TestBase {



    @LocalServerPort
    private int port;


    @BeforeEach
    public void setUp() {
        System.out.println("port:" + port);

        RestAssured.port = port;
        RestAssured.basePath = "/";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
