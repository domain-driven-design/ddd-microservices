package lock.mysql;

import lock.mysql.repository.MysqlLockPO;
import lock.mysql.repository.MysqlLockRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.IdGenerator;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MysqlLockClient {

    private MysqlLockRepository mysqlLockRepository;

    public Optional<MysqlLockPO> findLockByKey(String lockKey) {
        return Optional.ofNullable(mysqlLockRepository.getByKey(lockKey));
    }

    public boolean tryLock(String lockKey, long waitTime) {
        Optional<MysqlLockPO> lockPOOptional = findLockByKey(lockKey);
        if (lockPOOptional.isEmpty()) {
            MysqlLockPO mysqlLock = MysqlLockPO.builder()
                                               .id(IdGenerator.random().generateId())
                                               .lockKey(lockKey)
                                               .expirationTime(OffsetDateTime.now().plusSeconds(waitTime))
                                               .build();
            mysqlLockRepository.create(mysqlLock);
            return true;
        } else {
            MysqlLockPO mysqlLock = lockPOOptional.get();
            //锁还被占用，且未过期，加锁失败
            if (mysqlLock.getExpirationTime().isAfter(OffsetDateTime.now())) {
                return false;
            } else {
                //锁被占用，但已过期，直接抢占
                mysqlLock.setExpirationTime(OffsetDateTime.now().plusSeconds(waitTime));
                mysqlLockRepository.update(mysqlLock);
                return true;
            }
        }
    }

    public void unLock(String lockKey) {
        mysqlLockRepository.delete(lockKey);
    }
}
