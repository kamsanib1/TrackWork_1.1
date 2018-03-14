package com.demo.trackwork.services;

import java.util.Collection;

import com.demo.trackwork.entities.Tags;

public interface ITagsService {
	public Tags read(int tagID);
	public Tags read(String tagName);
	public Collection<Tags> readAll();
	public Collection<Tags> readAll(String username);
	public Collection<Tags> readAll(int recordID);
	public int create(Tags tag);
	public int create(Tags tag, String username);
	public int delete(int tagID);
	public int delete(String username, String tagname);
}
