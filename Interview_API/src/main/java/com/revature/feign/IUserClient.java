package com.revature.feign;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.cognito.annotations.CognitoAuth;
import com.revature.cognito.constants.CognitoRoles;
import com.revature.models.User;

@FeignClient(name="user-service", url="http://localhost:8765/users")
public interface IUserClient {

	//@CognitoAuth(roles = { "staging-manager" })
	@GetMapping
	String findAll();

	@GetMapping("{id}")
	public User findById(@PathVariable int id);

	@GetMapping(path = "email/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable("email") String email);

	@CognitoAuth(roles = { CognitoRoles.STAGING_MANAGER, CognitoRoles.TRAINER })
	@GetMapping("cohorts/{id}")
	public List<User> findAllByCohortId(@PathVariable int id);

	@CognitoAuth(roles = { "staging-manager" })
	@PostMapping
	public User save(@RequestBody User u);
}
