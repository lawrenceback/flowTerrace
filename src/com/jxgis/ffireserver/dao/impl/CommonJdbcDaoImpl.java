package com.jxgis.ffireserver.dao.impl;

import com.jxgis.ffireserver.core.util.DButil;
import com.jxgis.ffireserver.core.util.MapUtils;
import com.jxgis.ffireserver.core.util.StringUtil;
import com.jxgis.ffireserver.dao.CommonJdbcDao;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * Created by 言伟公共DAO实现类【连接自管理】 on 2017/6/4.
 *
 */
public class CommonJdbcDaoImpl extends DButil implements CommonJdbcDao {


	@Override
	public Map<String, Object> findOneForJdbc(String sql, Object... objs) throws Exception {
		Connection conn = getConnection();

		PreparedStatement pstm = getPreparedStatement(conn, sql, objs);

		ResultSet res = pstm.executeQuery();
		Map<String, Object> r = resultSetToJsonObject(res);
		closeCon(conn, pstm, res);

		return r;
	}

	@Override
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) throws Exception {
		Connection c = getConnection();

		PreparedStatement pstm = getPreparedStatement(c, sql, objs);

		ResultSet res = pstm.executeQuery();
		List<Map<String, Object>> r = resultSetToJson(res);
		closeCon(c, pstm, res);
		return r;
	}

	@Override
	public Integer executeForJdbc(String sql, Object... objs) throws Exception {

		Connection conn = getConnection();

		PreparedStatement pstm = getPreparedStatement(conn, sql, objs);
		Integer rows = pstm.executeUpdate();
		closeCon(conn, pstm);
		return rows;
	}



}
