package com.example.testspr;

import com.example.testspr.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cloudgc
 * @since 10/12/2020
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 5149607201171677882L;


    public CommonResult(int status) {
        this.status = status;
    }

    public CommonResult(T data) {
        this.status = MessageCode.SUCCESS.getStatus();
        this.message = MessageCode.SUCCESS.getMessage();
        this.data = data;
    }

    public CommonResult(T data, String traceId) {
        this.status = MessageCode.SUCCESS.getStatus();
        this.message = MessageCode.SUCCESS.getMessage();
        this.data = data;
        this.traceId = traceId;
    }

    public CommonResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public CommonResult(MessageCode messageCode) {
        this.status = messageCode.getStatus();
        this.message = messageCode.getMessage();

    }

    public CommonResult(MessageCode messageCode, String traceId) {
        this.status = messageCode.getStatus();
        this.message = messageCode.getMessage();
        this.traceId = traceId;
    }


    public CommonResult(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public CommonResult(BusinessException e) {
        this.status = MessageCode.BIZ_ERROR.getStatus();
        this.message = e.getMessage();
    }

    /**
     * 状态编码
     */
    private int status;

    /**
     * 消息
     */
    private String message;

    /**
     * traceId
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String traceId;

    /**
     * 数据
     */
    private T data;

    /**
     * 是否成功
     */
    @JsonIgnore
    public boolean isSuccess() {
        return MessageCode.SUCCESS.getStatus() == this.getStatus();
    }

    /**
     * 构建失败 返回对象
     *
     * @param messageCode 消息
     * @param <T>         t
     * @return T
     */
    public static <T> CommonResult<T> failed(MessageCode messageCode) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setStatus(messageCode.getStatus());
        commonResult.setMessage(messageCode.getMessage());
        return commonResult;
    }

    /**
     * 构建失败 返回对象
     *
     * @param message 错误信息
     * @param <T>     t
     * @return T
     */
    public static <T> CommonResult<T> failed(String message) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setStatus(MessageCode.BIZ_ERROR.getStatus());
        commonResult.setMessage(message);
        return commonResult;
    }

    @Override
    public String toString() {
        String dataStr = "";
        if (data != null) {
            dataStr = data.toString();
        }
        return "{\"status\":" + status + ",\"message\":\"" + message + '\"' + ",\"traceId\":\"" + traceId + '\"'
                + ",\"data\":" + dataStr + "}";
    }
}
