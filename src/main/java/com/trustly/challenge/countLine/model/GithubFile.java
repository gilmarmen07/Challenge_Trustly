package com.trustly.challenge.countLine.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table(name = "githubfile")
public class GithubFile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3951368993439078031L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

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

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GithubFile other = (GithubFile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
