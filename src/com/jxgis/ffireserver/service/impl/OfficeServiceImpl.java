package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPOffice;
import com.jxgis.ffireserver.bean.FFPOfficeMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.OfficeService;
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
 * Created by YanWei on 2017/8/28.
 *
 * 森林防火办公室 业务层实现类
 *
 */
@Service("officeService")
@Transactional
public class OfficeServiceImpl extends SystemServiceImpl implements OfficeService {
	@Override
	public ReturnBody addOffice(FFPOffice office, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();

		if (StringUtil.isEmpty(office.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [pac] must not be null.");
			return body;
		}
		if (StringUtil.isEmpty(office.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [name] must not be null.");
			return body;
		}
		if (!StringUtil.isEmpty(office.getShape())){
			try{
				WKTReader fromText = new WKTReader();
				fromText.read(office.getShape());
			} catch (ParseException e) {
				body.setStatus(Glossary.Return_State_Geometry_Format_Error);
				body.setMsg( "Parameter [shape] must not be null.");
				return body;
			}
		}
		save(office);

		List<FFPOfficeMedia> officeMedias = new ArrayList<>();
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

					FFPOfficeMedia officeMedia = new FFPOfficeMedia();
					officeMedia.setOffice(office);
					officeMedia.setMediaFile(mediaFile_save);
					officeMedias.add(officeMedia);


				} catch (Exception e){
					e.printStackTrace();
				}
			}

		}


		try{
			batchSave(officeMedias);
			body.setObj(office.getId());
			return body;
		} catch (Exception e){
			delete(office);

			for (TSMediaFile mediaFile_save: mediaFile_saves){
				FileUtils.delete(mediaFile_save.getFileUrl());
				if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
					FileUtils.delete(mediaFile_save.getFileThumbUrl());
				}
			}
			deleteAllEntitie(mediaFile_saves);
			deleteAllEntitie(officeMedias);
		}
		body.setStatus(Glossary.Return_State_Exception_Operation);
		body.setMsg("operation failed.");
		return body;
	}

	@Override
	public ReturnBody deleteOffice(FFPOffice office) throws Exception {
		ReturnBody body = new ReturnBody();
		office = get(FFPOffice.class, oConvertUtils.getString(office.getId()));
		if (StringUtil.isNotEmpty(office)){
			List<FFPOfficeMedia> officeMedias = findByProperty(FFPOfficeMedia.class, "office.id", oConvertUtils.getString(office.getId()));
			if (StringUtil.isNotEmpty(officeMedias) && officeMedias.size() > 0){
				for (FFPOfficeMedia officeMedia: officeMedias){
					deleteOfficeMedia(officeMedia);
				}
			}
			delete(office);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter must not be null [id].");
		return body;
	}
	@Override
	public ReturnBody deleteOffices(List<FFPOffice> offices) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(offices)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		for (FFPOffice office: offices){
			deleteOffice(office);
		}
		return body;
	}

	@Override
	public ReturnBody editOffice(FFPOffice office) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPOffice office_db = get(FFPOffice.class, oConvertUtils.getString(office.getId()));
		if (StringUtil.isNotEmpty(office_db)){

			MyBeanUtils.copyBeanNotNull2Bean(office, office_db);

			if (!StringUtil.isEmpty(office.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(office.getShape());
					updateEntitie(office_db);
					executeSql("UPDATE ffp_office SET shape=POINTFROMTEXT('"+office.getShape()+"') WHERE id=?", office_db.getId());
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


			updateEntitie(office_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getOfficeList(FFPOffice office, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();
		Criteria criteria = this.getSession().createCriteria(FFPOffice.class);


		if (StringUtil.isNotEmpty(office.getType())){
			criteria.add( Restrictions.eq("type", office.getType() ) );
		}



		if (!StringUtil.isEmpty(office.getPac())){

			switch (fetchType){
				case 1:
					//只加载下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(office.getPac(),true) ) );
					break;
				case 2:
					//加载所有下一级，甚用
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(office.getPac()) + "%" ) );
					break;
				case 3:
					//加载所有下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(office.getPac()) ) );
					break;
				default:
					//默认： .eq()
					criteria.add( Restrictions.eq("pac", office.getPac() ) );
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

		if (!StringUtil.isEmpty(office.getName())){
			criteria.add( Restrictions.like("name", "%" + office.getName() + "%") );
		}

		grid.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPOffice> lists = criteria.list();
		for (FFPOffice office1: lists){
			office1.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPOfficeMedia om where mf.id=om.mediaFile.id and om.office.id=? ", office1.getId()));
			Object shape = findOneForJdbc("select ASTEXT(shape) shape FROM ffp_office where id=?", office1.getId()).get("shape");
			if (StringUtil.isNotEmpty(shape)){
				office1.setShape(shape.toString());
			}
		}
		grid.setRows(lists);
		return grid;
	}

	@Override
	public ReturnBody addOfficeMedia(FFPOfficeMedia officeMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(officeMedia.getOffice()) && !StringUtil.isNotEmpty(get(FFPOffice.class, oConvertUtils.getString(officeMedia.getOffice().getId())))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [office.id].");
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

				officeMedia.setMediaFile(mediaFile1);

				save(officeMedia);

				body.setObj(officeMedia.getMediaFile());

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
	public ReturnBody deleteOfficeMedia(FFPOfficeMedia officeMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPOfficeMedia officeMedia_db = get(FFPOfficeMedia.class, oConvertUtils.getString(officeMedia.getId()));
		if (!StringUtil.isNotEmpty(officeMedia_db)){
			officeMedia_db = findUniqueByProperty(FFPOfficeMedia.class, "mediaFile.id", oConvertUtils.getString(officeMedia.getId()));
		}
		if (StringUtil.isNotEmpty(officeMedia_db)){
			if (StringUtil.isNotEmpty(officeMedia_db.getMediaFile())){
				delete(officeMedia_db.getMediaFile());
				delete(officeMedia_db);
				String fileUrl = projectDirectory + officeMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + officeMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
