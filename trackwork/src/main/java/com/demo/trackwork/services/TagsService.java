package com.demo.trackwork.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.trackwork.dao.ITagsDAO;
import com.demo.trackwork.dao.IUserDAO;
import com.demo.trackwork.entities.Records;
import com.demo.trackwork.entities.Tags;
import com.demo.trackwork.entities.Users;

@Service
public class TagsService implements ITagsService {

	@Autowired
	ITagsDAO tagsDAO;
	
	@Autowired
	IRecordsService recordsService;
	
	@Autowired
	IUserDAO usersDAO;
	
	@Override
	public Tags read(int tagID) {
		// TODO Auto-generated method stub
		return removeDependencies(tagsDAO.read(tagID));
	}

	@Override
	public Tags read(String tagName) {
		// TODO Auto-generated method stub
		return removeDependencies(tagsDAO.read(tagName));
	}

	@Override
	public Collection<Tags> readAll() {
		// TODO Auto-generated method stub
		return readAll("default");
	}

	@Override
	public Collection<Tags> readAll(String username) {
		// TODO Auto-generated method stub
		Collection<Tags> tags = tagsDAO.readAll(username);
		for(Tags tag:tags) {
			tag = removeDependencies(tag);
		}
		return tags;
	}

	@Override
	public int create(Tags tag) {
		// TODO Auto-generated method stub
		return create(tag,"default");
	}

	@Override
	public int delete(int tagID) {
		// TODO Auto-generated method stub
		Tags tag = tagsDAO.read(tagID);
		return tagsDAO.delete(tag);
	}

	@Override
	public int create(Tags tag, String username) {
		// TODO Auto-generated method stub
		Users user = usersDAO.read(username);
		tag.setUsername(user);
		return tagsDAO.create(tag);
	}

	@Override
	public int delete(String username, String tagname) {
		// TODO Auto-generated method stub
		Collection<Tags> tags = tagsDAO.readAll(username);
		System.out.println("Tag Service: Delete: Source: tagname: "+tagname+"::username:"+username);
		for(Tags tag:tags) {
			System.out.println("Tag Service: delete: tagname: "+tag.getName()+"::username: "+tag.getUsername().getUsername());
			if(tag.getName().compareTo(tagname) == 0) {
				tagsDAO.delete(tag);
				return 0;
			}
		}
		return 1;
	}

	private Tags removeDependencies(Tags tag) {
		tag.getUsername().setTags(null);
		tag.getUsername().setRecords(null);
		tag.setRecords(null);
		return tag;
	}

	@Override
	public Collection<Tags> readAll(int recordID) {
		// TODO Auto-generated method stub
		Records record = recordsService.read(recordID);
		if(record == null) return null;
		Collection<Tags> tags = record.getTags();
		if(tags == null) return null;
		for(Tags tag:tags) {
			tag = removeDependencies(tag);
		}
		return tags;
	}
	
}
