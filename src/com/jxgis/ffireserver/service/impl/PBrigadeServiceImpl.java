package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPPBrigade;
import com.jxgis.ffireserver.bean.FFPPBrigadeMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.PBrigadeService;
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
 * 专业森林消防队 业务逻辑层实现类
 *
 */
@Service("pBrigadeService")
@Transactional
public class PBrigadeServiceImpl extends SystemServiceImpl implements PBrigadeService {
	@Override
	public ReturnBody addPBrigade(FFPPBrigade brigade, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();

		if (StringUtil.isEmpty(brigade.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [pac] must not be null.");
			return body;
		}
		if (StringUtil.isEmpty(brigade.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [name] must not be null.");
			return body;
		}

		if (!StringUtil.isEmpty(brigade.getShape())){
			try{
				WKTReader fromText = new WKTReader();
				fromText.read(brigade.getShape());
			} catch (ParseException e) {
				body.setStatus(Glossary.Return_State_Geometry_Format_Error);
				body.setMsg( "Parameter [shape] must not be null.");
				return body;
			}
		}

		save(brigade);

		List<FFPPBrigadeMedia> brigadeMedias = new ArrayList<>();
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

					FFPPBrigadeMedia brigadeMedia = new FFPPBrigadeMedia();
					brigadeMedia.setBrigade(brigade);
					brigadeMedia.setMediaFile(mediaFile_save);
					brigadeMedias.add(brigadeMedia);


				} catch (Exception e){
					e.printStackTrace();
				}
			}

		}


		try{
			batchSave(brigadeMedias);
			body.setObj(brigade.getId());
			return body;
		} catch (Exception e){
			delete(brigade);

			for (TSMediaFile mediaFile_save: mediaFile_saves){
				FileUtils.delete(mediaFile_save.getFileUrl());
				if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
					FileUtils.delete(mediaFile_save.getFileThumbUrl());
				}
			}
			deleteAllEntitie(mediaFile_saves);
			deleteAllEntitie(brigadeMedias);
		}
		body.setStatus(Glossary.Return_State_Exception_Operation);
		body.setMsg("operation failed.");
		return body;
	}

	@Override
	public ReturnBody deletePBrigade(FFPPBrigade brigade) throws Exception {
		ReturnBody body = new ReturnBody();
		brigade = get(FFPPBrigade.class, oConvertUtils.getString(brigade.getId()));
		if (StringUtil.isNotEmpty(brigade)){
			List<FFPPBrigadeMedia> brigadeMedias = findByProperty(FFPPBrigadeMedia.class, "brigade.id", oConvertUtils.getString(brigade.getId()));
			if (StringUtil.isNotEmpty(brigadeMedias) && brigadeMedias.size() > 0){
				for (FFPPBrigadeMedia brigadeMedia: brigadeMedias){
					deletePBrigadeMedia(brigadeMedia);
				}
			}
			delete(brigade);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter must not be null [id].");
		return body;
	}
	@Override
	public ReturnBody deletePBrigades(List<FFPPBrigade> brigades) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(brigades)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}

		for (FFPPBrigade brigade: brigades){
			deletePBrigade(brigade);
		}
		return body;
	}

	@Override
	public ReturnBody editPBrigade(FFPPBrigade brigade) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPPBrigade brigade_db = get(FFPPBrigade.class, oConvertUtils.getString(brigade.getId()));
		if (StringUtil.isNotEmpty(brigade_db)){

			MyBeanUtils.copyBeanNotNull2Bean(brigade, brigade_db);

			if (!StringUtil.isEmpty(brigade.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(brigade.getShape());
					updateEntitie(brigade_db);
					executeSql("UPDATE ffp_pbrigade SET shape=POINTFROMTEXT('"+brigade.getShape()+"') WHERE id=?", brigade_db.getId());
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

			updateEntitie(brigade_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getPBrigadeList(FFPPBrigade brigade, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();
		Criteria criteria = this.getSession().createCriteria(FFPPBrigade.class);


		if (!StringUtil.isEmpty(brigade.getPac())){

			switch (fetchType){
				case 1:
					//只加载下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(brigade.getPac(),true) ) );
					break;
				case 2:
					//加载所有下一级，甚用
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(brigade.getPac()) + "%" ) );
					break;
				case 3:
					//加载所有下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(brigade.getPac()) ) );
					break;
				default:
					//默认： .eq()
					criteria.add( Restrictions.eq("pac", brigade.getPac() ) );
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

		if (!StringUtil.isEmpty(brigade.getName())){
			criteria.add( Restrictions.like("name", "%" + brigade.getName() + "%" ) );
		}

		grid.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPPBrigade> lists = criteria.list();
		for (FFPPBrigade brigade1: lists){
			brigade1.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPPBrigadeMedia bm where mf.id=bm.mediaFile.id and bm.brigade.id=? ", brigade1.getId()));
			Object shape = findOneForJdbc("select ASTEXT(shape) shape FROM ffp_pbrigade where id=?", brigade1.getId()).get("shape");
			if (StringUtil.isNotEmpty(shape)){
				brigade1.setShape(shape.toString());
			}
		}
		grid.setRows(lists);
		return grid;
	}

	@Override
	public ReturnBody addPBrigadeMedia(FFPPBrigadeMedia brigadeMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(brigadeMedia.getBrigade()) && !StringUtil.isNotEmpty(get(FFPPBrigade.class, brigadeMedia.getBrigade().getId()))){
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

				brigadeMedia.setMediaFile(mediaFile1);

				save(brigadeMedia);

				body.setObj(brigadeMedia.getMediaFile());

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
	public ReturnBody deletePBrigadeMedia(FFPPBrigadeMedia brigadeMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPPBrigadeMedia brigadeMedia_db = get(FFPPBrigadeMedia.class, oConvertUtils.getString(brigadeMedia.getId()));
		if (!StringUtil.isNotEmpty(brigadeMedia_db)){
			brigadeMedia_db = findUniqueByProperty(FFPPBrigadeMedia.class, "mediaFile.id", oConvertUtils.getString(brigadeMedia.getId()));
		}
		if (StringUtil.isNotEmpty(brigadeMedia_db)){
			if (StringUtil.isNotEmpty(brigadeMedia_db.getMediaFile())){
				delete(brigadeMedia_db.getMediaFile());
				delete(brigadeMedia_db);
				String fileUrl = projectDirectory + brigadeMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + brigadeMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
