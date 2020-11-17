package com.liu.study.redis.lock.controller;

import com.liu.study.redis.lock.first.FirstRedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CountDownLatch;

/**
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/11/17 13:00
 */
@Controller
@RequestMapping("/first")
public class FirstController {

    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    @Autowired
    private FirstRedisLock firstRedisLock;

    @RequestMapping("/test")
    public void test() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            countDownLatch.countDown();
            new Thread(new ConcurrentThread(firstRedisLock)).start();
            Thread.sleep(100L);
        }
    }

    class ConcurrentThread implements Runnable {

        FirstRedisLock firstRedisLock;

        public ConcurrentThread(FirstRedisLock firstRedisLock) {
            this.firstRedisLock = firstRedisLock;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                System.out.println("starting.....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            firstRedisLock.deductionRepository();
        }
    }

}
