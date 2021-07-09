package com.silence.weixin.config;

import com.silence.common.utils.encryption.EncryptUtil;
import com.silence.weixin.utils.JsonUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @desc: yml配置映射成实体类
 * @author: cao_wencao
 * @date: 2019-09-02 17:08
 */
@Data
@ConfigurationProperties(prefix = "wx.mp")
public class WxMpProperties {

    public static String AESkey="silence";

    private List<MpConfig> configs;

    @Data
    public static class MpConfig {
        /**
         * 设置微信公众号的appid
         * 加密
         */
        private String appId;

        /**
         * 设置微信公众号的app secret
         * 加密
         */
        private String secret;

        /**
         * 设置微信公众号的token
         * 加密
         */
        private String token;

        /**
         * 设置微信公众号的EncodingAESKey
         * 加密
         */
        private String aesKey;

    }
}
