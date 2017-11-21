package com.jxgis.ffireserver.util;

/**
 * Created by YanWei on 2017/8/17.
 *
 * 广播对象
 *
 */
public class RadioBody {

	private String id;
	private RadioUser user;
	private int type;
	private int subType;
	private String content;
	private Object data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RadioUser getUser() {
		return user;
	}

	public void setUser(RadioUser user) {
		this.user = user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
