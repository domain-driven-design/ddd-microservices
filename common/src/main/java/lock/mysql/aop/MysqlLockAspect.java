package lock.mysql.aop;

import lock.mysql.MysqlLockClient;
import lock.redis.RedisLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


import java.util.concurrent.TimeUnit;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class MysqlLockAspect {

    private MysqlLockClient mysqlLockClient;

    @Pointcut("@annotation(lock)")
    public void pointcut(RedisLock lock) {
    }

    @Around("pointcut(lock)")
    public Object aroundAdvice(ProceedingJoinPoint point, RedisLock lock) throws Throwable {

        String lockKey = lock.value();
        Assert.hasText(lockKey, "@RedisLock key can not empty！");

        log.info("lockKey :{}", lockKey);
        long waitTime = lock.waitTime();
        long leaseTime = lock.leaseTime();
        TimeUnit timeUnit = lock.timeUnit();
        boolean tryLock = mysqlLockClient.tryLock(lockKey, waitTime);
        if (!tryLock) {
            throw new Exception("The lock is occupied, please submit later!");
        }
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            log.error("Method execution failed：{}", throwable.getMessage());
            throw throwable;
        } finally {
            mysqlLockClient.unLock(lockKey);
        }
    }

}
