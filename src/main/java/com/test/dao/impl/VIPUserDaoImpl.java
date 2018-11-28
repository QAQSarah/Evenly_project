package com.test.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.test.dao.VIPUserDao;
import com.test.entity.User;
import com.test.entity.VIPUser;

@Repository
public class VIPUserDaoImpl implements VIPUserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private SessionFactory sessionFactory;

	// 必须设置set方法
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public int insertVIPUser(VIPUser vuser) {
		int i = 0;
		hibernateTemplate.setCheckWriteOperations(false);
		if (vuser != null && !(vuser.equals(""))) {
			i = (int) hibernateTemplate.save(vuser);
		}
		return i;

	}

	@Override
	public boolean deleteVIPUser(int id) {
		boolean val = false;
		VIPUser vuser = findById(id);
		if (vuser != null && !(vuser.equals(""))) {
			hibernateTemplate.setCheckWriteOperations(false);
			Session session = sessionFactory.getCurrentSession();
			hibernateTemplate.delete(vuser);
			session.flush();
			val=true;
		}
		return val;
	}

	@Override
	public boolean updateVIPUser(VIPUser vuser) {
		hibernateTemplate.setCheckWriteOperations(false);
		boolean val = false;
		if (vuser != null && !(vuser.equals(""))) {
			Session session = sessionFactory.getCurrentSession();
			hibernateTemplate.update(vuser);
			session.flush();
			val = true;
		}
		return val;
	}

	@Override
	public List<VIPUser> findAll(int startIndex, int pageSize) {
		hibernateTemplate.setCheckWriteOperations(false);
		String hql = "from VIPUser";
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		// HQL语句在查询时不能使用limit关键字
		Query query = session.createQuery(hql).setFirstResult(startIndex).setMaxResults(pageSize);
		List<VIPUser> list = query.list();

		for (VIPUser user : list) {
			System.out.println(user);
		}
		session.close();
		if (list != null && list.size() > 0) {
			return (List<VIPUser>) list;
		} else {
			return null;
		}
	}

	@Override
	public VIPUser findById(int id) {
		VIPUser vuser = null;
		hibernateTemplate.setCheckWriteOperations(false);
		vuser = hibernateTemplate.get(VIPUser.class, id);
		return vuser;
	}

	@Override
	public VIPUser findByUser(VIPUser vuser) {
		VIPUser user = null;
		if (vuser != null && !(vuser.equals(""))) {
			hibernateTemplate.setCheckWriteOperations(false);
			String hql = "from VIPUser where username=? and password=?  ";
			List<VIPUser> list = (ArrayList<VIPUser>) hibernateTemplate.find(hql, vuser.getUsername(),
					vuser.getPassword());
			user = list.get(0);
		}

		return user;
	}

	@Override
	public List<VIPUser> findAll() {
		hibernateTemplate.setCheckWriteOperations(false);
		String hql = "from VIPUser";
		List<VIPUser> list = (List<VIPUser>) hibernateTemplate.find(hql);
		if (list != null && list.size() > 0) {
			return (List<VIPUser>) list;
		} else {
			return null;
		}
	}

	@Override
	public List<VIPUser> findByName(String name) {
		hibernateTemplate.setCheckWriteOperations(false);
		String hql = "from VIPUser where username like ?";
		List<VIPUser> list = (List<VIPUser>) hibernateTemplate.find(hql,"%"+name+"%");
		if (list != null && list.size() > 0) {
			return (List<VIPUser>) list;
		} else {
			return null;
		}
	}
	@Override
	public List<VIPUser> findByName(int startIndex, int pageSize,String name) {
		hibernateTemplate.setCheckWriteOperations(false);
		String hql = "from VIPUser where username like ?";
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		// HQL语句在查询时不能使用limit关键字
		Query query = session.createQuery(hql).setString(0, name).setFirstResult(startIndex).setMaxResults(pageSize);
		List<VIPUser> list = query.list();

		for (VIPUser user : list) {
			System.out.println(user);
		}
		session.close();
		if (list != null && list.size() > 0) {
			return (List<VIPUser>) list;
		} else {
			return null;
		}
	}
}
