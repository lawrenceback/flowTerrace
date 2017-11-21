package com.jxgis.ffireserver.web.service.impl;

import com.jxgis.ffireserver.bean.openfire.Ofuser;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.util.FormatPac;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.*;
import com.jxgis.ffireserver.web.service.TSUserService;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by YanWei on 2017/6/15.
 *
 * 用户管理业务层
 *
 * @author 言伟
 *
 *
 */
@Service("userService")
@Transactional
public class TSUserServiceImpl extends SystemServiceImpl implements TSUserService {

	@Override
	public ReturnDataGrid pacByUser(TSUser user, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(TSUser.class);


		if ( !StringUtil.isEmpty(user.getPac()) ){
			switch (fetchType){
				case 1:
					//及下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(user.getPac(), true)) );
					criteria.add(Restrictions.ne("pac", user.getPac()));
					break;
				case 2:
					//查询所有下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(user.getPac()) + "%") );
					break;
				case 3:
					//只查询下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(user.getPac())) );
					break;
				default:
					//默认 .eq()
					criteria.add( Restrictions.eq("pac", user.getPac() ) );
					break;
			}
		}

		if ( !StringUtil.isEmpty(user.getAccount())){
			criteria.add( Restrictions.like("account", "%" +user.getAccount() + "%") );
		}

		if ( !StringUtil.isEmpty(user.getPhone())){
			criteria.add( Restrictions.like("phone", "%" +user.getPhone() + "%") );
		}

		if ( !StringUtil.isEmpty(user.getName()) ){
			criteria.add( Restrictions.like("name", "%" +user.getName() + "%") );
		}

		if ( StringUtil.isNotEmpty(user.getCompetence()) && user.getCompetence() > 0){
			criteria.add( Restrictions.eq("competence", user.getCompetence()) );
		}

//		TSUser lobin_user = ResourceUtil.getSessionUserName();
//		if (!StringUtil.isNotEmpty(lobin_user)){
//			criteria.add( Restrictions.ne("competence", -999) );
//		}

		criteria.add( Restrictions.ne("competence", -999) );


		if ( StringUtil.isNotEmpty(user.getAccountType()) && user.getAccountType() > 0){
			criteria.add( Restrictions.eq("accountType", user.getAccountType()) );
		}

		if ( !StringUtil.isEmpty(dataGrid.getSort())){
			if (dataGrid.getOrder().name().equals("asc")) {

				criteria.addOrder( Order.asc(dataGrid.getSort()) );

			} else {

				criteria.addOrder( Order.desc(dataGrid.getSort()) );

			}

		}



		if (dataGrid.getRows()<1){
			grid.setPage(dataGrid.getPage());
			grid.setRows(criteria.list());
			grid.setTotal(criteria.list().size());
			return grid;
		}

		grid.setTotal(criteria.list().size());
		criteria.setMaxResults(dataGrid.getRows());
		criteria.setFirstResult( (dataGrid.getPage()-1) * dataGrid.getRows());
		grid.setPage(dataGrid.getPage());

		List<TSUser> list = criteria.list();

		for(TSUser user_db: list){

			//查询角色
			StringBuilder hql_sb =new StringBuilder("select distinct r from TSRole r,TSRoleUser ru ").append("where ru.role.id=r.id and ru.user.id=? ");

			List<TSRole> roles = findHql(hql_sb.toString(), user_db.getId());

			user_db.setRoles(roles);

		}
		grid.setRows(list);
		return grid;
	}


	@Override
	public ReturnDataGrid pacByUser(TSUser user, DataGrid dataGrid, int fetchType, HttpServletRequest request) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(TSUser.class);


		if ( !StringUtil.isEmpty(user.getPac()) ){
			switch (fetchType){
				case 1:
					//及下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(user.getPac(), true)) );
					criteria.add(Restrictions.ne("pac", user.getPac()));
					break;
				case 2:
					//查询所有下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(user.getPac()) + "%") );
					break;
				case 3:
					//只查询下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(user.getPac())) );
					break;
				default:
					//默认 .eq()
					criteria.add( Restrictions.eq("pac", user.getPac() ) );
					break;
			}
		}

