package com.aeCoder.project3ae.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	@GetMapping("/user")
	public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User user) {
		if (user == null) {
            throw new RuntimeException("User not authenticated"); // Ngăn lỗi null
        }
		return user.getAttributes();
	}
}
