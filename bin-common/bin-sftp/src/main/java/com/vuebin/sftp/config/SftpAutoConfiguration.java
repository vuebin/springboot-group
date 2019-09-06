package com.vuebin.sftp.config;

import com.vuebin.sftp.properties.SftpProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * sftp自动配置类
 *
 * @author fengjiabin
 * @date 2019/7/16 17:35
 */
@Configuration
public class SftpAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "sftp")
    public SftpProperties paramValidateAop() {
        return new SftpProperties();
    }

}
