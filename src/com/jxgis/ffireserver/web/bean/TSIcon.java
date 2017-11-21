package com.jxgis.ffireserver.web.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by YanWei on 2017/6/16.
 *
 * 菜单图标,使用阿里图标库 + Font Awesome
 *
 * @author 言伟
 */
@Entity
@Table(name = "t_s_icon")
public class TSIcon extends IdEntity implements java.io.Serializable {
	private String iconName;
	private String iconClass;

	@Column(name = "icon_name")
	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	@Column(name = "icon_class")
	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
}
