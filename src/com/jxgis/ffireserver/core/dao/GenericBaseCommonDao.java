package com.jxgis.ffireserver.core.dao;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * 类描述：DAO层泛型基类接口
 *
 * 张代浩
 * @date： 日期：2012-12-8 时间：下午05:37:33
 * @version 1.0
 */
public interface GenericBaseCommonDao {


	Session getSession() throws Exception;

	/**
	 * 根据实体名称和主键获取实体
	 *
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	<T> T get(Class<T> entityName, Serializable id);

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

	<T> Serializable save(T entity);

	/**
	 * 更新指定的实体
	 *
	 * @param <T>
	 * @param pojo
	 */
	<T> void updateEntitie(T pojo);


	/**
	 * 根据sql查找List
	 *
	 * @param <T>
	 * @param query
	 * @return
	 */
	<T> List<T> findListbySql(String query);

	/**
	 * 通过hql 查询语句查找对象
	 *
	 * @param <T>
	 * @param hql
	 * @return
	 */
	<T> List<T> findByQueryString(String hql);

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
	<T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value);

	/**
	 * 通过属性称获取实体带排序
	 *
	 * @param <T>
	 * @param propertyName
	 * @return
	 */
	 <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc);

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
	 * 通过hql 查询语句查找对象
	 *
	 * @param <T>
	 * @param param
	 * @return
	 */
	<T> List<T> findHql(String hql, Object... param);

	/**
	 * 加载全部实体
	 *
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	<T> List<T> loadAll(final Class<T> entityClass);


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
	 * */
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
