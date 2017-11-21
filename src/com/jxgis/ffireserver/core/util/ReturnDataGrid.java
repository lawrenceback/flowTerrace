package com.jxgis.ffireserver.core.util;


import com.jxgis.ffireserver.util.Glossary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanWei on 2017/6/19.
 *
 * ReturnDataGrid
 *
 */
public class ReturnDataGrid {
	private int page;
	private long total;
	private int status = Glossary.Return_State_Success;
	private String msg = "success";
	private List rows = new ArrayList();

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

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

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
