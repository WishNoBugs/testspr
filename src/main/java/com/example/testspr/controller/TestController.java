package com.example.testspr.controller;

import cn.hutool.core.codec.Base64;
import com.example.testspr.CommonResult;
import com.example.testspr.domain.User;
import com.example.testspr.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TestController
 *
 * @author zhousy
 * @date 2022/8/25
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/user")
    public String getUser(){
        return "user:name=zhou";
    }

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

}
