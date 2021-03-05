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

    static final String REDIS_HOST = "10.250.43.166";

    static final boolean IS_AUTH = true;

    static final String REDIS_PASSWORD = "3DGiuazc7wkAppV3";

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        /**
         * 直接构建。
         */
        directBuild();


        /**
         *
         */
        builderModelBuild();


    }

    /**
     *
     * @throws Exception
     */
    public static void directBuild() throws Exception {
        /**
         * RedisURI：直接构建。
         */
        Duration duration = Duration.ofSeconds(60L);
        RedisURI redisURI = new RedisURI(REDIS_HOST, 6379, duration);
        if (IS_AUTH) {
            redisURI.setPassword(REDIS_PASSWORD);
        }
        getData(redisURI);
    }


    /**
     * RedisURI建造者模式
     * @throws Exception
     */
    public static void builderModelBuild() throws Exception {
        /**
         * RedisURI：RedisURI建造者模式。
         */
        Duration duration = Duration.ofSeconds(60L);
        RedisURI redisURIBuild = RedisURI.builder()
                .withHost(REDIS_HOST)
                .withPort(6379).withTimeout(duration).build();
        getData(redisURIBuild);
    }

    /**
     * 其他配置
     * @throws Exception
     */
    public static void otherBuild() throws Exception {
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
        URI uri = URI.create("redis://" + REDIS_HOST + ":6379");
        RedisURI redisURISpecial = RedisURI.create(uri);
        getData(redisURISpecial);
    }


    /**
     * 获取数据。
     *
     * @param redisURI
     * @throws Exception
     */
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