package com.liu.study.redis.redisson.lock;

import com.liu.study.redis.redisson.common.CommonOperator;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * Redisson实现可重入锁。
 *
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/12/27 13:53
 */
public class RedissonRealizeReentrantLockDemo {

    /**
     * 可重入锁，前缀。
     */
    private static String REENTRANT_LOCK_KEY = "lock:reentrant:test:1";

    /**
     * 注意：
     *      存储的是一个hash类型的值。
     *      key：一个随机值：  value：上锁的次数。
     *
     */
    public static void main(String[] args) {
        RedissonClient redissonClient = CommonOperator.programConfigForSingleton();
        RLock lock = redissonClient.getLock(REENTRANT_LOCK_KEY);

        try {
            lock.lock(30, TimeUnit.SECONDS);
            secondLock(lock);
        } finally {
            lock.unlock();
        }

        redissonClient.shutdown();
    }

    /**
     * 第二次上锁、
     *
     * @param lock
     */
    private static void secondLock(RLock lock) {
        try {
            System.out.println("-----------------------  第二次加锁  -------------------------");
            lock.lock(30, TimeUnit.SECONDS);
        } finally {
            lock.unlock();
        }
    }


}
