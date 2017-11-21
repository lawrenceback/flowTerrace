package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPDangerousFacilities;
import com.jxgis.ffireserver.bean.FFPFireHot;
import com.jxgis.ffireserver.bean.FFPFireHotMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.FireHotService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by YanWei on 2017/6/26.
 *
 */
@Service("fireHotService")
public class FireHotServiceImpl extends SystemServiceImpl implements FireHotService {

	@Override
	public ReturnBody addHot(FFPFireHot hot, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isEmpty(hot.getPac())) {
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [pac],must not be empty.");
			return body;
		}

		if (StringUtil.isEmpty(hot.getSatellite())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [satellite],must not be empty.");
			return body;
		}

		FFPFireHot fireHot = get(FFPFireHot.class, oConvertUtils.getString(hot.getId()));
		if (StringUtil.isNotEmpty(fireHot)){
			MyBeanUtils.copyBeanNotNull2Bean(hot, fireHot);
			updateEntitie(fireHot);
		} else {

			if (StringUtil.isEmpty(hot.getNo())) {
				body.setStatus(Glossary.Return_State_Parameter_Null);
				body.setMsg("Parameter error [no],must not be empty.");
				return body;
			}

			String _time = DateUtils.date2Str(hot.getReporttime(), DateUtils.yyyyMMdd);
			long count = getCountForJdbcParam("select COUNT(*) from ffp_fire_hot where date_format(reporttime, '%Y%m%d') = ? AND `no`=?;", new Object[]{ _time, hot.getNo() });
			if (count > 0){
				//当NO字段在同一天已经存在数据时，将不允许插入
				body.setStatus(Glossary.Return_State_Fire_Hot_NO_Exist_Today);
				body.setMsg("parameter error [no], The number already exists today.");
				return body;
			}

			if (!StringUtil.isEmpty(hot.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(hot.getShape());
				} catch (ParseException e) {
					body.setStatus(Glossary.Return_State_Geometry_Format_Error);
					body.setMsg( "Parameter [shape] must not be null.");
					return body;
				}
			}

			save(hot);


			List<FFPFireHotMedia> fireHotMedias = new ArrayList<>();
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

						FFPFireHotMedia fireHotMedia = new FFPFireHotMedia();
						fireHotMedia.setFireHot(hot);
						fireHotMedia.setMediaFile(mediaFile_save);
						fireHotMedias.add(fireHotMedia);


					} catch (Exception e){
						e.printStackTrace();
					}
				}

			}

			try{
				batchSave(fireHotMedias);
				body.setObj(hot.getId());
				return body;
			} catch (Exception e){
				delete(hot);

				for (TSMediaFile mediaFile_save: mediaFile_saves){
					FileUtils.delete(mediaFile_save.getFileUrl());
					if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
						FileUtils.delete(mediaFile_save.getFileThumbUrl());
					}
				}
				deleteAllEntitie(mediaFile_saves);
				deleteAllEntitie(fireHotMedias);
			}


		}

		body.setObj(hot.getId());
		return body;
	}

	@Override
	public ReturnBody deleteHot(FFPFireHot hot) throws Exception {
		ReturnBody body = new ReturnBody();
		hot = get(FFPFireHot.class, hot.getId());
		if (StringUtil.isNotEmpty(hot)) {
			List<FFPFireHotMedia> fireHotMedias = findByProperty(FFPFireHotMedia.class, "fireHot.id", hot.getId());
			if (StringUtil.isNotEmpty(fireHotMedias) && fireHotMedias.size() > 0){
				for (FFPFireHotMedia facilitiesMedia: fireHotMedias){
					deleteHotMedia(facilitiesMedia);
				}
			}

			delete(hot);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter error [id],must not be empty.");
		return body;
	}

	@Override
	public ReturnBody editHot(FFPFireHot hot) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPFireHot hot_db = get(FFPFireHot.class, hot.getId());
		if (StringUtil.isNotEmpty(hot_db)){
			MyBeanUtils.copyBeanNotNull2Bean(hot, hot_db);

			if (!StringUtil.isEmpty(hot.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(hot.getShape());
					updateEntitie(hot_db);
					executeSql("UPDATE ffp_fire_hot SET shape=POINTFROMTEXT('"+hot.getShape()+"') WHERE id=?", hot_db.getId());
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

			updateEntitie(hot_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter error [id],must not be empty.");
		return body;
	}

	@Override
	public ReturnDataGrid getHotList(FFPFireHot hot, DataGrid dataGrid, int fetchType, Date reportTime_start,Date reportTime_end) throws Exception {
		ReturnDataGrid body = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(FFPFireHot.class);


		if (StringUtil.isNotEmpty(hot.getType())){

			criteria.add( Restrictions.eq("type", hot.getType() ) );

		}

		if (StringUtil.isNotEmpty(reportTime_start)){
			criteria.add(Restrictions.ge("reporttime", reportTime_start));
		}
		if (StringUtil.isNotEmpty(reportTime_end)){
			criteria.add(Restrictions.le("reporttime", reportTime_end));
		}
		if (!StringUtil.isEmpty(hot.getNo())){
			criteria.add(Restrictions.le("no", hot.getNo()));
		}



		if (!StringUtil.isEmpty(hot.getPac())){

			switch (fetchType){
				case 1:
					//只加载下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(hot.getPac(),true) ) );
					break;
				case 2:
					//加载所有下一级，甚用
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(hot.getPac()) + "%" ) );
					break;
				case 3:
					//加载所有下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(hot.getPac()) ) );
					break;
				default:
					//默认： .eq()
					criteria.add( Restrictions.eq("pac", hot.getPac() ) );
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

		body.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPFireHot> lists = criteria.list();
		for (FFPFireHot fireHot: lists){
			fireHot.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPFireHotMedia fhm where mf.id=fhm.mediaFile.id and fhm.fireHot.id=? ", fireHot.getId()));
			Object shape = findOneForJdbc("select ASTEXT(shape) shape FROM ffp_fire_hot where id=?", fireHot.getId()).get("shape");
			if (StringUtil.isNotEmpty(shape)){
				fireHot.setShape(shape.toString());
			}
		}
		body.setRows(lists);
		return body;
	}

	@Override
	public ReturnBody getHotDetail(FFPFireHot hot) throws Exception {
		ReturnBody body = new ReturnBody();
		hot = get(FFPFireHot.class, oConvertUtils.getString(hot.getId()));
		if (StringUtil.isNotEmpty(hot)){
			hot.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPFireHotMedia fhm where mf.id=fhm.mediaFile.id and fhm.fireHot.id=? ", hot.getId()));
			try{
				Map<String, Object> map = findOneForJdbc("SELECT a.*, b.`name` AS initiator_name, c.`name` AS examineUser_name FROM ffp_hot_feedback a LEFT JOIN t_s_base_user b ON a.initiator = b.id LEFT JOIN t_s_base_user c ON a.examineUser = c.id WHERE hot_id = ?", hot.getId());
				if (StringUtil.isNotEmpty(map)){
					map.put("mediaFiles", this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPHotFeedbackMedia hfm where mf.id=hfm.mediaFile.id and hfm.hotFeedback.id=? ", oConvertUtils.getString(map.get("id"))));
				}
				hot.setHotFeedback(map);
			} catch (Exception e){
				e.printStackTrace();
			}

			body.setObj(hot);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id]");
		return body;
	}

	@Override
	public ReturnBody addHotMedia(FFPFireHotMedia hotMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(hotMedia.getFireHot()) && !StringUtil.isNotEmpty(get(FFPDangerousFacilities.class, hotMedia.getFireHot().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [fireHot.id].");
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

				hotMedia.setMediaFile(mediaFile1);

				save(hotMedia);

				body.setObj(hotMedia.getMediaFile());

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
	public ReturnBody deleteHotMedia(FFPFireHotMedia hotMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(hotMedia)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [id].");
			return body;
		}
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPFireHotMedia hotMedia_db = get(FFPFireHotMedia.class, oConvertUtils.getString(hotMedia.getId()));
		if (!StringUtil.isNotEmpty(hotMedia_db)){
			hotMedia_db = findUniqueByProperty(FFPFireHotMedia.class, "mediaFile.id", oConvertUtils.getString(hotMedia.getId()));
		}
		if (StringUtil.isNotEmpty(hotMedia_db)){
			if (StringUtil.isNotEmpty(hotMedia_db.getMediaFile())){
				delete(hotMedia_db.getMediaFile());
				delete(hotMedia_db);
				String fileUrl = projectDirectory + hotMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + hotMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
