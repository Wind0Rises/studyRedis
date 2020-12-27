package com.liu.study.redis.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

/**
 *
 * Redisson配置。
 *
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/12/27 12:23
 */
public class RedissonConfigDemo {

    public static void main(String[] args) {
        programConfig();
    }

    /**
     * 程序化配置
     */
    public static void programConfig() {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO)
                .useSingleServer()
                .setAddress("redis://127.0.0.1:6379");

        RedissonClient redissonClient = Redisson.create(config);
        // redissonClient.set

    }

}
