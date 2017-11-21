package com.jxgis.ffireserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jxgis.ffireserver.bean.FireMedia;
import com.jxgis.ffireserver.bean.FireRecord;
import com.jxgis.ffireserver.dao.FireRecordDao;
import com.jxgis.ffireserver.service.bean.response.FireMediaList;

public class FireRecordDaoImpl extends BaseDao implements FireRecordDao {

	@Override
	public List<FireRecord> getFireRecordList() {
		Connection conn = getConnection();
		List<FireRecord> recordList = null;
		try {
			String sql = "select * from fire_record fr1 left join fire_media fm1 on fr1.record_number = fm1.record_number";
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				if (null == recordList) {
					recordList = new ArrayList<FireRecord>();
				}
				
				String number = rs.getString("record_number").trim();
				if (!recordList.isEmpty()) {
					FireRecord preFireRecord = recordList.get(recordList.size() - 1);
					// 上一个的火灾编号
					String preNumber = preFireRecord.getRecordNumber().trim();
					if (number.equals(preNumber)) {
						FireMedia media = loadFireMedia(rs);
						if (null != media) {
							if (null != preFireRecord.getFireMediaList()) {
								preFireRecord.getFireMediaList().getFireMedia().add(media);
							} else {
								List<FireMedia> medias = new ArrayList<FireMedia>();
								medias.add(media);
								FireMediaList fireMediaList = new FireMediaList(medias);
								preFireRecord.setFireMediaList(fireMediaList);
							}
						}
					} else {
						FireRecord record = loadFireRecord(rs);
						recordList.add(record);
					}
				} else {
					FireRecord record = loadFireRecord(rs);
					recordList.add(record);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return recordList;
	}
	
	/**
	 * 组装火灾档案对象
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private FireRecord loadFireRecord(ResultSet rs) throws SQLException {
		FireRecord record = new FireRecord();
		
		record.setRecordNumber(rs.getString("record_number"));
		record.setHotId(rs.getString("hot_id"));
		record.setName(rs.getString("name"));
		record.setAddress(rs.getString("address"));
		record.setLongitude(rs.getDouble("longitude"));
		record.setLatitude(rs.getDouble("latitude"));
		record.setsTime(rs.getString("s_time"));
		record.seteTime(rs.getString("e_time"));
		record.setFireType(rs.getString("fire_type"));
		record.setCause(rs.getString("cause"));
		record.setFireArea(rs.getDouble("fire_area"));
		record.settCateg(rs.getString("t_categ"));
		record.setConductor(rs.getString("conductor"));
		record.setBrigade(rs.getInt("brigade"));
		record.setEquip(rs.getInt("equip"));
		record.setFightCost(rs.getDouble("fight_cost"));
		record.setDescription(rs.getString("description"));
		record.setCreTime(rs.getString("cre_time"));
		record.setCrePers(rs.getString("cre_pers"));
		record.setModTime(rs.getString("mod_time"));
		record.setModPers(rs.getString("mod_pers"));
		record.setCity(rs.getString("city"));
		record.setCounty(rs.getString("county"));
		
		FireMedia media = loadFireMedia(rs);
		
		if (null != media) {
			List<FireMedia> medias = new ArrayList<FireMedia>();
			medias.add(media);
			FireMediaList fireMediaList = new FireMediaList(medias);
			record.setFireMediaList(fireMediaList);
		}
		
		return record;
	}
	
	/**
	 * 组装火灾媒体信息对象
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private FireMedia loadFireMedia(ResultSet rs) throws SQLException {
		FireMedia media = null;
		String filename = rs.getString("filename");
		if (null != filename && !filename.isEmpty()) {
			media = new FireMedia();
			media.setRecordNumber(rs.getString("record_number"));
			media.setFilename(filename);
		}
		
		return media;
	}

}
