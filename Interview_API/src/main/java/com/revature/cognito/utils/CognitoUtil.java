package com.revature.cognito.utils;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.cognito.dtos.CognitoTokenClaims;
import com.revature.cognito.intercomm.CognitoClient;

@Component
public class CognitoUtil {

	@Value("${spring.profiles}")
	private String stage;

	private Logger logger = Logger.getRootLogger();

	@Autowired
	private CognitoClient cognitoClient;

	public List<String> getRequesterRoles() {
		String cognitoToken = getCurrentUserToken();
		
		System.out.println(cognitoToken);
		if (cognitoToken == null) {
			return null;
		}

		CognitoTokenClaims claims = cognitoClient.authenticateToken(cognitoToken);

		return Arrays.asList(claims.getGroups().split(","));
	}
	
	public String getCurrentUserToken() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		return req.getHeader(HttpHeaders.AUTHORIZATION);
	}

}
