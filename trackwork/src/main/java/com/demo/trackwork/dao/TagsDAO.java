package com.demo.trackwork.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.demo.trackwork.entities.Records;
import com.demo.trackwork.entities.Tags;

@Transactional
@Repository
public class TagsDAO implements ITagsDAO {

	@PersistenceContext
	EntityManager entityMgr;
	
	@Override
	public int create(Tags tag) {
		// TODO Auto-generated method stub
		entityMgr.persist(tag);
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Tags read(String tagName) {
		// TODO Auto-generated method stub
		String query = "from Tags where name = '"+tagName+"'";
		List<Tags> tags =(List<Tags>) entityMgr.createQuery(query)
												.getResultList();
		if(tags == null) return null;
		return tags.get(0);
	}

	@Override
	public Tags read(int tagID) {
		// TODO Auto-generated method stub
		return entityMgr.find(Tags.class, tagID);
	}

	@Override
	public int update(Tags tag) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Tags tag) {
		// TODO Auto-generated method stub
		entityMgr.remove(tag);
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Tags> read(Records record) {
		// TODO Auto-generated method stub
		//int[] tagids = entityMgr.fi
		System.out.println("TagsDAO: read by record: retrieving tags based on record id's...");
		String query = "select Tags from Records, Tags where Record="+record.getId();
		Collection<Tags> tags = (Collection<Tags>)entityMgr.createQuery(query).getResultList();
		return tags;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Tags> readAll(String username) {
		// TODO Auto-generated method stub
		String query = "from Tags where username='"+username+"' OR username='default'";
		Collection<Tags> tags = (Collection<Tags>)entityMgr.createQuery(query).getResultList();
		return tags;
	}

}
