package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPPlanewaterpoint;
import com.jxgis.ffireserver.bean.FFPPlanewaterpointMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.PlanewaterpointService;
import com.jxgis.ffireserver.util.FormatPac;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.TSMediaFile;
import com.jxgis.ffireserver.web.service.impl.SystemServiceImpl;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/8/29.
 *
 * 飞机吊桶取水点 业务层 实现类
 *
 */
@Service("planewaterpointService")
@Transactional
public class PlanewaterpointServiceImpl extends SystemServiceImpl implements PlanewaterpointService {
	@Override
	public ReturnBody addplanewaterpoint(FFPPlanewaterpoint planewaterpoint, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isEmpty(planewaterpoint.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [pac],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(planewaterpoint.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [name],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(planewaterpoint.getManager())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [manager],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(planewaterpoint.getPhone())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [phone],must not be empty.");
			return body;
		}
		if (!StringUtil.isNotEmpty(planewaterpoint.getLatitude())) {
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [latitude],must not be empty.");
			return body;
		}
		if (!StringUtil.isNotEmpty(planewaterpoint.getLongitude())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [longitude],must not be empty.");
			return body;
		}

		if (!StringUtil.isEmpty(planewaterpoint.getShape())){
			try{
				WKTReader fromText = new WKTReader();
				fromText.read(planewaterpoint.getShape());
			} catch (ParseException e) {
				body.setStatus(Glossary.Return_State_Geometry_Format_Error);
				body.setMsg( "Parameter [shape] error.");
				return body;
			}
		}

		save(planewaterpoint);

		List<FFPPlanewaterpointMedia> planewaterpointMedias = new ArrayList<>();
		List<TSMediaFile> mediaFile_saves = new ArrayList<>();


		if (StringUtil.isNotEmpty(mediaFiles)){

			// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
			String projectDirectory = ResourceUtil.getSysPath();
			for (Map.Entry<String, MultipartFile> entry : mediaFiles.entrySet()){
				try {

					MultipartFile mediaFile = entry.getValue();

					String suffix = FileUtils.getExtend(mediaFile.getOriginalFilename());//文件后缀

					// 即将保存的文件名称
					String saveFileName = DateUtils
							.date2Str(DateUtils.yyyymmddhhmmss)
							+ (int) ((Math.random() * 9 + 1) * 100000);
					String saveFileName_suffix = saveFileName + "." + suffix;
					// 得到URL的输入流
					InputStream input = mediaFile.getInputStream();

					// 设置数据缓冲
					byte[] bs = new byte[1024 * 2];
					// 读取到的数据长度
					int len;
					// 输出的文件流保存图片至本地
					OutputStream os;
					String toDay = DateUtils.date2Str(new Date(), DateUtils.yyyyMMdd);
					String path = projectDirectory + "files/" + toDay +"/";

					FileUtils.createDir(path);

					//数据库存储的值
					String savePath = "files/" + toDay + "/" + saveFileName_suffix;

					os = new FileOutputStream(path + "/" + saveFileName_suffix);
					while ((len = input.read(bs)) != -1) {
						os.write(bs, 0, len);
					}
					os.close();
					input.close();

					TSMediaFile mediaFile_save = new TSMediaFile();

					String thumb_fileUrl = "";
					int fileType = FileType.getAllFileType(suffix);
					switch (fileType){
						case Glossary.File_Type_Img:
							//截取图片缩略图
							new ImageThumUtil().thumbnailImage(path + "/" + saveFileName_suffix, 256, 256);
							thumb_fileUrl = "files/" + toDay + "/thumb_" + saveFileName_suffix;
							mediaFile_save.setFileThumbUrl(thumb_fileUrl);
							break;
						case Glossary.File_Type_Video:
							//视频预览图
							thumb_fileUrl = "files/" + toDay + "/thumb_" + saveFileName + ".jpg";
							PicHelper.handler(projectDirectory + Glossary.File_Ffmpeg_ads, path + "/" + saveFileName_suffix, projectDirectory + thumb_fileUrl );
							mediaFile_save.setFileThumbUrl(thumb_fileUrl);
							break;
					}

					// 原始文件名称
					String filename = FileUtils.getFilePrefix2(mediaFile.getOriginalFilename());
					mediaFile_save.setFileName(filename);
					mediaFile_save.setFileUrl(savePath);
					mediaFile_save.setLength(mediaFile.getSize());
					mediaFile_save.setFileSuffix(suffix);//TODO 后缀
					mediaFile_save.setFileType(fileType);
					save(mediaFile_save);
					mediaFile_saves.add(mediaFile_save);

					FFPPlanewaterpointMedia planewaterpointMedia = new FFPPlanewaterpointMedia();
					planewaterpointMedia.setPlanewaterpoint(planewaterpoint);
					planewaterpointMedia.setMediaFile(mediaFile_save);
					planewaterpointMedias.add(planewaterpointMedia);


				} catch (Exception e){
					e.printStackTrace();
				}
			}

		}

		try{
			batchSave(planewaterpointMedias);
			body.setObj(planewaterpoint.getId());
			return body;
		} catch (Exception e){
			delete(planewaterpoint);

			for (TSMediaFile mediaFile_save: mediaFile_saves){
				FileUtils.delete(mediaFile_save.getFileUrl());
				if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
					FileUtils.delete(mediaFile_save.getFileThumbUrl());
				}
			}
			deleteAllEntitie(mediaFile_saves);
			deleteAllEntitie(planewaterpointMedias);
		}
		body.setStatus(Glossary.Return_State_Exception_Operation);
		body.setMsg("operation failed.");
		return body;

	}


