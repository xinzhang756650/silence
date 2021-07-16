package com.silence.tencent.config;

import com.tencentcloudapi.common.Credential;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
@EnableConfigurationProperties(TencentProperties.class)
public class  TencentConfiguration{

    private final TencentProperties tencentProperties;

    @Bean
    public Credential getInstances(){
        Credential credential=new Credential(tencentProperties.getSecretId(),tencentProperties.getSecretKey(),tencentProperties.getToken());
        return credential;
    }

    @Bean
    public String getRealmName(){
        return tencentProperties.getRealmName();
    }
}
