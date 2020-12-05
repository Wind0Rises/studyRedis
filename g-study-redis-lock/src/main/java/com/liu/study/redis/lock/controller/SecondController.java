package com.liu.study.redis.lock.controller;

import com.liu.study.redis.lock.second.SecondRedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/11/23 13:24
 */
@Controller
@RequestMapping("/second")
public class SecondController {

    @Autowired
    private SecondRedisLock secondRedisLock;

    @RequestMapping("/lock")
    public String lock() {
        Boolean result = secondRedisLock.testLunLock(1000L);
        if (result) {
            return "success";
        }
        return "error";
    }

}
