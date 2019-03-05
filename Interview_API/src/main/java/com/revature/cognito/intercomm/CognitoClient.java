package com.revature.cognito.intercomm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.revature.cognito.dtos.CognitoRegisterBody;
import com.revature.cognito.dtos.CognitoRegisterResponse;
import com.revature.cognito.dtos.CognitoTokenClaims;

@FeignClient(name = "cognito-service", url = "${cognito.url}")
public interface CognitoClient {
	@GetMapping("/cognito/auth")
	CognitoTokenClaims authenticateToken(@RequestHeader("Authorization") String token);

	@PostMapping("/cognito/users")
	CognitoRegisterResponse registerUser(@RequestHeader("x-api-key") String apiKey, CognitoRegisterBody body);

}
