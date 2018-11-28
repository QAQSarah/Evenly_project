package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.AreaDao;
import com.test.entity.Area;
import com.test.service.AreaService;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areadao;

	@Override
	public boolean addArea(Area area) {
		boolean val=false;
		if(area!=null&&!(area.equals(""))) {
			val=areadao.insertArea(area);
		}
		return val;
	}

	@Override
	public boolean deleteArea(int id) {
		boolean val=false;
		val=areadao.deleteArea(id);
		return val;
	}

	@Override
	public boolean updateArea(Area area) {
		boolean val=false;
		if(area!=null&&!(area.equals(""))) {
			val=areadao.updateArea(area);
		}
		return val;
	}

	@Override
	public List<Area> findAll() {
		return areadao.findAll();
	}

	@Override
	public Area findById(int id) {
		return areadao.findById(id);
	}

	@Override
	public List<Area> findByPid(int pid) {
		
		return areadao.findByPid(pid);
	}

}
