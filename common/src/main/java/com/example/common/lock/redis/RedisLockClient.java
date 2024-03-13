package com.example.common.lock.redis;

import com.example.common.lock.DistributeLockClient;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisLockClient implements DistributeLockClient {

    private final RedissonClient redissonClient;

    @Override
    public boolean tryLock(String key, long waitTime) throws InterruptedException {
        RLock redissonClientLock = redissonClient.getLock(key);
        return redissonClientLock.tryLock(waitTime, TimeUnit.SECONDS);
    }

    @Override
    public void unLock(String key) {
        RLock redissonClientLock = redissonClient.getLock(key);
        redissonClientLock.unlock();
    }
}
