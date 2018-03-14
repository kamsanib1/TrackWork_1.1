package com.demo.trackwork.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.trackwork.dao.IUserDAO;
import com.demo.trackwork.entities.Users;

@Service
public class UserService implements IUserService {

	@Autowired
	IUserDAO userDAO;
	
	@Override
	public int create(Users user) {
		// TODO Auto-generated method stub
		System.out.println("service:creating new user...");
		return userDAO.create(user);
	}

	@Override
	public Users read(String username) {
		// TODO Auto-generated method stub
		Users user = userDAO.read(username);
		if(user.getUsername()=="default") return null;
		user.setRecords(null);
		return removeDependencies(user);
	}

	@Override
	public int delete(Users user) {
		// TODO Auto-generated method stub
		
		return userDAO.delete(user);
	}

	@Override
	public int update(Users user) {
		// TODO Auto-generated method stub
		return userDAO.update(user);
	}

	@Override
	public Collection<Users> read() {
		// TODO Auto-generated method stub
		return userDAO.readAll();
	}

	@Override
	public int delete(String username) {
		// TODO Auto-generated method stub
		Users user = userDAO.read(username);
		return delete(user);
	}
	
	private Users removeDependencies(Users user) {
		user.setRecords(null);
		user.setTags(null);
		return user;
	}

}
