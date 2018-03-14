package com.demo.trackwork.dao;

import java.util.Collection;

import com.demo.trackwork.entities.Records;
import com.demo.trackwork.entities.Tags;

public interface ITagsDAO {
	public int create(Tags tag);
	public Collection<Tags> readAll(String username);
	public Tags read(String tagName);
	public Tags read(int tagID);
	public Collection<Tags> read(Records record);
	public int update(Tags tag);
	public int delete(Tags tag);
}
