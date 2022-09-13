package com.example.testspr.controller;

import com.example.testspr.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * LoginController
 *
 * @author zhousy
 * @date 2022/9/8
 */
@RestController
@RequestMapping("/api/lo")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login/{name}/{pass}")
    public Map<String,Object> login(@PathVariable("name") String username, @PathVariable("pass") String password){
        return loginService.login(username,password);
    }
}
