package com.liu.study.redis.redisson.lock;

import com.liu.study.redis.redisson.common.CommonOperator;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * redisson实现读写锁。
 *
 * @author lwa
 * @version 1.0.0
 * @createTime 2021/3/5 15:47
 */
public class RedissonRealizeReadWriteLockDemo {

    private final static String LOCK_PREFIX = "lock:read:write:test:1";

    public static void main(String[] args) throws Exception {

    }

    private static void readWriteLockTest() throws InterruptedException {
        RedissonClient redissonClient = CommonOperator.programConfigForSingleton();

        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(LOCK_PREFIX);
        RLock readLock = readWriteLock.readLock();
        RLock writeLock = readWriteLock.writeLock();

        new Thread(() -> {
            try {
                readLock.lock(30, TimeUnit.SECONDS);
                System.out.println("线程一：=====================================");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                readLock.unlock();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);


        redissonClient.shutdown();
    }

}
