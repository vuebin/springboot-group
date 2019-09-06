package com.vuebin.common.core.config;

import com.vuebin.common.core.util.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认的工具类
 *
 * @author fengjiabin
 * @date 2019/6/12 14:32
 */
@Configuration
public class UtilAutoConfiguration {

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

}
