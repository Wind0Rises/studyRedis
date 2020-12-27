package com.liu.study.redis.redisson.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

/**
 * 使用Redis实现可重入锁。
 *
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/12/27 13:53
 */
public class ReentrantLockDemo {

    /**
     * 可重入锁，前缀。
     */
    private static String REENTRANT_LOCK_KEY = "reentrant:lock:test-lock";

    public static void main(String[] args) {
        RedissonClient redissonClient = getRedissonClient();
        RLock lock = redissonClient.getLock(REENTRANT_LOCK_KEY);

    }

    private static RedissonClient getRedissonClient() {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO)
                .useSingleServer()
                .setAddress("redis://127.0.0.1:6379");

        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

}
