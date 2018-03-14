package com.demo.trackwork.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.demo.trackwork.entities.Solutions;

@Transactional
@Repository
public class SolutionsDAO implements ISolutionsDAO {

	@PersistenceContext
	EntityManager entityMgr;
	
	@Override
	public int create(Solutions solution) {
		// TODO Auto-generated method stub
		entityMgr.persist(solution);
		return 0;
	}

	@Override
	public Solutions read(int solutionID) {
		// TODO Auto-generated method stub
		return entityMgr.find(Solutions.class, solutionID);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Solutions> readByRecord(int recordID) {
		// TODO Auto-generated method stub
		String query = "from Solutions where record="+recordID;
		Collection<Solutions> solutions = (Collection<Solutions>)entityMgr.createQuery(query)
																			.getResultList();
		return solutions;
	}

	@Override
	public int update(Solutions solution) {
		// TODO Auto-generated method stub
		entityMgr.merge(solution);		
		return 0;
	}

	@Override
	public int delete(Solutions solution) {
		// TODO Auto-generated method stub
		entityMgr.remove(solution);
		return 0;
	}

}
