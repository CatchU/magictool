package com.magic.domain;

import java.util.Date;

/**
 * @author 刘俊重
 * @Description 日志表
 * @date 16:29
 */
public class Log {
    /**
     * 日志编号
     */
    private String id;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 操作结果（00000000表示成功，否则为错误代码）
     */
    private int result;

    /**
     * 操作描述
     */
    private String logDesc;

    /**
     * 操作接口
     */
    private String logInterface;

    /**
     * 操作参数
     */
    private String logArgs;

    /**
     * 操作时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getLogDesc() {
        return logDesc;
    }

    public void setLogDesc(String logDesc) {
        this.logDesc = logDesc;
    }

    public String getLogInterface() {
        return logInterface;
    }

    public void setLogInterface(String logInterface) {
        this.logInterface = logInterface;
    }

    public String getLogArgs() {
        return logArgs;
    }

    public void setLogArgs(String logArgs) {
        this.logArgs = logArgs;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
