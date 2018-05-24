package com.magic.exception;

import com.alibaba.fastjson.JSONObject;
import com.magic.constant.ResultCode;
import com.magic.result.ApiGeneralResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 刘俊重
 * @Description 自定义异常处理器，捕获所有throw出来的异常，
 * 包装之后显示给用户一个json类型的错误信息，避免把所有的堆栈信息都给用户
 * @date 17:14
 */
@Component
public class CustomExceptionHandler implements HandlerExceptionResolver{

    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
       HttpServletResponse response, Object handler, Exception ex) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        ApiGeneralResult result = null;
        if(ex instanceof BusinessException){
            ResultCode code = ((BusinessException) ex).getResultCode();
            logger.error("error code:{},error message:{}",code.getCodeNo(),code.getCustomerMessage());
            result = new ApiGeneralResult(code.getCodeNo(),
                    code.getCustomerMessage(),code.getDeveloperMessage());
        }else{
            result = new ApiGeneralResult(ResultCode.SYSTEM_ERROR.getCodeNo(),
                    ResultCode.SYSTEM_ERROR.getCustomerMessage(),ResultCode.SYSTEM_ERROR.getDeveloperMessage());
        }
        // write out
        try {
            PrintWriter writer = response.getWriter();
            String mess = JSONObject.toJSONString(result);
            logger.info("error mess:{}",mess);
            writer.write(mess);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error("waite error :{}",e);
        }
        return null;
    }
}
