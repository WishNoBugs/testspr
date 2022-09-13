package com.example.testspr.exception;



import com.example.testspr.MessageCode;

import java.io.Serializable;

/**
 * 业务场景预期之外的系统异常
 */
public class SystemException extends BaseRuntimeException implements Serializable {

    private static final long serialVersionUID = 4080682597205244132L;

    public SystemException(MessageCode messageCode) {
        super(messageCode);
    }

    public SystemException(MessageCode messageCode, String message) {
        super(messageCode, message);
    }

    public SystemException(MessageCode messageCode, Throwable cause) {
        super(messageCode, cause);
    }

    public SystemException(MessageCode messageCode, String... sub) {
        super(messageCode, sub);
    }

    public SystemException(MessageCode messageCode, Throwable cause, String... sub) {
        super(messageCode, cause, sub);
    }
}
