package com.demo.trackwork.services;

import java.util.Collection;

import com.demo.trackwork.entities.Solutions;

public interface ISolutionsService {
	public Solutions read(int solutionID);
	public Collection<Solutions> readByRecord(int recordID);
	public int create(Solutions solution);
	public int create(Solutions solution, int recordID);
	public int update(Solutions solution);
}
