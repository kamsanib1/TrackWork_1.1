package com.demo.trackwork.controllers;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.trackwork.entities.Tags;
import com.demo.trackwork.services.ITagsService;

@RestController
@RequestMapping("api/tags")
public class TagsController {

	@Autowired
	ITagsService tagsService;
	
	@GetMapping("")
	public ResponseEntity<Collection<Tags>> getTags(){
		Collection<Tags> tags = tagsService.readAll();
		
		return new ResponseEntity<Collection<Tags>>(tags,HttpStatus.OK);
	}
	
	@GetMapping("/{record_id}")
	public ResponseEntity<Collection<Tags>> getTagsByUsername(@PathVariable("record_id") int recordID){
		Collection<Tags> tags = tagsService.readAll(recordID);
		
		return new ResponseEntity<Collection<Tags>>(tags,HttpStatus.OK);
	}
	
	@PostMapping("{username}")
	public ResponseEntity<String> createTag(@RequestBody Tags tag, 
											@PathVariable("username") String username){
		if(tagsService.create(tag, username)==0)
		return new ResponseEntity<String>("tag created.", HttpStatus.CREATED);
		return new ResponseEntity<String>("tag creation failed.", HttpStatus.EXPECTATION_FAILED);
	}
	
	@DeleteMapping("{username}/{tagname}")
	public ResponseEntity<String> deleteTag(@PathVariable("username") String username,
											@PathVariable("tagname") String tagname){
		if(tagsService.delete(username, tagname)==0)
		return new ResponseEntity<String>("deleted tag.", HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("tag not found.", HttpStatus.EXPECTATION_FAILED);
		
	}
	
	
}
