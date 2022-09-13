package com.example.testspr.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Map;
import java.util.Objects;

/**
 * 文本工具
 *
 * @author xiongmw@wbpharma.com
 * @version 1.0
 */
public class TextUtil {

    private static final PropertyPlaceholderHelper PROPERTY_PLACEHOLDER = new PropertyPlaceholderHelper("{", "}", ":",
            false);

    /**
     * 占位符替换
     *
     * @param tempText
     * @param tempData
     * @return {@link String}
     **/
    public static String replacePlaceholders(String tempText, Map<String, String> tempData) {
        if (StrUtil.isNotBlank(tempText)) {
            return PROPERTY_PLACEHOLDER.replacePlaceholders(tempText, name -> Objects.isNull(tempData) ? StrUtil.EMPTY : tempData.getOrDefault(name, StrUtil.EMPTY));
        }
        return null;
    }

}
