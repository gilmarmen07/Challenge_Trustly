package com.trustly.challenge.countLine.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trustly.challenge.countLine.model.GithubFile;

public interface GithubUserGroupRepository extends MongoRepository<GithubFile, Long>{
	
}
