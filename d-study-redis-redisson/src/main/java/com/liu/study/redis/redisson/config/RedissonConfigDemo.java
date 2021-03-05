package com.liu.study.redis.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.io.File;

/**
 *
 * Redisson配置——示例。
 *
 *
 * RedissonClient:主Redisson接口，用于通过同步/异步接口访问所有Redisson对象。
 *
 *
 * Redssion的缺点：
 *      1. 不支持字符串存储，Redisson的实现类中只支持集合操作，不能对普通字符做操作。
 *      2. 不支持很多redis特性，比如排序，事务，管道，集群等。
 *      3. 发布时间短，稳定性和可靠性有待验证。
 *
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/12/27 12:23
 */
public class RedissonConfigDemo {

    static final String REDIS_HOST = "10.250.43.166";

    static final boolean IS_AUTH = true;

    static final String REDIS_PASSWORD = "3DGiuazc7wkAppV3";

    public static void main(String[] args) {
        RedissonClient redissonClient = programConfigForSingleton();

        /**
         * TODO：为啥main线程执行完成以后，进程没有结束？？？
         */

        redissonClient.shutdown();
    }

    /**
     * 程序化配置：单机模式
     */
    public static RedissonClient programConfigForSingleton() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + REDIS_HOST + ":6379")
                .setPassword(REDIS_PASSWORD);

        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

    /**
     * 程序化配置：哨兵模式
     */
    public static RedissonClient programConfigForSentinel() {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO)
                .useSentinelServers()
                .setMasterName("mymater")
                //可以用"rediss://"来启用SSL连接
                .addSentinelAddress("127.0.0.1:26389", "127.0.0.1:26379")
                .addSentinelAddress("127.0.0.1:26319");

        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }


    /**
     * 程序化配置：集群模式
     */
    public static RedissonClient programConfigForCluster() {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO)
                .useClusterServers()
                .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
                .addNodeAddress("redis://127.0.0.1:7002");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

    /**
     * 单机模式：
     * singleServerConfig:
     *   idleConnectionTimeout: 10000
     *   connectTimeout: 10000
     *   timeout: 3000
     *   retryAttempts: 3
     *   retryInterval: 1500
     *   password: null
     *   subscriptionsPerConnection: 5
     *   clientName: null
     *   address: "redis://127.0.0.1:6379"
     *   subscriptionConnectionMinimumIdleSize: 1
     *   subscriptionConnectionPoolSize: 50
     *   connectionMinimumIdleSize: 32
     *   connectionPoolSize: 64
     *   database: 0
     *   dnsMonitoringInterval: 5000
     * threads: 0
     * nettyThreads: 0
     * codec: !<org.redisson.codec.JsonJacksonCodec> {}
     * "transportMode":"NIO"
     *
     *
     * 哨兵模式：
     *  sentinelServersConfig:
     *   idleConnectionTimeout: 10000
     *   connectTimeout: 10000
     *   timeout: 3000
     *   retryAttempts: 3
     *   retryInterval: 1500
     *   password: null
     *   subscriptionsPerConnection: 5
     *   clientName: null
     *   loadBalancer: !<org.redisson.connection.balancer.RoundRobinLoadBalancer> {}
     *   slaveSubscriptionConnectionMinimumIdleSize: 1
     *   slaveSubscriptionConnectionPoolSize: 50
     *   slaveConnectionMinimumIdleSize: 32
     *   slaveConnectionPoolSize: 64
     *   masterConnectionMinimumIdleSize: 32
     *   masterConnectionPoolSize: 64
     *   readMode: "SLAVE"
     *   sentinelAddresses:
     *   - "redis://127.0.0.1:26379"
     *   - "redis://127.0.0.1:26389"
     *   masterName: "mymaster"
     *   database: 0
     * threads: 0
     * nettyThreads: 0
     * codec: !<org.redisson.codec.JsonJacksonCodec> {}
     * "transportMode":"NIO"
     *
     *
     * 集群模式：
     * clusterServersConfig:
     *   idleConnectionTimeout: 10000
     *   connectTimeout: 10000
     *   timeout: 3000
     *   retryAttempts: 3
     *   retryInterval: 1500
     *   password: null
     *   subscriptionsPerConnection: 5
     *   clientName: null
     *   loadBalancer: !<org.redisson.connection.balancer.RoundRobinLoadBalancer> {}
     *   slaveSubscriptionConnectionMinimumIdleSize: 1
     *   slaveSubscriptionConnectionPoolSize: 50
     *   slaveConnectionMinimumIdleSize: 32
     *   slaveConnectionPoolSize: 64
     *   masterConnectionMinimumIdleSize: 32
     *   masterConnectionPoolSize: 64
     *   readMode: "SLAVE"
     *   nodeAddresses:
     *   - "redis://127.0.0.1:7004"
     *   - "redis://127.0.0.1:7001"
     *   - "redis://127.0.0.1:7000"
     *   scanInterval: 1000
     * threads: 0
     * nettyThreads: 0
     * codec: !<org.redisson.codec.JsonJacksonCodec> {}
     * "transportMode":"NIO"
     */
    /**
     * yml陪着。
     */
    public RedissonClient ymlFileConfig() throws Exception {
        Config config = Config.fromYAML(new File("D:\\myself\\idea_workspace\\studyRedis\\d-study-redis-redisson\\src\\main\\resources\\redis-config.yml"));
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

}
