package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPWarehouse;
import com.jxgis.ffireserver.bean.FFPWarehouseMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.WarehouseService;
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
 * Created by YanWei on 2017/8/30.
 *
 * 森林防火物资储备库 业务层 实现类
 *
 */
@Service("warehouseService")
@Transactional
public class WarehouseServiceImpl extends SystemServiceImpl implements WarehouseService {
	@Override
	public ReturnBody addSWarehouse(FFPWarehouse warehouse, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isEmpty(warehouse.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [pac],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(warehouse.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [name],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(warehouse.getManager())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [manager],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(warehouse.getPhone())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [phone],must not be empty.");
			return body;
		}
		if (!StringUtil.isNotEmpty(warehouse.getLatitude())) {
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [latitude],must not be empty.");
			return body;
		}
		if (!StringUtil.isNotEmpty(warehouse.getLongitude())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [longitude],must not be empty.");
			return body;
		}
		if (!StringUtil.isEmpty(warehouse.getShape())){
			try{
				WKTReader fromText = new WKTReader();
				fromText.read(warehouse.getShape());
			} catch (ParseException e) {
				body.setStatus(Glossary.Return_State_Geometry_Format_Error);
				body.setMsg( "Parameter [shape] error.");
				return body;
			}
		}

		save(warehouse);

		List<FFPWarehouseMedia> warehouseMedias = new ArrayList<>();
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

					FFPWarehouseMedia warehouseMedia = new FFPWarehouseMedia();
					warehouseMedia.setWarehouse(warehouse);
					warehouseMedia.setMediaFile(mediaFile_save);
					warehouseMedias.add(warehouseMedia);


				} catch (Exception e){
					e.printStackTrace();
				}
			}

		}

		try{
			batchSave(warehouseMedias);
			body.setObj(warehouse.getId());
			return body;
		} catch (Exception e){
			delete(warehouse);

			for (TSMediaFile mediaFile_save: mediaFile_saves){
				FileUtils.delete(mediaFile_save.getFileUrl());
				if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
					FileUtils.delete(mediaFile_save.getFileThumbUrl());
				}
			}
			deleteAllEntitie(mediaFile_saves);
			deleteAllEntitie(warehouseMedias);
		}
		body.setStatus(Glossary.Return_State_Exception_Operation);
		body.setMsg("operation failed.");
		return body;
	}

	@Override
	public ReturnBody deleteSWarehouse(FFPWarehouse warehouse) throws Exception {
		ReturnBody body = new ReturnBody();
		warehouse = get(FFPWarehouse.class, warehouse.getId());
		if (StringUtil.isNotEmpty(warehouse)){
			List<FFPWarehouseMedia> warehouseMedias = findByProperty(FFPWarehouseMedia.class, "warehouse.id", oConvertUtils.getString(warehouse.getId()));
			if (StringUtil.isNotEmpty(warehouseMedias) && warehouseMedias.size() > 0){
				for (FFPWarehouseMedia warehouseMedia: warehouseMedias){
					deleteSWarehouseMedia(warehouseMedia);
				}
			}
			delete(warehouse);
			return body;
		}


		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Parameter error [id].");
		return body;
	}
	@Override
	public ReturnBody deleteSWarehouses(List<FFPWarehouse> warehouses) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(warehouses)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		for(FFPWarehouse warehouse: warehouses){
			deleteSWarehouse(warehouse);
		}
		return body;
	}

	@Override
	public ReturnBody editSWarehouse(FFPWarehouse warehouse) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPWarehouse warehouse_db = get(FFPWarehouse.class, warehouse.getId());
		if (StringUtil.isNotEmpty(warehouse_db)){
			MyBeanUtils.copyBeanNotNull2Bean(warehouse, warehouse_db);
			if (!StringUtil.isEmpty(warehouse.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(warehouse.getShape());
					updateEntitie(warehouse_db);
					executeSql("UPDATE ffp_warehouse SET shape=POINTFROMTEXT('"+warehouse.getShape()+"') WHERE id=?", warehouse_db.getId());
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
			updateEntitie(warehouse_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getSWarehouseList(FFPWarehouse warehouse, DataGrid dataGrid, int fetchTypt) throws Exception {
		ReturnDataGrid body = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(FFPWarehouse.class);


		if (!StringUtil.isEmpty(warehouse.getPac())){

			switch (fetchTypt){
				case 1:
					//只加载下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(warehouse.getPac(),true) ) );
					break;
				case 2:
					//加载所有下一级，甚用
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(warehouse.getPac()) + "%" ) );
					break;
				case 3:
					//加载所有下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(warehouse.getPac()) ) );
					break;
				default:
					//默认： .eq()
					criteria.add( Restrictions.eq("pac", warehouse.getPac() ) );
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

		if (!StringUtil.isEmpty(warehouse.getName())){
			criteria.add( Restrictions.like("name", "%" + warehouse.getName() + "%" ) );
		}

		body.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPWarehouse> lists = criteria.list();
		for (FFPWarehouse warehouse1: lists){
			warehouse1.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPWarehouseMedia wm where mf.id=wm.mediaFile.id and wm.warehouse.id=? ", warehouse1.getId()));
			Map<String, Object> map =findOneForJdbc("select ASTEXT(shape) shape FROM ffp_warehouse where id=?", warehouse1.getId());
			if (StringUtil.isNotEmpty(map) && StringUtil.isNotEmpty(map.get("shape"))){
				warehouse1.setShape(map.get("shape").toString());
			}

		}
		body.setRows(lists);
		return body;
	}

	@Override
	public ReturnBody addSWarehouseMedia(FFPWarehouseMedia warehouseMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(warehouseMedia.getWarehouse()) && !StringUtil.isNotEmpty(get(FFPWarehouse.class, warehouseMedia.getWarehouse().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [warehouse.id].");
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

				warehouseMedia.setMediaFile(mediaFile1);

				save(warehouseMedia);

				body.setObj(warehouseMedia.getMediaFile());

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
	public ReturnBody deleteSWarehouseMedia(FFPWarehouseMedia warehouseMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(warehouseMedia)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [id].");
			return body;
		}
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPWarehouseMedia warehouseMedia_db = get(FFPWarehouseMedia.class, oConvertUtils.getString(warehouseMedia.getId()));
		if (!StringUtil.isNotEmpty(warehouseMedia_db)){
			warehouseMedia_db = findUniqueByProperty(FFPWarehouseMedia.class, "mediaFile.id", oConvertUtils.getString(warehouseMedia.getId()));
		}
		if (StringUtil.isNotEmpty(warehouseMedia_db)){
			if (StringUtil.isNotEmpty(warehouseMedia_db.getMediaFile())){
				delete(warehouseMedia_db.getMediaFile());
				delete(warehouseMedia_db);
				String fileUrl = projectDirectory + warehouseMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + warehouseMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
