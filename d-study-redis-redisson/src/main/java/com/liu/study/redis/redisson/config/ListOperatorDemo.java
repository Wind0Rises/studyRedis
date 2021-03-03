package com.liu.study.redis.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

/**
 * redis：对数据结构为List的操作。
 *
 * @author lwa
 * @version 1.0.0
 * @createTime 2021/1/29 14:34
 */
public class ListOperatorDemo {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://redis-9c411c2-dcs-scxe.dcs.huaweicloud.com:6379")
                .setPassword("pacf4d99g3kTCGGS");
        config.setCodec(new JsonJacksonCodec());

        RedissonClient redissonClient = Redisson.create(config);


        RList<String> list = redissonClient.getList("list");
        // list.expire(1, TimeUnit.DAYS);
        System.out.println(list);

        redissonClient.shutdown();
    }

}
