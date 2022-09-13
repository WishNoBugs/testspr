package com.example.testspr.constants;

import com.example.testspr.MessageCode;

/**
 * MessageCodeConstant
 *
 * @author zhousy
 * @date 2022/9/8
 */
public class MessageCodeConstant {
    public static final MessageCode LOGIN_ERROR = MessageCode.build(1000, "登录失败");
    public static final MessageCode LOGIN_FORBID = MessageCode.build(1001, "账号被禁");
    public static final MessageCode NOT_MATCH = MessageCode.build(1002, "账号密码错误");
}
