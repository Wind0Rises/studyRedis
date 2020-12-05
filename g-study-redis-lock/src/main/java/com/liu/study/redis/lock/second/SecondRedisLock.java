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

    /**
     * 锁。
     */
    private static final String LOCK_PREFIX = "concurrent.test.redis.lock";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 上锁。
     * @param releaseTime
     * @return
     */
    public Boolean testLunLock(long releaseTime) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("LuaLock.lua"));
        Long result = stringRedisTemplate.execute(redisScript, Arrays.asList(LOCK_PREFIX), Thread.currentThread().getName(), releaseTime);
        System.out.println(result);
        return result > 0;
    }

}
