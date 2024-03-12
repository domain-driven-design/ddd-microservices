package enabler;

import datadictionary.application.DataDictionaryAppService;
import datadictionary.controller.DataDictionaryController;
import lock.mysql.aop.MysqlLockAspect;
import lock.redis.RedisLockAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RedisLockAspect.class, MysqlLockAspect.class})
public @interface EnableDistributeLock {
}
