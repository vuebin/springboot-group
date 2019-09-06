package com.vuebin.sftp.properties;

import lombok.Data;

/**
 * sftp配置文件类
 *
 * @author fengjiabin
 * @date 2019/7/16 17:33
 */
@Data
public class SftpProperties {

    public static final String PREFIX = "sftp";

    /**
     * 缩略图ip
     */
    private String host;

    /**
     * 缩略图端口
     */
    private Integer port;

    /**
     * 缩略图服务器用户名
     */
    private String username;

    /**
     * 缩略图服务器密码
     */
    private String password;

    /**
     * 缩略图前缀
     */
    private String prefix;

    /**
     * 服务器目录
     */
    private String serverDir;

    /**
     * 文件路径
     */
    private String filePath;

}
