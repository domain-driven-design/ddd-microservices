package com.example.common.bizidgenerator.infrastructure;

import com.example.common.bizidgenerator.config.BizIdGeneratorConfig;
import com.example.common.bizidgenerator.domain.BizIdVariable;
import com.example.common.bizidgenerator.domain.BizType;
import com.example.common.cache.CommonRedisTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@RequiredArgsConstructor
@EnableConfigurationProperties(BizIdGeneratorConfig.class)
public class RedisBizIdGenerator implements BizIdGenerator {

    private final BizIdGeneratorConfig bizIdGeneratorConfig;
    private final CommonRedisTemplate redisTemplate;

    @Override
    public String getBizId(BizType type) {
        BizIdVariable bizIdVariable = bizIdGeneratorConfig.getBizIdVariable(type);
        String seq = redisTemplate.increaseOrDefault(bizIdVariable.getBizType().getCode(), INITIAL_SEQ).toString();
        return BizIdGeneratorUtil.buildBizId(bizIdVariable, seq);
    }

    @Override
    public void clearCache(BizType type) {
        redisTemplate.clear(type.getCode());
    }

}
