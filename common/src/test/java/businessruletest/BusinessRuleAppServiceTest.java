package businessruletest;

import businessrule.BusinessRuleConfig;
import businessrule.BusinessRuleProvider;
import businessrule.BusinessRuleResponse;
import businessrule.application.BusinessRuleAppService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusinessRuleAppServiceTest {

    @InjectMocks
    private BusinessRuleAppService businessRuleAppService;
    @Mock
    private BusinessRuleProvider businessRuleProvider;
    @Mock
    private BusinessRuleConfig businessRuleConfig;

    @Test
    void should_query_business_rules_success() {
        // 配置
        when(businessRuleConfig.getClassPath()).thenReturn("businessruletest.BusinessRuleDemo");

        // 执行测试的业务逻辑
        List<BusinessRuleResponse> responses = businessRuleAppService.query();

        // 验证结果
        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals("demo1", responses.get(0).getRuleName());
        assertEquals("desc1", responses.get(0).getDescription());
        assertEquals("demo2", responses.get(1).getRuleName());
        assertEquals("desc2", responses.get(1).getDescription());
    }

    @Test
    void should_get_business_rule_success() {
        // 配置
        when(businessRuleProvider.getBusinessRule(any(), any())).thenReturn("test rule");

        // 执行测试的业务逻辑
        Object response = businessRuleAppService.getRule("demo1");

        // 验证结果
        assertEquals("test rule", response.toString());
    }

}
