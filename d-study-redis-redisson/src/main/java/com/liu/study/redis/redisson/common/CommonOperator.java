package com.liu.study.redis.redisson.common;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import static com.liu.study.redis.CommonConstant.REDIS_HOST;
import static com.liu.study.redis.CommonConstant.REDIS_PASSWORD;

/**
 * @author lwa
 * @version 1.0.0
 * @createTime 2021/3/5 12:56
 */
public class CommonOperator {

    /**
     * 程序化配置：单机模式。创建RedissonClient。
     */
    public static RedissonClient programConfigForSingleton() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + REDIS_HOST + ":6379")
                .setPassword(REDIS_PASSWORD);

        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
