package lock.mysql.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class MysqlLockRepository {

    private final MysqlLockMapper mysqlLockMapper;


    public MysqlLockPO getByKey(String lockKey) {
        LambdaQueryWrapper<MysqlLockPO> wrapper = Wrappers.lambdaQuery(MysqlLockPO.class)
                                                          .eq(MysqlLockPO::getLockKey, lockKey);
        return mysqlLockMapper.selectOne(wrapper);
    }

    public void create(MysqlLockPO lockPO) {
        mysqlLockMapper.insert(lockPO);
    }

    public void update(MysqlLockPO mysqlLock) {
        mysqlLockMapper.updateById(mysqlLock);
    }

    public void delete(String lockKey) {
        LambdaQueryWrapper<MysqlLockPO> wrapper = Wrappers.lambdaQuery(MysqlLockPO.class)
                                                          .eq(MysqlLockPO::getLockKey, lockKey);
        mysqlLockMapper.delete(wrapper);

    }
}