		Disjunction dis = Restrictions.disjunction();
		if ( !StringUtil.isEmpty(user.getAccount())){
			dis.add( Restrictions.like("account", "%" +user.getAccount() + "%") );
		}

		if ( !StringUtil.isEmpty(user.getPhone())){
			dis.add( Restrictions.like("phone", "%" +user.getPhone() + "%") );
		}

		if ( !StringUtil.isEmpty(user.getName()) ){
			dis.add( Restrictions.like("name", "%" +user.getName() + "%") );
		}
		criteria.add(dis);

		if ( StringUtil.isNotEmpty(user.getCompetence()) && user.getCompetence() > 0){
			criteria.add( Restrictions.eq("competence", user.getCompetence()) );
		}

//		TSUser lobin_user = ResourceUtil.getSessionUserName();
//		if (!StringUtil.isNotEmpty(lobin_user)){
//			criteria.add( Restrictions.ne("competence", -999) );
//		}



		criteria.add( Restrictions.or(Restrictions.isNull("competence"), Restrictions.ne("competence", -999) ));


		if (StringUtil.isNotEmpty(user.getState())){
			criteria.add( Restrictions.eq("state", user.getState()));
		}

		if ( StringUtil.isNotEmpty(user.getAccountType())){
			criteria.add( Restrictions.eq("accountType", user.getAccountType()) );
		}


		if ( !StringUtil.isEmpty(dataGrid.getSort())){
			if (dataGrid.getOrder().name().equals("asc")) {

				criteria.addOrder( Order.asc(dataGrid.getSort()) );

			} else {

				criteria.addOrder( Order.desc(dataGrid.getSort()) );

			}

		}



		if (dataGrid.getRows()<1){
			grid.setPage(dataGrid.getPage());
			grid.setRows(criteria.list());
			grid.setTotal(criteria.list().size());
			return grid;
		}

		grid.setTotal(criteria.list().size());
		criteria.setMaxResults(dataGrid.getRows());
		criteria.setFirstResult( (dataGrid.getPage()-1) * dataGrid.getRows());
		grid.setPage(dataGrid.getPage());

		List<TSUser> list = criteria.list();

