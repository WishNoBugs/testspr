package com.example.testspr.service.impl;

import cn.hutool.core.codec.Base64;
import com.example.testspr.constants.MessageCodeConstant;
import com.example.testspr.domain.User;
import com.example.testspr.exception.BusinessException;
import com.example.testspr.service.LoginService;
import com.example.testspr.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * LoginServiceImpl
 *
 * @author zhousy
 * @date 2022/9/8
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String, Object> login(String userName, String password) {
        User user;
        try {
            user = (User) userDetailsService.loadUserByUsername(userName);
        } catch (UsernameNotFoundException e) {
            throw new BusinessException(MessageCodeConstant.LOGIN_ERROR);
        }
        if(user.getStatus()==0){
            throw new BusinessException(MessageCodeConstant.LOGIN_FORBID);
        }
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName,password);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            throw new BusinessException(MessageCodeConstant.NOT_MATCH);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("user", user);
        resultMap.put("token", JwtTokenUtil.generateToken(user));
        return resultMap;
    }
}
