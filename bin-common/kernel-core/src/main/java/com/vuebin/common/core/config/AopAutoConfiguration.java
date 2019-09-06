package com.vuebin.common.core.config;

import com.vuebin.common.core.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 保留类，如果控制器需要些aop在这里写
 *
 * @author fengjiabin
 * @date 2019/6/12 9:27
 */
@Configuration
public class AopAutoConfiguration {

    /**
     * 默认的异常拦截器
     */
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

}
