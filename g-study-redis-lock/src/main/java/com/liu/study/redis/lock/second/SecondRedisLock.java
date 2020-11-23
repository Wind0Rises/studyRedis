package com.liu.study.redis.lock.second;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/11/18 13:07
 */
@Service
public class SecondRedisLock {

    private static final String LOCK_PREFIX = "concurrent.test.redis.lock";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public Boolean testLunLock(long releaseTime) {
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("LuaLock.lua"));
        Boolean execute = stringRedisTemplate.execute(redisScript, Arrays.asList(LOCK_PREFIX), Thread.currentThread().getName());
        return execute;
    }

}
