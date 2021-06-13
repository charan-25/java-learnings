package com.spring.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jwt.helper.JwtUtil;
import com.spring.jwt.model.JwtRequest;
import com.spring.jwt.model.JwtResponse;
import com.spring.jwt.service.CustomUserDetailsService;

@RestController
public class JwtController {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest request) throws Exception{
		System.out.println(request);
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}catch(BadCredentialsException ex) {
			ex.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		UserDetails userName = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtUtil.generateToken(userName);
		System.out.println(token);
		
		//we should return this token by converting it into Json format
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
