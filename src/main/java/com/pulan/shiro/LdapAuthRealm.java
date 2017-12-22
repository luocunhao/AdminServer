package com.pulan.shiro;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;



public class LdapAuthRealm extends AuthorizingRealm{
	private Logger logger = Logger.getLogger(LdapAuthRealm.class.getName());
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		logger.info("身份认证登录");
		UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
		String userName = authcToken.getUsername();
		String password = String.valueOf(authcToken.getPassword());
        return new SimpleAuthenticationInfo(userName, password, getName());
		}
	

}
