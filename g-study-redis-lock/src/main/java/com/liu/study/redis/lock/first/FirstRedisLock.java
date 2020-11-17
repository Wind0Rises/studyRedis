package com.liu.study.redis.lock.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <note>使用setnx构建简单的redis的分布式锁</note>
 *
 * 存在的问题：
 *      1、如果业务时间过长，超过设置的过期的时间，
 * 
 * @author Liuweian
 * @createTime 2020/11/16 20:08
 * @version 1.0.0
 */
@Service
public class FirstRedisLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void deductionRepository() {
        String threadName = Thread.currentThread().getName();
        if (stringRedisTemplate.opsForValue().setIfAbsent("redis:test:lock", threadName, 100L, TimeUnit.SECONDS)) {
          try {
              Long decrement = stringRedisTemplate.opsForValue().decrement("redis:test:deduction");
              System.out.println("-----现场：" + threadName + "----数量：" + decrement);
          } finally {
              String redisOfThreadName = stringRedisTemplate.opsForValue().get("redis:test:lock");
              if (redisOfThreadName.equals(threadName)) {
                  stringRedisTemplate.delete("redis:test:lock");
              }
              threadName += " --------------------";
          }
        }
        System.out.println("++++++++++   " + threadName);
    }

}
