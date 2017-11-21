package com.jxgis.ffireserver.util;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtil {

	private static String url;
	private static String driver;
	private static String username;
	private static String password;
	private static BasicDataSource ds;

	// connHolder为每一个线程保存一个对应的connection对象。
	private static ThreadLocal<Connection> connHolder = new ThreadLocal<Connection>();

	static {
		Properties props = new Properties();
		try {
			// 从属性文件中读取数据库配置信息
			props.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {

		}

		if (props != null) {
			url = props.getProperty("jdbc.url");
			driver = props.getProperty("jdbc.driver");
			username = props.getProperty("jdbc.username");
			password = props.getProperty("jdbc.password");

			ds = new BasicDataSource();
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);

			// 装载并注册数据库驱动
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
			}
		}
	}

	public static Connection getConnection() {
		Connection conn = connHolder.get();
		if (conn == null) {
			conn = getConn();
			connHolder.set(conn);
		}
		return conn;
	}

	public static void close() {
		Connection conn = connHolder.get();
		if (conn != null) {
			close(conn);
			connHolder.remove();
		}
	}

	private static Connection getConn() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			// conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	private static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
