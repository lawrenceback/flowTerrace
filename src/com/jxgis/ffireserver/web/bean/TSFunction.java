package com.jxgis.ffireserver.web.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/6/8.
 *
 * 角色功能表
 *
 * @author 言伟
 */
@Entity
@Table(name = "t_s_function")
@org.hibernate.annotations.Proxy(lazy = false)
public class TSFunction extends IdEntity implements java.io.Serializable {

	private TSFunction functionParent;//父菜单
	private List<TSFunction> functions = new ArrayList<TSFunction>();
	private String functionName;//菜单名称
	private int functionLevel;//菜单等级
	private String functionUrl;//菜单地址
	private String functionOrder;//菜单排序
	private TSIcon icon;//菜单图标


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "function_parent_id")
	public TSFunction getFunctionParent() {
		return functionParent;
	}

	public void setFunctionParent(TSFunction functionParent) {
		this.functionParent = functionParent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "functionParent")
	public List<TSFunction> getFunctions() {
		return functions;
	}

	public void setFunctions(List<TSFunction> functions) {
		this.functions = functions;
	}

	@Column(name = "function_name")
	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	@Column(name = "function_level")
	public int getFunctionLevel() {
		return functionLevel;
	}

	public void setFunctionLevel(int functionLevel) {
		this.functionLevel = functionLevel;
	}

	@Column(name = "function_url")
	public String getFunctionUrl() {
		return functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	@Column(name = "function_order")
	public String getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "icon_id")
	public TSIcon getIcon() {
		return icon;
	}

	public void setIcon(TSIcon icon) {
		this.icon = icon;
	}

	public boolean hasSubFunction(Map<Integer, List<TSFunction>> map) {
		if(map.containsKey(this.getFunctionLevel()+1)){
			return hasSubFunction(map.get(this.getFunctionLevel()+1));
		}
		return false;
	}
	public boolean hasSubFunction(List<TSFunction> functions) {
		for (TSFunction f : functions) {
			if(f.getFunctionParent()!=null){
				if(f.getFunctionParent().getId().equals(this.getId())){
					return true;
				}
			}

		}
		return false;
	}
}
