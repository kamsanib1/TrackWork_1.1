package com.demo.trackwork.dao;

import java.util.Collection;

import com.demo.trackwork.entities.Users;

public interface IUserDAO {
public int create(Users user);
public Users read(String userName);
public int update(Users user);
public int delete(Users user);
public Collection<Users> readAll();
}
