package com.jxgis.ffireserver.web.bean;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/6/8.
 *
 * 用户部门表（组织机构）
 *
 * @author 言伟
 *
 */
@Entity
@Table(name = "t_s_depart")
public class TSDepart extends IdEntity implements java.io.Serializable {

	private String departName;//部门名称
	private String description;//部门描述
	private String orgCode;//机构编码
	private Integer sorting;   //排序

	@Column(name = "depart_name", nullable = false, length = 100)
	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "org_code", length = 64,unique = true)
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getSorting() {
		return sorting;
	}

	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}
}
