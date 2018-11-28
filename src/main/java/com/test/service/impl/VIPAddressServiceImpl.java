package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.VIPAddressDao;
import com.test.entity.VIPAddress;
import com.test.service.VIPAddressService;
@Service
@Transactional
public class VIPAddressServiceImpl implements VIPAddressService {
	@Autowired
	private VIPAddressDao addressdao;

	@Override
	public boolean addVIPAddress(VIPAddress address) {
		boolean val=false;
		if(address!=null&&!(address.equals(""))) {
			val=addressdao.insertVIPAddress(address);
		}
		return val;
	}

	@Override
	public boolean deleteVIPAddress(int id) {
		boolean val=false;
		val=addressdao.deleteVIPAddress(id);
		return val;
	}

	@Override
	public boolean updateVIPAddress(VIPAddress address) {
		boolean val=false;
		if(address!=null&&!(address.equals(""))) {
			val=addressdao.updateVIPAddress(address);
		}
		return val;
	}

	@Override
	public List<VIPAddress> findAll() {
		
		return addressdao.findAll();
	}

	@Override
	public VIPAddress findById(int id) {
		
		return addressdao.findById(id);
	}

}
