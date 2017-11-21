package com.jxgis.ffireserver.web.bean;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.*;

@Entity
@Table(name = "t_s_user")
@PrimaryKeyJoinColumn(name = "id")
@XmlRootElement(name = "user")
public class TSUser extends TSBaseUser implements java.io.Serializable
{

	private String phone;
	private Integer competence; // -999_admin;-1：普通用户 0：省级用户1：市级用户2：县级用户
	private Date lastTime;
	private Date createTime;    //注册时间
	private String lastIp;	    //最后一次
	private Integer accountType;        //账号类型：超级管理员_-1,系统用户_1
	private String pac;      //账户行政区码

	private List<TSRole> roles = new ArrayList<>();
	private List<Map<String,Object>> departs = new ArrayList<>();


	public Integer getCompetence() {
		return competence;
	}

	public void setCompetence(Integer competence) {
		this.competence = competence;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "last_time")
	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}


	@Column(name = "account_type")
	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	@Column(name = "pac")
	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}


	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Transient
	public List<TSRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TSRole> roles) {
		this.roles = roles;
	}


	@Transient
	public List<Map<String, Object>> getDeparts() {
		return departs;
	}

	public void setDeparts(List<Map<String, Object>> departs) {
		this.departs = departs;
	}



	public Map<String, Object> loginReturn(){
		Map<String, Object> map = new HashMap<>();
		map.put("id", this.getId());
		map.put("pac", this.getPac());
		map.put("account", this.getAccount());
		map.put("name", this.getName());
		map.put("headPortrait", this.getHeadPortrait());
		map.put("competence", this.getCompetence());
		map.put("phone", this.getPhone());
		return map;
	}


}
