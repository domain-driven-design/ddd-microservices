package businessrule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(BusinessRuleConfig.class)
public class BusinessRuleProvider {

    public static final String BUSINESS_RULES_FILE_PATH = "classpath:/business-rules/**/*.yml";
    private static final Map<String, Object> RULE_MAP = new HashMap<>();
    private static final Cache<String, Object> CACHE = CacheBuilder.newBuilder().build();

    private final BusinessRuleConfig businessRuleConfig;
    private final YAMLMapper yamlMapper = new YAMLMapper();

    @PostConstruct
    public void initialize() {
        loadBusinessRules();
    }

    private void loadBusinessRules() {
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources(BUSINESS_RULES_FILE_PATH);

            for (Resource resource : resources) {
                String ruleName = Objects.requireNonNull(resource.getFilename()).replace(".yml", "");
                RULE_MAP.put(ruleName, yamlMapper.readValue(resource.getInputStream(), Object.class));
            }
        } catch (IOException e) {
            log.error("Failed to load business rules", e);
            throw new IllegalArgumentException(); //todo replace by proper exception
        }
    }

    public <T> T getBusinessRule(String ruleName, TypeReference<T> valueTypeRef) {
        if (!RULE_MAP.containsKey(ruleName)) {
            log.error("No rule with name {} found", ruleName);
            throw new IllegalArgumentException(); //todo replace by proper exception
        }
        try {
            if (businessRuleConfig.isEnableCache()) {
                return (T) CACHE.get(ruleName, () -> readRule(ruleName, valueTypeRef));
            }
            return readRule(ruleName, valueTypeRef);
        } catch (ExecutionException e) {
            log.error("Fail to read rule with ruleName {}", ruleName, e);
            throw new IllegalArgumentException(); //todo replace by proper exception
        }
    }

    private <T> T readRule(String ruleName, TypeReference<T> valueTypeRef) {
        try {
            return yamlMapper.readValue(yamlMapper.writeValueAsString(RULE_MAP.get(ruleName)), valueTypeRef);
        } catch (JsonProcessingException e) {
            log.error("Fail to process json with name {}", ruleName, e);
            throw new IllegalArgumentException(); //todo replace by proper exception
        }
    }

}
