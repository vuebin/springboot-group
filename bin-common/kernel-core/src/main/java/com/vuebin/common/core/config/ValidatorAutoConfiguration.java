package com.vuebin.common.core.config;

import com.vuebin.common.core.aop.ParamValidateAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 参数验证自动配置
 *
 * @author fengjiabin
 * @date 2019/6/11 22:10
 */
@Configuration
public class ValidatorAutoConfiguration {

    @Bean
    public ParamValidateAop paramValidateAop() {
        return new ParamValidateAop();
    }

}
