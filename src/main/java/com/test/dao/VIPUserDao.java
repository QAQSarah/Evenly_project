package com.test.dao;

import java.util.List;

import com.test.entity.Area;
import com.test.entity.VIPUser;

public interface VIPUserDao {

	public int insertVIPUser(VIPUser vuser);
	public boolean deleteVIPUser(int id);
	public boolean updateVIPUser(VIPUser vuser);
	public List<VIPUser> findAll(int startIndex, int pageSize);
	public List<VIPUser> findAll();
	public VIPUser findById(int id);
	public VIPUser findByUser(VIPUser vuser);
	public List<VIPUser> findByName(String name);
	List<VIPUser> findByName(int startIndex, int pageSize, String name);
}
