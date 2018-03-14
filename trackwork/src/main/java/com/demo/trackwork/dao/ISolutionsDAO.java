package com.demo.trackwork.dao;

import java.util.Collection;

import com.demo.trackwork.entities.Solutions;

public interface ISolutionsDAO {
	public int create(Solutions solution);
	public Solutions read(int solutionID);
	public Collection<Solutions> readByRecord(int recordID);
	public int update(Solutions solution);
	public int delete(Solutions solution);
}
