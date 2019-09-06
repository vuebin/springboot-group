package com.vuebin.sftp;

import com.vuebin.sftp.server.SftpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 启动类
 *
 * @author fengjiabin
 * @date 2019/7/16 17:49
 */
@Slf4j
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class SftpApp {

    @Resource
    private SftpServer sftpServer;

    public static void main(String[] args) {
        SpringApplication.run(SftpApp.class, args);
        log.info("bin -> SFTP服务已启动...");
    }

    @PostMapping("/upload")
    public Object uploadTest(@RequestParam MultipartFile file) {
        return sftpServer.uploadFile(file);
    }

}
