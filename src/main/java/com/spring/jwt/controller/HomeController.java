package com.spring.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/welcome")
	
	public String welcome() {
		String text="You are trying to access the secured content, ";
		text= text+" It cannot be accessed by the unaurthorized users";
		return text;
	}
}
