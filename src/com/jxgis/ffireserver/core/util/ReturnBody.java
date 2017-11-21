package com.jxgis.ffireserver.core.util;

import com.jxgis.ffireserver.util.Glossary;

/**
 * Created by YanWei on 2017/6/12.
 * 返回值
 */
public class ReturnBody {

	private int status = Glossary.Return_State_Success;
	private String msg = "success";
	private Object obj = null;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
