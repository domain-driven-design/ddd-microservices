package cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonRedisTemplate {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 尝试自增，如果key不存在，创建这个key并初始化为initValue，然后进行自增操作
     */
    public Long increaseOrDefault(String key, long initValue) {
        return redisTemplate.opsForValue().increment(key, initValue);
    }

    /**
     * 清除key的缓存
     */
    public Boolean clear(String key) {
        return redisTemplate.delete(key);
    }

}
