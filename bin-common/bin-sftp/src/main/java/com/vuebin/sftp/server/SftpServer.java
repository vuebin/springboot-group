package com.vuebin.sftp.server;

import com.vuebin.sftp.properties.SftpProperties;
import com.vuebin.sftp.util.SftpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件上传
 *
 * @author fengjiabin
 * @date 2019/7/16 17:45
 */
@Slf4j
@Service
public class SftpServer {

    @Resource
    private SftpProperties sftpProperties;

    /**
     * 上传文件
     *
     * @param file
     * @return 文件的全路径地址
     */
    public String uploadFile(MultipartFile file) {
        Long start = System.currentTimeMillis();
        String allPath = null;
        try {
            allPath = SftpUtil.uploadFileByStream(sftpProperties.getFilePath(), sftpProperties.getPort(), sftpProperties.getUsername(),
                    sftpProperties.getPassword(), file.getInputStream(), System.currentTimeMillis() + ".png", sftpProperties.getServerDir() + sftpProperties.getFilePath(), sftpProperties.getPrefix() + sftpProperties.getFilePath());
            log.info("bin -> 上传文件成功, 全路径地址为: " + allPath + ", use time " + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allPath;
    }

}
