package businessruletest;

import com.example.common.businessrule.BusinessRuleConfig;
import com.example.common.businessrule.BusinessRuleProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusinessRuleProviderTest {

    @InjectMocks
    private BusinessRuleProvider businessRuleProvider;
    @Mock
    private BusinessRuleConfig businessRuleConfig;

    @BeforeEach
    void setup() {
        businessRuleProvider.initialize();
    }

    @Test
    void should_get_business_rule_success() {
        // Config cache
        when(businessRuleConfig.isEnableCache()).thenReturn(true);

        MockRuleResponse response = businessRuleProvider.getBusinessRule(
                "biz_mock", new TypeReference<>(){});

        assertNotNull(response);
        assertEquals("test", response.getType());
        assertEquals("this is a test", response.getDescription());
    }

    @Disabled("待异常处理后打开")
    @Test
    void should_throw_error_if_name_not_found() {
        // 执行测试的业务逻辑
        assertThrows(IllegalArgumentException.class,
                () -> businessRuleProvider.getBusinessRule("non_exist_name", new TypeReference<>(){})
        );
    }

    @Disabled("待异常处理后打开")
    @Test
    void should_throw_error_if_type_not_match() {
        // 执行测试的业务逻辑
        assertThrows(IllegalArgumentException.class,
                () -> businessRuleProvider.getBusinessRule("biz_mock", new TypeReference<List<MockRuleResponse>>(){})
        );
    }
}
