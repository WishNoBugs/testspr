package com.example.testspr.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.testspr.domain.User;
import com.example.testspr.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author admin
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-09-06 20:16:34
*/
@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User authUser = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        String password = authUser.getPassword();
        if (ObjectUtil.isNotEmpty(authUser)&& StrUtil.isNotBlank(password)){
            List<String> list = new ArrayList<>();
            list.add(authUser.getRole());
            List<GrantedAuthority> grantedAuthorities = list.stream().map(t -> new SimpleGrantedAuthority(t)).collect(Collectors.toList());
            authUser.setAuthorities(grantedAuthorities);
            authUser.setPassword(passwordEncoder.encode(password));
            return authUser;
        }else {
            throw new UsernameNotFoundException("用户"+username+"不存在");
        }
    }
}
