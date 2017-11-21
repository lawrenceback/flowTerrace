package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPObservatory;
import com.jxgis.ffireserver.bean.FFPObservatoryMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.ObservatoryService;
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
 * Created by YanWei on 2017/7/27.
 *
 */
@Service("observatoryService")
@Transactional
public class ObservatoryServiceImpl extends SystemServiceImpl implements ObservatoryService {

	@Override
	public ReturnBody addObservatory(FFPObservatory observatory) throws Exception {
		ReturnBody body = new ReturnBody();
		if ( StringUtil.isEmpty(observatory.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter must not be null [name].");
			return body;
		}
		if (StringUtil.isEmpty(observatory.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter must not be null [pac].");
			return body;
		}
		save(observatory);
		return body;
	}

	@Override
	public ReturnBody addObservatory(FFPObservatory observatory, Map<String, MultipartFile> mediaFiles ) throws Exception {
		ReturnBody body = new ReturnBody();
		if ( StringUtil.isEmpty(observatory.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter must not be null [name].");
			return body;
		}
		if (StringUtil.isEmpty(observatory.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter must not be null [pac].");
			return body;
		}

		if (!StringUtil.isEmpty(observatory.getShape())){
			try{
				WKTReader fromText = new WKTReader();
				fromText.read(observatory.getShape());
			} catch (ParseException e) {
				body.setStatus(Glossary.Return_State_Geometry_Format_Error);
				body.setMsg( "Parameter [shape] error.");
				return body;
			}
		}

		save(observatory);

		List<FFPObservatoryMedia> observatoryMedias = new ArrayList<>();
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

					FFPObservatoryMedia observatoryMedia = new FFPObservatoryMedia();
					observatoryMedia.setObservatory(observatory);
					observatoryMedia.setMediaFile(mediaFile_save);
					observatoryMedias.add(observatoryMedia);


				} catch (Exception e){
					e.printStackTrace();
				}
			}


		}


		try{
			batchSave(observatoryMedias);
			body.setObj(observatory.getId());
			return body;
		} catch (Exception e){
			delete(observatory);

			for (TSMediaFile mediaFile_save: mediaFile_saves){
				FileUtils.delete(mediaFile_save.getFileUrl());
				if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
					FileUtils.delete(mediaFile_save.getFileThumbUrl());
				}
			}
			deleteAllEntitie(mediaFile_saves);
			deleteAllEntitie(observatoryMedias);
		}
		body.setStatus(Glossary.Return_State_Exception_Operation);
		body.setMsg("operation failed.");
		return body;
	}

	@Override
	public ReturnBody deleteObservatory(FFPObservatory observatory) throws Exception {
		ReturnBody body = new ReturnBody();
		observatory = get(FFPObservatory.class, oConvertUtils.getString(observatory.getId()));
		if (StringUtil.isNotEmpty(observatory)){
			List<FFPObservatoryMedia> observatoryMedias = findByProperty(FFPObservatoryMedia.class, "observatory.id", oConvertUtils.getString(observatory.getId()));
			if (StringUtil.isNotEmpty(observatoryMedias) && observatoryMedias.size() > 0){
				for (FFPObservatoryMedia observatoryMedia: observatoryMedias){
					deleteObservatoryMedia(observatoryMedia);
				}
			}
			delete(observatory);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter must not be null [id].");
		return body;
	}

	@Override
	public ReturnBody deleteObservatorys(List<FFPObservatory> observatories) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(observatories)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		for(FFPObservatory observatory: observatories){
			deleteObservatory(observatory);
		}
		return body;
	}

