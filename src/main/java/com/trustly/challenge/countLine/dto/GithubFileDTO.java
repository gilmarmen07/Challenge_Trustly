package com.trustly.challenge.countLine.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.trustly.challenge.countLine.model.GithubFile;

public class GithubFileDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7070882141717156134L;

	private Long id;
	
	//Repository
	private String userGithub;
	private String nameGithub;
	
	//File
	private String description;
	private Long size;
	private Long quantLines;
	private String extension;
	
	public GithubFileDTO() {
	}
		
	public GithubFileDTO(GithubFile u) {
		this.setId(u.getId());
		this.userGithub = u.getUserGithub();
		this.nameGithub = u.getNameGithub();
		this.description = u.getDescription();
		this.size = u.getSize();
		this.quantLines = u.getQuantLines();
		this.extension = u.getExtension();
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

	public List<GithubFileDTO> toList(List<GithubFile> listObj) {
		List<GithubFileDTO> listDto = listObj.stream().map(u -> new GithubFileDTO(u)).collect(Collectors.toList()); 
		return listDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
