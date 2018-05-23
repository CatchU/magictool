package com.magic.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 刘俊重
 * @Description 自定义realm实现用户权限的认证和授权
 * @date 11:38
 */
public class UserRealm extends AuthorizingRealm {

    private final  static Logger logger = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 认证
     * 从AuthenticationToken中获取的身份信息和凭证信息，之后查数据库进行鉴权
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        //模拟从数据库从查用户信息
        if(!"aa".equals(username) ||!"aa".equals(password)){
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());
        return info;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("这是授权方法");
        return null;
    }
}
