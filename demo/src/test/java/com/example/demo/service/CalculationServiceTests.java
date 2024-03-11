package com.example.demo.service;

import com.example.calculation.application.service.CalculationAppService;
import com.example.calculation.domain.aggregate.CalculationExpression;
import com.example.demo.application.calculation.service.DemoExpressionLoader;
import com.example.demo.domain.aggregate.calculation.DemoCalculationMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CalculationServiceTests {

    @Autowired
    private CalculationAppService calculationService;
    @MockBean
    private DemoExpressionLoader expressionLoader;
    @TempDir
    Path tempDir;

    @Test
    void should_generate_flow_xml_success() throws IOException {
        // prepare test data
        Path testFilePath = tempDir.resolve("testFlow.xml");
        String expectedContent =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<flow>\n" +
                "    <chain name=\"DEPOSIT\">\n" +
                "        THEN(\n" +
                "            WHEN(\n" +
                "                proposedInterestRate\n" +
                "            ),\n" +
                "            WHEN(\n" +
                "                benchmarkInterestRate\n" +
                "            )\n" +
                "        )\n" +
                "    </chain>\n" +
                "</flow>";
        // mock some methods
        when(expressionLoader.getLoadedExpression(any())).thenReturn(Map.of(
                "benchmarkInterestRate", CalculationExpression.builder().componentName("benchmarkInterestRate").variables(List.of("proposedInterestRate")).build(),
                "proposedInterestRate", CalculationExpression.builder().componentName("proposedInterestRate").variables(List.of()).build())
        );

        // run the target method
        calculationService.generateFlow(DemoCalculationMode.DEPOSIT, testFilePath.toAbsolutePath().toString());

        // compare to the result
        String generatedContent = new String(Files.readAllBytes(testFilePath));
        assertEquals(expectedContent, generatedContent);
    }

}
