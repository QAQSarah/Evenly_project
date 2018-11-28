package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.Area;
import com.test.entity.PageBean;
import com.test.entity.User;
import com.test.entity.VIPAddress;
import com.test.entity.VIPUser;
import com.test.service.AreaService;
import com.test.service.VIPAddressService;
import com.test.service.VIPUserService;

@Controller
public class VIPUserController {
	
	@Autowired
	private VIPUserService vipUserService;
	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/addvip")
	public ModelAndView ejld1() {
		ModelAndView mv = new ModelAndView("vipuseradd");
		List<Area> list = areaService.findByPid(0);// 所有省份查询
		mv.addObject("provinces", list);
		return mv;
	}

	@RequestMapping(value = "/ejld/{id}")
	@ResponseBody
	private void ejld2(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(id);
		List<Area> list = areaService.findByPid(id);// 所有pid查询
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "201");
		map.put("cities", list);
		String jsres = JSONObject.toJSONString(map);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsres);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/saveVip")
	@ResponseBody
	public void saveVIPUser(HttpServletRequest request, HttpServletResponse response) {
		boolean val = false;
		System.out.println("1111111");
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String countyId = request.getParameter("countyId");
		String detailAdress = request.getParameter("detailAdress");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String realname = request.getParameter("realname");
		String password = request.getParameter("password");
		System.out.println(provinceId + " " + cityId + " " + countyId + " " + detailAdress + " " + username + " "
				+ phone + " " + realname + " " + password);
		VIPUser vuser = new VIPUser();
		vuser.setUsername(username);
		vuser.setRealname(realname);
		vuser.setPassword(password);
		vuser.setPhone(phone);
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss");
		String creatTime = df.format(new Date());
		vuser.setCreatTime(creatTime);
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

		address.setDetailAddress(detailAdress);
		address.setVuser(vuser);
		vuser.getAddresses().add(address);
		val = vipUserService.addVIPUSer(vuser);
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

	@RequestMapping(value = "/delvip")
	@ResponseBody
	public void delVIPUser(HttpServletRequest request, HttpServletResponse response) {
		boolean val = false;
		String id=request.getParameter("id");
		System.out.println(id);
		if(id!=null&&!(id.equals(""))) {
			val=vipUserService.delVIPUser(Integer.parseInt(id));
		}
		if(val) {
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "用户删除成功");
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
			map.put("msg", "用户删除失败");
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

	@RequestMapping(value = "/showvip")
	public ModelAndView findAllStudentWithPage(HttpServletRequest request, HttpServletResponse response) {

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		PageBean<VIPUser> pb = vipUserService.findAllStudentWithPage(Integer.parseInt(pageNum), 10);
		ModelAndView model = new ModelAndView("vipuserlist");
		model.addObject("pageBean", pb);
		return model;
	}

	@RequestMapping(value = "/showAddress/{id}")
	public ModelAndView showVIPAdress(@PathVariable int id) {
		System.out.println(id);
		ModelAndView mv = new ModelAndView("SHAdress");
		List<Area> list = areaService.findByPid(0);// 所有省份查询
		mv.addObject("provinces", list);
		VIPUser user = vipUserService.findById(id);
		mv.addObject("user", user);
		return mv;
	}

	@RequestMapping(value="/editVip/{id}")
	public ModelAndView editVIPUser(@PathVariable int id) {
		System.out.println(id);
		VIPUser user= vipUserService.findById(id);
		ModelAndView mv=new ModelAndView("vipusermodify");
		mv.addObject("mduser", user);
		return mv;
	}
	@RequestMapping(value = "/seleteByName")
	public ModelAndView seleteByName(VIPUser user,HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		
		ModelAndView mv=new ModelAndView();
		List<VIPUser> users=new ArrayList<>();
		PageBean<VIPUser> pb = vipUserService.findByName(Integer.parseInt(pageNum), 10,user.getUsername());
		if(pb==null||pb.equals("")) {
			mv.setViewName("vipuserlist");
		}else {
			mv.clear();
			mv.addObject("pageBean", pb);
			mv.setViewName("vipuserlist");
		}
		
		return mv;
	}
	@RequestMapping("/updateVipUser")
	public String updateVIP(VIPUser vuser) {
		boolean val=false;
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss");
		String creatTime = df.format(new Date());
		vuser.setCreatTime(creatTime);
		System.out.println(vuser);
		for (VIPAddress a : vuser.getAddresses()) {
			a.setVuser(vuser);
		}
		if(vuser!=null&&!(vuser.equals(""))) {
			val=vipUserService.updateUser(vuser);
		}
		if(val) {
			return"redirect:/showvip";
		}else {
			return"redirect:/updateVipUser";
		}
	}
}
