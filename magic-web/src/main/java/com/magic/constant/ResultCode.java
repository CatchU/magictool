package com.magic.constant;

import java.text.MessageFormat;

/**
 *
 * @author xuanyi.sxy
 * @date 18/2/28
 */
public enum ResultCode {
    // 全局返回码
    SUCCESS(0, "成功", "成功"),
    INVALID_TOKEN(300, "登录超时，请重新登录", "登录超时，请重新登录"),
    ILLEGAL_REQUEST(301, "非法的请求", "非法的请求，没有继承BaseRequest请求基类"),
    NO_DATA_PERMISSION(302, "无此数据权限", "无此数据权限"),
    NO_PERMISSION(303, "无权限", "无权限"),
    ILLEGAL_RESPONSE(304, "非法的响应", "非法的响应，不符合响应规范"),

    PARAMETER_FORMAT_ERROR(400, "请求参数格式不正确", "请求参数格式不正确"),
    PARAMETER_EMPTY(401, "参数 {0} 不能为空", "参数 {0} 不能为空"),
    ILLEGAL_ID(410, "非法ID", "非法ID"),
    PARAMETER_VALID_ERROR(422, "请求参数校验不通过 {0}", "请求参数校验不通过 {0}"),
    SYSTEM_ERROR(500, "系统异常", "系统异常"),

    LOGIN_STATE_EXPIRE(501,"登录已过期","登录已过期"),
    // 共用返回码，前两位为：00
    AMOUNT_INTEGER_PART_15(1, "金额整数部分不能大于15位", "金额整数部分不能大于15位"),
    VERIFY_CODE_ERROR(2, "验证码错误", "验证码错误"),
    ACCOUNTING_ERROR(3, "记账错误", "记账错误"),
    ILLEGAL_SRC_PLATFORM_TOKEN(4, "非法来源平台token", "非法来源平台token"),

    // 业务模块返回码
    // 商家后台
    INSERT_LOG_ERROR(10001,"插入日志时出现异常","插入日志时出现异常"),


    ;
    /**
     * 返回码编号，除了全局返回码之外，用6位数字表示。前两位表示业务模块。
     */
    private int codeNo;

    /**
     * 展示给用户的提示信息
     */
    private String customerMessage;

    /**
     * 给开发人员看的提示信息
     */
    private String developerMessage;

    ResultCode(int codeNo, String customerMessage, String developerMessage) {
        this.codeNo = codeNo;
        this.customerMessage = customerMessage;
        this.developerMessage = developerMessage;
    }

    public int getCodeNo() {
        return this.codeNo;
    }

    public String getCustomerMessage() {
        return this.customerMessage;
    }

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public String getDeveloperMessage(Object[] args) {
        String result = developerMessage;
        if (args != null && args.length > 0) {
            result = MessageFormat.format(result, args);
        }
        return result;
    }
}

