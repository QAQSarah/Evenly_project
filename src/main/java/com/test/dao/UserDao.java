package com.test.dao;

import java.util.ArrayList;
import java.util.List;

import com.test.entity.User;

public interface UserDao {
	public boolean addUser(User user);
	public boolean deleteUser(int id);
	public boolean updateUser(User user);
	public ArrayList<User> findAll();
	public User findUser(int id);
	/**
	 * 查询单个用户(登录)
	 * */
	public User selUser(User user)throws Exception;
	/**
	 * 多条件查询
	 * */
	public List<User> search(String search);

}
