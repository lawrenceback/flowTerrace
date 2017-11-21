package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPImportantUnits;
import com.jxgis.ffireserver.bean.FFPImportantUnitsMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.ImportantUnitsService;
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
 * Created by YanWei on 2017/8/24.
 *
 * 重点防火单位 业务层实现类
 *
 */
@Service("importantUnitsService")
@Transactional
public class ImportantUnitsServiceImpl extends SystemServiceImpl implements ImportantUnitsService {
	@Override
	public ReturnBody addImportant(FFPImportantUnits importantUnits, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();

		if (StringUtil.isEmpty(importantUnits.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [pac] must not be null.");
			return body;
		}
		if (StringUtil.isEmpty(importantUnits.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [name] must not be null.");
			return body;
		}

		if (!StringUtil.isEmpty(importantUnits.getShape())){
			try{
				WKTReader fromText = new WKTReader();
				fromText.read(importantUnits.getShape());
			} catch (ParseException e) {
				body.setStatus(Glossary.Return_State_Geometry_Format_Error);
				body.setMsg( "Parameter [shape] must not be null.");
				return body;
			}
		}

		save(importantUnits);

		List<FFPImportantUnitsMedia> unitsMedias = new ArrayList<>();
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
					mediaFile_save.setFileSuffix(suffix);
					mediaFile_save.setFileType(fileType);
					save(mediaFile_save);
					mediaFile_saves.add(mediaFile_save);

					FFPImportantUnitsMedia unitsMedia = new FFPImportantUnitsMedia();
					unitsMedia.setImportantUnits(importantUnits);
					unitsMedia.setMediaFile(mediaFile_save);
					unitsMedias.add(unitsMedia);


				} catch (Exception e){
					e.printStackTrace();
				}
			}

		}


		try{
			batchSave(unitsMedias);
			body.setObj(importantUnits.getId());
			return body;
		} catch (Exception e){
			delete(importantUnits);

			for (TSMediaFile mediaFile_save: mediaFile_saves){
				FileUtils.delete(mediaFile_save.getFileUrl());
				if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
					FileUtils.delete(mediaFile_save.getFileThumbUrl());
				}
			}
			deleteAllEntitie(mediaFile_saves);
			deleteAllEntitie(unitsMedias);
		}
		body.setStatus(Glossary.Return_State_Exception_Operation);
		body.setMsg("operation failed.");
		return body;
	}

	@Override
	public ReturnBody deleteImportant(FFPImportantUnits importantUnits) throws Exception {
		ReturnBody body = new ReturnBody();
		importantUnits = get(FFPImportantUnits.class, oConvertUtils.getString(importantUnits.getId()));
		if (StringUtil.isNotEmpty(importantUnits)){
			List<FFPImportantUnitsMedia> unitsMedias = findByProperty(FFPImportantUnitsMedia.class, "importantUnits.id", oConvertUtils.getString(importantUnits.getId()));
			if (StringUtil.isNotEmpty(unitsMedias) && unitsMedias.size() > 0){
				for (FFPImportantUnitsMedia unitsMedia: unitsMedias){
					deleteImportantMedia(unitsMedia);
				}
			}
			delete(importantUnits);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter must not be null [id].");
		return body;
	}

	@Override
	public ReturnBody deleteImportants(List<FFPImportantUnits> importantUnits) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(importantUnits)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error");
			return body;
		}

		for(FFPImportantUnits importantUnits1: importantUnits){
			deleteImportant(importantUnits1);
		}
		return body;
	}

	@Override
	public ReturnBody editImportant(FFPImportantUnits importantUnits) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPImportantUnits importantUnits_db = get(FFPImportantUnits.class, oConvertUtils.getString(importantUnits.getId()));
		if (StringUtil.isNotEmpty(importantUnits_db)){

			MyBeanUtils.copyBeanNotNull2Bean(importantUnits, importantUnits_db);

			if (!StringUtil.isEmpty(importantUnits.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(importantUnits.getShape());
					updateEntitie(importantUnits_db);
					executeSql("UPDATE ffp_importantUnits SET shape=POINTFROMTEXT('"+importantUnits.getShape()+"') WHERE id=?", importantUnits_db.getId());
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

			updateEntitie(importantUnits_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getImportantList(FFPImportantUnits importantUnits, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();
		Criteria criteria = this.getSession().createCriteria(FFPImportantUnits.class);


		if (StringUtil.isNotEmpty(importantUnits)){

			switch (fetchType){
				case 1:
					//只加载下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(importantUnits.getPac(),true) ) );
					break;
				case 2:
					//加载所有下一级，甚用
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(importantUnits.getPac()) + "%" ) );
					break;
				case 3:
					//加载所有下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(importantUnits.getPac()) ) );
					break;
				default:
					//默认： .eq()
					criteria.add( Restrictions.eq("pac", importantUnits.getPac() ) );
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

		if (!StringUtil.isEmpty(importantUnits.getName())){
			criteria.add( Restrictions.like("name", "%" + importantUnits.getName() + "%") );
		}

		grid.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPImportantUnits> lists = criteria.list();
		for (FFPImportantUnits importantUnits1: lists){
			importantUnits1.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPImportantUnitsMedia ium where mf.id=ium.mediaFile.id and ium.importantUnits.id=? ", importantUnits1.getId()));
			Object shape = findOneForJdbc("select ASTEXT(shape) shape FROM ffp_importantUnits where id=?", importantUnits1.getId()).get("shape");
			if (StringUtil.isNotEmpty(shape)){
				importantUnits1.setShape(shape.toString());
			}
		}
		grid.setRows(lists);
		return grid;
	}

	@Override
	public ReturnBody addImportantMedia(FFPImportantUnitsMedia importantUnitsMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(importantUnitsMedia.getImportantUnits()) && !StringUtil.isNotEmpty(get(FFPImportantUnits.class, importantUnitsMedia.getImportantUnits().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [brigade.id].");
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

				importantUnitsMedia.setMediaFile(mediaFile1);

				save(importantUnitsMedia);

				body.setObj(importantUnitsMedia.getMediaFile());

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
	public ReturnBody deleteImportantMedia(FFPImportantUnitsMedia importantUnitsMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPImportantUnitsMedia importantUnitsMedia_db = get(FFPImportantUnitsMedia.class, oConvertUtils.getString(importantUnitsMedia.getId()));
		if (!StringUtil.isNotEmpty(importantUnitsMedia_db)){
			importantUnitsMedia_db = findUniqueByProperty(FFPImportantUnitsMedia.class, "mediaFile.id", oConvertUtils.getString(importantUnitsMedia.getId()));
		}
		if (StringUtil.isNotEmpty(importantUnitsMedia_db)){
			if (StringUtil.isNotEmpty(importantUnitsMedia_db.getMediaFile())){
				delete(importantUnitsMedia_db.getMediaFile());
				delete(importantUnitsMedia_db);
				String fileUrl = projectDirectory + importantUnitsMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + importantUnitsMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
