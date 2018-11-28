package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.test.entity.Area;
import com.test.entity.VIPAddress;
import com.test.entity.VIPUser;
import com.test.service.AreaService;
import com.test.service.VIPAddressService;
import com.test.service.VIPUserService;

@Controller
public class AddressController {
	@Autowired
	private VIPAddressService aService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private VIPUserService vipUserService;

	@RequestMapping(value = "/delAddress")
	@ResponseBody
	public void delAddress(HttpServletRequest request, HttpServletResponse response) {
		boolean val = false;
		String id = request.getParameter("id");
		System.out.println(id);
		if (id != null && !(id.equals(""))) {
			val = aService.deleteVIPAddress(Integer.parseInt(id));
		}
		if (val) {
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "地址删除成功");
			String str = JSON.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "地址删除失败");
			String str = JSON.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@RequestMapping(value = "/saveAddress")
	@ResponseBody
	public void saveAddress(HttpServletRequest request, HttpServletResponse response) {
		boolean val = false;
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String countyId = request.getParameter("countyId");
		String detailAdress = request.getParameter("detailAdress");
		String userid = request.getParameter("userid");
		Area province = null;
		Area city = null;
		Area county = null;
		VIPAddress address = new VIPAddress();
		if (provinceId != null && !(provinceId.equals(""))) {
			province = areaService.findById(Integer.parseInt(provinceId));
			address.setProvince(province.getShortName());
		}
		if (cityId != null && !(cityId.equals(""))) {
			city = areaService.findById(Integer.parseInt(cityId));
			address.setCity(city.getShortName());
		}
		if (countyId != null && !(countyId.equals(""))) {
			county = areaService.findById(Integer.parseInt(countyId));
			address.setCounty(county.getShortName());
		} else {
			address.setCounty(city.getShortName());
		}
		VIPUser vuser = new VIPUser();
		vuser = vipUserService.findById(Integer.parseInt(userid));
		address.setDetailAddress(detailAdress);
		address.setVuser(vuser);
		val = aService.addVIPAddress(address);
		if (val) {
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "用户添加成功");
			String str = JSON.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "用户添加失败");
			String str = JSON.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@RequestMapping(value = "/show/{id}")
	public ModelAndView showAddress(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		System.out.println(id);
		VIPAddress address = aService.findById(id);
		ModelAndView mv = new ModelAndView("addressModify");
		mv.addObject("address", address);
		return mv;
	}

	@RequestMapping(value = "/post/saveupdate")
	@ResponseBody
	public void saveUpdate(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("1111111111");
		boolean val = false;
		String id = request.getParameter("id");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String detailAdress = request.getParameter("detailAdress");
		String county = request.getParameter("county");
		VIPAddress address = aService.findById(Integer.parseInt(id));
		address.setProvince(province);
		address.setCity(city);
		address.setCounty(county);
		address.setDetailAddress(detailAdress);
		System.out.println(address);
		if (address != null) {
			val = aService.updateVIPAddress(address);
		}
		if (val) {
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "地址修改成功");
			String str = JSON.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "地址修改失败");
			String str = JSON.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
