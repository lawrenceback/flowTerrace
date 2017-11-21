package com.jxgis.ffireserver.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by 言伟公共DAO【连接自管理】 on 2017/6/4.
 * 出现异常信息，向最外层抛出
 */
public interface CommonJdbcDao {

	/**
	 * 查询只有一条数据的情况
	 *
	 * @param sql
	 * @param objs
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> findOneForJdbc(String sql, Object... objs) throws Exception;

	/**
	 * 查询列表数据
	 *
	 * @param sql
	 * @param objs
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> findForJdbc(String sql, Object... objs) throws Exception;

	/***
	 * 执行更新或删除操作
	 * @param sql
	 * @param objs
	 * @return
	 * @throws Exception
	 */
	Integer executeForJdbc(String sql, Object... objs) throws Exception;


}