		for(TSUser user_db: list){

			//查询角色
			StringBuilder hql_sb =new StringBuilder("select distinct r from TSRole r,TSRoleUser ru ").append("where ru.role.id=r.id and ru.user.id=? ");

			List<TSRole> roles = findHql(hql_sb.toString(), user_db.getId());

			user_db.setRoles(roles);



			//查询部门
			hql_sb = new StringBuilder("select distinct d from TSDepart d,TSDepartUser du ").append("where du.depart.id=d.id and du.user.id=? order by d.orgCode asc");

			List<TSDepart> departs = findHql(hql_sb.toString(), user_db.getId());

			if (departs.size() > 0){

				TSDepart depart = departs.get(0);

				request.getSession().setAttribute("orgCodes", new ArrayList<String>());

				List<String> orgCodes = peggingOrgCode(oConvertUtils.getString(depart.getOrgCode()), request);

				if (orgCodes.size() > 0){

					String sql = "SELECT * FROM t_s_depart WHERE 1=1 ";
					List<Object> sql_like = new ArrayList<>();
					int i=0;
					for(String orgCode: orgCodes){
						if (i==0){
							sql += " and org_code=?";
							sql_like.add(orgCode);
						} else {
							sql += " or org_code=?";
							sql_like.add(orgCode);
						}
						i++;
					}
					sql += " ORDER BY org_code ASC";

					user_db.setDeparts(findForJdbc(sql, sql_like.toArray()));

				}

			}

		}
		grid.setRows(list);
		return grid;
	}

	@Override
	public ReturnBody getUser(TSUser user, HttpServletRequest request) throws Exception {
		ReturnBody body = new ReturnBody();

		if (!StringUtil.isNotEmpty(user)){
			body.setStatus(Glossary.Return_State_User_Not_Exist);
			body.setMsg("parameter error.");
			return body;
		}


		TSUser user_db = get(TSUser.class, user.getId());
		if ( !StringUtil.isNotEmpty(user_db)){

			user_db = findUniqueByProperty(TSUser.class, "account", oConvertUtils.getString(user.getAccount()));

			if (!StringUtil.isNotEmpty(user_db)){
				body.setStatus(Glossary.Return_State_User_Not_Exist);
				body.setMsg("parameter error [account].");
				return body;
			}

		}

		//查询角色
		StringBuilder hql_sb =new StringBuilder("select distinct r from TSRole r,TSRoleUser ru ").append("where ru.role.id=r.id and ru.user.id=? ");

		List<TSRole> roles = findHql(hql_sb.toString(), user_db.getId());

		user_db.setRoles(roles);



		//查询部门
		hql_sb = new StringBuilder("select distinct d from TSDepart d,TSDepartUser du ").append("where du.depart.id=d.id and du.user.id=? order by d.orgCode asc");

		List<TSDepart> departs = findHql(hql_sb.toString(), user_db.getId());

		if (departs.size() > 0){

			TSDepart depart = departs.get(0);

			request.getSession().setAttribute("orgCodes", new ArrayList<String>());

			List<String> orgCodes = peggingOrgCode(oConvertUtils.getString(depart.getOrgCode()), request);

			if (orgCodes.size() > 0){

				String sql = "SELECT * FROM t_s_depart WHERE 1=1 ";
				List<Object> sql_like = new ArrayList<>();
				int i=0;
				for(String orgCode: orgCodes){
					if (i==0){
						sql += " and org_code=?";
						sql_like.add(orgCode);
					} else {
						sql += " or org_code=?";
						sql_like.add(orgCode);
					}
					i++;
				}
				sql += " ORDER BY org_code ASC";

				user_db.setDeparts(findForJdbc(sql, sql_like.toArray()));

			}

		}
		body.setObj(user_db);

		return body;
	}

	@Override
	public ReturnBody addUser(TSUser user) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isEmpty(user.getAccount())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [account],Must not be empty");
			return body;
		}
		if (StringUtil.isEmpty(user.getPassword())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [password],Must not be empty");
			return body;
		}

		if (StringUtil.isEmpty(user.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [name],Must not be empty");
			return body;
		}
		if (StringUtil.isEmpty(user.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [pac],Must not be empty");
			return body;
		}

		//验证用户是否存在
		TSUser user_db = this.findUniqueByProperty(TSUser.class, "account", user.getAccount());
		if (StringUtil.isNotEmpty(user_db)){
			body.setStatus(Glossary.Return_State_User_Exist);
			body.setMsg("Parameter error [account],TSUser already exists");
			return body;
		}

		user.setHeadPortrait("images/defaultPic.png");

		//这里对密码进行加密
		user.setPassword(PasswordUtil.encrypt(user.getAccount(), user.getPassword(), PasswordUtil.getStaticSalt()));
		user.setAccountType(1);//
		user.setCreateTime(new Date());
		this.save(user);
		return body;
	}

	@Override
	public ReturnBody addUser(TSUser user, MultipartFile headPortrait, List<TSRole> roles, TSDepart depart) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isEmpty(user.getAccount())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [account],Must not be empty");
			return body;
		}
		if (StringUtil.isEmpty(user.getPassword())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [password],Must not be empty");
			return body;
		}

		if (StringUtil.isEmpty(user.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [name],Must not be empty");
			return body;
		}
		if (StringUtil.isEmpty(user.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [pac],Must not be empty");
			return body;
		}


		Ofuser ofuser = get(Ofuser.class, user.getAccount());
		if (StringUtil.isNotEmpty(ofuser)){
			body.setStatus(Glossary.Return_State_User_Exist);
			body.setMsg("Parameter error [Openfire username],TSUser already exists");
			return body;
		}

		//验证用户是否存在
		TSUser user_db = this.findUniqueByProperty(TSUser.class, "account", user.getAccount());
		if (StringUtil.isNotEmpty(user_db)){
			body.setStatus(Glossary.Return_State_User_Exist);
			body.setMsg("Parameter error [account],TSUser already exists");
			return body;
		}

		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		if (StringUtil.isNotEmpty(headPortrait)){

			try {

				// 原始文件名称
				String filename = FileUtils.getFilePrefix2(headPortrait.getOriginalFilename());
				// 即将保存的文件名称
				String saveFileName = DateUtils.date2Str(DateUtils.yyyymmddhhmmss) + (int) ((Math.random() * 9 + 1) * 100000) + "." + FileUtils.getExtend(headPortrait.getOriginalFilename());
				// 得到URL的输入流
				InputStream input = headPortrait.getInputStream();
				// 设置数据缓冲
				byte[] bs = new byte[1024 * 2];
				// 读取到的数据长度
				int len;
				// 输出的文件流保存图片至本地
				OutputStream os;
				String path = projectDirectory + "files/userfiles/" + user.getId();

				FileUtils.createDir(path);

				//数据库存储的值
				String savePath = "files/userfiles/" + user.getId() + "/" + saveFileName;

				os = new FileOutputStream(path + "/" + saveFileName);
				while ((len = input.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
				os.close();
				input.close();

				user.setHeadPortrait(savePath);
			} catch (Exception e){
				e.printStackTrace();
			}

		} else {
			user.setHeadPortrait("images/defaultPic.png");
		}


		//这里对密码进行加密
		user.setPassword(PasswordUtil.encrypt(user.getAccount(), user.getPassword(), PasswordUtil.getStaticSalt()));
		user.setAccountType(1);//
		user.setCreateTime(new Date());
		this.save(user);



		//保存角色信息
		List<TSRoleUser> roleUsers = new ArrayList<>();
		for(TSRole role: roles){
			role = get(TSRole.class, oConvertUtils.getString(role.getId()));
			if (StringUtil.isNotEmpty(role)){
				TSRoleUser roleUser = new TSRoleUser();
				roleUser.setRole(role);
				roleUser.setUser(user);
				roleUsers.add(roleUser);
			}
		}
		batchSave(roleUsers);


		if (StringUtil.isNotEmpty(depart)){

			depart = get(TSDepart.class, oConvertUtils.getString(depart.getId()));
			if (StringUtil.isNotEmpty(depart)){
				//保存部门
				TSDepartUser departUser = new TSDepartUser();
				departUser.setDepart(depart);
				departUser.setUser(user);
				save(departUser);
			}

		}


		Map<String, Object> mp = new HashMap<>();
		mp.put("id", user.getId());
		mp.put("account", user.getAccount());
		body.setObj(mp);
		return body;
	}

	@Override
	public ReturnBody delUser(TSUser user) throws Exception {
		ReturnBody body = new ReturnBody();
		user = this.get(TSUser.class, user.getId());

		if (StringUtil.isNotEmpty(user)){
			List<TSRoleUser> roleUsers = findByProperty(TSRoleUser.class, "user.id", user.getId());
			deleteAllEntitie(roleUsers);

			List<TSDepartUser> departUsers = findByProperty(TSDepartUser.class, "user.id", user.getId());
			deleteAllEntitie(departUsers);

			this.delete(user);
			return body;
		}

		body.setStatus(Glossary.Return_State_User_Not_Exist);
		body.setMsg("Parameter error [user],user does not exist.");
		return body;
	}

	@Override
	public ReturnBody delUsers(List<TSUser> users) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(users)){
			body.setStatus(Glossary.Return_State_User_Not_Exist);
			body.setMsg("Parameter error.");
			return body;
		}
		for(TSUser user: users){
			delUser(user);
		}
		return body;
	}

	@Override
	public ReturnBody setUserRole(TSUser user, List<TSRoleUser> roleUsers) throws Exception {
		ReturnBody body = new ReturnBody();
		user = get(TSUser.class, user.getId());
		if (!StringUtil.isNotEmpty(user)){
			body.setStatus(Glossary.Return_State_Data_Not_Exist);
			body.setMsg("Data does not exist.");
			return body;
		}
		List<TSRoleUser> roleUsers_db = findByProperty(TSRoleUser.class, "user.id", user.getId());
		List<TSRoleUser> roleUsers_db_back = findByProperty(TSRoleUser.class, "user.id", user.getId());

		try{
			//在设置前，先清除现有的用户角色
			deleteAllEntitie(roleUsers_db);

		} catch (HibernateException e){
			e.printStackTrace();
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			return body;
		}

		try{
			//保存新的用户角色
			batchSave(roleUsers);

		} catch (HibernateException e){
			e.printStackTrace();

			try{
				//保存新用户角色失败时，应还原该用户原本的用户角色
				batchSave(roleUsers_db_back);

			} catch (HibernateException j){
				j.printStackTrace();
				System.out.println("============================ 角色权限还原失败【角色ID：“ "+user.getId()+" ”】 =================================");
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.Role restore failure.");
				return body;
			}
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			return body;
		}

		return body;
	}

	@Override
	public ReturnBody editUser(TSUser user, MultipartFile headPortrait) throws Exception {
		ReturnBody body = new ReturnBody();
		TSUser user_db = get(TSUser.class, user.getId());

		if (StringUtil.isNotEmpty(user_db)){

			if (!StringUtil.isEmpty(user.getPassword())){
				//修改用户信息不允许修改密码，如果 调用者，传入了”password“，在这里应该还原密码
				user.setPassword(user_db.getPassword());
			}

			// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
			String projectDirectory = ResourceUtil.getSysPath();
			if (StringUtil.isNotEmpty(headPortrait)){

				try {

					// 原始文件名称
					String filename = FileUtils.getFilePrefix2(headPortrait.getOriginalFilename());
					// 即将保存的文件名称
					String saveFileName = DateUtils
							.date2Str(DateUtils.yyyymmddhhmmss)
							+ (int) ((Math.random() * 9 + 1) * 100000)
							+ "."
							+ FileUtils.getExtend(headPortrait.getOriginalFilename());
					// 得到URL的输入流
					InputStream input = headPortrait.getInputStream();
					// 设置数据缓冲
					byte[] bs = new byte[1024 * 2];
					// 读取到的数据长度
					int len;
					// 输出的文件流保存图片至本地
					OutputStream os;
					String path = projectDirectory + "files/userfiles/" + user.getId();

					FileUtils.createDir(path);

					//数据库存储的值
					String savePath = "files/userfiles/" + user.getId() + "/" + saveFileName;

					os = new FileOutputStream(path + "/" + saveFileName);
					while ((len = input.read(bs)) != -1) {
						os.write(bs, 0, len);
					}
					os.close();
					input.close();

					if (!oConvertUtils.isEmpty(user_db.getHeadPortrait())){
						FileUtils.deleteFile(projectDirectory+ user_db.getHeadPortrait());
					}

					user.setHeadPortrait(savePath);
				} catch (Exception e){
					e.printStackTrace();
				}

			}

			MyBeanUtils.copyBeanNotNull2Bean(user, user_db);
			updateEntitie(user_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Data does not exist.");
		return body;
	}

	@Override
	public ReturnBody editUser(TSUser user, MultipartFile headPortrait, TSDepartUser departUser, String password) throws Exception {
		ReturnBody body = new ReturnBody();
		TSUser user_db = get(TSUser.class, user.getId());

		if (!StringUtil.isNotEmpty(user_db)){
			body.setStatus(Glossary.Return_State_Data_Not_Exist);
			body.setMsg("Data does not exist.");
			return body;
		}


		if (!StringUtil.isEmpty(password)){
			user.setPassword(PasswordUtil.encrypt(user_db.getAccount(), password, PasswordUtil.getStaticSalt()));
		} else {
			//修改用户信息不允许修改密码，如果 调用者，传入了”password“，在这里应该还原密码
			user.setPassword(user_db.getPassword());
		}


		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		if (StringUtil.isNotEmpty(headPortrait)){

			try {

				// 原始文件名称
				String filename = FileUtils.getFilePrefix2(headPortrait.getOriginalFilename());
				// 即将保存的文件名称
				String saveFileName = DateUtils
						.date2Str(DateUtils.yyyymmddhhmmss)
						+ (int) ((Math.random() * 9 + 1) * 100000)
						+ "."
						+ FileUtils.getExtend(headPortrait.getOriginalFilename());
				// 得到URL的输入流
				InputStream input = headPortrait.getInputStream();
				// 设置数据缓冲
				byte[] bs = new byte[1024 * 2];
				// 读取到的数据长度
				int len;
				// 输出的文件流保存图片至本地
				OutputStream os;
				String path = projectDirectory + "files/userfiles/" + user.getId();

				FileUtils.createDir(path);

				//数据库存储的值
				String savePath = "files/userfiles/" + user.getId() + "/" + saveFileName;

				os = new FileOutputStream(path + "/" + saveFileName);
				while ((len = input.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
				os.close();
				input.close();

				if (!oConvertUtils.isEmpty(user_db.getHeadPortrait())){
					FileUtils.deleteFile(projectDirectory+ user_db.getHeadPortrait());
				}

				user.setHeadPortrait(savePath);
			} catch (Exception e){
				e.printStackTrace();
			}

		}

		if (StringUtil.isNotEmpty(departUser) && StringUtil.isNotEmpty(departUser.getDepart())){

			TSDepart depart = findUniqueByProperty(TSDepart.class, "id", departUser.getDepart().getId());

			if (StringUtil.isNotEmpty(depart)){

				List<TSDepartUser> departUsers = findByProperty(TSDepartUser.class, "user.id", user_db.getId());

				deleteAllEntitie(departUsers);

				departUser.setId(null);

				departUser.setUser(user);

				save(departUser);

			}

		}

		MyBeanUtils.copyBeanNotNull2Bean(user, user_db);
		updateEntitie(user_db);
		return body;
	}

	@Override
	public ReturnBody editUser(List<TSRole> roles, TSUser user, MultipartFile headPortrait, TSDepartUser departUser, String password) throws Exception {
		ReturnBody body = new ReturnBody();
		TSUser user_db = get(TSUser.class, user.getId());

		if (!StringUtil.isNotEmpty(user_db)){
			body.setStatus(Glossary.Return_State_Data_Not_Exist);
			body.setMsg("Data does not exist.");
			return body;
		}

		if (StringUtil.isNotEmpty(roles)){
			List<TSRoleUser> roleUsers = new ArrayList<>();
			for (TSRole role: roles){
				role = get(TSRole.class, oConvertUtils.getString(role.getId()));
				if (StringUtil.isNotEmpty(role)){
					TSRoleUser roleUser = new TSRoleUser();
					roleUser.setRole(role);
					roleUser.setUser(user_db);
					roleUsers.add(roleUser);
				}
			}
			if (roleUsers.size() > 0){
				List<TSRoleUser> roleUsers_db = findByProperty(TSRoleUser.class, "user.id", user_db.getId());
				deleteAllEntitie(roleUsers_db);
				batchSave(roleUsers);
			}
		}

		if (!StringUtil.isEmpty(password)){
			user.setPassword(PasswordUtil.encrypt(user_db.getAccount(), password, PasswordUtil.getStaticSalt()));
		} else {
			//修改用户信息不允许修改密码，如果 调用者，传入了”password“，在这里应该还原密码
			user.setPassword(user_db.getPassword());
		}


		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		if (StringUtil.isNotEmpty(headPortrait)){

			try {

				// 原始文件名称
				String filename = FileUtils.getFilePrefix2(headPortrait.getOriginalFilename());
				// 即将保存的文件名称
				String saveFileName = DateUtils
						.date2Str(DateUtils.yyyymmddhhmmss)
						+ (int) ((Math.random() * 9 + 1) * 100000)
						+ "."
						+ FileUtils.getExtend(headPortrait.getOriginalFilename());
				// 得到URL的输入流
				InputStream input = headPortrait.getInputStream();
				// 设置数据缓冲
				byte[] bs = new byte[1024 * 2];
				// 读取到的数据长度
				int len;
				// 输出的文件流保存图片至本地
				OutputStream os;
				String path = projectDirectory + "files/userfiles/" + user.getId();

				FileUtils.createDir(path);

				//数据库存储的值
				String savePath = "files/userfiles/" + user.getId() + "/" + saveFileName;

				os = new FileOutputStream(path + "/" + saveFileName);
				while ((len = input.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
				os.close();
				input.close();

				if (!oConvertUtils.isEmpty(user_db.getHeadPortrait())){
					FileUtils.deleteFile(projectDirectory+ user_db.getHeadPortrait());
				}

				user.setHeadPortrait(savePath);
			} catch (Exception e){
				e.printStackTrace();
			}

		}

		if (StringUtil.isNotEmpty(departUser) && StringUtil.isNotEmpty(departUser.getDepart())){

			TSDepart depart = findUniqueByProperty(TSDepart.class, "id", departUser.getDepart().getId());

			if (StringUtil.isNotEmpty(depart)){

				List<TSDepartUser> departUsers = findByProperty(TSDepartUser.class, "user.id", user_db.getId());

				deleteAllEntitie(departUsers);

				departUser.setId(null);

				departUser.setUser(user);

				save(departUser);

			}

		}

		MyBeanUtils.copyBeanNotNull2Bean(user, user_db);
		updateEntitie(user_db);
		return body;
	}

	@Override
	public ReturnBody updateUserPassword(int id, String password, String passwordNew) throws Exception {
		ReturnBody body = new ReturnBody();
		TSUser user = get(TSUser.class, id);

		if (StringUtil.isEmpty(passwordNew)){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter must not be null.");
			return body;
		}

		if (StringUtil.isNotEmpty(user)){

			String pString = PasswordUtil.encrypt(user.getAccount(), password, PasswordUtil.getStaticSalt());
			if (!pString.equals(user.getPassword())) {
				body.setMsg("The original password is incorrect.");
				body.setStatus(Glossary.Return_State_User_Password_Not_Null);
			} else {
				try {
					user.setPassword(PasswordUtil.encrypt(user.getAccount(), passwordNew, PasswordUtil.getStaticSalt()));
				} catch (Exception e) {
					e.printStackTrace();
					body.setStatus(Glossary.Return_State_Exception_Operation);
					body.setMsg("error");
					return body;
				}
				updateEntitie(user);
			}
			return body;
		}

		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Data does not exist.");
		return body;
	}

	@Override
	public ReturnBody setUserDepart(TSUser user, List<TSDepartUser> departUsers) throws Exception {
		ReturnBody body = new ReturnBody();
		user = get(TSUser.class, user.getId());
		if (!StringUtil.isNotEmpty(user)){
			body.setStatus(Glossary.Return_State_Data_Not_Exist);
			body.setMsg("Data does not exist.");
			return body;
		}
		List<TSDepartUser> departUsers_db = findByProperty(TSDepartUser.class, "user.id", user.getId());
		List<TSDepartUser> departUsers_db_back = findByProperty(TSDepartUser.class, "user.id", user.getId());

		try{
			//在设置前，先清除现有的用户角色
			deleteAllEntitie(departUsers_db);

		} catch (HibernateException e){
			e.printStackTrace();
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			return body;
		}

		try{
			//保存新的用户部门
			batchSave(departUsers);

		} catch (HibernateException e){
			e.printStackTrace();

			try{
				//保存新用户角色失败时，应还原该用户原本的用户角色
				batchSave(departUsers_db_back);

			} catch (HibernateException j){
				j.printStackTrace();
				System.out.println("============================ 部门信息还原失败【用户ID：“ "+user.getId()+" ”】 =================================");
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.Role restore failure.");
				return body;
			}
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			return body;
		}

		return body;
	}

	//向上追查所有的部门编号
	private List<String> peggingOrgCode(String orgCode,HttpServletRequest request) throws Exception{
		List<String> orgCodes = (List<String>) request.getSession().getAttribute("orgCodes");
		if (orgCode.length() > 3){
			orgCodes.add(orgCode);
			return peggingOrgCode(orgCode.substring(0, orgCode.length()-2), request);
		}
		return orgCodes;
	}

}
