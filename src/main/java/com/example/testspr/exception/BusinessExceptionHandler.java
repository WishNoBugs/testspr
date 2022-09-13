package com.example.testspr.exception;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.example.testspr.CommonResult;
import com.example.testspr.MessageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共异常拦截
 *
 * @author xiongmw@wbpharma.com
 * @version 1.0
 */
@Slf4j
@ControllerAdvice
public class BusinessExceptionHandler {

    public static final String LOCAL_ENV_NAME = "local";
    public static final String PROD_ENV_NAME = "prod";

    /**
     * 未找到可用的适配器
     *
     * @param ex 异常
     * @return ResponseEntity
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<CommonResult<Void>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        CommonResult<Void> returnBean = new CommonResult<>(
                MessageCode.build(HttpStatus.NOT_FOUND.value(), "访问的服务不存在或已消失!"));
        return new ResponseEntity<>(returnBean, null, HttpStatus.OK);
    }


    /**
     * Http请求方法不被支持
     *
     * @param e       异常
     * @param request 请求
     * @return ResponseEntity
     */
    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class
            , BindException.class
            , MethodArgumentNotValidException.class
            , HttpMessageNotReadableException.class
            , MethodArgumentTypeMismatchException.class
            , MissingServletRequestParameterException.class
    })
    public ResponseEntity<CommonResult<Object>> handleBadRequestException(Exception e, HttpServletRequest request) {
        String prodDomain = "boss.fengyouhui.net";
        String testDomain = "boss-test.fengyouhui.net";
        log.info("handleBadRequestException!", e);
        CommonResult<Object> commonResult = new CommonResult<>();
        commonResult.setStatus(HttpStatus.BAD_REQUEST.value());
        String host = URLUtil.url(request.getRequestURL().toString()).getHost();
        if (StrUtil.equalsAny(host, prodDomain, testDomain)) {
            String errorMsg = "请求不被允许或不满足要求!";
            commonResult.setMessage(errorMsg);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException ex = (HttpRequestMethodNotSupportedException) e;
            String errorMsg = String.format("该资源不支持使用`%s`访问！", ex.getMethod());
            commonResult.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
            commonResult.setMessage(errorMsg);
        } else if (e instanceof BindException || e instanceof MethodArgumentNotValidException) {
            ObjectError errorBean = null;
            if (e instanceof BindException) {
                BindException ex = (BindException) e;
                errorBean = ex.getBindingResult().getAllErrors().stream().findFirst().get();
            } else {
                MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
                errorBean = ex.getBindingResult().getAllErrors().stream().findFirst().get();
            }
            String errorMsg = errorBean.getDefaultMessage();
            if (errorBean instanceof FieldError) {
                FieldError errorField = (FieldError) errorBean;
                errorMsg = String.format("属性`%s`未按要求提供数据,数据绑定失败!", errorField.getField());
            }
            commonResult.setMessage(errorMsg);
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) e;
            String errorMsg = String.format("参数`%s`类型不匹配!", ex.getParameter().getParameterName());
            commonResult.setMessage(errorMsg);
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException ex = (MissingServletRequestParameterException) e;
            String errorMsg = String.format("缺少必要的参数`%s`!", ex.getParameterName());
            commonResult.setMessage(errorMsg);
        } else if (e instanceof HttpMessageNotReadableException) {
            String errorMsg = "参数类型不匹配`!";
            commonResult.setMessage(errorMsg);
        }
        return new ResponseEntity<>(commonResult, HttpHeaders.EMPTY, HttpStatus.OK);
    }

    /**
     * 业务异常处理
     *
     * @param ex 异常信息
     * @return ResponseEntity
     */
    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<CommonResult<Object>> handleBusinessException(BusinessException ex, HttpServletRequest request) {
        log.info("业务处理异常!", ex);
        CommonResult<Object> returnBean = new CommonResult<>(ex.getMessageCode());
        returnBean.setMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(returnBean, null, HttpStatus.OK);
    }


    /**
     * 其他异常处理
     *
     * @param ex 异常
     * @return ResponseEntity
     * @throws Exception 异常信息
     */
    @ExceptionHandler({SystemException.class, Exception.class})
    public ResponseEntity<CommonResult<Void>> exception(Exception ex, HttpServletRequest request) throws Exception {
        log.info("服务器处理异常!", ex);

        CommonResult<Void> returnBean;
        if (ex instanceof SystemException) {
            returnBean = new CommonResult<>(((SystemException) ex).getMessageCode());
        } else {
            returnBean = new CommonResult<>(MessageCode.build(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务繁忙，请稍后再试!"));
        }
        return new ResponseEntity<>(returnBean, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
