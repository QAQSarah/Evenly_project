package com.test.service;

import java.util.List;

import com.test.entity.PageBean;
import com.test.entity.VIPUser;

public interface VIPUserService {
	public boolean addVIPUSer(VIPUser vuser);
	public boolean delVIPUser(int id);
	public boolean updateUser(VIPUser vuser);
	public List<VIPUser> findAll();
	public VIPUser findById(int id);
	public VIPUser findByUser(VIPUser vuser);
	public List<VIPUser> findByName(String name);
	public PageBean<VIPUser> findAllStudentWithPage(int pageNum, int pageSize);
	PageBean<VIPUser> findByName(int pageNum, int pageSize, String name);
}
