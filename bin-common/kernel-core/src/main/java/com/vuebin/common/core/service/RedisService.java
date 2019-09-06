package com.vuebin.common.core.service;

import com.alibaba.fastjson.JSON;
import com.vuebin.common.core.util.SpringContextHolder;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis通用服务
 *
 * @author fengjiabin
 * @date 2019/6/12 11:17
 */
@Component
public class RedisService {

    /**
     * 将一个对象放入redis中
     *
     * @param redisKey
     * @param object
     * @param hour
     */
    public static void put(String redisKey, Object object, Long hour, TimeUnit unit) {
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getBean(StringRedisTemplate.class);
        BoundHashOperations<String, String, String> bho = stringRedisTemplate.boundHashOps(redisKey);

//        put redis, 并设失效时间
        bho.put(redisKey, JSON.toJSONString(object));
        bho.expire(hour, unit);
    }

    /**
     * 根据一个key获取redis中value
     *
     * @param redisKey
     * @return
     */
    public static Object get(String redisKey) {
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getBean(StringRedisTemplate.class);
        BoundHashOperations<String, String, String> bho = stringRedisTemplate.boundHashOps(redisKey);
        return bho.get(redisKey);
    }

    /**
     * 根据一个key删除value
     *
     * @param redisKey
     */
    public static void delete(String redisKey) {
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getBean(StringRedisTemplate.class);
        BoundHashOperations<String, String, String> bho = stringRedisTemplate.boundHashOps(redisKey);
        bho.delete(redisKey);
    }

}
