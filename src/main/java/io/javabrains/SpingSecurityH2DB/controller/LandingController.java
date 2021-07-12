package io.javabrains.SpingSecurityH2DB.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {

	@GetMapping("/")
	public String home() {

		return "Welcome Home ..!!";
	}

	@GetMapping("/admin")
	public String admin() {
		return "Welcome to Admin Page ..!!";
	}

	@GetMapping("/user")
	public String user() {
		return "Welcome User Page ..!!";
	}
	
	@GetMapping("/logoutSuccess")
	public String logoutSuccess() {
		return "Welcome Logout Page ..!!";
	}
	
	@GetMapping("/unauthorized")
	public String unauthorized() {
		return "UnAthorized Access to Page ..!!";
	}
}
