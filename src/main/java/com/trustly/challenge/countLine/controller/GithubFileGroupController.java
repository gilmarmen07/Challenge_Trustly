package com.trustly.challenge.countLine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trustly.challenge.countLine.dto.GithubFileDTO;
import com.trustly.challenge.countLine.dto.GithubUserGroupDTO;
import com.trustly.challenge.countLine.model.GithubFile;
import com.trustly.challenge.countLine.services.GithubFileGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/githubFileGroups")
@Api(value="/githubFileGroups",  tags="githubFileGroups")
public class GithubFileGroupController {
	
	@Autowired
	private GithubFileGroupService githubFileService;
	
	@ApiOperation(value="Load all repository from a database")
	@GetMapping
	public ResponseEntity<List<GithubFileDTO>> loadAll() {
		List<GithubFile> list = githubFileService.findAll();
		List<GithubFileDTO> listDto = new GithubFileDTO().toList(list);
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value="Load repository by ID")
	@GetMapping(value="/{id}")
	public ResponseEntity<GithubFileDTO> loadById(@PathVariable("id") Long id) {
		GithubFile obj = githubFileService.findById(id);		
		return ResponseEntity.ok().body(new GithubFileDTO(obj));
	}
	
	@ApiOperation(value="Delete repository by id from database")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		githubFileService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Find repository by name and user from GitHub and insert in buffer ")
	@GetMapping(value="/find")
	public ResponseEntity<List<GithubUserGroupDTO>> findByRepository(@RequestParam(name = "user") String user, @RequestParam(name = "name") String name) {
		List<GithubFile> githubUserGroup = githubFileService.findByRepository(user, name);		
		List<GithubUserGroupDTO> listDto = new GithubUserGroupDTO().toList(githubUserGroup, user, name);
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value="Load repository by name and user from database")
	@GetMapping(value="/load")
	public ResponseEntity<List<GithubUserGroupDTO>> loadByRepository(@RequestParam(name = "user") String user, @RequestParam(name = "name") String name) {
		List<GithubFile> githubUserGroup = githubFileService.loadFromRepository(user, name);		
		List<GithubUserGroupDTO> listDto = new GithubUserGroupDTO().toList(githubUserGroup, user, name);
		return ResponseEntity.ok().body(listDto);
	}
	

}
