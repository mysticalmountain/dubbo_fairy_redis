package com.fairy.redis.exception;

/**
 * 数据格式验证异常
 * Created by andongxu on 16-4-29.
 */
public class ValidatorException extends Exception {

    private String errorCode;

    private String errorMsg;

    private Throwable e;

    public ValidatorException(String errorCode, String errorMsg, Throwable e) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.e = e;
    }

    public ValidatorException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ValidatorException(String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getMessage() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Throwable getE() {
        return e;
    }

    public void setE(Throwable e) {
        this.e = e;
    }
}
