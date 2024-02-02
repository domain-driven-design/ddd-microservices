package datadictionarytest;

import datadictionary.DataDictionaryConfig;
import datadictionary.DataDictionaryResponse;
import datadictionary.application.DataDictionaryAppService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataDictionaryAppServiceTest {

    @InjectMocks
    private DataDictionaryAppService dataDictionaryAppService;
    @Mock
    private DataDictionaryConfig dataDictionaryConfig;

    @Test
    void should_query_data_dictionary_success() {
        // 配置
        when(dataDictionaryConfig.getClassPath()).thenReturn("datadictionarytest.DataDictionaryDemo");

        // 执行测试的业务逻辑
        List<DataDictionaryResponse> responses = dataDictionaryAppService.query();

        // 验证结果
        assertNotNull(responses);
        assertEquals(1, responses.size());

        DataDictionaryResponse dataDictionaryResponse = responses.get(0);
        assertEquals("StatusDemo", dataDictionaryResponse.getName());
        assertEquals("状态", dataDictionaryResponse.getDescription());
        assertEquals(2, dataDictionaryResponse.getAttributes().size());

        List<DataDictionaryResponse.DictionaryAttributeResponse> attributes = dataDictionaryResponse.getAttributes();
        assertEquals("SUBMITTED", attributes.get(0).getCode());
        assertEquals("已提交", attributes.get(0).getDescription());
        assertEquals("CALCULATED", attributes.get(1).getCode());
        assertEquals("已测算", attributes.get(1).getDescription());
    }

}
