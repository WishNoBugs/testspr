package com.example.testspr;

import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author cloudgc
 * @since 10/12/2020
 */
@ControllerAdvice("com.example.testspr")
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {


    private static final String VOID = "void";

    public static final String TRACE_ID = "traceId";
    public static final String MOBILE = "mobile";

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {

        // void type
        if (VOID.equals(getReturnName(returnType))) {
            return null;
        }

        if (isBasicType(returnType)) {
            return body;
        }

        String traceId = MDC.get(TRACE_ID);

        if (body == null) {
            return new CommonResult(MessageCode.SUCCESS, traceId);
        }
        if (!(body instanceof CommonResult)) {
            return new CommonResult(body, traceId);
        }
        MDC.remove(MOBILE);
        return body;
    }

    private String getReturnName(MethodParameter returnType) {
        if (returnType == null || returnType.getMethod() == null) {
            return StrUtil.EMPTY;
        }
        return returnType.getMethod().getReturnType().getName();

    }

    private boolean isBasicType(MethodParameter returnType) {

        if (returnType == null || returnType.getMethod() == null) {
            return true;
        }

        String name = returnType.getMethod().getReturnType().getSimpleName();
        switch (name) {
            case "String":
            case "byte[]":
            case "ResponseEntity":
                return true;
            default:
                return false;
        }

    }


}
