package com.example.demo.api;

import com.example.calculation.application.args.CalculationCommand;
import com.example.calculation.application.dto.CalculationNodeResult;
import com.example.demo.domain.aggregate.calculation.DemoBizType;
import com.example.demo.domain.aggregate.calculation.DemoCalculationData;
import com.example.demo.domain.aggregate.calculation.DemoCalculationMode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.example.common.utils.JacksonUtil;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class CalculationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_calculate_success() throws Exception {
        // 数据准备
        DemoCalculationData data = new DemoCalculationData();
        data.setBasicRate(BigDecimal.valueOf(0.50));
        data.setOriginalAmount(BigDecimal.valueOf(1000));
        data.setBasicRefactor(BigDecimal.valueOf(0.7));
        CalculationCommand<DemoCalculationData> command = new CalculationCommand<>();
        command.setBizType(DemoBizType.DEPOSIT);
        command.setMode(DemoCalculationMode.DEPOSIT);
        command.setBizId("mockBizId");
        command.setTransactionId("mockTransId");
        command.setData(data);

        // 执行
        MvcResult mvcResult = mockMvc.perform(post("/calculation/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JacksonUtil.toJson(command)))
                .andExpect(status().isOk())
                .andReturn();

        // 校验
        List<CalculationNodeResult> nodeResults = JacksonUtil.fromJsonToList(
                mvcResult.getResponse().getContentAsString(),
                CalculationNodeResult.class);
        assertEquals(10, nodeResults.size());
        CalculationNodeResult node0 = nodeResults.get(0);
        assertEquals("benchmarkInterestRate", node0.getName());
        assertEquals("basicRefactor*listingPrice", node0.getExpression());
        assertEquals(BigDecimal.valueOf(0.25), node0.getValue());
//        CalculationNodeResult node1 = nodeResults.get(1);
//        assertEquals("periodicCost", node1.getName());
//        assertNull(node1.getExpression());
//        assertEquals(BigDecimal.valueOf(900.0), node1.getValue());
//        CalculationNodeResult node2 = nodeResults.get(2);
//        assertEquals("operatingCost", node2.getName());
//        assertNull(node2.getExpression());
//        assertEquals(BigDecimal.valueOf(9.0), node2.getValue());
    }

}
