package com.liu.study.redis.redisson.lock;

import com.liu.study.redis.redisson.common.CommonOperator;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 *
 * 联锁：有多个锁，并且由这些组成一把新的锁，只有所有的锁都获取到锁，才真正的获取到锁。
 *
 * @author lwa
 * @version 1.0.0
 * @createTime 2021/3/5 14:24
 */
public class RedissonRealizeMultiLockDemo {

    private final static String FIRST_LOCK_PREFIX = "lock:multi:test:1";

    private final static String SECOND_LOCK_PREFIX = "lock:multi:test:2";

    private final static String THREE_LOCK_PREFIX = "lock:multi:test:3";

    public static void main(String[] args) {
        multiLock();
    }

    /**
     * 联锁测试。
     */
    private static void multiLock() {
        RedissonClient redissonClient = CommonOperator.programConfigForSingleton();

        RLock firstLock = redissonClient.getLock(FIRST_LOCK_PREFIX);
        RLock secondLock = redissonClient.getLock(SECOND_LOCK_PREFIX);
        RLock threeLock = redissonClient.getLock(THREE_LOCK_PREFIX);

        RedissonMultiLock multiLock = new RedissonMultiLock(firstLock, secondLock, threeLock);

        // 只有firstLock、secondLock、threeLock：都获取到锁的时候，才能真正的获取到锁。
        try {
            multiLock.lock(30, TimeUnit.SECONDS);
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            multiLock.unlock();
        }

        redissonClient.shutdown();
    }


}
