package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "Tarea")
public class Area {

	@Id
	@GeneratedValue
	private int id;// id
	@Column(name = "pid")
	private int pid;// 父id
	@Column(name = "shortName")
	private String shortName;// 简称
	@NotNull(message = "名称不允许为空")
	@NotEmpty(message = "名称不允许为空")
	@NotBlank(message = "名称不允许为空")
	@Column(name = "name")
	private String name;// 名称
	@Column(name = "merger_name")
	private String merger_name;// 全称
	@Column(name = "level")
	private int level;// 层级 0 1 2 省市区县
	@Column(name = "pinyin")
	private String pinyin;// 拼音
	@Column(name = "code")
	private String code;// 长途区号
	@Column(name = "zip_code")
	private String zip_code;// 邮编
	@Column(name = "first")
	private String first;// 首字母
	@Column(name = "lng")
	private String lng;// 经度
	@Column(name = "lat")
	private String lat;// 纬度

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMerger_name() {
		return merger_name;
	}

	public void setMerger_name(String merger_name) {
		this.merger_name = merger_name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Area(int id, int pid, String shortName, String name, String merger_name, int level, String pinyin,
			String code, String zip_code, String first, String lng, String lat) {
		super();
		this.id = id;
		this.pid = pid;
		this.shortName = shortName;
		this.name = name;
		this.merger_name = merger_name;
		this.level = level;
		this.pinyin = pinyin;
		this.code = code;
		this.zip_code = zip_code;
		this.first = first;
		this.lng = lng;
		this.lat = lat;
	}

	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}

}
