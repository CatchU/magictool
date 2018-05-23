package com.magic.result;

/**
* 封装返回结果
* @author ljz on 2018/4/4
*/
public class ApiGeneralResult<T> {

    private int code = 0;

    private T value;

    private ReturnMessage message;

    public ApiGeneralResult() {
    }

    public ApiGeneralResult(int code) {
        this.code = code;
    }

    public ApiGeneralResult(T value) {
        this.value = value;
    }
    public ApiGeneralResult(int code, T value, ReturnMessage message) {
        this.code = code;
        this.value = value;
        this.message = message;
    }

    public ApiGeneralResult(int code, String cust, String dev) {
        this.code = code;
        this.message = new ReturnMessage(cust, dev);
    }

    /**
    * 包括给开发人员看的和给用户看的错误信息，用静态内部类封装
    */
    public static class ReturnMessage{

        private String cust;

        private String dev;

        public ReturnMessage() {
        }

        public ReturnMessage(String cust, String dev) {
            this.cust = cust;
            this.dev = dev;
        }

        public String getCust() {
            return cust;
        }

        public void setCust(String cust) {
            this.cust = cust;
        }

        public String getDev() {
            return dev;
        }

        public void setDev(String dev) {
            this.dev = dev;
        }
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ReturnMessage getMessage() {
        return message;
    }

    public void setMessage(ReturnMessage message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ApiGeneralResult{");
        sb.append("code=").append(code);
        sb.append(", value=").append(value);
        sb.append(", message=").append(message);
        sb.append('}');
        return sb.toString();
    }
}
