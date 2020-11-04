package com.liu.study.redis.lettuce.basis;

import io.lettuce.core.RedisURI;

import java.time.Duration;

/**
 *
 *
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/11/4 13:24
 */
public class SecondExampleBasisUse {

    public static void main(String[] args) {
        Duration duration = Duration.ofSeconds(60L);
        RedisURI redisURIBuild = RedisURI.builder()
                .withHost("192.168.10.221")
                .withPort(6379).withTimeout(duration).build();
    }

}