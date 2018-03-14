package com.demo.trackwork.services;

import java.util.Collection;

import com.demo.trackwork.RecordsColumn;
import com.demo.trackwork.entities.Records;

public interface IRecordsService {
	public int create(Records record);
	public Records read(int recordID);
	public Collection<Records> read(String username);
	public Collection<Records> read(String username, String tagname);
	public int delete(int recordID);
	public int update(Records record, int recordID, RecordsColumn column);
}
