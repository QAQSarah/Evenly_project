package com.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.UserDao;
import com.test.entity.User;
import com.test.service.MdCode;
import com.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;

	@Override
	public boolean addUser(User user) {
		boolean val = false;
		String psw=user.getPassword();
		String password=MdCode.md5(psw);
		user.setPassword(password);
		val = userdao.addUser(user);
		return val;
	}

	@Override
	@Transactional
	public boolean deleteUser(String id) {
		boolean val = false;
		int i = Integer.parseInt(id);
		val = userdao.deleteUser(i);
		return val;
	}

	@Override
	@Transactional
	public boolean update(User user) {
		boolean val = false;
		val = userdao.updateUser(user);
		return val;
	}

	@Override
	public List<User> showUsers() {
		ArrayList<User> list = userdao.findAll();
		return list;
	}

	@Override
	public User showUser(int id) {
		User user = userdao.findUser(id);
		return user;
	}

	
	
	@Override
	public User login(User user) {
		User u=new User();
		try {
			String psw=user.getPassword();
			String password=MdCode.md5(psw);
			user.setPassword(password);
			u=userdao.selUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public List<User> search(String search) {
		return userdao.search(search);
	}
}
