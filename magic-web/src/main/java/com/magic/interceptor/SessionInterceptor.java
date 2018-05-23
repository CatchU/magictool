package com.magic.interceptor;

import com.magic.annotation.NoLogin;
import com.magic.constant.ResultCode;
import com.magic.domain.User;
import com.magic.exception.BusinessException;
import com.magic.util.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.AbstractController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * session验证拦截器  <br>
 * <br>
 * 判断是否已登录，加NoLogin的除外<br>
 * <br>
 * <br>
 * @author 刘俊重
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	@Autowired
	private EhCacheCacheManager cacheManager;

	/**
	 * @Description 自定义session拦截校验
	 * @Author 刘俊重
	 */
	private boolean preHandle(HttpServletRequest request,
							  HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {

        /**判断是否包含noLogin注解*/
        if (null != handlerMethod.getMethodAnnotation(NoLogin.class)) {
            return true;    //直接放行
        }
		String token = CookieUtils.getCookieValue(request, "缓存");
        logger.info("拦截器中的token>>>>>>>>>>>>>>>"+token);
        if(token == null ) {
        	throw new BusinessException(ResultCode.INVALID_TOKEN);
        }
        Cache cache = cacheManager.getCache("缓存");
        if(null==cache){
			throw new BusinessException(ResultCode.INVALID_TOKEN);
        }

        ValueWrapper valueWrapper = cache.get(token);
        if(null==valueWrapper){
			throw new BusinessException(ResultCode.INVALID_TOKEN);
		}

		Object obj = valueWrapper.get();
        if(null==obj){
			throw new BusinessException(ResultCode.INVALID_TOKEN);
		}

		if (obj instanceof User) {

		}else{
        	//任何用户信息都未获取到
			throw new BusinessException(ResultCode.INVALID_TOKEN);
		}

		return true;
	}

	/***
	 * 访问后台前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) throws Exception {

		if(handler instanceof HandlerMethod  ){
			if(!(((HandlerMethod)handler).getBean() instanceof AbstractController)){
				throw new Exception("Controle doesn't inherit from AbstractController!!!");
			}

			try{
				AbstractController baseCtrl = (AbstractController) ((HandlerMethod) handler).getBean();
				HandlerMethod handlerMethod = (HandlerMethod)handler;
				if (preHandle(request, response, handlerMethod)){
					return super.preHandle(request, response, handler);
				}
				return false;
			}catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {


        super.postHandle(request, response, handler, modelAndView);

	}



}

