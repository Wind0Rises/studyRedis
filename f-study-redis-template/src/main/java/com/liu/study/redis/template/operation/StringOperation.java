package com.liu.study.redis.template.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/7/28 17:07
 */
@Component
public class StringOperation {

    @Autowired
    private RedisTemplate redisTemplate;

    public void stringOperation() {
        redisTemplate.opsForValue().set("first:redis:lie", "zhangsan");
    }

    public void testExpire() {
        redisTemplate.expire("first:redis:lie", 23, TimeUnit.SECONDS);

        ValueOperations valueOperations = redisTemplate.opsForValue();



        // redisTemplate.boundValueOps();
    }

}