package com.example.testspr.enums;

/**
 * 邮件模板类
 *
 * @author lhm
 */
public enum EmailTemp {

    /**
     * 邮件模板枚举
     */
    BILL_PUSH("账单推送"),
    BILL_RECEIPT("账单回执");

    private final String desc;

    EmailTemp(String desc) {
        this.desc = desc;
    }
}
