package bizidgenerator.infrastructure;

import bizidgenerator.config.BizIdGeneratorConfig;
import bizidgenerator.domain.BizIdVariable;
import bizidgenerator.domain.BizType;
import bizidgenerator.repository.BizIdGeneratorPO;
import bizidgenerator.repository.BizIdGeneratorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import utils.TimeUtil;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Component
@RequiredArgsConstructor
public class DbBizIdGenerator implements BizIdGenerator {

    private final ConcurrentHashMap<BizType, Pair<Integer, Integer>> cachedSeqRange = new ConcurrentHashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    private final ApplicationContext applicationContext;
    private final BizIdGeneratorConfig bizIdGeneratorConfig;
    private final BizIdGeneratorRepository bizIdGeneratorRepository;

    @Override
    public String getBizId(BizType type) {
        lock.lock();
        try {
            BizIdVariable bizIdVariable = bizIdGeneratorConfig.getBizIdVariable(type);
            return BizIdGeneratorUtil.buildBizId(bizIdVariable, getSeq(bizIdVariable));
        } finally {
            lock.unlock();
        }
    }

    private String getSeq(BizIdVariable variable) {
        Pair<Integer, Integer> seqRange = cachedSeqRange.get(variable.getBizType());
        if (isRangeEmpty(seqRange)) {
            seqRange = applicationContext.getBean(getClass()).getNewSeqRange(variable);
        }
        cachedSeqRange.put(variable.getBizType(), Pair.of(seqRange.getLeft() + 1, seqRange.getRight()));

        return seqRange.getLeft().toString();
    }

    private boolean isRangeEmpty(Pair<Integer, Integer> seqRange) {
        return Objects.isNull(seqRange) || Objects.equals(seqRange.getLeft(), seqRange.getRight());
    }

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public Pair<Integer, Integer> getNewSeqRange(BizIdVariable variable) {
        BizIdGeneratorPO bizIdGeneratorPO = bizIdGeneratorRepository.getById(variable.getBizType());
        Integer seq = bizIdGeneratorPO.getSeq();
        OffsetDateTime expirationTime = bizIdGeneratorPO.getExpirationTime();

        if (TimeUtil.expired(expirationTime)) {
            expirationTime = TimeUtil.addDays(variable.getExpireDays()).truncatedTo(ChronoUnit.DAYS);
            seq = BizIdGenerator.INITIAL_SEQ.intValue();
        }

        // 更新序列范围
        int nextSeq = seq + variable.getStep();
        // 如果更新失败，再次获取新序列
        if (0 == bizIdGeneratorRepository.updateSeq(bizIdGeneratorPO, nextSeq, expirationTime)) {
            return getNewSeqRange(variable);
        }
        return Pair.of(seq, nextSeq);
    }

}
