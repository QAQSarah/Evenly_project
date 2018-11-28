package com.test.service;

import java.util.List;

import com.test.entity.VIPAddress;

public interface VIPAddressService {

	public boolean addVIPAddress(VIPAddress address);
	public boolean deleteVIPAddress(int id);
	public boolean updateVIPAddress(VIPAddress address);
	public List<VIPAddress> findAll();
	public VIPAddress findById(int id);
	
}
