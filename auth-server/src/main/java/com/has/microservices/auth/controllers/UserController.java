package com.has.microservices.auth.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping(value = "/user")
	public Principal user(Principal user) {
	    return user;
	}
}
