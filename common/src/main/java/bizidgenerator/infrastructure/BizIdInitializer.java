package bizidgenerator.infrastructure;

import bizidgenerator.config.BizIdGeneratorBeanCondition;
import bizidgenerator.config.BizIdGeneratorConfig;
import bizidgenerator.domain.BizIdVariable;
import bizidgenerator.repository.BizIdGeneratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Conditional(BizIdGeneratorBeanCondition.class)
@EnableConfigurationProperties(BizIdGeneratorConfig.class)
public class BizIdInitializer {

    private final BizIdGeneratorConfig bizIdGeneratorConfig;
    private final BizIdGeneratorRepository bizIdGeneratorRepository;

    @Bean
    public CommandLineRunner init() {
        return args -> initBizIdGenerators();
    }

    private void initBizIdGenerators() {
        List<BizIdVariable> bizIdVariables = bizIdGeneratorConfig.getBizIdVariables();
        bizIdVariables.forEach(bizIdGeneratorRepository::upsert);
    }

}
