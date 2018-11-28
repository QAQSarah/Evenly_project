package com.test.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "TAddress")
public class VIPAddress {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "province")
	private String province;
	@Column(name = "city")
	private String city;
	@Column(name = "county")
	private String county;
	@Column(name = "detailAddress")
	private String detailAddress;
	
	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name = "vip_id")
	private VIPUser vuser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public VIPUser getVuser() {
		return vuser;
	}

	public void setVuser(VIPUser vuser) {
		this.vuser = vuser;
	}

	
	public VIPAddress(Integer id, String province, String city, String county, String detailAddress, VIPUser vuser) {
		super();
		this.id = id;
		this.province = province;
		this.city = city;
		this.county = county;
		this.detailAddress = detailAddress;
		this.vuser = vuser;
	}

	public VIPAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*@Override
	public String toString() {
		return "VIPAddress [id=" + id + ", province=" + province + ", city=" + city + ", county=" + county + ", vuser="
				+ vuser + "]";
	}*/
	
	

}
