package com.example.common.bizidgenerator.config;

import com.example.common.bizidgenerator.domain.BizIdVariable;
import com.example.common.bizidgenerator.domain.BizType;
import com.example.common.error.BusinessException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import static com.example.common.error.CommonError.NO_MATCHED_BUSINESS_TYPE;

@Setter
@Getter
@ConfigurationProperties(prefix = "biz-id-generator-config")
public class BizIdGeneratorConfig {

    public static final String PROPERTY_NAME = "biz-id-generator-config.biz-id-variables";

    private List<BizIdVariable> bizIdVariables;

    public BizIdVariable getBizIdVariable(BizType bizType) {
        return bizIdVariables.stream()
                .filter(v -> v.getBizType() == bizType)
                .findFirst()
                .orElseThrow(() -> new BusinessException(NO_MATCHED_BUSINESS_TYPE, bizType.getCode()));
    }

}
