package com.jxgis.ffireserver.core.util;

import org.json.JSONException;

import java.sql.*;
import java.util.*;

/**
 * Created by YanWei on 2017/5/12.
 *
 * 数据库连接工具类
 */
public class DButil {


	private static String DBDriver = "";
	private static String DBURL = "";
	private static String DBUser = "";
	private static String DBPassWord = "";

	public DButil(){
		ResourceBundle resource = ResourceBundle.getBundle("db");

		DBDriver = resource.getString("jdbc.driver");
		DBURL = resource.getString("jdbc.url");
		DBUser = resource.getString("jdbc.username");
		DBPassWord = resource.getString("jdbc.password");

	}



	public Connection getConnection(){
		Connection con = null; //数据库连接状态

		boolean flag = false ; //通过数据库匹配标识
		String name_checked = null; //检查通过的用户名

		try{
			//连接
			Class.forName(DBDriver);//加载数据库驱动
			con = DriverManager.getConnection(DBURL, DBUser, DBPassWord);//连接
		} catch (Exception e){
			e.printStackTrace();
		}
		return con;
	}


	public boolean closeCon(Connection con, Statement stmt, ResultSet res){
		try{
			res.close();//依次关闭
		} catch (Exception e){
			e.printStackTrace();
		}

		try{
			stmt.close();
		} catch (Exception e){
			e.printStackTrace();
		}

		try{
			con.close();
		} catch (Exception e){
			e.printStackTrace();
		}



		return false;
	}
	public boolean closeCon(Connection con, Statement stmt){

		try{
			stmt.close();
		} catch (Exception e){
			e.printStackTrace();
		}

		try{
			con.close();
		} catch (Exception e){
			e.printStackTrace();
		}



		return false;
	}



	/**
	 * 判断数据类型
	 *
	 * @param obj
	 * @return
	 */
	public int pd(Object obj) {
		if (obj instanceof Integer) {
			return 1;
		}
		if (obj instanceof Double) {
			return 2;
		}
		if (obj instanceof Long) {
			return 3;
		}
		if (obj instanceof String) {
			return 4;
		}
		if (obj instanceof Boolean) {
			return 5;
		}
		if (obj instanceof Float) {
			return 6;
		}
		if (obj instanceof Short) {
			return 7;
		}

		return 0;
	}

	public List<Map<String, Object>> resultSetToJson(ResultSet rs) throws SQLException,JSONException
	{
		// json数组
		List array = new ArrayList();

		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			Map<String, Object> jsonObj = new HashMap<>();

			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName =metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.add(jsonObj);
		}

		return array;
	}

	public Map<String, Object> resultSetToJsonObject(ResultSet rs) throws SQLException,JSONException
	{
		// json数组
		Map<String, Object> jsonObj = new HashMap<>();

		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		// 遍历ResultSet中的每条数据
		while (rs.next()) {

			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName =metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
		}

		return jsonObj;
	}



	/**
	 * 组装参数
	 * */
	public PreparedStatement getPreparedStatement(Connection conn, String sql, Object... objs) throws SQLException,JSONException{

		PreparedStatement pstm=conn.prepareStatement(sql);
		int index = 1;
		for(Object o : objs){

			switch (pd( o) ){
				case 1: pstm.setInt(index, (int)o);break;
				case 2: pstm.setDouble(index, (double)o);break;
				case 3: pstm.setLong(index, (long)o);break;
				case 4: pstm.setString(index, (String) o);break;
				case 5: pstm.setBoolean(index, (boolean) o);break;
				case 6: pstm.setFloat(index, (float) o);break;
				case 7: pstm.setShort(index, (short) o);break;
			}

			index++;

		}
		return pstm;

	}

}
