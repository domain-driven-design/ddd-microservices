package lock.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class RedisLockAspect {
    private final RedissonClient redissonClient;

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

        RLock redissonClientLock = redissonClient.getLock(lockKey);

        boolean tryLock = redissonClientLock.tryLock(waitTime, leaseTime, timeUnit);
        if (!tryLock) {
            throw new Exception("The lock is occupied, please submit later!");
        }
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            log.error("Method execution failed：{}", throwable.getMessage());
            throw throwable;
        } finally {
            redissonClientLock.unlock();
        }
    }

}

