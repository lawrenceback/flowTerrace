package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPRemoteMonitoring;
import com.jxgis.ffireserver.bean.FFPRemoteMonitoringMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.RemoteMonitoringService;
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
 * Created by YanWei on 2017/6/26.
 *
 */
@Service("remoteMonitoringService")
@Transactional
public class RemoteMonitoringServiceImpl extends SystemServiceImpl implements RemoteMonitoringService {


	@Override
	public ReturnBody addRemoteMonitoring(FFPRemoteMonitoring remoteMonitoring, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isEmpty(remoteMonitoring.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [pac],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(remoteMonitoring.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [name],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(remoteMonitoring.getManager())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [manager],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(remoteMonitoring.getPhone())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [phone],must not be empty.");
			return body;
		}
		if (!StringUtil.isNotEmpty(remoteMonitoring.getLatitude())) {
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [latitude],must not be empty.");
			return body;
		}
		if (!StringUtil.isNotEmpty(remoteMonitoring.getLongitude())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [longitude],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(remoteMonitoring.getDeviceid())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [deviceid],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(remoteMonitoring.getIp())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [ip],must not be empty.");
			return body;
		}
		if (!StringUtil.isEmpty(oConvertUtils.getString(remoteMonitoring.getId()))){
			FFPRemoteMonitoring remoteMonitoring_db = get(FFPRemoteMonitoring.class, remoteMonitoring.getId());
			MyBeanUtils.copyBeanNotNull2Bean(remoteMonitoring, remoteMonitoring_db);
			updateEntitie(remoteMonitoring_db);
		} else {

			if (!StringUtil.isEmpty(remoteMonitoring.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(remoteMonitoring.getShape());
				} catch (ParseException e) {
					body.setStatus(Glossary.Return_State_Geometry_Format_Error);
					body.setMsg( "Parameter [shape] error.");
					return body;
				}
			}

			save(remoteMonitoring);

			List<FFPRemoteMonitoringMedia> monitoringMedias = new ArrayList<>();
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

						FFPRemoteMonitoringMedia monitoringMedia = new FFPRemoteMonitoringMedia();
						monitoringMedia.setMonitoring(remoteMonitoring);
						monitoringMedia.setMediaFile(mediaFile_save);
						monitoringMedias.add(monitoringMedia);


					} catch (Exception e){
						e.printStackTrace();
					}
				}

			}

