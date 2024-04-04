package com.sample.app.controller;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/basic-auth")
public class BasicAuthController {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "password123";

	@Autowired
	private HttpServletRequest httpRequest;

	@GetMapping
	public ResponseEntity<String> validate() {

		String authHeader = httpRequest.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Basic ")) {
			// Extract credentials from the Authorization header
			String base64Credentials = authHeader.substring("Basic ".length()).trim();
			// Decode base64 string to get username and password
			String credentials = new String(Base64.getDecoder().decode(base64Credentials));
			// Now 'credentials' should contain 'username:password' string
			String[] credentialsArray = credentials.split(":", 2);
			String username = credentialsArray[0];
			String password = credentialsArray[1];
			
			if(USERNAME.equals(username) && PASSWORD.equals(password)) {
				// Do whatever you need to do with the extracted credentials
				return ResponseEntity.ok("Credentials are valid!!!!!!");
			}
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Invalid credentials");
			
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Credentials are not passed in Authorization header");
		}

	}
}
