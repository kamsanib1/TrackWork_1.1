package com.demo.trackwork.services;

import java.util.Collection;

import com.demo.trackwork.entities.Users;

public interface IUserService {

	public int create(Users user);
	public Users read(String username);
	public int delete(Users user);
	public int delete(String username);
	public int update(Users user);
	public Collection<Users> read();
}
