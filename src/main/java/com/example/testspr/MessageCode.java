package com.example.testspr;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

/**
 * @author cloudgc
 * @since 8/3/2020
 */
@Slf4j
public class MessageCode {

    /**
     * default ok
     */
    public static final MessageCode SUCCESS = MessageCode.build(200, "ok");

    public static final MessageCode BIZ_ERROR = MessageCode.build(10006, "bizError");

    private int status;

    private String message;

    MessageCode(int status, String message) {

        this.status = status;
        this.message = message;

    }

    /**
     * get new type exception category
     *
     * @param status  exception code
     * @param message exception message
     * @return this
     */
    public static MessageCode build(int status, String message) {
        return new MessageCode(status, message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取替换的message
     *
     * @param sub 替换的字符串
     * @return finally string
     */
    public String getSubMessage(String[] sub) {

        if (this.getMessage() == null) {
            return "";
        }

        if (sub == null || sub.length <= 0) {
            return this.getMessage();
        }

        for (int i = 0, j = sub.length; i < j; i++) {
            if (StrUtil.isEmpty(sub[i])) {
                sub[i] = "";
            }
        }
        try {
            return MessageFormat.format(this.getMessage(), sub);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return this.getMessage();
        }

    }

    @Override
    public String toString() {
        return "{\"status\":" + status + ",\"message\":\"" + message + '\"' + "}";
    }
}
