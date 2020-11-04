package com.liu.study.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

/**
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/11/4 12:50
 */
@SpringBootApplication
public class LettuceApplication {

    /**
     * https://www.cnblogs.com/throwable/p/11601538.html
     *
     * @param args
     */
    public static void main(String[] args) {
        Duration duration = Duration.ofSeconds(60L);
        RedisURI redisURI = new RedisURI("localhost", 6379, duration);

        RedisClient redisClient = RedisClient.create(redisURI);
        redisClient.connect();

    }

}