	@Override
	public ReturnBody editObservatory(FFPObservatory observatory) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPObservatory observatory_db = get(FFPObservatory.class, oConvertUtils.getString(observatory.getId()));
		if (StringUtil.isNotEmpty(observatory_db)){


			if (!StringUtil.isEmpty(observatory.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(observatory.getShape());

					int rows = executeSql("UPDATE ffp_observatory SET shape=POINTFROMTEXT('"+observatory.getShape()+"') WHERE id=?", observatory_db.getId());
					if (rows < 1){
						body.setStatus(Glossary.Return_State_Exception_Operation);
						body.setMsg( "Update [shape] failed.");
						return body;
					}
				} catch (ParseException e) {
					body.setStatus(Glossary.Return_State_Geometry_Format_Error);
					body.setMsg( "Parameter [shape] must not be null.");
					return body;
				}
			}

			MyBeanUtils.copyBeanNotNull2Bean(observatory, observatory_db);

			if (!StringUtil.isEmpty(observatory.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(observatory.getShape());
					updateEntitie(observatory_db);
					executeSql("UPDATE ffp_observatory SET shape=POINTFROMTEXT('"+observatory.getShape()+"') WHERE id=?", observatory_db.getId());
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

			updateEntitie(observatory_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getObservatoryList(FFPObservatory observatory, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(FFPObservatory.class);

		if (StringUtil.isNotEmpty(observatory)){

			if (!StringUtil.isEmpty(observatory.getPac())){

				switch (fetchType){
					case 1:
						//只加载下一级
						criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(observatory.getPac(),true) ) );
						break;
					case 2:
						//加载所有下一级，甚用
						criteria.add( Restrictions.like("pac", FormatPac.formatPac(observatory.getPac()) + "%" ) );
						break;
					case 3:
						//加载所有下一级，最大到县
						criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(observatory.getPac()) ) );
						break;
					default:
						//默认： .eq()
						criteria.add( Restrictions.eq("pac", observatory.getPac() ) );
						break;
				}

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

		if (!StringUtil.isEmpty(observatory.getName())){
			criteria.add( Restrictions.like("name", "%" + observatory.getName() + "%" ) );
		}
		List li = criteria.list();
		grid.setTotal(li.size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPObservatory> lists = criteria.list();
		for (FFPObservatory observatory_ls: lists){
			observatory_ls.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPObservatoryMedia om where mf.id=om.mediaFile.id and om.observatory.id=? ", observatory_ls.getId()));
			Map<String, Object> map =findOneForJdbc("select ASTEXT(shape) shape FROM ffp_observatory where id=?", observatory_ls.getId());
			if (StringUtil.isNotEmpty(map) && StringUtil.isNotEmpty(map.get("shape"))){
				observatory_ls.setShape(map.get("shape").toString());
			}

		}
		grid.setRows(lists);
		return grid;
	}

	@Override
	public ReturnBody addObservatoryMedia(FFPObservatoryMedia observatoryMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(observatoryMedia.getObservatory()) && !StringUtil.isNotEmpty(get(FFPObservatory.class, observatoryMedia.getObservatory().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [observatory.id].");
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

				observatoryMedia.setMediaFile(mediaFile1);

				save(observatoryMedia);

				body.setObj(observatoryMedia.getMediaFile());
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
	public ReturnBody deleteObservatoryMedia(FFPObservatoryMedia observatoryMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(observatoryMedia)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [id].");
			return body;
		}
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPObservatoryMedia observatoryMedia_db = get(FFPObservatoryMedia.class, oConvertUtils.getString(observatoryMedia.getId()));
		if (!StringUtil.isNotEmpty(observatoryMedia_db)){
			observatoryMedia_db = findUniqueByProperty(FFPObservatoryMedia.class, "mediaFile.id", oConvertUtils.getString(observatoryMedia.getId()));
		}
		if (StringUtil.isNotEmpty(observatoryMedia_db)){
			if (StringUtil.isNotEmpty(observatoryMedia_db.getMediaFile())){
				delete(observatoryMedia_db.getMediaFile());
				delete(observatoryMedia_db);
				String fileUrl = projectDirectory + observatoryMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + observatoryMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}


}
