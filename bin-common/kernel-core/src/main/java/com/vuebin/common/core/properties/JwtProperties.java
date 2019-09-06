package com.vuebin.common.core.properties;

/**
 * jwt相关配置
 *
 * @author fengjiabin
 * @date 2019/6/11 21:59
 */
public class JwtProperties {

    /**
     * jwt的秘钥
     */
    private String secret = "fengjiabin";

    /**
     * jwt过期时间(单位:秒)(默认:1天)
     */
    private Long expiration = 82800L;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

}
