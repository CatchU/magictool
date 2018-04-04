package com.magic.result;

import java.util.List;

/**
* 用于封装返回集合类型的数据
* @author ljz on 2018/4/4 0004
*/
public class ReturnListValue<E> {

    private List<E> result;

    private Long totalCount;

    public ReturnListValue() {
    }

    public ReturnListValue(List<E> result) {
        this.result = result;
    }

    public ReturnListValue(Long totalCount) {
        this.totalCount = totalCount;
    }

    public ReturnListValue(List<E> result, Long totalCount) {
        this.result = result;
        this.totalCount = totalCount;
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ReturnListValue{");
        sb.append("result=").append(result);
        sb.append(", totalCount=").append(totalCount);
        sb.append('}');
        return sb.toString();
    }
}
