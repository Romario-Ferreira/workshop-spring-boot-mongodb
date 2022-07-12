package com.francaemp.workshopmongo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francaemp.workshopmongo.dto.UserDTO;
import com.francaemp.workshopmongo.entities.User;
import com.francaemp.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserService service;
	
	
	@GetMapping
	public ResponseEntity <List<UserDTO>>findAll(){
		List<User> users = service.findAll();
		List<UserDTO> dtos = users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}

}
