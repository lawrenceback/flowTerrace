package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPGridUserTable;
import com.jxgis.ffireserver.bean.FFPGridUserTablePhoto;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.GridUserTableService;
import com.jxgis.ffireserver.util.FormatPac;
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
 * Created by YanWei on 2017/9/14.
 *
 * 网格人员管理表 业务层实现类
 *
 */
@Service("gridUserTableService")
@Transactional
public class GridUserTableServiceImpl extends SystemServiceImpl implements GridUserTableService {

	@Override
	public ReturnBody addGridUserTable(FFPGridUserTable gridUserTable, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();

		if (!StringUtil.isNotEmpty(gridUserTable)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}

		if (StringUtil.isEmpty(gridUserTable.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [name].");
			return body;
		}
		if (StringUtil.isEmpty(gridUserTable.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [pac].");
			return body;
		}
		if (!StringUtil.isNotEmpty(gridUserTable.getGender())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [gender].");
			return body;
		}
		if (!StringUtil.isNotEmpty(gridUserTable.getAge())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [age].");
			return body;
		}
		if (StringUtil.isEmpty(gridUserTable.getPhone())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [phone].");
			return body;
		}
		if (!StringUtil.isNotEmpty(gridUserTable.getType())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [type].");
			return body;
		}
		save(gridUserTable);



		List<FFPGridUserTablePhoto> tablePhotos = new ArrayList<>();
		List<TSMediaFile> photo_saves = new ArrayList<>();

		if (StringUtil.isNotEmpty(mediaFiles)){

			// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
			String projectDirectory = ResourceUtil.getSysPath();
			for (Map.Entry<String, MultipartFile> entry : mediaFiles.entrySet()) {
				try {

					MultipartFile mediaFile = entry.getValue();

					String suffix = FileUtils.getExtend(mediaFile.getOriginalFilename());//文件后缀
					int fileType = FileType.getAllFileType(suffix);
					if (fileType != Glossary.File_Type_Img){
						//当用户上传的文件类型并不是图片，路过该文件
						continue;
					}

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
					String path = projectDirectory + "files/" + toDay + "/";

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
					switch (fileType) {
						case Glossary.File_Type_Img:
							//截取图片缩略图
							new ImageThumUtil().thumbnailImage(path + "/" + saveFileName_suffix, 256, 256);
							thumb_fileUrl = "files/" + toDay + "/thumb_" + saveFileName_suffix;
							mediaFile_save.setFileThumbUrl(thumb_fileUrl);
							break;
						case Glossary.File_Type_Video:
							//视频预览图
							thumb_fileUrl = "files/" + toDay + "/thumb_" + saveFileName + ".jpg";
							PicHelper.handler(projectDirectory + Glossary.File_Ffmpeg_ads, path + "/" + saveFileName_suffix, projectDirectory + thumb_fileUrl);
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

					photo_saves.add(mediaFile_save);

					FFPGridUserTablePhoto tablePhoto = new FFPGridUserTablePhoto();
					tablePhoto.setGridUserTable(gridUserTable);
					tablePhoto.setMediaFile(mediaFile_save);
					tablePhotos.add(tablePhoto);


				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}


		try{
			batchSave(tablePhotos);
			body.setObj(gridUserTable.getId());
			return body;
		} catch (Exception e){
			delete(gridUserTable);

			for (TSMediaFile photo_save: photo_saves){
				FileUtils.delete(photo_save.getFileUrl());
				if (StringUtil.isEmpty(photo_save.getFileThumbUrl())){
					FileUtils.delete(photo_save.getFileThumbUrl());
				}
			}
			deleteAllEntitie(photo_saves);
			deleteAllEntitie(tablePhotos);
		}
		body.setStatus(Glossary.Return_State_Exception_Operation);
		body.setMsg("operation failed.");
		return body;
	}

	@Override
	public ReturnBody deleteGridUserTable(FFPGridUserTable gridUserTable) throws Exception {
		ReturnBody body = new ReturnBody();
		gridUserTable = get(FFPGridUserTable.class, oConvertUtils.getString(gridUserTable.getId()));
		if (StringUtil.isNotEmpty(gridUserTable)){
			List<FFPGridUserTablePhoto> tablePhotos = findByProperty(FFPGridUserTablePhoto.class, "gridUserTable.id", oConvertUtils.getString(gridUserTable.getId()));
			if (StringUtil.isNotEmpty(tablePhotos) && tablePhotos.size() > 0){
				for (FFPGridUserTablePhoto tablePhoto: tablePhotos){
					deleteBeltPointPhoto(tablePhoto);
				}
			}
			delete(gridUserTable);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter must not be null [id].");
		return body;
	}

	@Override
	public ReturnBody editGridUserTable(FFPGridUserTable gridUserTable) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPGridUserTable gridUserTable_db = get(FFPGridUserTable.class, oConvertUtils.getString(gridUserTable.getId()));
		if (StringUtil.isNotEmpty(gridUserTable_db)){

			MyBeanUtils.copyBeanNotNull2Bean(gridUserTable, gridUserTable_db);
			updateEntitie(gridUserTable_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getGridUserTableList(FFPGridUserTable gridUserTable, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(FFPGridUserTable.class);

		if (StringUtil.isNotEmpty(gridUserTable)){

			if (!StringUtil.isEmpty(gridUserTable.getPac())){

				switch (fetchType){
					case 1:
						//只加载下一级
						criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(gridUserTable.getPac(),true) ) );
						break;
					case 2:
						//加载所有下一级，甚用
						criteria.add( Restrictions.like("pac", FormatPac.formatPac(gridUserTable.getPac()) + "%" ) );
						break;
					case 3:
						//加载所有下一级，最大到县
						criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(gridUserTable.getPac()) ) );
						break;
					default:
						//默认： .eq()
						criteria.add( Restrictions.eq("pac", gridUserTable.getPac() ) );
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

		if (!StringUtil.isEmpty(gridUserTable.getName())){
			criteria.add( Restrictions.like("name", "%" + gridUserTable.getName() + "%" ) );
		}

		grid.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPGridUserTable> lists = criteria.list();
		for (FFPGridUserTable gridUserTable1: lists){
			gridUserTable1.setPhoto(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPGridUserTablePhoto gutp where mf.id=gutp.mediaFile.id and gutp.gridUserTable.id=? ", gridUserTable1.getId()));
		}
		grid.setRows(lists);
		return grid;
	}

	@Override
	public ReturnBody addBeltPointPhoto(FFPGridUserTablePhoto gridUserTablePhoto, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(gridUserTablePhoto.getGridUserTable()) && !StringUtil.isNotEmpty(get(FFPGridUserTable.class, gridUserTablePhoto.getGridUserTable().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [gridUserTable.id].");
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

				gridUserTablePhoto.setMediaFile(mediaFile1);

				save(gridUserTablePhoto);

				body.setObj(gridUserTablePhoto.getMediaFile());

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
	public ReturnBody deleteBeltPointPhoto(FFPGridUserTablePhoto gridUserTablePhoto) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(gridUserTablePhoto)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [id].");
			return body;
		}
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPGridUserTablePhoto gridUserTablePhoto_db = get(FFPGridUserTablePhoto.class, oConvertUtils.getString(gridUserTablePhoto.getId()));
		if (!StringUtil.isNotEmpty(gridUserTablePhoto_db)){
			gridUserTablePhoto_db = findUniqueByProperty(FFPGridUserTablePhoto.class, "mediaFile.id", oConvertUtils.getString(gridUserTablePhoto.getId()));
		}
		if (StringUtil.isNotEmpty(gridUserTablePhoto_db)){
			if (StringUtil.isNotEmpty(gridUserTablePhoto_db.getMediaFile())){
				delete(gridUserTablePhoto_db.getMediaFile());
				delete(gridUserTablePhoto_db);
				String fileUrl = projectDirectory + gridUserTablePhoto_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + gridUserTablePhoto_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