			try{
				batchSave(monitoringMedias);
				body.setObj(remoteMonitoring.getId());
				return body;
			} catch (Exception e){
				delete(remoteMonitoring);

				for (TSMediaFile mediaFile_save: mediaFile_saves){
					FileUtils.delete(mediaFile_save.getFileUrl());
					if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
						FileUtils.delete(mediaFile_save.getFileThumbUrl());
					}
				}
				deleteAllEntitie(mediaFile_saves);
				deleteAllEntitie(monitoringMedias);
			}
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			return body;
		}
		body.setObj(remoteMonitoring.getId());
		return body;
	}

	@Override
	public ReturnBody deleteRemoteMonitoring(FFPRemoteMonitoring remoteMonitoring) throws Exception {
		ReturnBody body = new ReturnBody();
		remoteMonitoring = get(FFPRemoteMonitoring.class, remoteMonitoring.getId());
		if (StringUtil.isNotEmpty(remoteMonitoring)){
			List<FFPRemoteMonitoringMedia> monitoringMedias = findByProperty(FFPRemoteMonitoringMedia.class, "monitoring.id", oConvertUtils.getString(remoteMonitoring.getId()));
			if (StringUtil.isNotEmpty(monitoringMedias) && monitoringMedias.size() > 0){
				for (FFPRemoteMonitoringMedia monitoringMedia: monitoringMedias){
					deleteRemoteMonitoringMedia(monitoringMedia);
				}
			}
			delete(remoteMonitoring);
			return body;
		}


		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Parameter error [id].");
		return body;
	}

	@Override
	public ReturnBody deleteRemoteMonitorings(List<FFPRemoteMonitoring> remoteMonitorings) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(remoteMonitorings)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error.");
			return body;
		}
		for(FFPRemoteMonitoring remoteMonitoring: remoteMonitorings){
			deleteRemoteMonitoring(remoteMonitoring);
		}
		return body;
	}

	@Override
	public ReturnBody editRemoteMonitoring(FFPRemoteMonitoring remoteMonitoring) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPRemoteMonitoring remotemonitoring_db = get(FFPRemoteMonitoring.class, remoteMonitoring.getId());
		if (StringUtil.isNotEmpty(remotemonitoring_db)){
			MyBeanUtils.copyBeanNotNull2Bean(remoteMonitoring, remotemonitoring_db);
			if (!StringUtil.isEmpty(remoteMonitoring.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(remoteMonitoring.getShape());
					updateEntitie(remotemonitoring_db);
					executeSql("UPDATE ffp_fire_remotemonitoring SET shape=POINTFROMTEXT('"+remoteMonitoring.getShape()+"') WHERE id=?", remotemonitoring_db.getId());
					return body;
				} catch (ParseException e) {
					body.setStatus(Glossary.Return_State_Geometry_Format_Error);
					body.setMsg( "Parameter [shape] error.");
					return body;
				} catch (HibernateException h){
					body.setStatus(Glossary.Return_State_Exception_Operation);
					body.setMsg( "operation failed.");
					return body;
				}
			}
			updateEntitie(remotemonitoring_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getRemoteMonitoringList(FFPRemoteMonitoring remoteMonitoring, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid body = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(FFPRemoteMonitoring.class);


		if (!StringUtil.isEmpty(remoteMonitoring.getPac())){

			switch (fetchType){
				case 1:
					//只加载下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(remoteMonitoring.getPac(),true) ) );
					break;
				case 2:
					//加载所有下一级，甚用
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(remoteMonitoring.getPac()) + "%" ) );
					break;
				case 3:
					//加载所有下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(remoteMonitoring.getPac()) ) );
					break;
				default:
					//默认： .eq()
					criteria.add( Restrictions.eq("pac", remoteMonitoring.getPac() ) );
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

		if (!StringUtil.isEmpty(remoteMonitoring.getName())){
			criteria.add( Restrictions.like("name", "%" + remoteMonitoring.getName() + "%" ) );
		}

		body.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPRemoteMonitoring> lists = criteria.list();
		for (FFPRemoteMonitoring monitoring: lists){
			monitoring.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPRemoteMonitoringMedia rmm where mf.id=rmm.mediaFile.id and rmm.monitoring.id=? ", monitoring.getId()));
			Map<String, Object> map =findOneForJdbc("select ASTEXT(shape) shape FROM ffp_fire_remotemonitoring where id=?", monitoring.getId());
			if (StringUtil.isNotEmpty(map) && StringUtil.isNotEmpty(map.get("shape"))){
				monitoring.setShape(map.get("shape").toString());
			}

		}
		body.setRows(lists);
		return body;
	}

	@Override
	public ReturnBody addRemoteMonitoringMedia(FFPRemoteMonitoringMedia monitoringMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(monitoringMedia.getMonitoring()) && !StringUtil.isNotEmpty(get(FFPRemoteMonitoring.class, monitoringMedia.getMonitoring().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [monitoring.id].");
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

				monitoringMedia.setMediaFile(mediaFile1);

				save(monitoringMedia);

				body.setObj(monitoringMedia.getMediaFile());

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
	public ReturnBody deleteRemoteMonitoringMedia(FFPRemoteMonitoringMedia monitoringMedia) throws Exception {

		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(monitoringMedia)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [id].");
			return body;
		}
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPRemoteMonitoringMedia monitoringMedia_db = get(FFPRemoteMonitoringMedia.class, oConvertUtils.getString(monitoringMedia.getId()));
		if (!StringUtil.isNotEmpty(monitoringMedia_db)){
			monitoringMedia_db = findUniqueByProperty(FFPRemoteMonitoringMedia.class, "mediaFile.id", oConvertUtils.getString(monitoringMedia.getId()));
		}
		if (StringUtil.isNotEmpty(monitoringMedia_db)){
			if (StringUtil.isNotEmpty(monitoringMedia_db.getMediaFile())){
				delete(monitoringMedia_db.getMediaFile());
				delete(monitoringMedia_db);
				String fileUrl = projectDirectory + monitoringMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + monitoringMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
