package com.amazon.project.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.amazon.project.entity.User;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {
	
	private static String secret ="This_is_secret";
	
	public String generateJwt(User user) {
		Date issuedAt = new Date();
		
		Claims claims = Jwts.claims().setIssuer(user.getId().toString())
				.setIssuedAt(issuedAt);
		return Jwts.builder().setClaims(claims).compact();
		
		
	}

}
