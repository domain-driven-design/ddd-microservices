package bizidgenerator.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class BizIdGeneratorBeanCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        String bizIdProperty = BizIdGeneratorConfig.PROPERTY_NAME;
        return env.containsProperty(bizIdProperty) && !StringUtils.isEmpty(env.getProperty(bizIdProperty));
    }

}
