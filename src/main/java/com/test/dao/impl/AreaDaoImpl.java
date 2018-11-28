package com.test.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.test.dao.AreaDao;
import com.test.entity.Area;
import com.test.entity.VIPAddress;
@Repository
public class AreaDaoImpl implements AreaDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	// 必须设置set方法
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	@Override
	public boolean insertArea(Area area) {
		boolean val = false;
		hibernateTemplate.setCheckWriteOperations(false);
		if (area != null && !(area.equals(""))) {
			val = (boolean) hibernateTemplate.save(area);
		}
		return val;
	}

	@Override
	public boolean deleteArea(int id) {
		boolean val = false;
		hibernateTemplate.setCheckWriteOperations(false);
		Area area = hibernateTemplate.get(Area.class, id);
		if (area != null && !(area.equals(""))) {
			hibernateTemplate.delete(area);
			val = true;
		}
		return val;
	}

	@Override
	public boolean updateArea(Area area) {
		hibernateTemplate.setCheckWriteOperations(false);
		boolean val = false;
		if (area != null && !(area.equals(""))) {
			hibernateTemplate.update(area);
			val = true;
		}
		return val;
	}

	@Override
	public List<Area> findAll() {
		hibernateTemplate.setCheckWriteOperations(false);
		String hql = "from Area";
		List<Area> list = (ArrayList<Area>) hibernateTemplate.find(hql);
		if (list != null && list.size() > 0) {
			return (List<Area>) list;
		} else {
			return null;
		}
	}

	@Override
	public Area findById(int id) {
		Area area=null;
		hibernateTemplate.setCheckWriteOperations(false);
		area=hibernateTemplate.get(Area.class, id);
		return area;
	}


	@Override
	public List<Area> findByPid(int pid) {
		hibernateTemplate.setCheckWriteOperations(false);
		String hql = "from Area where pid=?";
		List<Area> list = (ArrayList<Area>) hibernateTemplate.find(hql,pid);
		if (list != null && list.size() > 0) {
			return (List<Area>) list;
		} else {
			return null;
		}
	}

}
