package com.trustly.challenge.countLine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trustly.challenge.countLine.model.GithubFile;

@Repository
public interface GithubFileRepository extends JpaRepository<GithubFile, Long>{
	
}
