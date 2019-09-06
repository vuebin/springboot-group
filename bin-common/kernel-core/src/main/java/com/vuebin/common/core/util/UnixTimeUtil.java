package com.vuebin.common.core.util;

import org.springframework.util.Base64Utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Unix时间戳工具类
 *
 * @author fengjiabin
 * @date 2019/6/12 16:34
 */
public class UnixTimeUtil {

    public static void main(String[] args) {

        String str = "123456";
        byte[] arr = Base64Utils.decodeFromString(str);
        String base = Base64Utils.encodeToUrlSafeString(arr);
        System.out.println("arr: " + base);


//        Long unixTime = getCurrentUnixTimestamp();
//        System.out.println("UnixTime: " + unixTime);
//        Long timestamp = toTimestamp(unixTime);
//        System.out.println("Timestamp: " + timestamp);
//        Date date = new Date(timestamp);
//        System.out.println("Date: " + date);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
    }

    /**
     * 获取当前系统时间的Unix时间戳
     *
     * @return
     */
    public static Long getCurrentUnixTimestamp() {

//        初始化时区对象，北京时间是UTC+8，所以入参为8
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);

//        初始化LocalDateTime对象
        LocalDateTime localDateTime = LocalDateTime.now();

//        获取LocalDateTime对象对应时区的Unix时间戳
        Long unixTimestamp = localDateTime.toEpochSecond(zoneOffset);

        return unixTimestamp;
    }

    /**
     * 时间戳转Unix时间戳
     *
     * @param timestamp
     * @return
     */
    public static Long toUnixTimeStamp(Long timestamp) {
        return timestamp / 1000;
    }

    /**
     * Unix时间戳转时间戳
     *
     * @param unixTimeStamp
     * @return
     */
    public static Long toTimestamp(Long unixTimeStamp) {
        return unixTimeStamp * 1000;
    }


}
