package com.test.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.test.dao.VIPAddressDao;
import com.test.entity.VIPAddress;

@Repository
public class VIPAddressDaoImpl implements VIPAddressDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private SessionFactory sessionFactory;

	// 必须设置set方法
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public boolean insertVIPAddress(VIPAddress vaddress) {
		boolean val = false;
		int i = 0;
		hibernateTemplate.setCheckWriteOperations(false);
		if (vaddress != null && !(vaddress.equals(""))) {
			i = (int) hibernateTemplate.save(vaddress);
			if (i != 0) {
				val = true;
			}
		}
		return val;
	}

	@Override
	public boolean deleteVIPAddress(int id) {
		boolean val = false;
		VIPAddress address = findById(id);
		String hql = "Delete FROM  VIPAddress where id=?";
		if (address != null && !(address.equals(""))) {
			Transaction tran = sessionFactory.getCurrentSession().beginTransaction();
			Query q = sessionFactory.getCurrentSession().createQuery(hql);
			q.setInteger(0, id);
			q.executeUpdate();
			tran.commit();
			val = true;
		}
		return val;
	}

	@Override
	public boolean updateVIPAddress(VIPAddress vaddress) {
		hibernateTemplate.setCheckWriteOperations(false);
		boolean val = false;
		if (vaddress != null && !(vaddress.equals(""))) {

			Session session = sessionFactory.getCurrentSession();
			hibernateTemplate.update(vaddress);
			session.flush();
			val = true;
		}
		return val;
	}

	@Override
	public List<VIPAddress> findAll() {
		hibernateTemplate.setCheckWriteOperations(false);
		String hql = "from VIPAddress";
		List<VIPAddress> list = (ArrayList<VIPAddress>) hibernateTemplate.find(hql);
		if (list != null && list.size() > 0) {
			return (List<VIPAddress>) list;
		} else {
			return null;
		}
	}

	@Override
	public VIPAddress findById(int id) {
		VIPAddress vaddress = null;
		hibernateTemplate.setCheckWriteOperations(false);
		vaddress = hibernateTemplate.get(VIPAddress.class, id);
		return vaddress;
	}

}
