package com.demo.trackwork.services;

import java.util.Collection;
import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.trackwork.dao.IRecordsDAO;
import com.demo.trackwork.dao.ISolutionsDAO;
import com.demo.trackwork.entities.Records;
import com.demo.trackwork.entities.Solutions;

@Service
public class SolutionsService implements ISolutionsService {

	@Autowired
	ISolutionsDAO solutionsDAO;
	
	@Autowired
	IRecordsDAO recordsDAO;
	
	@Override
	public Solutions read(int solutionID) {
		// TODO Auto-generated method stub
		return removeDependencies(solutionsDAO.read(solutionID));
	}

	@Override
	public Collection<Solutions> readByRecord(int recordID) {
		// TODO Auto-generated method stub
		Collection<Solutions> solutions = solutionsDAO.readByRecord(recordID);
		for(Solutions solution:solutions) {
			solution = removeDependencies(solution);
		}
		return solutions;
	}

	@Override
	public int create(Solutions solution) {
		// TODO Auto-generated method stub
		Collection<Solutions> solutions = solutionsDAO.readByRecord(solution.getRecord().getId());
		if(solutions!=null) {solution.setNumber(solutions.size()+1);}
		else solution.setNumber(1);
		solution = setDate(solution);
		solutionsDAO.create(solution);
		return 0;
	}

	@Override
	public int update(Solutions solution) {
		// TODO Auto-generated method stub
		Solutions oldSolution = solutionsDAO.read(solution.getId());
		oldSolution.setSolution(solution.getSolution());
		int ret = solutionsDAO.update(oldSolution);
		return ret;
	}
	
	private Solutions removeDependencies(Solutions solution) {
		solution.setRecord(null);
		return solution;
	}

	@Override
	public int create(Solutions solution, int recordID) {
		// TODO Auto-generated method stub
		Records record = recordsDAO.read(recordID);
		solution.setRecord(record);
		return create(solution);
		
	}
	
	private Solutions setDate(Solutions solution) {
		
		java.util.Date date = new java.util.Date();
		Date justDate = new Date(date.getYear(),date.getMonth(),date.getDate());
		Time time = new Time(date.getHours(),date.getMinutes(),date.getSeconds());
		solution.setDate(justDate);
		solution.setTime(time);
		
		return solution;
	}
}
