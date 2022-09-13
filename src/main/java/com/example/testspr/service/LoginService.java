package com.example.testspr.service;

import java.util.Map;

/**
 * LoginService
 *
 * @author zhousy
 * @date 2022/9/8
 */
public interface LoginService {
    Map<String,Object> login(String userName, String password);
}
