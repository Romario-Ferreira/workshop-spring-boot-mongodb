package com.francaemp.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.francaemp.workshopmongo.dto.AuthorDTO;
import com.francaemp.workshopmongo.dto.CommentDTO;
import com.francaemp.workshopmongo.entities.Post;
import com.francaemp.workshopmongo.entities.User;
import com.francaemp.workshopmongo.repositories.PostRepository;
import com.francaemp.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post p1 = new Post(null, sdf.parse("21/03/2018"), "Bom dia", "lugar maravilhoso", new AuthorDTO(maria));
		Post p2 = new Post(null, sdf.parse("23/03/2018"), "amor", "eu amo alguem", new AuthorDTO(maria));
	
		p1.getComments().add(new CommentDTO("Boa viagem", new Date(), new AuthorDTO(bob)));
		p1.getComments().add(new CommentDTO("Aproveite", new Date(), new AuthorDTO(alex)));
		p2.getComments().add(new CommentDTO("aaah o amor", new Date(), new AuthorDTO(alex)));

		
		postRepository.saveAll(Arrays.asList(p1,p2));
		maria.setPosts(p1);
		maria.setPosts(p2);
		userRepository.save(maria);
		
		
		
	}

}
