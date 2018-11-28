package com.test.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "Tvipuser")
public class VIPUser {

	@Id
	@GeneratedValue
	private Integer id;
	@NotNull(message="用户名不允许为空")
	@NotEmpty(message="用户名不允许为空")
	@NotBlank(message="用户名不允许为空")
	@Column(name = "username")
	private String username;
	@NotNull(message="真实姓名不允许为空")
	@NotEmpty(message="真实姓名不允许为空")
	@NotBlank(message="真实姓名不允许为空")
	@Column(name = "realname")
	private String realname;
	@NotNull(message="密码不允许为空")
	@NotEmpty(message="密码不允许为空")
	@NotBlank(message="密码不允许为空")
	@Column(name = "password")
	private String password;
	@NotNull(message="手机号码不允许为空")
	@NotEmpty(message="手机号码不允许为空")
	@NotBlank(message="手机号码不允许为空")
	@Column(name = "phone")
	private String phone;
	@NotNull(message="创建日期不允许为空")
	@NotEmpty(message="创建日期不允许为空")
	@Column(name = "creatTime")
	private String creatTime;
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL,mappedBy = "vuser")
	List<VIPAddress> addresses = new ArrayList();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	

	public List<VIPAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<VIPAddress> addresses) {
		this.addresses = addresses;
	}

	public VIPUser(Integer id,  String username, String realname, String password, String phone,
			String creatTime) {
		super();
		this.id = id;
		this.username = username;
		this.realname = realname;
		this.password = password;
		this.phone = phone;
		this.creatTime = creatTime;
	}

	public VIPUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "VIPUser [id=" + id + ", username=" + username + ", realname=" + realname
				+ ", password=" + password + ", phone=" + phone + ", creatTime=" + creatTime + ", addresses="
				+ addresses + "]";
	}
	
	

}
