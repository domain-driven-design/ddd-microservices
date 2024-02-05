package bizidgeneratortest.businessruletest;

import bizidgenerator.config.BizIdGeneratorConfig;
import bizidgenerator.domain.BizIdVariable;
import bizidgenerator.infrastructure.BizIdGeneratorUtil;
import bizidgenerator.infrastructure.DbBizIdGenerator;
import bizidgenerator.repository.BizIdGeneratorPO;
import bizidgenerator.repository.BizIdGeneratorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbBizIdGeneratorTest {

    @InjectMocks
    private DbBizIdGenerator dbBizIdGenerator;
    @Mock
    private BizIdGeneratorRepository bizIdGeneratorRepository;
    @Mock
    private BizIdGeneratorConfig bizIdGeneratorConfig;
    @Mock
    private ApplicationContext applicationContext;

    @Test
    void should_get_biz_id_success() {
        // 配置
        BizIdVariable bizIdVariable = buildBizIdVariable();
        BizIdGeneratorPO bizIdGeneratorPO = buildBizIdGeneratorPO();
        when(bizIdGeneratorConfig.getBizIdVariable(BizTypeDemo.DEMO1)).thenReturn(bizIdVariable);
        when(bizIdGeneratorRepository.getById(BizTypeDemo.DEMO1)).thenReturn(bizIdGeneratorPO);
        when(bizIdGeneratorRepository.updateSeq(eq(bizIdGeneratorPO), eq(11), any())).thenReturn(1);
        when(applicationContext.getBean(DbBizIdGenerator.class)).thenReturn(dbBizIdGenerator);

        // 执行测试的业务逻辑
        String actual = dbBizIdGenerator.getBizId(BizTypeDemo.DEMO1);

        // 验证结果
        String result = bizIdVariable.getPrefix() +
                BizIdGeneratorUtil.formatDate(
                        bizIdVariable.getDayRule(), OffsetDateTime.now().truncatedTo(ChronoUnit.DAYS)) +
                "001";
        assertNotNull(actual);
        assertEquals(result, actual);
    }

    private BizIdGeneratorPO buildBizIdGeneratorPO() {
        BizIdGeneratorPO po = new BizIdGeneratorPO();
        po.setBizType(BizTypeDemo.DEMO1);
        po.setSeq(10);
        po.setExpirationTime(OffsetDateTime.now().minusDays(1));
        po.setExpireDays(1);
        return po;
    }

    private BizIdVariable buildBizIdVariable() {
        return BizIdVariable.builder()
                .bizType(BizTypeDemo.DEMO1)
                .prefix("CLS")
                .dayRule("yyyyMMdd")
                .length(3)
                .step(10)
                .expireDays(1)
                .build();
    }

}
