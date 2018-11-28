package com.test.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.test.dao.UserDao;
import com.test.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private SessionFactory sessionFactory;

	// 必须设置set方法
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public boolean addUser(User user) {
		boolean val = true;
		hibernateTemplate.setCheckWriteOperations(false);
		hibernateTemplate.save(user);
		return val;
	}

	@Override
	public boolean deleteUser(int id) {
		hibernateTemplate.setCheckWriteOperations(false);
		Session session = sessionFactory.getCurrentSession();
		User user = findUser(id);
		hibernateTemplate.delete(user);
		session.flush();
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		
		hibernateTemplate.setCheckWriteOperations(false);
		Session session = sessionFactory.getCurrentSession();
		hibernateTemplate.update(user);
		session.flush();
		return true;
	}

	@Override
	public ArrayList<User> findAll() {
		String hql = "from User";
		List<User> list = (ArrayList<User>) hibernateTemplate.find(hql);
		if (list != null && list.size() > 0) {
			return (ArrayList<User>) list;
		} else {
			return null;
		}

	}

	@Override
	public User findUser(int id) {
		String hql = "from User where id=?";
		ArrayList<User> list = (ArrayList<User>) hibernateTemplate.find(hql, id);
		return list.get(0);
	}

	public List<User> search(String search) {
		List<User> users = new ArrayList<User>();
		String hql = "from User where username like ? order by username asc";
		users = (List<User>) hibernateTemplate.find(hql, "%" + search + "%");
		return users;
	}

	@Override
	public User selUser(User user) throws Exception {
		User user1 = null;
		String hql = "from User where name=? and password=?";
		List<User> users = (List<User>) hibernateTemplate.find(hql, user.getName(), user.getPassword());
		if(users.size()>0) {
		user1=users.get(0);
		}
		return user1;

	}

}