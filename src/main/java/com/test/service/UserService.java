package com.test.service;

import java.util.List;

import com.test.entity.User;

public interface UserService {
	public boolean addUser(User user);

	public boolean deleteUser(String id);

	public boolean update(User user);

	public List<User> showUsers();

	public User showUser(int id);
	/**
	 * 多条件查询
	 * */
	
	public List<User> search( String search);
	/**
	 * 登录
	 * */
	public User login(User user);
}
