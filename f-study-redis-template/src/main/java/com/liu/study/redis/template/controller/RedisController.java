package com.liu.study.redis.template.controller;

import com.liu.study.redis.template.operation.StringOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/7/28 13:04
 */
@RestController
@RequestMapping("/test")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/first")
    public String firstMethod() {
        redisTemplate.opsForValue().set("redis:test:first", "liuweian");
        return "success";
    }

    @Autowired
    private StringOperation stringOperation;

    @RequestMapping("/second")
    public String secondMethod() {
        stringOperation.testExpire();
        return "success";
    }

}