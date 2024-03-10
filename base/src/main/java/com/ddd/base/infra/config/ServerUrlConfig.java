package com.ddd.base.infra.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "scheduler")
public class ServerUrlConfig {

    private Map<String, String> serverUrls;

    public String getUrl(String serverName) {
        return serverUrls.get(serverName);
    }

}
