package com.magic.exception;

/**
 * @author 刘俊重
 * @Description 自定义异常
 */
public class MagicException extends RuntimeException {

    private String msg;

    private int code;

    public MagicException(String msg){
        super(msg);
        this.msg = msg;
    }

    public MagicException(String msg, Throwable e){
        super(msg,e);
        this.msg = msg;
    }

    public MagicException(String msg, int code){
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public MagicException(String msg, int code, Throwable e){
        super(msg,e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
