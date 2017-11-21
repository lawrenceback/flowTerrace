package com.jxgis.ffireserver.web.bean;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/6/8.
 *
 * 基础用户表
 *
 * @author 言伟
 *
 */
@Entity
@Table(name = "t_s_base_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class TSBaseUser extends IdEntityAtuo implements java.io.Serializable{

	private String account;     //登陆帐号
	private String password;    //登陆密码
	private String headPortrait;//头像
	private String name;        //用户名
	private Integer state;      //用户状态 0/null_正常,1_冻结


	@Column(nullable = false, unique = true)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(columnDefinition = "LONGTEXT DEFAULT NULL", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "head_portrait", columnDefinition = "LONGTEXT DEFAULT NULL")
	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(columnDefinition = "int default 0")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