	@Override
	public ReturnBody deleteplanewaterpoint(FFPPlanewaterpoint planewaterpoint) throws Exception {
		ReturnBody body = new ReturnBody();
		planewaterpoint = get(FFPPlanewaterpoint.class, oConvertUtils.getString(planewaterpoint.getId()));
		if (StringUtil.isNotEmpty(planewaterpoint)){
			List<FFPPlanewaterpointMedia> planewaterpointMedias = findByProperty(FFPPlanewaterpointMedia.class, "planewaterpoint.id", oConvertUtils.getString(planewaterpoint.getId()));
			if (StringUtil.isNotEmpty(planewaterpointMedias) && planewaterpointMedias.size() > 0){
				for (FFPPlanewaterpointMedia planewaterpointMedia: planewaterpointMedias){
					deleteplanewaterpointMedia(planewaterpointMedia);
				}
			}
			delete(planewaterpoint);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter must not be null [id].");
		return body;
	}

	@Override
	public ReturnBody deleteplanewaterpoints(List<FFPPlanewaterpoint> planewaterpoints) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(planewaterpoints)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		for (FFPPlanewaterpoint planewaterpoint: planewaterpoints){
			deleteplanewaterpoint(planewaterpoint);
		}
		return body;
	}

	@Override
	public ReturnBody editplanewaterpoint(FFPPlanewaterpoint planewaterpoint) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPPlanewaterpoint planewaterpoint_db = get(FFPPlanewaterpoint.class, oConvertUtils.getString(planewaterpoint.getId()));
		if (StringUtil.isNotEmpty(planewaterpoint_db)){

			MyBeanUtils.copyBeanNotNull2Bean(planewaterpoint, planewaterpoint_db);

			if (!StringUtil.isEmpty(planewaterpoint.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(planewaterpoint.getShape());
					updateEntitie(planewaterpoint_db);
					executeSql("UPDATE ffp_planewaterpoint SET shape=POINTFROMTEXT('"+planewaterpoint.getShape()+"') WHERE id=?", planewaterpoint_db.getId());
					return body;
				} catch (ParseException e) {
					body.setStatus(Glossary.Return_State_Geometry_Format_Error);
					body.setMsg( "Parameter [shape] must not be null.");
					return body;
				} catch (HibernateException h){
					body.setStatus(Glossary.Return_State_Exception_Operation);
					body.setMsg( "operation failed.");
					return body;
				}
			}


			updateEntitie(planewaterpoint_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getplanewaterpointList(FFPPlanewaterpoint planewaterpoint, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();
		Criteria criteria = this.getSession().createCriteria(FFPPlanewaterpoint.class);


		if (!StringUtil.isEmpty(planewaterpoint.getPac())){

			switch (fetchType){
				case 1:
					//只加载下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(planewaterpoint.getPac(),true) ) );
					break;
				case 2:
					//加载所有下一级，甚用
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(planewaterpoint.getPac()) + "%" ) );
					break;
				case 3:
					//加载所有下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(planewaterpoint.getPac()) ) );
					break;
				default:
					//默认： .eq()
					criteria.add( Restrictions.eq("pac", planewaterpoint.getPac() ) );
					break;
			}

		}

		if ( !StringUtil.isEmpty(dataGrid.getSort())){
			if (dataGrid.getOrder().name().equals("asc")) {

				criteria.addOrder( Order.asc(dataGrid.getSort()) );

			} else {

				criteria.addOrder( Order.desc(dataGrid.getSort()) );

			}

		} else {
			criteria.addOrder( Order.desc("createTime") );
		}

		if (!StringUtil.isEmpty(planewaterpoint.getName())){
			criteria.add( Restrictions.like("name", "%" + planewaterpoint.getName() + "%" ) );
		}
		grid.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPPlanewaterpoint> lists = criteria.list();
		for (FFPPlanewaterpoint planewaterpoint1: lists){
			planewaterpoint1.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPPlanewaterpointMedia pm where mf.id=pm.mediaFile.id and pm.planewaterpoint.id=? ", planewaterpoint1.getId()));
			Object shape = findOneForJdbc("select ASTEXT(shape) shape FROM ffp_planewaterpoint where id=?", planewaterpoint1.getId()).get("shape");
			if (StringUtil.isNotEmpty(shape)){
				planewaterpoint1.setShape(shape.toString());
			}
		}
		grid.setRows(lists);
		return grid;
	}

