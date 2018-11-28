package com.test.dao;

import java.util.List;

import com.test.entity.VIPAddress;


public interface VIPAddressDao {
	public boolean insertVIPAddress(VIPAddress vaddress);
	public boolean deleteVIPAddress(int id);
	public boolean updateVIPAddress(VIPAddress vaddress);
	public List<VIPAddress> findAll();
	public VIPAddress findById(int id);

}
