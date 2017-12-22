package com.pulan.shiro;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.pulan.utils.LdapHelper;


public class CredentialsMatcher extends SimpleCredentialsMatcher{
	private Logger logger = Logger.getLogger(CredentialsMatcher.class.getName());
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//		String inPassword = "";
//		// TODO Auto-generated method stub
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;
		return LdapHelper.authenticate(utoken.getUsername(),String.valueOf(utoken.getPassword()));
	}
}
