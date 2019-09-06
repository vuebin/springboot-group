package com.vuebin.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 启动类
 *
 * @author fengjiabin
 * @date 2019/6/10 21:07
 */
@Slf4j
@EntityScan(basePackages = {"com.vuebin.common.model"})
@SpringBootApplication
public class BinJpaApp {

    public static void main(String[] args) {
        SpringApplication.run(BinJpaApp.class, args);
        log.info("bin ->【SpringBoot工程组】集成Jpa已启动...");
    }

}
