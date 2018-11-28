package com.test.service;

import java.util.List;

import com.test.entity.Area;

public interface AreaService {

	public boolean addArea(Area area);
	public boolean deleteArea(int id);
	public boolean updateArea(Area area);
	public List<Area> findAll();
	public Area findById(int id);
	public List<Area> findByPid(int pid);
	
}
