package com.liu.study.redis.redisson.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static com.liu.study.redis.CommonConstant.*;

/**
 * @author lwa
 * @version 1.0.0
 * @createTime 2021/3/4 17:23
 */
public class FirstLockByRedissonDemo {

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        /**
         * 基础
         */
        // basisLockUseTest();


        /**
         * lock(...)：都无法实现看门狗的功能。
         * tryLock(long time, TimeUnit unit)：可以实现看门狗。
         * tryLock(long waitTime, long leaseTime, TimeUnit unit)：无法实现看门狗的功能。
         */
        // watchDogLockTest();
        // watchDogTryLockTest();

    }


    /**
     * TTL返回值为-1时，值不存在；-2时key不存在。
     */
    public static void basisLockUseTest() throws InterruptedException {
        RedissonClient redissonClient = programConfigForSingleton();

        RLock lock = redissonClient.getLock(LOCK_TEST);

        List<Integer> list = new LinkedList<Integer>(){{add(0);}};

        IntStream.range(0, 40).forEach(item -> {
            new Thread(() -> {
                try {
                    lock.lock(100, TimeUnit.SECONDS);
                    Integer integer = list.get(0);
                    list.add(0, integer + 1);
                } finally {
                    lock.unlock();
                }
            }, "线程 --------" + item).start();
        });

        Thread.sleep(5000);

        System.out.println(list.get(0));

        redissonClient.shutdown();
    }


    /**
     * WatchDog：测试。
     *
     * lock()：无法实现进行操作的。
     */
    public static void watchDogLockTest() {
        RedissonClient redissonClient = programConfigForSingleton();

        RLock lock = redissonClient.getLock(LOCK_TEST);

        try {
            lock.lock(2, TimeUnit.SECONDS);
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } finally {
            lock.unlock();
        }

        redissonClient.shutdown();
    }

    /**
     * WatchDog：测试。
     *
     * tryLock(long time, TimeUnit unit)：可以实现看门狗。
     * tryLock(long waitTime, long leaseTime, TimeUnit unit)：无法实现看门狗的功能。
     *
     */
    public static void watchDogTryLockTest() throws Exception {
        RedissonClient redissonClient = programConfigForSingleton();

        RLock lock = redissonClient.getLock(LOCK_TEST);

        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                try {
                    TimeUnit.SECONDS.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } finally {
            lock.unlock();
        }
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

}
