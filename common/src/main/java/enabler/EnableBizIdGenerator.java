package enabler;

import bizidgenerator.domain.BizType;
import bizidgenerator.infrastructure.BizIdGenerator;
import bizidgenerator.infrastructure.BizIdInitializer;
import bizidgenerator.infrastructure.DbBizIdGenerator;
import bizidgenerator.infrastructure.RedisBizIdGenerator;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({BizIdInitializer.class, DbBizIdGenerator.class, RedisBizIdGenerator.class, BizType.class, BizIdGenerator.class})
public @interface EnableBizIdGenerator {}
