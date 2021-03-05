package com.liu.study.redis.redisson.lock;

import com.liu.study.redis.redisson.common.CommonOperator;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 *
 * RedLock使用示例。
 *
 * @author lwa
 * @version 1.0.0
 * @createTime 2021/3/5 15:14
 */
public class RedissonRealizeRedLockDemo {

    private final static String FIRST_LOCK_PREFIX = "lock:red:lock:test:1";

    private final static String SECOND_LOCK_PREFIX = "lock:red:lock:test:2";

    private final static String THREE_LOCK_PREFIX = "lock:red:lock:test:3";


    public static void main(String[] args) {

        RedissonClient redissonClient = CommonOperator.programConfigForSingleton();

        RLock firstLock = redissonClient.getLock(FIRST_LOCK_PREFIX);
        RLock secondLock = redissonClient.getLock(SECOND_LOCK_PREFIX);
        RLock threeLock = redissonClient.getLock(THREE_LOCK_PREFIX);

        RedissonRedLock redLock = new RedissonRedLock(firstLock, secondLock, threeLock);

        // 同时加锁：firstLock、secondLock、threeLock红锁在大部分节点上加锁成功就算成功。
        try {
            redLock.lock(30, TimeUnit.SECONDS);
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            redLock.unlock();
        }

        redissonClient.shutdown();

    }

}
