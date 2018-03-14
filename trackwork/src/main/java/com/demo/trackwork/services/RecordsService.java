package com.demo.trackwork.services;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.trackwork.RecordsColumn;
import com.demo.trackwork.dao.IRecordsDAO;
import com.demo.trackwork.dao.ISolutionsDAO;
import com.demo.trackwork.dao.ITagsDAO;
import com.demo.trackwork.dao.IUserDAO;
import com.demo.trackwork.entities.Records;
import com.demo.trackwork.entities.Solutions;
import com.demo.trackwork.entities.Tags;

@Service
public class RecordsService implements IRecordsService {

	@Autowired
	IRecordsDAO recordsDAO;
	@Autowired
	ISolutionsService solutionsService;
	@Autowired
	ITagsService tagsService;
	
	@Override
	public int create(Records record) {
		// TODO Auto-generated method stub
		
				//Add tags to the database.
				if(!record.getTags().isEmpty()) {
					for(Tags tag:record.getTags()) {
						System.out.println("ReordService: TagID:"+tag.getId()+" ::TagName:"+tag.getName());
						Tags tmp =tagsService.read(tag.getName()); 
						if(tmp == null) {
							tagsService.create(tag);
						}else tag.setId(tmp.getId());
					}
				}
				
				
		//Create a new Record
		java.util.Date date = new java.util.Date();
		Date justDate = new Date(date.getYear(),date.getMonth(),date.getDate());
		Time time = new Time(date.getHours(),date.getMinutes(),date.getSeconds());
		record.setDate(justDate);
		record.setTime(time);
		record.setViews(0);
		System.out.println("Service: Creating data...");
		recordsDAO.create(record);
		
		//Add solutions to the database.
		if(!record.getSolutions().isEmpty()) {
			for(Solutions solution:record.getSolutions()) {
				solution.setRecord(record);
				solutionsService.create(solution);
			}
		}
		
		return 0;
	}

	@Override
	public Records read(int recordID) {
		// TODO Auto-generated method stub
		System.out.println("Service: Reading data...");
		Records record = recordsDAO.read(recordID);
		record = removeDependencies(record);
		return record;
	}

	@Override
	public Collection<Records> read(String username) {
		// TODO Auto-generated method stub
		System.out.println("Service: Reading data...");
		Collection<Records> records = recordsDAO.read("'"+username+"'");
		System.out.println("Records Service: Printing tags...");
		for(Records record : records) {
			record = removeDependencies(record);
			for(Tags tag:record.getTags())
				System.out.println(tag.getName());
		}
		return records;
	}
	
	private Records removeDependencies(Records record) {
		for(Tags tag:record.getTags()) {tag.setRecords(null);}
		for(Solutions solution:record.getSolutions()) {solution.setRecord(null);}
		record.getUser().setRecords(null);
		return record;
	}

	@Override
	public Collection<Records> read(String username, String tagname) {
		// TODO Auto-generated method stub
		Collection<Records> records = read(username);
		Collection<Records> finalRecords = new ArrayList<Records>();
		for(Records record: records) {
			for(Tags tag :record.getTags()) {
				if(tag.getName().compareTo(tagname)==0)
					removeDependencies(record);
					finalRecords.add(record);
			}
		}
		return finalRecords;
	}

	@Override
	public int delete(int recordID) {
		// TODO Auto-generated method stub
		Records record = recordsDAO.read(recordID);
		return recordsDAO.delete(record);
	}

	@Override
	public int update(Records record, int recordID, RecordsColumn column) {
		// TODO Auto-generated method stub
		Records newRecord = recordsDAO.read(recordID);
		List<Tags> tags;
		Tags tag;
		
		if(newRecord == null) return 1;
		switch(column) {
		case QUERY: newRecord.setQuery(record.getQuery());break;
		case PRIORITY: newRecord.setPriority(record.getPriority());break;
		case TOPIC: newRecord.setTopic(record.getTopic());break;
		case VIEWS: newRecord.setViews(newRecord.getViews()+record.getViews());break;
		case TYPE: newRecord.setType(record.getType());break;
		case STATUS: newRecord.setStatus(record.getStatus());break;
		case TAG: tags = (List<Tags>)record.getTags();
		for(Tags t:tags)System.out.println("Records Service: Update: Add Tag: Tag Name: "+t.getName());
			if(tags == null || tags.size() == 0) return 1;
			System.out.println("Records Service: Update: Add Tag: reading tag...");
			tag = tagsService.read(tags.get(0).getName());
			System.out.println("Records Service: Update: Add Tag: adding tag...");
			newRecord.getTags().add(tag);
			System.out.println("Records Service: Update: Add Tag: new tag "+tag.getName()+" is added to record.");	
			break;
		case TAG_REMOVE: tags = (List<Tags>)record.getTags();
			if(tags == null || tags.size() == 0) return 1;
			tag = tagsService.read(tags.get(0).getName());
			newRecord.getTags().remove(tag);
			break;
		}
		
		return recordsDAO.update(newRecord);
	}
	
	
}
