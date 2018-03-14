package com.demo.trackwork.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.trackwork.entities.Solutions;
import com.demo.trackwork.entities.Tags;
import com.demo.trackwork.entities.TestPOJO;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
public class HomeController {
	
	@PostMapping("test/post")
	public ResponseEntity<String> processPost(@RequestBody String json){
		
		System.out.println("HomeController: Post Test: RequestBody: "+json);
		return new ResponseEntity<String>("succesfull",HttpStatus.CREATED);
		
	}
	
	@PostMapping("test/post/tag")
	public ResponseEntity<String> processTagPost(@RequestBody Tags tag){
		
		
		return new ResponseEntity<String>("tag posted successfully",HttpStatus.CREATED);
		
	}
	
	@PostMapping("test/post/solution")
	public ResponseEntity<String> processSolutionPost(@RequestBody Solutions solution){
		
		
		return new ResponseEntity<String>("solution posted successfully",HttpStatus.CREATED);
		
	}
	
	@PostMapping("test/post/pojo")
	public ResponseEntity<String> testPOJO(@RequestBody TestPOJO tag){
		
		
		return new ResponseEntity<String>("pojo test successful",HttpStatus.CREATED);
		
	}
}
