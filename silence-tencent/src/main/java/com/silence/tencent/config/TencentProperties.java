package com.silence.tencent.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tencent.config")
public class TencentProperties {

    private String secretId;

    private String secretKey;

    private String token;

    private String realmName;

}
