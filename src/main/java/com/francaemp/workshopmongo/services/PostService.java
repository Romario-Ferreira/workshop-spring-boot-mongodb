package com.francaemp.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francaemp.workshopmongo.dto.PostDTO;
import com.francaemp.workshopmongo.entities.Post;
import com.francaemp.workshopmongo.repositories.PostRepository;
import com.francaemp.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	PostRepository repository;

	public List<Post> findAll(){
		return repository.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Post insert(Post obj) {
		return repository.insert(obj);
	}
	
	public void deleteById(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Post update(Post obj) {
		return repository.save(obj);
	}

	public Post fromDTO(PostDTO objDTO) {
		return new Post(objDTO.getId(),objDTO.getDate(),objDTO.getTitle(), objDTO.getBody(),objDTO.getAuthor());
	}
}
