package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.test.dao.VIPAddressDao;
import com.test.dao.VIPUserDao;
import com.test.entity.PageBean;
import com.test.entity.VIPAddress;
import com.test.entity.VIPUser;
import com.test.service.VIPUserService;

@Service
@Transactional
public class VIPUserServiceImpl implements VIPUserService {
	@Autowired
	private VIPUserDao vuserdao;
	@Autowired
	private VIPAddressDao vAddressDao;

	@Override
	public boolean addVIPUSer(VIPUser vuser) {
		boolean val = false;
		if (vuser != null && !(vuser.equals(""))) {
			for (VIPAddress address : vuser.getAddresses()) {
				vAddressDao.insertVIPAddress(address);
			}
			int i = vuserdao.insertVIPUser(vuser);
			if (i != 0) {
				val = true;
			}
		}
		return val;
	}

	@Override
	public boolean delVIPUser(int id) {
		boolean val = false;
		val = vuserdao.deleteVIPUser(id);
		return val;
	}

	@Override
	public boolean updateUser(VIPUser vuser) {
		boolean val = false;
		if (!(vuser.equals("")) && vuser != null) {
			val = vuserdao.updateVIPUser(vuser);
		}
		return val;
	}

	@Override
	public List<VIPUser> findAll() {
		return vuserdao.findAll();
	}

	@Override
	public VIPUser findById(int id) {
		return vuserdao.findById(id);
	}

	@Override
	public VIPUser findByUser(VIPUser vuser) {
		VIPUser user = null;
		if (vuser != null && !(vuser.equals(""))) {
			user = vuserdao.findByUser(vuser);
		}
		return user;
	}

	@Override
	public PageBean<VIPUser> findAllStudentWithPage(int pageNum, int pageSize) {
		List<VIPUser> allStudent = vuserdao.findAll();

		int totalRecord = allStudent.size();

		PageBean<VIPUser> pb = new PageBean<VIPUser>(pageNum, pageSize, totalRecord);

		int startIndex = pb.getStartIndex();

		pb.setList(vuserdao.findAll(startIndex, pageSize));

		return pb;

	}

	@Override
	public List<VIPUser> findByName(String name) {
		if(name!=null&&!(name.equals(""))) {
		return vuserdao.findByName(name);
		}
		return null;
	}
	@Override
	public PageBean<VIPUser> findByName(int pageNum, int pageSize,String name) {
		if(name!=null&&!(name.equals(""))) {
			List<VIPUser> allStudent = vuserdao.findByName(name);
			int totalRecord = 0;
			if(allStudent!=null) {
				totalRecord=allStudent.size();
			}
			PageBean<VIPUser> pb = new PageBean<VIPUser>(pageNum, pageSize, totalRecord);

			int startIndex = pb.getStartIndex();

			if(vuserdao.findByName(startIndex, pageSize,name)==null) {
				return null;
			}else {
				pb.setList(vuserdao.findByName(startIndex, pageSize,name));

				return pb;
			}
			
		}
		return null;
	}
}
