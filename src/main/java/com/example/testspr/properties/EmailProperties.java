package com.example.testspr.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailProperties {

    /**
     * 发件人昵称
     */
    private String nickName;

    private Map<String, EmailTemp> emailTempMap = new HashMap<>();

    /**
     * 公众号模板消息
     */
    @Data
    public static class EmailTemp {
        /**
         * 标题
         */
        private String subject;
        /**
         * 邮件内容
         */
        private String content;
    }
}
