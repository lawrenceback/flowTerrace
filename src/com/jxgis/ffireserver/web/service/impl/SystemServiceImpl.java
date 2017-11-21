package com.jxgis.ffireserver.web.service.impl;

import com.jxgis.ffireserver.core.dao.CommonDao;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.web.bean.TSLog;
import com.jxgis.ffireserver.web.service.SystemService;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/6/6.
 *
 * 业务层 基础实现类
 *
 * @author 言伟
 *
 */
@Service("systemService")
@Transactional
public class SystemServiceImpl implements SystemService {


	private CommonDao commonDao = null;

	@Resource
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}


	@Override
	public Session getSession() throws Exception {
		return commonDao.getSession();
	}

	@Override
	public <T> T get(Class<T> class1, Serializable id) {
		return this.commonDao.get(class1, id);
	}

	@Override
	public <T> Serializable save(T entity) {
		return this.commonDao.save(entity);
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		this.commonDao.saveOrUpdate(entity);
	}

	@Override
	public <T> void delete(T entity) {
		commonDao.delete(entity);
	}

	@Override
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		commonDao.deleteEntityById(entityName, id);
	}

	@Override
	public <T> void deleteAllEntitie(Collection<T> entities) {
		commonDao.deleteAllEntitie(entities);
	}


	@Override
	public <T> void batchSave(List<T> entitys) {
		this.commonDao.batchSave(entitys);
	}

	@Override
	public <T> void updateEntitie(T pojo) {
		this.commonDao.updateEntitie(pojo);
	}

	@Override
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
		return commonDao.findByProperty(entityClass, propertyName, value);
	}

	/**
	 * 根据实体名称和字段名称和字段值获取唯一记录
	 *
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass,
	                                  String propertyName, Object value){
		return this.commonDao.findUniqueByProperty(entityClass, propertyName, value);
	}

	@Override
	public <T> T getEntity(Class entityName, Serializable id) {
		return this.commonDao.getEntity(entityName, id);
	}

	/**
	 * 通过hql 查询语句查找对象
	 *
	 * @param hql
	 * @param param
	 * @return
	 */
	public <T> List<T> findHql(String hql, Object... param) {
		return this.commonDao.findHql(hql, param);
	}

	@Override
	public <T> List<T> loadAll(Class<T> entityClass) {
		return this.commonDao.loadAll(entityClass);
	}

	@Override
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
		return this.commonDao.findForJdbc(sql, objs);
	}

	/**
	 * 通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
	 */
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows){
		return this.commonDao.findForJdbc(sql, page, rows);
	}
	public List<Map<String, Object>> findForJdbcParam(String sql, int page, int rows, Object... objs) {
		return this.commonDao.findForJdbcParam(sql, page, rows, objs);
	}

	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		return this.commonDao.findOneForJdbc(sql, objs);
	}

	@Override
	public Long getCountForJdbc(String sql) {
		return this.commonDao.getCountForJdbc(sql);
	}

	@Override
	public Long getCountForJdbcParam(String sql, Object[] objs) {
		return this.commonDao.getCountForJdbcParam(sql, objs);
	}

	/**
	 * 添加日志
	 */
	public void addLog(String logcontent, Short loglevel, Short operatetype) {
		HttpServletRequest request = ContextHolderUtils.getRequest();
		String broswer = BrowserUtils.checkBrowse(request);
		TSLog log = new TSLog();
		log.setLogcontent(logcontent);
		log.setLoglevel(loglevel);
		log.setOperatetype(operatetype);
		log.setNote(oConvertUtils.getIp());
		log.setBroswer(broswer);
		log.setOperatetime(DateUtils.gettimestamp());
		log.setTSUser(ResourceUtil.getSessionUserName());
		commonDao.save(log);
	}

	@Override
	public Integer executeSql(String sql, List<Object> param) {
		return commonDao.executeSql(sql, param);
	}

	@Override
	public Integer executeSql(String sql, Object... param) {
		return commonDao.executeSql(sql, param);
	}

	@Override
	public Integer executeSql(String sql, Map<String, Object> param) {
		return executeSql(sql, param);
	}

	@Override
	public Object executeSqlReturnKey(String sql, Map<String, Object> param) {
		return executeSqlReturnKey(sql, param);
	}
}
