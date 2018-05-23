package com.magic.exception;

import com.magic.constant.ResultCode;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
/**
 * 自定义异常处理器
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -7393203417303653279L;
    /**
     * 返回码
     */
    private final ResultCode resultCode;

    /**
     * ResultCode中customerMessage的占位符参数
     */
    private final Object[] msgArgs;

    /**
     * 异常额外信息，主要用户记录异常发生时的上下文信息，有助于错误排查
     */
    private final Map<Object, Object> context = new HashMap<>();

    public BusinessException(ResultCode resultCode) {
        this(null, resultCode, null);
    }

    public BusinessException(ResultCode resultCode, Object[] msgArgs) {
        this(null, resultCode, msgArgs);
    }

    public BusinessException(Throwable cause, ResultCode resultCode) {
        this(cause, resultCode, null);
    }

    public BusinessException(Throwable cause, ResultCode resultCode, Object[] msgArgs) {
        super(resultCode.getDeveloperMessage(msgArgs), cause);
        this.resultCode = resultCode;
        this.msgArgs = msgArgs;
    }

    /**
     * 设置额外信息中的值
     *
     * @param key
     * @param value
     * @return
     */
    public BusinessException setContextEntry(Object key, Object value) {
        context.put(key, value);
        return this;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public Object[] getMsgArgs() {
        return msgArgs;
    }

    public Map<Object, Object> getContext() {
        return context;
    }


    public String getResolvedCustomerMessage() {
        if (resultCode == null) {
            return "";
        }
        return replacePlaceHolder(resultCode.getDeveloperMessage(), msgArgs);
    }

    public String getResolvedDeveloperMessage() {
        if (resultCode == null) {
            return "";
        }
        return replacePlaceHolder(resultCode.getCustomerMessage(), msgArgs);
    }

    private String replacePlaceHolder(String message, Object[] args) {
        String result = message;
        if (args != null && args.length > 0) {
            result = MessageFormat.format(message, args);
        }
        return result;
    }
}
