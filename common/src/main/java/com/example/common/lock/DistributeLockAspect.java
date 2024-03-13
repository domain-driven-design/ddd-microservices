package com.example.common.lock;

import com.example.common.error.SystemException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

import static com.example.common.error.CommonError.LOCK_FAILED;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class DistributeLockAspect {
    private final DistributeLockClient lockClient;

    @Pointcut("@annotation(lock)")
    public void pointcut(DistributeLock lock) {
    }

    @Around("pointcut(lock)")
    public Object aroundAdvice(ProceedingJoinPoint point, DistributeLock lock) throws Throwable {

        String lockKey = lock.value();
        Assert.hasText(lockKey, "@RedisLock key can not empty！");

        log.info("lockKey :{}", lockKey);
        long waitTime = lock.waitTime();
        long leaseTime = lock.leaseTime();
        TimeUnit timeUnit = lock.timeUnit();


        boolean tryLock = lockClient.tryLock(lockKey, waitTime);
        if (!tryLock) {
            throw new SystemException(LOCK_FAILED);
        }
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            log.error("Method execution failed：{}", throwable.getMessage());
            throw throwable;
        } finally {
            lockClient.unLock(lockKey);
        }
    }

}

