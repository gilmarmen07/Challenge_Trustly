package com.trustly.challenge.countLine.services;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trustly.challenge.countLine.exception.ObjectNotFoundException;
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
	
	public GithubFile findById(Long id) {
		Optional<GithubFile> obj = githubFileRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Repository not found"));
	}

	public GithubFile insert(GithubFile obj) {
		return githubFileRepository.insert(obj);
	}
	
	public void delete(Long id) {
		this.findById(id);
		githubFileRepository.deleteById(id);
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
	
			list = ZipUtils.readZipStream(url);       

			githubFileRepository.insert(list);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
