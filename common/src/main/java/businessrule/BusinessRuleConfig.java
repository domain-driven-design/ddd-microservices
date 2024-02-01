package businessrule;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "business-rule")
public class BusinessRuleConfig {

    /**
     * BusinessRule路径
     */
    private String classPath;

    /**
     * 是否缓存business rules
     */
    private boolean enableCache = true;
}
