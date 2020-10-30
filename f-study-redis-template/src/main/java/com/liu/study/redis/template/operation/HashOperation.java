package com.liu.study.redis.template.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/7/28 17:07
 */
@Component
public class HashOperation {

    @Autowired
    private RedisTemplate redisTemplate;

    public void stringOperation() {
        redisTemplate.opsForHash();
    }

}