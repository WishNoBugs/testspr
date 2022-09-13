package com.example.testspr.config;

import com.example.testspr.domain.User;
import com.example.testspr.mapper.UserMapper;
import com.example.testspr.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述:
 * token验证
 *
 * @author wangtao
 * @create 2018-12-17 18:01
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Authorization,Origin,X-Requested-With,Content-Type,Accept");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");

        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        }
        if (!httpServletRequest.getMethod().equals("OPTIONS")) {
            String authHeader  = httpServletRequest.getHeader(JwtTokenUtil.TOKENHEADER);
            if (authHeader != null && authHeader.startsWith(JwtTokenUtil.TOKENHEAD)) {
                final String authToken = authHeader.substring(JwtTokenUtil.TOKENHEAD.length()); // The part after "Bearer "
                String username = jwtUtil.getUserAccountFromToken(authToken);
                if (log.isInfoEnabled()) {
                    log.info("JwtAuthenticationTokenFilter_doFilterInternal checking authentication " + username);
                }
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {//token校验通过
                    User userDetails = (User) this.userDetailsService.loadUserByUsername(username);//根据account去数据库中查询user数据
                    userDetails.setPassword(null);//将密码不保存在SecurityContext中
                    if (jwtUtil.validateToken(authToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                                httpServletRequest));
                        if (log.isInfoEnabled()) {
                            log.info("JwtAuthenticationTokenFilter_doFilterInternal  用户验证token,验证用户： " + username + ", setting security context");
                        }
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }

            filterChain.doFilter(httpServletRequest, httpServletResponse);

        }
    }
}
