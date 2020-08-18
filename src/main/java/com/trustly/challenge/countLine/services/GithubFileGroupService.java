package com.trustly.challenge.countLine.services;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trustly.challenge.countLine.model.GithubFile;
import com.trustly.challenge.countLine.repository.GithubFileRepository;
import com.trustly.challenge.countLine.utils.ZipUtils;

import org.springframework.data.domain.Example;

@Service
public class GithubFileGroupService {
	
	@Autowired
	private GithubFileRepository githubFileRepository;
	
	public List<GithubFile> findAll() {
		return githubFileRepository.findAll();
	}
	
	//Load repository by name and user from database
	public List<GithubFile> loadFromRepository(String user, String name) {
		GithubFile filter = new GithubFile(user, name);
		Example<GithubFile> example = Example.of(filter);
		return githubFileRepository.findAll(example);
	}

	//Find repository by name and user from GitHub and insert in database 
	public List<GithubFile> findByRepository(String user, String name) {
		List<GithubFile> repository = loadFromRepository(user, name);		
		if (repository.size() == 0) {
			repository = this.serch(user, name);
		}	
		return repository;
	}

	private List<GithubFile> serch(String user, String name) {
		List<GithubFile> list = new ArrayList<GithubFile>();	
		try {
			URL url = new URL("https://github.com/"+user+"/"+name+"/archive/master.zip");    
	
			list = ZipUtils.readZipStream(url, user, name);       

			githubFileRepository.insert(list);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
