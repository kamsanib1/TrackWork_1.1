package com.demo.trackwork.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.trackwork.RecordsColumn;
import com.demo.trackwork.entities.Records;
import com.demo.trackwork.entities.Tags;
import com.demo.trackwork.entities.Users;
import com.demo.trackwork.services.IRecordsService;

@RestController
@RequestMapping("api/records")
public class RecordsController {

	@Autowired
	IRecordsService recordsService;
	
	@GetMapping("/{username}")
	protected ResponseEntity<Collection<Records>> get(@PathVariable("username") String username) {
		Collection<Records> res = recordsService.read(username);
		return new ResponseEntity<Collection<Records>>(res,HttpStatus.OK);
	}
	
	@GetMapping("/{username}/{tagname}")
	protected ResponseEntity<Collection<Records>> get(@PathVariable("username") String username,
														@PathVariable("tagname") String tagname) {
		Collection<Records> res = recordsService.read(username,tagname);
		return new ResponseEntity<Collection<Records>>(res,HttpStatus.OK);
	}
	
	@PostMapping("/{username}")
	protected ResponseEntity<String> create(@RequestBody Records record,
											@PathVariable("username") String username){
		String res = "{'res':'new record created.'}";
		System.out.println("controller:creating new record...");
		if(recordsService.create(record)!=0)
			res = "{'res':'cannot add the user'}";
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
	
	@DeleteMapping("/{record_id}")
	protected ResponseEntity<String> delete(@PathVariable("record_id") int recordID){
		if(recordsService.delete(recordID)==0)
		return new ResponseEntity<String>("record deleted sucessfully", HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("record not found.", HttpStatus.EXPECTATION_FAILED);
	}
	
	@PutMapping("/{record_id}/query")
	protected ResponseEntity<String> updateQuery(@RequestBody Records record, 
													@PathVariable("record_id") int recordID){
		if(recordsService.update(record, recordID, RecordsColumn.QUERY)==0)
		return new ResponseEntity<String>("query updated sucessfully", HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("query failed to update", HttpStatus.EXPECTATION_FAILED);
	}
	
	@PutMapping("/{record_id}/topic")
	protected ResponseEntity<String> updateTopic(@RequestBody Records record, 
													@PathVariable("record_id") int recordID){
		
		if(recordsService.update(record, recordID, RecordsColumn.TOPIC)==0)
		return new ResponseEntity<String>("topic updated sucessfully", HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("topic failed to update", HttpStatus.EXPECTATION_FAILED);
	}

	@PutMapping("/{record_id}/views")
	protected ResponseEntity<String> updateViews(@RequestBody Records record, 
													@PathVariable("record_id") int recordID){
		
		if(recordsService.update(record, recordID, RecordsColumn.VIEWS)==0)
		return new ResponseEntity<String>("views updated sucessfully", HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("views failed to update", HttpStatus.EXPECTATION_FAILED);
	}

	@PutMapping("/{record_id}/priority")
	protected ResponseEntity<String> updatePriority(@RequestBody Records record, 
													@PathVariable("record_id") int recordID){
		
		if(recordsService.update(record, recordID, RecordsColumn.PRIORITY)==0)
		return new ResponseEntity<String>("priority updated sucessfully", HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("priority failed to update", HttpStatus.EXPECTATION_FAILED);
	}

	@PutMapping("/{record_id}/type")
	protected ResponseEntity<String> updateType(@RequestBody Records record, 
													@PathVariable("record_id") int recordID){
		
		if(recordsService.update(record, recordID, RecordsColumn.TYPE)==0)
		return new ResponseEntity<String>("type updated sucessfully", HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("type failed to update", HttpStatus.EXPECTATION_FAILED);
	}

	@PutMapping("/{record_id}/status")
	protected ResponseEntity<String> updateStatus(@RequestBody Records record, 
													@PathVariable("record_id") int recordID){
		
		if(recordsService.update(record, recordID, RecordsColumn.STATUS)==0)
		return new ResponseEntity<String>("status updated sucessfully", HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("status failed to update", HttpStatus.EXPECTATION_FAILED);
	}

	@PutMapping("/{record_id}/tag")
	protected ResponseEntity<String> updateTags(@RequestBody Tags tag, 
												@PathVariable("record_id") int recordID){
		Records record = new Records();
		record.getTags().add(tag);
		
		if(recordsService.update(record, recordID, RecordsColumn.TAG)==0)
		return new ResponseEntity<String>("Tag added sucessfully", HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("Tag failed to add", HttpStatus.EXPECTATION_FAILED);
	}

	@PutMapping("/{record_id}/tag/remove")
	protected ResponseEntity<String> updateTagsRemove(@RequestBody Tags tag, 
														@PathVariable("record_id") int recordID){
		Records record = new Records();
		record.getTags().add(tag);
		
		if(recordsService.update(record, recordID, RecordsColumn.TAG_REMOVE)==0)
		return new ResponseEntity<String>("tag removed sucessfully", HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("tag failed to remove", HttpStatus.EXPECTATION_FAILED);
	}
}
