package com.revature.cognito.aspects;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.cognito.annotations.CognitoAuth;
import com.revature.cognito.constants.CognitoRoles;
import com.revature.cognito.utils.CognitoUtil;

@Aspect
@Component
public class CognitoAspect {

	@Value("${spring.profiles}")
	private String stage;

	@Autowired
	private CognitoUtil cUtil;

	@Pointcut(" @annotation(ca)")
	public void annotationPointCutDefinition(CognitoAuth ca) {
	}

	// Defines a pointcut that we can use in the @Before,@After, @AfterThrowing,
	// @AfterReturning,@Around specifications
	// The pointcut is a catch-all pointcut with the scope of execution
	@Pointcut("execution(* *(..))")
	public void atExecution() {
	}

	@Around("annotationPointCutDefinition(ca) && atExecution()")
	public Object CognitoAuth(ProceedingJoinPoint pjp, CognitoAuth ca) throws Throwable {
		Logger log = Logger.getRootLogger();
		List<String> currentUsersRoles = cUtil.getRequesterRoles();

		// if no roles are specified in the annotation give permission to all
		if (ca.roles().length == 0) {
			return pjp.proceed();
		}

		// make sure the user has some roles
		if (currentUsersRoles == null) {
			HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getResponse();
			resp.setStatus(401);
			resp.getWriter().write("Invalid Authentication");
			return null;
		}

		// Give admins access to everything
		if (currentUsersRoles.contains(CognitoRoles.ADMIN)) {
			return pjp.proceed();
		}

		// bypass security if stage is dev
		if (stage.equals("dev")) {
			log.info("\n Authorization 401 bypassed by Dev Route");
			return pjp.proceed();
		}

		// check to see if the user has one of the allowed roles
		for (String allowedRole : ca.roles()) {
			if (currentUsersRoles.contains(allowedRole)) {
				return pjp.proceed();

			}
		}

		HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		resp.setStatus(403);
		resp.getWriter().write("Access Forbidden, Contact Admin If You Need Elevated Permissions");
		return null;
	}

}
