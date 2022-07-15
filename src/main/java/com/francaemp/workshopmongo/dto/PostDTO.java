package com.francaemp.workshopmongo.dto;

import java.io.Serializable;
import java.util.Date;

import com.francaemp.workshopmongo.entities.Post;


public class PostDTO implements Serializable{
static final long serialVersionUID = 1L;

	private String id;
	private Date date;
	private String title;
	private String body;
	private AuthorDTO author;

	public PostDTO() {
	}

	public PostDTO(Post post) {
		id = post.getId();
		date = post.getDate();
		title = post.getTitle();
		body = post.getBody();
		author = post.getAuthor();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	
	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
	
}
