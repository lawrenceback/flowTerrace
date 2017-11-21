package com.jxgis.ffireserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jxgis.ffireserver.bean.Monitor;
import com.jxgis.ffireserver.dao.MonitorDao;

public class MonitorDaoImpl extends BaseDao implements MonitorDao {

	@Override
	public List<Monitor> getMonitors() {
		Connection conn = getConnection();
		String sql = "SELECT * FROM monitor where longitude != 0 and latitude != 0";
		ArrayList<Monitor> monitorList = null;
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			monitorList = new ArrayList<Monitor>();
			while (rs.next()) {
				Monitor monitor = new Monitor();
				
				monitor.setId(rs.getInt("id"));
				monitor.setCity(rs.getString("city"));
				monitor.setCountry(rs.getString("country"));
				monitor.setPac(rs.getString("pac"));
				monitor.setAddress(rs.getString("address"));
				monitor.setIp(rs.getString("ip"));
				monitor.setPort(rs.getInt("port"));
				monitor.setChannel(rs.getInt("channel"));
				monitor.setLongitude(rs.getDouble("longitude"));
				monitor.setLatitude(rs.getDouble("latitude"));
				monitor.setElevation(rs.getInt("elevation"));
				monitor.setAccount(rs.getString("account"));
				monitor.setPassword(rs.getString("password"));
				
				monitorList.add(monitor);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return monitorList;
	}

	@Override
	public boolean insert(Monitor monitor) {
		Connection conn = getConnection();
		String sql = "insert into monitor (city, country, pac, address, ip, port, channel, longitude, latitude, elevation, account, password) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, monitor.getCity());
			psmt.setString(2, monitor.getCountry());
			psmt.setString(3, monitor.getPac());
			psmt.setString(4, monitor.getAddress());
			psmt.setString(5, monitor.getIp());
			psmt.setInt(6, monitor.getPort());
			psmt.setInt(7, monitor.getChannel());
			psmt.setDouble(8, monitor.getLongitude());
			psmt.setDouble(9, monitor.getLatitude());
			psmt.setDouble(10, monitor.getElevation());
			psmt.setString(11, monitor.getAccount());
			psmt.setString(12, monitor.getPassword());
			
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeConnection();
		}
		return true;
	}

	@Override
	public boolean update(Monitor monitor) {
		Connection conn = getConnection();
		int result = 0;
		try {
			String sql = "UPDATE monitor SET city = ?, country = ?, pac = ?, address = ?, ip = ?, port = ?, channel = ?, longitude = ?, latitude = ?, elevation = ?, account = ?, password = ? WHERE id = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, monitor.getCity());
			psmt.setString(2, monitor.getCountry());
			psmt.setString(3, monitor.getPac());
			psmt.setString(4, monitor.getAddress());
			psmt.setString(5, monitor.getIp());
			psmt.setInt(6, monitor.getPort());
			psmt.setInt(7, monitor.getChannel());
			psmt.setDouble(8, monitor.getLongitude());
			psmt.setDouble(9, monitor.getLatitude());
			psmt.setDouble(10, monitor.getElevation());
			psmt.setString(11, monitor.getAccount());
			psmt.setString(12, monitor.getPassword());
			psmt.setInt(13, monitor.getId());
			
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return result > 0;
	}

	@Override
	public void delete(int id) {
		Connection conn = getConnection();
		try {
			String sql = "DELETE FROM monitor WHERE id = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public void delete(String id) {
		delete(Integer.parseInt(id));
	}

}
