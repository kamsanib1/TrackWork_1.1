package com.demo.trackwork.dao;

import java.util.Collection;

import com.demo.trackwork.entities.Records;

public interface IRecordsDAO {
	public int create(Records record);
	public Records read(int recordID);
	public Collection<Records> read(String username);
	public int update(Records record);
	public int delete(Records record);
}
