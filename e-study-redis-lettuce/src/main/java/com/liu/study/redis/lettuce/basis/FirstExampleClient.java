package com.liu.study.redis.lettuce.basis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;

import java.net.URI;
import java.time.Duration;

/**
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/11/4 13:02
 */
public class FirstExampleClient {

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        /**
         * RedisURI：直接构建。
         */
        Duration duration = Duration.ofSeconds(60L);
        RedisURI redisURI = new RedisURI("192.168.10.221", 6379, duration);
        getData(redisURI);

        /**
         * RedisURI：RedisURI建造者模式。
         */
        RedisURI redisURIBuild = RedisURI.builder()
                .withHost("192.168.10.221")
                .withPort(6379).withTimeout(duration).build();
        getData(redisURIBuild);

        /**
         * URI语法：
         *      单机：
         *          redis://localhost
         *          redis://mypassword@127.0.0.1:6379/0?timeout=10s
         *          redis://[password@]host[:port][/databaseNumber][?[timeout=timeout[d|h|m|s|ms|us|ns]]
         *
         *      SSL
         *          rediss://localhost
         *          rediss://mypassword@127.0.0.1:6379/0?timeout=10s
         *          rediss://[password@]host[:port][/databaseNumber][?[timeout=timeout[d|h|m|s|ms|us|ns]]
         *
         *
         *     单机socket
         *          redis-socket://path[?[timeout=timeout[d|h|m|s|ms|us|ns]][&_database=database_]]
         *          redis-socket:///tmp/redis?timeout=10s&_database=0
         *
         *     集群
         *          redis-sentinel://[password@]host[:port][,host2[:port2]][/databaseNumber][?[timeout=timeout[d|h|m|s|ms|us|ns]]#sentinelMasterId
         *          redis-sentinel://mypassword@127.0.0.1:6379,127.0.0.1:6380/0?timeout=10s#mymaster
         *
         *
         */
        URI uri = URI.create("redis://192.168.10.221:6379");
        RedisURI redisURISpecial = RedisURI.create(uri);
        getData(redisURISpecial);
    }

    private static void getData(RedisURI redisURI) throws Exception {
        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisFuture<String> firstTest = connect.async().get("lettuce:first:test:1");
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");
        System.out.println(firstTest.get());
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");
    }

}