package com.jxgis.ffireserver.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxgis.ffireserver.util.DBUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseDao {
	public Connection getConnection() {
		return DBUtil.getConnection();
	}

	public void closeConnection() {
		DBUtil.close();
	}

}
