package com.trustly.challenge.countLine.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GithubFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	//Repository
	private String userGithub;
	private String nameGithub;
	
	//File
	private String description;
	private Long size;
	private Long quantLines;
	private String extension;
	
	public GithubFile() {
	}
	
	public GithubFile(String userGithub, String nameGithub) {
		this.userGithub = userGithub;
		this.nameGithub = nameGithub;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getSize() {
		return size;
	}
	
	public void setSize(Long size) {
		this.size = size;
	}
	
	public Long getQuantLines() {
		return quantLines;
	}
	
	public void setQuantLines(Long quantLines) {
		this.quantLines = quantLines;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}


	public String getUserGithub() {
		return userGithub;
	}

	public void setUserGithub(String userGithub) {
		this.userGithub = userGithub;
	}

	public String getNameGithub() {
		return nameGithub;
	}

	public void setNameGithub(String nameGithub) {
		this.nameGithub = nameGithub;
	}
}
