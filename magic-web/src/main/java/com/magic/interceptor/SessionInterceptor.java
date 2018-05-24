package com.magic.interceptor;

import com.magic.annotation.NoLogin;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * session验证拦截器
 * 判断是否已登录，加NoLogin的除外,跟SysPathMatchingFilter的效果基本一样
 * @author 刘俊重
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	@Autowired
	private EhCacheCacheManager cacheManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(!(handler instanceof HandlerMethod)){
			logger.info("不是映射到方法的，直接放行");
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		NoLogin noLogin = method.getAnnotation(NoLogin.class);
		if(noLogin!=null){
			logger.info("有NoLogin注解，不需要登录，直接放行");
			return true;
		}
		String token = request.getHeader("token");
		if(StringUtils.isEmpty(token)){
			logger.error("请求没有token，直接拒绝");
			return false;
		}
		//模拟从后台缓存中拿token进行比对
		String mock_token = "63284739";
		if(!token.equals(mock_token)){
			logger.error("token过期，请重新登录");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
						   Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}

