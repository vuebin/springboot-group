package com.vuebin.common.core.config;

import com.vuebin.common.core.properties.JwtProperties;
import com.vuebin.common.core.util.JwtTokenUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * jwt自动配置
 *
 * @author fengjiabin
 * @date 2019/6/11 21:57
 */
@Configuration
public class JwtAutoConfiguration {

    /**
     * jwt token的配置
     */
    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public JwtProperties jwtProperties() {
        return new JwtProperties();
    }

    /**
     * jwt工具类
     */
    @Bean
    public JwtTokenUtil jwtTokenUtil(JwtProperties jwtProperties) {
        return new JwtTokenUtil(jwtProperties.getSecret(), jwtProperties.getExpiration());
    }

}
