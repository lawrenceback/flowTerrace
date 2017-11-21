package com.jxgis.ffireserver.web.service;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/6/6.
 *
 * 业务层基础接口
 *
 * 其他业务层应该继承该类进行拓展
 *
 * @author 言伟
 *
 */
public interface SystemService {

	Session getSession() throws Exception;

	/**
	 * 根据实体名称和主键获取实体
	 *
	 * @param <T>
	 * @param id
	 * @return
	 */
	<T> T get(Class<T> class1, Serializable id);


	<T> Serializable save(T entity);

	<T> void saveOrUpdate(T entity);

	<T> void delete(T entity);

	<T> void deleteEntityById(Class entityName, Serializable id);

	/**
	 * 删除实体集合
	 *
	 * @param <T>
	 * @param entities
	 */
	<T> void deleteAllEntitie(Collection<T> entities);

	<T> void batchSave(List<T> entitys);

	/**
	 * 更新指定的实体
	 *
	 * @param <T>
	 * @param pojo
	 */
	<T> void updateEntitie(T pojo);


	/**
	 * 按属性查找对象列表.
	 */
	<T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value);

	/**
	 * 根据实体名称和字段名称和字段值获取唯一记录
	 *
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	<T> T findUniqueByProperty(Class<T> entityClass,
	                                  String propertyName, Object value);

	/**
	 * 根据实体名称和主键获取实体
	 *
	 * @param <T>
	 *
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	<T> T getEntity(Class entityName, Serializable id);

	/**
	 * 加载全部实体
	 *
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	<T> List<T> loadAll(final Class<T> entityClass);

	/**
	 * 通过hql 查询语句查找对象
	 *
	 * @param <T>
	 * @param param
	 * @return
	 */
	<T> List<T> findHql(String hql, Object... param);


	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	List<Map<String, Object>> findForJdbc(String sql, Object... objs);

	/**
	 * 通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
	 */
	List<Map<String, Object>> findForJdbc(String sql, int page, int rows);

	/**
	 * 使用指定的检索标准检索数据并分页返回数据-采用预处理方式
	 *
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws DataAccessException
	 */
	List<Map<String, Object>> findForJdbcParam(String sql, int page, int rows, Object... objs);

	/**
	 * 通过JDBC查找对象数据
	 */
	Map<String, Object> findOneForJdbc(String sql, Object... objs);

	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC
	 */
	Long getCountForJdbc(String sql);

	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
	 *
	 */
	Long getCountForJdbcParam(String sql, Object[] objs);

	/**
	 * 日志添加
	 * @param LogContent 内容
	 * @param loglevel 级别
	 * @param operatetype 类型
	 * @param TUser 操作人
	 */
	void addLog(String LogContent, Short loglevel,Short operatetype);

	/**
	 * 执行SQL
	 */
	Integer executeSql(String sql, List<Object> param);

	/**
	 * 执行SQL
	 */
	Integer executeSql(String sql, Object... param);

	/**
	 * 执行SQL 使用:name占位符
	 */
	Integer executeSql(String sql, Map<String, Object> param);
	/**
	 * 执行SQL 使用:name占位符,并返回执行后的主键值
	 */
	Object executeSqlReturnKey(String sql, Map<String, Object> param);
}
