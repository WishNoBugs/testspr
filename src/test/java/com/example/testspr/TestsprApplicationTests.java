package com.example.testspr;

import com.example.testspr.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class TestsprApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private EmailService emailService;


    @Test
    void contextLoads() {
        Object o = redisTemplate.opsForHash().get("age", "zhousy");
        System.out.println(o);
        redisTemplate.opsForHash().put("age","zhousy","100");
    }

    @Test
    void test(){
       emailService.send("02589678@yto.net.cn");
    }

}
