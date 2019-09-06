package com.vuebin.sftp.util;

import com.jcraft.jsch.*;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

/**
 * SFtp上传文件工具类 (基于 ssh 协议), 含功能: 上传、下载、删除)
 *
 * @author fengjiabin
 * @date 2018-11-13
 */
@Slf4j
public class SftpUtil {

    /**
     * 获取 sftp channel 连接
     *
     * @param host
     * @param port
     * @param username
     * @param password
     * @return
     * @throws Exception
     * @author 冯佳斌
     */
    public static ChannelSftp getSftpConnect(final String host, final int port, final String username, final String password) throws Exception {

        JSch jsch = new JSch();
        Session sshSession = jsch.getSession(username, host, port);
        sshSession.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();

        return (ChannelSftp) channel;
    }

    /**
     * 从服务器上下载一个文件
     *
     * @param host           host
     * @param port           port
     * @param username       username
     * @param password       password
     * @param allPath        allPath
     * @param local_save_dir local_save_dir
     * @return
     * @author 冯佳斌
     */
    public static boolean download(String host, Integer port, String username, String password, String allPath, String local_save_dir) {

//          获取文件名
        String fileName = allPath.substring(allPath.lastIndexOf("/"));

        File file = new File(local_save_dir + fileName);

        ChannelSftp channelSftp = null;
        try (FileOutputStream fos = new FileOutputStream(file)) {
            channelSftp = getSftpConnect(host, port, username, password);

//          serverPath
            String serverPath = allPath.substring(0, allPath.lastIndexOf("/"));
            log.info("bin -> download file: path " + serverPath + fileName + " --> " + local_save_dir + fileName);

            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                file.createNewFile();
            }
            channelSftp.cd(serverPath);

            channelSftp.get(serverPath + fileName, fos);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            close(channelSftp);
        }

    }

    /**
     * 从服务器上删除一个文件
     *
     * @param host
     * @param port
     * @param username
     * @param password
     * @param all_path 全路径
     *                 http://60.223.235.71:8181/ueditor/image/2019/01/10/37/article_1547113057958.jpg
     *                 /data1/sftp/ueditor/image/2019/01/10/12/2.jpg
     * @return
     * @author 冯佳斌
     */
    @Synchronized
    public static boolean rmFile(String host, Integer port, String username, String password, String all_path) {

        ChannelSftp channelSftp = null;
        try {
            channelSftp = getSftpConnect(host, port, username, password);

//          获取全路径的文件所在目录
            String dir = all_path.substring(0, all_path.lastIndexOf("/"));

//          进入到文件目录
            channelSftp.cd(dir);
//          删除指定的文件
            channelSftp.rm(all_path);
            return true;

        } catch (Exception e) {
            return false;
        } finally {
            close(channelSftp);
        }

    }

    /**
     * 写文件（覆盖）
     *
     * @param host             ip
     * @param port             端口
     * @param username         用户名
     * @param password         密码
     * @param fis              文件流
     * @param fileName         文件名
     * @param server_directory 服务器目录
     * @param PREFIX           前缀
     * @return
     * @throws Exception
     * @author 冯佳斌
     */
    @Synchronized
    public static String uploadFileByStream(String host, Integer port, String username, String password, InputStream fis, String fileName, String server_directory, String PREFIX) throws Exception {

//        获取 sftpchannel 连接
        ChannelSftp channelSftp = getSftpConnect(host, port, username, password);

        try {
            String[] arr = fileName.split("/");

//            文件存储路径
            String path = server_directory + getTodayDate() + arr[0] + "/";

//            如果路径不存在, 则递归创建文件夹
            if (!dirExist(path, channelSftp)) {
                mkdirs(path, channelSftp);
            }
            channelSftp.cd(path);

//            upload
            channelSftp.put(fis, arr[1], 1);
            String allPath = PREFIX + getTodayDate() + fileName;
//              返回全路径地址
            return allPath;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(channelSftp);
        }
        return null;

    }

    /**
     * 写文件（追加）
     *
     * @param host
     * @param port
     * @param username
     * @param password
     * @param fis
     * @param fileName
     * @param server_directory
     * @param PREFIX
     * @return
     * @throws Exception
     * @author 冯佳斌
     */
    @Synchronized
    public static String uploadFileByStreamAndAppend(String host, Integer port, String username, String password, InputStream fis, String fileName, String server_directory, String PREFIX) throws Exception {

//        获取 sftpchannel 连接
        ChannelSftp channelSftp = getSftpConnect(host, port, username, password);

        try {
//            如果路径不存在, 则递归创建文件夹
            if (!dirExist(server_directory, channelSftp)) {
                mkdirs(server_directory, channelSftp);
            }
            channelSftp.cd(server_directory);

//            upload
            channelSftp.put(fis, fileName, 2);
            String allPath = PREFIX + fileName;
//              返回全路径地址
            log.info("============安泽积分util: allPath: " + allPath);
            return allPath;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(channelSftp);
        }
        return null;
    }

    /**
     * 从服务器上读取文件内容, 一行算一条记录
     *
     * @param host             host
     * @param port             port
     * @param username         username
     * @param password         password
     * @param fileName         fileName
     * @param server_directory server_directory
     * @return
     * @throws Exception
     * @author 冯佳斌
     */
    @Synchronized
    public static List<String> readFileContent(String host, Integer port, String username, String password, String fileName, String server_directory) {

//        获取 sftpchannel 连接
        ChannelSftp channelSftp = null;
//        将所有读取出来的数据存到一个list集合内
        ArrayList<String> list = new ArrayList<>();

        try {
            channelSftp = getSftpConnect(host, port, username, password);

//        构建文件输入流，读取文件内容，因为最终是要打成jar包，所以文件名从args上取
            BufferedReader reader = new BufferedReader(new InputStreamReader(channelSftp.get(server_directory + fileName), "UTF-8"));

            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(channelSftp);
        }
        return list;
    }

    public static boolean WriteFileOfUserDetail1(String host, int port, String username, String password, String basePath,
                                                 String filePath, String filename, InputStream is) throws Exception {
        ChannelSftp channelSftp = getSftpConnect(host, port, username, password);

        try {

            //String[] arr = filename.split("/");

//            文件存储路径
            String path = basePath + "/shenwei/behavior/" + filePath + "/";

//            如果路径不存在, 则递归创建文件夹
            if (!dirExist(path, channelSftp)) {
                mkdirs(path, channelSftp);
            }
            channelSftp.cd(path);

//              fileName 传进来，以保证缩略图和原图名字是一致的，从而通过改变url前缀来切换图片

//            upload
            channelSftp.put(is, filename, 2);

//              返回全路径地址


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(channelSftp);
        }
        return true;
    }

    public static void uploadPswLog(String host, Integer port, String username, String password, InputStream fis, Integer deptid, String pswlog) throws Exception {

//        获取 sftpchannel 连接
        ChannelSftp channelSftp = getSftpConnect(host, port, username, password);

        try {

            Calendar calendar = Calendar.getInstance();
            Integer year = calendar.get(Calendar.YEAR);
            Integer month = calendar.get(Calendar.MONTH);
            Integer day = calendar.get(Calendar.DATE);
            month = month + 1;

            //String[] arr = fileName.split("/");

            String fileName = "reset_passwd_" + deptid.toString() + ".txt";
//            文件存储路径
            String path = pswlog + year + "/" + month + "/" + day + "/";

//            如果路径不存在, 则递归创建文件夹
            if (!dirExist(path, channelSftp)) {
                mkdirs(path, channelSftp);
            }
            channelSftp.cd(path);

//            upload
            channelSftp.put(fis, fileName, 2);
//              返回全路径地址

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(channelSftp);
        }
    }

    /**
     * 递归创建文件夹
     *
     * @param dir
     * @param channelSftp
     * @return
     * @throws SftpException
     * @author 冯佳斌
     */
    private static boolean mkdirs(String dir, ChannelSftp channelSftp) throws SftpException {
        String dirs = dir.substring(1, dir.length() - 1);
        String[] dirArr = dirs.split("/");
        String path = "/";
        for (String d : dirArr) {
            path = path + d + "/";
            if (dirExist(path, channelSftp)) {
                continue;
            } else {
                channelSftp.mkdir(path);
                channelSftp.cd(path);
            }
        }
        return true;
    }

    /**
     * 判断文件夹是否存在
     *
     * @param dir
     * @param channelSftp
     * @return
     */
    private static boolean dirExist(String dir, ChannelSftp channelSftp) {
        try {
            Vector<?> vector = channelSftp.ls(dir);
            if (null == vector) {
                return false;
            } else {
                return true;
            }
        } catch (SftpException e) {
            return false;
        }
    }

    /**
     * 自动生成的路径
     *
     * @return
     */
    public static String getTodayDate() {
        //图片地址为{yyyy}/{MM}/{dd}/{ss}
        /**
         *  年月日 单位补零
         */
        Calendar calendar = Calendar.getInstance();
        Integer year = calendar.get(Calendar.YEAR);
        String month = calendar.get(Calendar.MONTH) + 1 < 10 ? "0" + (calendar.get(Calendar.MONTH) + 1) : (calendar.get(Calendar.MONTH) + 1) + "";
        String day = calendar.get(Calendar.DATE) < 10 ? "0" + calendar.get(Calendar.DATE) : calendar.get(Calendar.DATE) + "";
        String general_path = year + "/" + month + "/" + day + "/";
        return general_path;
    }

    /**
     * 关闭连接
     *
     * @param channelSftp
     */
    public static void close(ChannelSftp channelSftp) {
        try {
            channelSftp.getSession().disconnect();
            channelSftp.quit();
            channelSftp.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }


}
