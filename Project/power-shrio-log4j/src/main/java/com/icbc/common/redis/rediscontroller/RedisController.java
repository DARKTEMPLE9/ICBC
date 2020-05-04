package com.icbc.common.redis.rediscontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@RequestMapping("redis")
public class RedisController {


    @Autowired
    private StringRedisTemplate redisTemplate;

    String prefix = "asd_" + "*";


    @RequestMapping(value = "/setRedis", method = {RequestMethod.GET, RequestMethod.POST})
    public void setRedis() {
        //redisTemplate.opsForSet().add("asd_fgh", 1231);
        redisTemplate.opsForSet().add("testredis", "qwe");
        redisTemplate.opsForSet().add("testredis1", "testredis1");
        redisTemplate.opsForSet().add("testredis2", "testredis2");
        redisTemplate.opsForSet().add("testredis3", "testredis3");
        redisTemplate.opsForSet().add("testredis4", "testredis4");
        redisTemplate.opsForSet().add("testredis5", "testredis5");
        System.out.println(1);
    }

    @GetMapping("/TestRedis")
    public void TestRedis() {
        Set<String> keys = redisTemplate.keys(prefix);
        //Object asd_12312123123 = redisTemplate.opsForValue().get("asd_12312123123");
        System.out.println(1);
    }
}
