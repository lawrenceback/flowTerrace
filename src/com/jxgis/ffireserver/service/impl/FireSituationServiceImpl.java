package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPFireSituation;
import com.jxgis.ffireserver.bean.FFPFireSituationMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.FireSituationService;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.TSMediaFile;
import com.jxgis.ffireserver.web.service.impl.SystemServiceImpl;
import org.hibernate.Criteria;
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
 * Created by YanWei on 2017/8/17.
 *
 */
@Service("fireSituationService")
@Transactional
public class FireSituationServiceImpl extends SystemServiceImpl implements FireSituationService {

	@Override
	public ReturnBody addFireSituation(FFPFireSituation fireSituation, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(fireSituation)){
			if (StringUtil.isEmpty(fireSituation.getReporter())){
				body.setStatus(Glossary.Return_State_Parameter_Error);
				body.setMsg("parameter error [reporter].");
				return body;
			}
			if (!StringUtil.isNotEmpty(fireSituation.getTime())){
				body.setStatus(Glossary.Return_State_Parameter_Error);
				body.setMsg("parameter error [time].");
				return body;
			}

			if (!StringUtil.isNotEmpty(fireSituation.getType())){
				body.setStatus(Glossary.Return_State_Parameter_Error);
				body.setMsg("parameter error [type].");
				return body;
			}
			save(fireSituation);

			List<FFPFireSituationMedia> fireSituationMedias = new ArrayList<>();
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

						FFPFireSituationMedia situationMedia = new FFPFireSituationMedia();
						situationMedia.setFireSituation(fireSituation);
						situationMedia.setMediaFile(mediaFile_save);
						fireSituationMedias.add(situationMedia);


					} catch (Exception e){
						e.printStackTrace();
					}
				}

			}


			try{
				batchSave(fireSituationMedias);
				body.setObj(fireSituation.getId());
				return body;
			} catch (Exception e){
				delete(fireSituation);

				for (TSMediaFile mediaFile_save: mediaFile_saves){
					FileUtils.delete(mediaFile_save.getFileUrl());
					if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
						FileUtils.delete(mediaFile_save.getFileThumbUrl());
					}
				}
				deleteAllEntitie(mediaFile_saves);
				deleteAllEntitie(fireSituationMedias);
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error.");
		return body;
	}

	@Override
	public ReturnBody deleteFireSituation(FFPFireSituation fireSituation) throws Exception {
		ReturnBody body = new ReturnBody();
		fireSituation = get(FFPFireSituation.class, oConvertUtils.getString(fireSituation.getId()));
		if (StringUtil.isNotEmpty(fireSituation)){
			List<FFPFireSituationMedia> fireSituationMedias = findByProperty(FFPFireSituationMedia.class, "fireSituation.id", oConvertUtils.getString(fireSituation.getId()));
			if (StringUtil.isNotEmpty(fireSituationMedias) && fireSituationMedias.size() > 0){
				for (FFPFireSituationMedia fireSituationMedia: fireSituationMedias){
					deleteFireSituationMedia(fireSituationMedia);
				}
			}
			delete(fireSituation);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter must not be null [id].");
		return body;
	}

	@Override
	public ReturnBody editFireSituation(FFPFireSituation fireSituation) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPFireSituation fireSituation_db = get(FFPFireSituation.class, oConvertUtils.getString(fireSituation.getId()));
		if (StringUtil.isNotEmpty(fireSituation_db)){

			MyBeanUtils.copyBeanNotNull2Bean(fireSituation, fireSituation_db);
			updateEntitie(fireSituation_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getFireSituationList(FFPFireSituation facilities, DataGrid dataGrid, Date time_start,Date time_end) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(FFPFireSituation.class);

		if (StringUtil.isNotEmpty(time_start)){
			criteria.add(Restrictions.ge("time", time_start));
		}
		if (StringUtil.isNotEmpty(time_end)){
			criteria.add(Restrictions.le("time", time_end));
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

		grid.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPFireSituation> lists = criteria.list();
		for (FFPFireSituation fireSituation_ls: lists){
			fireSituation_ls.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPFireSituationMedia fsm where mf.id=fsm.mediaFile.id and fsm.fireSituation.id=? ", fireSituation_ls.getId()));
		}
		grid.setRows(lists);
		return grid;
	}

	@Override
	public ReturnBody addFireSituationMedia(FFPFireSituationMedia beltPointMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(beltPointMedia.getFireSituation()) && !StringUtil.isNotEmpty(get(FFPFireSituation.class, beltPointMedia.getFireSituation().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [facilities.id].");
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
				beltPointMedia.setMediaFile(mediaFile1);

				save(beltPointMedia);

				body.setObj(beltPointMedia.getMediaFile());

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
	public ReturnBody deleteFireSituationMedia(FFPFireSituationMedia beltPointMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPFireSituationMedia beltPointMedia_db = get(FFPFireSituationMedia.class, oConvertUtils.getString(beltPointMedia.getId()));
		if (!StringUtil.isNotEmpty(beltPointMedia_db)){
			beltPointMedia_db = findUniqueByProperty(FFPFireSituationMedia.class, "mediaFile.id", oConvertUtils.getString(beltPointMedia.getId()));
		}
		if (StringUtil.isNotEmpty(beltPointMedia_db)){
			if (StringUtil.isNotEmpty(beltPointMedia_db.getMediaFile())){
				delete(beltPointMedia_db.getMediaFile());
				delete(beltPointMedia_db);
				String fileUrl = projectDirectory + beltPointMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + beltPointMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
