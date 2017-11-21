package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YanWei on 2017/9/14.
 *
 * 网格人员管理表
 *
 */
@Entity
@Table(name = "ffp_gridUserTable")
public class FFPGridUserTable extends IdEntity implements java.io.Serializable {

	private String pac; //行政区管理
	private String name;    //姓名
	private Integer gender; //性别
	private Integer age;    //年龄
	private String post;    //职务
	private String phone;   //手机号码
	private String organization;    //单位职务
	private Integer type;   //人员类型；1-网格对应负责人员，2-网格对应工作人员
	private List<TSMediaFile> photo = new ArrayList<>();    //照片
	private Date createTime = new Date();

	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Transient
	public List<TSMediaFile> getPhoto() {
		return photo;
	}

	public void setPhoto(List<TSMediaFile> photo) {
		this.photo = photo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
