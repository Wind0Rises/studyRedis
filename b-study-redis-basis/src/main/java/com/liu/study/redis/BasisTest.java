package com.liu.study.redis;

import redis.clients.jedis.Jedis;

/**
 * @desc
 * @author Liuweian
 * @version 1.0.0
 * @createTime 2020/6/4 18:41
 */
public class BasisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);


    }

}