	@Override
	public ReturnBody addplanewaterpointMedia(FFPPlanewaterpointMedia planewaterpointMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(planewaterpointMedia.getPlanewaterpoint()) && !StringUtil.isNotEmpty(get(FFPPlanewaterpoint.class, planewaterpointMedia.getPlanewaterpoint().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [planewaterpoint.id].");
			return body;
		}
		TSMediaFile mediaFile1 = new TSMediaFile();

		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		if (StringUtil.isNotEmpty(mediaFile)){

			try {


				String extend = FileUtils.getExtend(mediaFile.getOriginalFilename());//文件后缀

				// 即将保存的文件名称
				String saveFileName = DateUtils
						.date2Str(DateUtils.yyyymmddhhmmss)
						+ (int) ((Math.random() * 9 + 1) * 100000);
				// 即将保存的文件名称 + 后缀
				String saveFileName_extend = saveFileName + "." + extend;
				// 得到URL的输入流
				InputStream input = mediaFile.getInputStream();

				// 设置数据缓冲
				byte[] bs = new byte[1024 * 2];
				// 读取到的数据长度
				int len;
				// 输出的文件流保存图片至本地
				OutputStream os;
				String toDay = DateUtils.date2Str(new Date(), DateUtils.yyyyMMdd);
				String path = projectDirectory + "files/" + toDay +"/";

				FileUtils.createDir(path);

				//数据库存储的值
				String savePath = "files/" + toDay + "/" + saveFileName_extend;

				os = new FileOutputStream(path + "/" + saveFileName_extend);
				while ((len = input.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
				os.close();
				input.close();


				String thumb_fileUrl = "";
				int fileType = FileType.getAllFileType(extend);
				switch (fileType){
					case Glossary.File_Type_Img:
						//截取图片缩略图
						new ImageThumUtil().thumbnailImage(path + "/" + saveFileName_extend, 256, 256);
						thumb_fileUrl = "files/" + toDay + "/thumb_" + saveFileName_extend;
						mediaFile1.setFileThumbUrl(thumb_fileUrl);
						break;
					case Glossary.File_Type_Video:
						//视频预览图
						thumb_fileUrl = "files/" + toDay + "/thumb_" + saveFileName + ".jpg";
						PicHelper.handler(projectDirectory + Glossary.File_Ffmpeg_ads, path + "/" + saveFileName_extend, projectDirectory + thumb_fileUrl );
						mediaFile1.setFileThumbUrl(thumb_fileUrl);
						break;
				}

				// 原始文件名称
				String filename = FileUtils.getFilePrefix2(mediaFile.getOriginalFilename());
				mediaFile1.setFileName(filename);
				mediaFile1.setFileUrl(savePath);
				mediaFile1.setLength(mediaFile.getSize());
				mediaFile1.setFileSuffix(extend);//TODO 后缀
				mediaFile1.setFileType(fileType);

				save(mediaFile1);

				planewaterpointMedia.setMediaFile(mediaFile1);

				save(planewaterpointMedia);

				body.setObj(planewaterpointMedia.getMediaFile());

				return body;

			} catch (Exception e){
				e.printStackTrace();
			}

		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [mediaFile]");
		return body;
	}

	@Override
	public ReturnBody deleteplanewaterpointMedia(FFPPlanewaterpointMedia planewaterpointMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPPlanewaterpointMedia planewaterpointMedia_db = get(FFPPlanewaterpointMedia.class, oConvertUtils.getString(planewaterpointMedia.getId()));
		if (!StringUtil.isNotEmpty(planewaterpointMedia_db)){
			planewaterpointMedia_db = findUniqueByProperty(FFPPlanewaterpointMedia.class, "mediaFile.id", oConvertUtils.getString(planewaterpointMedia.getId()));
		}
		if (StringUtil.isNotEmpty(planewaterpointMedia_db)){
			if (StringUtil.isNotEmpty(planewaterpointMedia_db.getMediaFile())){
				delete(planewaterpointMedia_db.getMediaFile());
				delete(planewaterpointMedia_db);
				String fileUrl = projectDirectory + planewaterpointMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + planewaterpointMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
