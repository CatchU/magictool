package com.magic.result;

import java.util.List;

public class ApiGeneralListResult<T> extends ApiGeneralResult<ReturnListValue<T>> {

    public ApiGeneralListResult() {
    }

    public ApiGeneralListResult(int code) {
        super(code);
    }

    public ApiGeneralListResult(int code, ReturnListValue<T> value, ReturnMessage message) {
        super(code, value, message);
    }

    public ApiGeneralListResult(int code, String cust, String dev) {
        super(code, cust, dev);
    }

    public void setValue(List<T> value, Long totalCount){
        super.setValue(new ReturnListValue<T>(value,totalCount));
    }

    public void setValue(List<T> value) {
        super.setValue(new ReturnListValue<T>(value,null));
    }


}
