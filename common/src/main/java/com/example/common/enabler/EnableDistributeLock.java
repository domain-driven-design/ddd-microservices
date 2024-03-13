package com.example.common.enabler;

import com.example.common.lock.DistributeLockAspect;
import com.example.common.lock.mysql.MysqlLockClient;
import com.example.common.lock.redis.RedisLockClient;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DistributeLockAspect.class, RedisLockClient.class, MysqlLockClient.class})
public @interface EnableDistributeLock {
}
