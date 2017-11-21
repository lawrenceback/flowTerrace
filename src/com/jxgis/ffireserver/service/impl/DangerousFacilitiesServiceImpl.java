package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPDangerousFacilities;
import com.jxgis.ffireserver.bean.FFPDangerousFacilitiesMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.DangerousFacilitiesService;
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
 * Created by YanWei on 2017/8/4.
 *
 * 林区危险及重要性业务层实现类
 *
 */
@Service("dangerousFacilitiesService")
@Transactional
public class DangerousFacilitiesServiceImpl extends SystemServiceImpl implements DangerousFacilitiesService {
	@Override
	public ReturnBody addDangerousFacilities(FFPDangerousFacilities facilities, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();

		if (StringUtil.isEmpty(facilities.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [pac] must not be null.");
			return body;
		}
		if (StringUtil.isEmpty(facilities.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [name] must not be null.");
			return body;
		}
		if (!StringUtil.isEmpty(facilities.getShape())){
			try{
				WKTReader fromText = new WKTReader();
				fromText.read(facilities.getShape());
			} catch (ParseException e) {
				body.setStatus(Glossary.Return_State_Geometry_Format_Error);
				body.setMsg( "Parameter [shape] must not be null.");
				return body;
			}
		}
		save(facilities);

		List<FFPDangerousFacilitiesMedia> dangerousFacilitiesMedias = new ArrayList<>();
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

					FFPDangerousFacilitiesMedia facilitiesMedia = new FFPDangerousFacilitiesMedia();
					facilitiesMedia.setFacilities(facilities);
					facilitiesMedia.setMediaFile(mediaFile_save);
					dangerousFacilitiesMedias.add(facilitiesMedia);


				} catch (Exception e){
					e.printStackTrace();
				}
			}

		}


		try{
			batchSave(dangerousFacilitiesMedias);
			body.setObj(facilities.getId());
			return body;
		} catch (Exception e){
			delete(facilities);

			for (TSMediaFile mediaFile_save: mediaFile_saves){
				FileUtils.delete(mediaFile_save.getFileUrl());
				if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
					FileUtils.delete(mediaFile_save.getFileThumbUrl());
				}
			}
			deleteAllEntitie(mediaFile_saves);
			deleteAllEntitie(dangerousFacilitiesMedias);
		}
		body.setStatus(Glossary.Return_State_Exception_Operation);
		body.setMsg("operation failed.");
		return body;
	}

	@Override
	public ReturnBody deleteDangerousFacilities(FFPDangerousFacilities facilities) throws Exception {
		ReturnBody body = new ReturnBody();
		facilities = get(FFPDangerousFacilities.class, oConvertUtils.getString(facilities.getId()));
		if (StringUtil.isNotEmpty(facilities)){
			List<FFPDangerousFacilitiesMedia> facilitiesMedias = findByProperty(FFPDangerousFacilitiesMedia.class, "facilities.id", oConvertUtils.getString(facilities.getId()));
			if (StringUtil.isNotEmpty(facilitiesMedias) && facilitiesMedias.size() > 0){
				for (FFPDangerousFacilitiesMedia facilitiesMedia: facilitiesMedias){
					deleteDangerousFacilitiesMedia(facilitiesMedia);
				}
			}
			delete(facilities);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter must not be null [id].");
		return body;
	}

	@Override
	public ReturnBody deleteDangerousFacilities(List<FFPDangerousFacilities> facilities) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(facilities)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		for(FFPDangerousFacilities dangerousFacilities: facilities){
			deleteDangerousFacilities(dangerousFacilities);
		}
		return body;
	}

	@Override
	public ReturnBody editDangerousFacilities(FFPDangerousFacilities facilities) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPDangerousFacilities facilities_db = get(FFPDangerousFacilities.class, oConvertUtils.getString(facilities.getId()));
		if (StringUtil.isNotEmpty(facilities_db)){

			MyBeanUtils.copyBeanNotNull2Bean(facilities, facilities_db);

			if (!StringUtil.isEmpty(facilities.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(facilities.getShape());
					updateEntitie(facilities_db);
					executeSql("UPDATE ffp_dangerous_facilities SET shape=POINTFROMTEXT('"+facilities.getShape()+"') WHERE id=?", facilities_db.getId());
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


			updateEntitie(facilities_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getDangerousFacilitiesList(FFPDangerousFacilities facilities, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();
		Criteria criteria = this.getSession().createCriteria(FFPDangerousFacilities.class);


		if (StringUtil.isNotEmpty(facilities.getType())){
			criteria.add( Restrictions.eq("type", facilities.getType() ) );
		}



		if (!StringUtil.isEmpty(facilities.getPac())){

			switch (fetchType){
				case 1:
					//只加载下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(facilities.getPac(),true) ) );
					break;
				case 2:
					//加载所有下一级，甚用
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(facilities.getPac()) + "%" ) );
					break;
				case 3:
					//加载所有下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(facilities.getPac()) ) );
					break;
				default:
					//默认： .eq()
					criteria.add( Restrictions.eq("pac", facilities.getPac() ) );
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

		if (!StringUtil.isEmpty(facilities.getName())){
			criteria.add( Restrictions.like("name", "%" + facilities.getName() + "%" ) );
		}

		grid.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPDangerousFacilities> lists = criteria.list();
		for (FFPDangerousFacilities facilities1: lists){
			facilities1.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPDangerousFacilitiesMedia dfm where mf.id=dfm.mediaFile.id and dfm.facilities.id=? ", facilities1.getId()));
			Object shape = findOneForJdbc("select ASTEXT(shape) shape FROM ffp_dangerous_facilities where id=?", facilities1.getId()).get("shape");
			if (StringUtil.isNotEmpty(shape)){
				facilities1.setShape(shape.toString());
			}
		}
		grid.setRows(lists);
		return grid;

	}

	@Override
	public ReturnBody addDangerousFacilitiesMedia(FFPDangerousFacilitiesMedia facilitiesMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(facilitiesMedia.getFacilities()) && !StringUtil.isNotEmpty(get(FFPDangerousFacilities.class, facilitiesMedia.getFacilities().getId()))){
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

				facilitiesMedia.setMediaFile(mediaFile1);

				save(facilitiesMedia);

				body.setObj(facilitiesMedia.getMediaFile());

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
	public ReturnBody deleteDangerousFacilitiesMedia(FFPDangerousFacilitiesMedia facilitiesMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPDangerousFacilitiesMedia facilitiesMedia_db = get(FFPDangerousFacilitiesMedia.class, oConvertUtils.getString(facilitiesMedia.getId()));
		if (!StringUtil.isNotEmpty(facilitiesMedia_db)){
			facilitiesMedia_db = findUniqueByProperty(FFPDangerousFacilitiesMedia.class, "mediaFile.id", oConvertUtils.getString(facilitiesMedia.getId()));
		}
		if (StringUtil.isNotEmpty(facilitiesMedia_db)){
			if (StringUtil.isNotEmpty(facilitiesMedia_db.getMediaFile())){
				delete(facilitiesMedia_db.getMediaFile());
				delete(facilitiesMedia_db);
				String fileUrl = projectDirectory + facilitiesMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + facilitiesMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
