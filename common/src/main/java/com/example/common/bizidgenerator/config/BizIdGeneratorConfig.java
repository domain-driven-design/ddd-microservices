package com.example.common.bizidgenerator.config;

import com.example.common.bizidgenerator.domain.BizIdVariable;
import com.example.common.bizidgenerator.domain.BizType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

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
                .orElseThrow(IllegalArgumentException::new); // todo exception
    }

}
