package com.francaemp.workshopmongo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francaemp.workshopmongo.entities.User;
import com.francaemp.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserService service;
	
	
	@GetMapping
	public ResponseEntity <List<User>>findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}
