package com.trustly.challenge.countLine.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.trustly.challenge.countLine.model.GithubFile;

public class GithubUserGroupDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4025797233700904893L;

	private String user;
	private String name;
	private long quantLines;
	private long size;
	private String extension;


	public GithubUserGroupDTO(String user, String name) {
		this.user = user;
		this.name = name;
	}
	
	public GithubUserGroupDTO() {
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public long getQuantLines() {
		return quantLines;
	}

	public void setQuantLines(long quantLines) {
		this.quantLines = quantLines;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public List<GithubUserGroupDTO> toList(List<GithubFile> listObj, String user, String name) {
		List<GithubUserGroupDTO> listDto = new ArrayList<GithubUserGroupDTO>();
		
		Map<String, Long> resultQuantLines = listObj.stream().collect(Collectors.groupingBy(GithubFile::getExtension, Collectors.summingLong(GithubFile:: getQuantLines)));
		Map<String, Long> resultSise = listObj.stream().collect(Collectors.groupingBy(GithubFile::getExtension, Collectors.summingLong(GithubFile:: getSize)));
		
		for(String extension : resultQuantLines.keySet()){			
			GithubUserGroupDTO githubUserGroupDTO = new GithubUserGroupDTO(user, name);
			githubUserGroupDTO.setQuantLines(resultQuantLines.get(extension));
			githubUserGroupDTO.setSize(resultSise.get(extension));
			githubUserGroupDTO.setExtension(extension);			
			
			listDto.add(githubUserGroupDTO);
		}
		
		return listDto;
	}
}
