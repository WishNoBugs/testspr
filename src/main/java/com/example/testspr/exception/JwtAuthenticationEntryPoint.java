package com.example.testspr.exception;

import com.alibaba.fastjson.JSONObject;

import com.example.testspr.constants.CommConstant;
import com.example.testspr.response.AuthorizeReponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * jwt 未授权 异常处理
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        logger.info("JwtAuthenticationEntryPoint[commence] 拦截到登陆授权异常："+ JSONObject.toJSONString(authException));
        AuthorizeReponse reponse;
        if(authException instanceof InternalAuthenticationServiceException){/**用户不存在**/
            reponse=new AuthorizeReponse(CommConstant.USER_NOT_EXIST_CODE,CommConstant.USER_NOT_EXIST_MSG);
            response.getWriter().write(JSONObject.toJSONString(reponse));
        } else if(authException instanceof BadCredentialsException){ /**密码不正确*/
            reponse=new AuthorizeReponse(CommConstant.PASSWORD_IS_WRONG_CODE,CommConstant.PASSWORD_IS_WRONG_msg);
            response.getWriter().write(JSONObject.toJSONString(reponse));
        }else if(authException instanceof LockedException){/**账号被禁用**/
            reponse=new AuthorizeReponse(CommConstant.USER_IS_DISABLED_CODE,CommConstant.USER_IS_DISABLED_msg);
            response.getWriter().write(JSONObject.toJSONString(reponse));
        }else if(authException instanceof CredentialsExpiredException){/**暂无资源访问权限**/
            reponse=new AuthorizeReponse(CommConstant.USER_HAS_NO_RIGHT_CODE,CommConstant.USER_HAS_NO_RIGHT_msg);
            response.getWriter().write(JSONObject.toJSONString(reponse));
        }else{       /**token认证未通过**/
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "无效的token");
        }
    }
}
