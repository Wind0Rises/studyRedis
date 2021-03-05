package com.liu.study.redis.redisson.lock;

import com.liu.study.redis.redisson.common.CommonOperator;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 *
 * Redisson：公平锁使用
 *
 * @author lwa
 * @version 1.0.0
 * @createTime 2021/3/5 13:14
 */
public class RedissonRealizeFairLockDemo {

    private static final String LOCK_PREFIX = "lock:fair:1";

    public static void main(String[] args) throws Exception {
        fairLockTest();
    }

    /**
     * 公平锁：它保证了当多个Redisson客户端线程同时请求加锁时，优先分配给先发出请求的线程。
     *
     * TODO:没有模拟出想要的效果。
     *
     */
    private static void fairLockTest() throws InterruptedException {
        RedissonClient redissonClient = CommonOperator.programConfigForSingleton();

        // RLock lock = redissonClient.getFairLock(LOCK_PREFIX);
        RLock lock = redissonClient.getLock(LOCK_PREFIX);

        new Thread(() -> {

            try {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                lock.lock(30, TimeUnit.SECONDS);
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

        Thread.sleep(1000);

        IntStream.range(0, 20).forEach(item -> {
            new Thread(() -> {
                try {
                    lock.lock(30, TimeUnit.SECONDS);
                    System.out.println("---------" + Thread.currentThread().getName());
                } finally {
                    lock.unlock();
                }
            }, String.valueOf(item)).start();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        System.out.println(next);
        redissonClient.shutdown();
    }

}
