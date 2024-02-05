package bizidgenerator.repository;

import bizidgenerator.domain.BizIdVariable;
import bizidgenerator.domain.BizType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import utils.TimeUtil;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BizIdGeneratorRepository {

    private final BizIdGeneratorMapper bizIdGeneratorMapper;

    public List<String> getAllBizTypes() {
        LambdaQueryWrapper<BizIdGeneratorPO> queryWrapper = Wrappers.lambdaQuery(BizIdGeneratorPO.class)
                .select(BizIdGeneratorPO::getBizType);
        return bizIdGeneratorMapper.selectList(queryWrapper).stream()
                .map(b -> b.getBizType().getCode())
                .collect(Collectors.toList());
    }

    public BizIdGeneratorPO getById(BizType bizType) {
        LambdaQueryWrapper<BizIdGeneratorPO> wrapper = Wrappers.lambdaQuery(BizIdGeneratorPO.class)
                .eq(BizIdGeneratorPO::getBizType, bizType)
                .last(" for update");
        return bizIdGeneratorMapper.selectOne(wrapper);
    }

    public void create(BizIdVariable variable) {
        BizIdGeneratorPO bizIdGeneratorPO = new BizIdGeneratorPO();
        bizIdGeneratorPO.setBizType(variable.getBizType());
        bizIdGeneratorPO.setSeq(1);
        if (variable.hasExpireDays()) {
            bizIdGeneratorPO.setExpireDays(variable.getExpireDays());
            bizIdGeneratorPO.setExpirationTime(TimeUtil.addDays(variable.getExpireDays()).truncatedTo(ChronoUnit.DAYS));
        }
        bizIdGeneratorMapper.insert(bizIdGeneratorPO);
    }

    public void update(BizIdGeneratorPO originalPO, BizIdVariable variable) {
        if (variable.hasExpireDays()) {
            if (!Objects.equals(originalPO.getExpireDays(), variable.getExpireDays())) {
                originalPO.setExpireDays(variable.getExpireDays());
                originalPO.setExpirationTime(TimeUtil.addDays(variable.getExpireDays()).truncatedTo(ChronoUnit.DAYS));
            }
        } else {
            originalPO.setExpireDays(null);
            originalPO.setExpirationTime(null);
        }
        bizIdGeneratorMapper.updateById(originalPO);
    }

    public void upsert(BizIdVariable bizIdVariable) {
        BizIdGeneratorPO bizIdGeneratorPO = bizIdGeneratorMapper.selectById(bizIdVariable.getBizType().getCode());
        if (Objects.isNull(bizIdGeneratorPO)) {
            create(bizIdVariable);
        } else {
            update(bizIdGeneratorPO, bizIdVariable);
        }
    }

    public int updateSeq(BizIdGeneratorPO bizIdGeneratorPO, int nextSeq, OffsetDateTime expirationTime) {
        bizIdGeneratorPO.setSeq(nextSeq);
        bizIdGeneratorPO.setExpirationTime(expirationTime);
        return bizIdGeneratorMapper.updateById(bizIdGeneratorPO);
    }

}
