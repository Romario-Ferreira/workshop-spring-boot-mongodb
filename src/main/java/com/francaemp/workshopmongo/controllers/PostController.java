package com.francaemp.workshopmongo.controllers;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.francaemp.workshopmongo.controllers.util.URL;
import com.francaemp.workshopmongo.dto.PostDTO;
import com.francaemp.workshopmongo.entities.Post;
import com.francaemp.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
	
	@Autowired
	PostService service;
	
	
	@GetMapping
	public ResponseEntity <List<PostDTO>>findAll(){
		List<Post> users = service.findAll();
		List<PostDTO> dtos = users.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable String id){
		return ResponseEntity.ok()
				.body(new PostDTO(service.findById(id)));	
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody PostDTO objDTO){
		Post obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable String id,
									   @RequestBody PostDTO objDTO){
		Post u = service.findById(id);
		BeanUtils.copyProperties(objDTO, u);
		u.setId(id);
		service.update(u);
		return ResponseEntity.noContent().build();	
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByHeader(@RequestParam(value = "text",defaultValue = "") String title){
		title = URL.decodingTitle(title);
		return ResponseEntity.ok().body(service.findByTitle(title));
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text",defaultValue = "") String title,
			@RequestParam(value = "minDate",defaultValue = "") String minDate,
			@RequestParam(value = "maxDate",defaultValue = "") String maxDate){
		title = URL.decodingTitle(title);
		Date min = URL.decodeDate(minDate, new Date(0L));
		Date max = URL.decodeDate(maxDate, new Date());	
		return ResponseEntity.ok().body(service.fullSearch(title, min, max));
	}
}
