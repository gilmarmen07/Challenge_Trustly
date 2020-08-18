package com.trustly.challenge.countLine.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.trustly.challenge.countLine.model.GithubFile;

public interface GithubFileRepository extends MongoRepository<GithubFile, Long>{
	
}
