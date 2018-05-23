package com.magic.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.annotation.LogParam;
import com.magic.constant.ResultCode;
import com.magic.domain.Log;
import com.magic.exception.BusinessException;
import com.magic.request.BaseRequest;
import com.magic.result.ApiGeneralListResult;
import com.magic.result.ApiGeneralResult;
import org.apache.shiro.util.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 刘俊重
 * @Description 日志切面
 * @date 14:51
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
            10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    /**
     * javabean validation工厂
     */
    ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
    /**
     * 定义一个切入点，此函数没有返回值，只是标识这是一个切入点
     */
    @Pointcut("execution(* com.magic.controller..*.*(..))")
    public void logPointCut(){
        logger.info("我是日志切入点");
    }

    /**
     * 使用环绕方式处理日志。
     * aop中的切面处理方式包括前置，后置，环绕和后置返回通知，后置通知异常通知
     * @param point
     * @return
     */
    @Around("logPointCut()")
    public Object handleLog(ProceedingJoinPoint point) throws Throwable {
        paramCheck(point);
        Log log = new Log();
        //下面的方法是为了插入日志表中。获取切面表达式拦截的那个方法签名
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        //获取切面表达式拦截的方法
        Method method = methodSignature.getMethod();
        //根据RequestMapping注解拿到这个方法的接口名和方法描述
        RequestMapping mapping = method.getAnnotation(RequestMapping.class);
        if(null!=mapping){
            String desc = mapping.name();  //描述信息
            String[] value = mapping.value();//接口名
            log.setLogDesc(desc);
            log.setLogInterface(JSONObject.toJSONString(value));
        }

        StringBuilder sb = new StringBuilder();
        //获取请求的参数,并遍历获取
        Object[] args = point.getArgs();
        Arrays.asList(args).stream().forEach(param -> {
            if(param!=null && param.getClass().getAnnotation(LogParam.class)!=null){
                //包含LogParam注解
                sb.append(JSONObject.toJSONString(param));
            }else if(param instanceof BaseRequest){
                sb.append(JSONObject.toJSON(param));
            }else if(param instanceof HttpServletRequest){
                sb.append(JSONObject.toJSON(param));
            }else if(param instanceof Map){
                sb.append(JSONObject.toJSON(param));
            }else if (param instanceof String) {
                sb.append(JSON.toJSONString(param));
            }
        });

        //请求参数
        String requestParam = sb.toString();
        log.setLogArgs(requestParam);
        //执行程序
        Object proceed = point.proceed();
        if(proceed instanceof ApiGeneralResult ||
                proceed instanceof ApiGeneralListResult){
            //执行结果
            int code = ((ApiGeneralResult) proceed).getCode();
            log.setResult(code);
        }else{
            throw new BusinessException(ResultCode.ILLEGAL_RESPONSE);
        }
        log.setId("54324238");
        log.setUserId("1"); //生产环境中获取登录用户id
        log.setCreateTime(new Date());
        //启动线程异步插入日志表
        asynchInsertLog(log);
        return proceed;
    }

    /**
     * 在切面中顺便调用hibernate-validation方法对参数进行校验
     * @param point
     */
    private void paramCheck(ProceedingJoinPoint point){
        Validator validator = validationFactory.getValidator();
        Set<ConstraintViolation<Object>> validateSet = validator.validate(point.getArgs()[0]);
        if(CollectionUtils.isEmpty(validateSet)){
            return;
        }
        //如果有多条数据只取第一条
        ConstraintViolation<Object> constraintViolation = validateSet.iterator().next();
        if(constraintViolation==null){
            return;
        }
        String message = constraintViolation.getMessage();
        throw new BusinessException(ResultCode.PARAMETER_VALID_ERROR,new String[]{message});
    }

    /**
     * 起一个线程异步的插入到日志表
     */
    private void asynchInsertLog(Log log){
        try {
            InsertLogTask task = new InsertLogTask(log);
            poolExecutor.execute(task);
        } catch (Exception e) {
            logger.error("错误信息：{}",e);
            throw new BusinessException(ResultCode.INSERT_LOG_ERROR);
        }
    }

    /**
     * 实际执行插入任务的线程
     */
    class InsertLogTask implements Runnable{

        private Log log;

        public InsertLogTask(Log log) {
            this.log = log;
        }

        @Override
        public void run(){
            try {
                //测试异步
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //模拟插入操作
            logger.info("将日志信息存储到数据库了：{}",JSONObject.toJSONString(log));
        }
    }
}
