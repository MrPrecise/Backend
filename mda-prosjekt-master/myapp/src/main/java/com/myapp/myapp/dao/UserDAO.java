package com.myapp.myapp.dao;

import java.util.List;

import com.myapp.myapp.entity.MyUser;

public interface UserDAO {
	public List<MyUser> findAll();

	public MyUser getUserById(String id);

	public void save(MyUser theUser);

	public void deleteById(String theId);
}
