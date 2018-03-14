package com.demo.trackwork.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.demo.trackwork.entities.Users;

@Transactional
@Repository
public class UsersDAO implements IUserDAO{

	@PersistenceContext
	EntityManager entityMgr;
	
	@Override
	public int update(Users user) {
		// TODO Auto-generated method stub
		
		Users newUser = read(user.getUsername());
		try {
/*			System.out.println("UsersDAO: update: starting transaction...");
			entityMgr.getTransaction().begin();
			System.out.println("UsersDAO: update: updating user information...");
			newUser.setFname(user.getFname());
			newUser.setLname(user.getLname());
			newUser.setMailid(user.getMailid());
			newUser.setPassword(user.getPassword());
			newUser.setRecords(user.getRecords());
			System.out.println("UsersDAO: update: trying to commit transaction...");
			entityMgr.getTransaction().commit();		
			System.out.println("UsersDAO: update: finished updating...");
*/			
			entityMgr.merge(user);
			return 0;
		}catch(Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	@Override
	public int create(Users user) {
		// TODO Auto-generated method stub
		System.out.println("DAO:adding new user:"+user.getUsername());
		if(read(user.getUsername())!=null) 
			return 1;
		entityMgr.persist(user);
		return 0;
	}


	@Override
	public Users read(String userName) {
		// TODO Auto-generated method stub
		return entityMgr.find(Users.class, userName);
	}

	@Override
	public int delete(Users user) {
		// TODO Auto-generated method stub
		System.out.println("trying to remove user:"+user.getUsername());
		entityMgr.remove(user);
		if(read(user.getUsername()) == null)
		return 0;
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Users> readAll() {
		// TODO Auto-generated method stub
		String query = "from Users";
		Collection<Users> users = (Collection<Users>)entityMgr.createQuery(query).getResultList();
		return users;
	}

}
