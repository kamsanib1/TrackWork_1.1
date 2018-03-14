package com.demo.trackwork.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.demo.trackwork.entities.Records;

@Transactional
@Repository
public class RecordsDAO implements IRecordsDAO {

	@PersistenceContext
	EntityManager entityMgr;
	
	@Override
	public int create(Records record) {
		// TODO Auto-generated method stub
		if(record==null) return 1;
		entityMgr.persist(record);
		return 0;
	}

	@Override
	public Records read(int recordID) {
		// TODO Auto-generated method stub
		return entityMgr.find(Records.class, recordID);
	}

	@Override
	public int update(Records record) {
		// TODO Auto-generated method stub
		if(record == null) return 1;
		entityMgr.merge(record);
		return 0;
	}

	@Override
	public int delete(Records record) {
		// TODO Auto-generated method stub
		if(record == null) return 1;
		entityMgr.remove(record);
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Records> read(String username) {
		// TODO Auto-generated method stub
		if(username == null) return null;
		String query = "from Records where user = "+username;
		Collection<Records> records =(Collection<Records>) entityMgr.createQuery(query)
												.getResultList();
		return records;
	}

}
