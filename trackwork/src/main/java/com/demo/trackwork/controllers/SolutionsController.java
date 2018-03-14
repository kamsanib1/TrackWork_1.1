package com.demo.trackwork.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.trackwork.entities.Solutions;
import com.demo.trackwork.services.ISolutionsService;

@RestController
@RequestMapping("api/solutions")
public class SolutionsController {

	@Autowired
	ISolutionsService solutionsService;
	
	@GetMapping("/{record_id}")
	public ResponseEntity<Collection<Solutions>> getSolutions(@PathVariable("record_id") int recordID){
		Collection<Solutions> solutions = (Collection<Solutions>)solutionsService.readByRecord(recordID);
		return new ResponseEntity<Collection<Solutions>>(solutions,HttpStatus.OK);
	}
	
	@PostMapping("/{record_id}")
	public ResponseEntity<String> createSolution(@RequestBody Solutions solution, 
												 @PathVariable("record_id") int recordID){
		solutionsService.create(solution, recordID);
		return new ResponseEntity<String>("new solution created", HttpStatus.CREATED);
	}

	@PutMapping("/{solution_id}")
	public ResponseEntity<String> updateSolution(@RequestBody Solutions solution, 
												 @PathVariable("solution_id") int solutionID){
		solution.setId(solutionID);
		solutionsService.update(solution);
		return new ResponseEntity<String>("updated solution.", HttpStatus.ACCEPTED);
	}
}
