package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPRadioStation;
import com.jxgis.ffireserver.bean.FFPRadioStationMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.RadioStationService;
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
 * Created by YanWei on 2017/7/31.
 *
 * 无线电台站业务层实现类
 *
 */
@Service("radioStationService")
@Transactional
public class RadioStationServiceImpl extends SystemServiceImpl implements RadioStationService {
	@Override
	public ReturnBody addRadioStation(FFPRadioStation station, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();

		if (StringUtil.isEmpty(station.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [pac] must not be null.");
			return body;
		}
		if (StringUtil.isEmpty(station.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [name] must not be null.");
			return body;
		}
		if (StringUtil.isEmpty(station.getRadioname())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [radioname] must not be null.");
			return body;
		}
		if (StringUtil.isEmpty(station.getNum_radio())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [num_radio] must not be null.");
			return body;
		}
		if (!StringUtil.isEmpty(station.getShape())){
			try{
				WKTReader fromText = new WKTReader();
				fromText.read(station.getShape());
			} catch (ParseException e) {
				body.setStatus(Glossary.Return_State_Geometry_Format_Error);
				body.setMsg( "Parameter [shape] error.");
				return body;
			}
		}
		save(station);

		List<FFPRadioStationMedia> radioStationMedias = new ArrayList<>();
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

					FFPRadioStationMedia radioStationMedia = new FFPRadioStationMedia();
					radioStationMedia.setRadioStation(station);
					radioStationMedia.setMediaFile(mediaFile_save);
					radioStationMedias.add(radioStationMedia);


				} catch (Exception e){
					e.printStackTrace();
				}
			}

		}



		try{
			batchSave(radioStationMedias);
			body.setObj(station.getId());
			return body;
		} catch (Exception e){
			delete(station);

			for (TSMediaFile mediaFile_save: mediaFile_saves){
				FileUtils.delete(mediaFile_save.getFileUrl());
				if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
					FileUtils.delete(mediaFile_save.getFileThumbUrl());
				}
			}
			deleteAllEntitie(mediaFile_saves);
			deleteAllEntitie(radioStationMedias);
		}
		body.setStatus(Glossary.Return_State_Exception_Operation);
		body.setMsg("operation failed.");
		return body;
	}

	@Override
	public ReturnBody deleteRadioStation(FFPRadioStation station) throws Exception {
		ReturnBody body = new ReturnBody();
		station = get(FFPRadioStation.class, oConvertUtils.getString(station.getId()));
		if (StringUtil.isNotEmpty(station)){
			List<FFPRadioStationMedia> radioStationMedias = findByProperty(FFPRadioStationMedia.class, "radioStation.id", oConvertUtils.getString(station.getId()));
			if (StringUtil.isNotEmpty(radioStationMedias) && radioStationMedias.size() > 0){
				for (FFPRadioStationMedia radioStationMedia: radioStationMedias){
					deleteRadioStationMediaFile(radioStationMedia);
				}
			}
			delete(station);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter must not be null [id].");
		return body;
	}

	@Override
	public ReturnBody deleteRadioStations(List<FFPRadioStation> stations) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(stations)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		for(FFPRadioStation radioStation: stations){
			deleteRadioStation(radioStation);
		}
		return body;
	}

	@Override
	public ReturnBody editRadioStation(FFPRadioStation station) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPRadioStation station_db = get(FFPRadioStation.class, oConvertUtils.getString(station.getId()));
		if (StringUtil.isNotEmpty(station_db)){

			MyBeanUtils.copyBeanNotNull2Bean(station, station_db);

			if (!StringUtil.isEmpty(station.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(station.getShape());
					updateEntitie(station_db);
					executeSql("UPDATE ffp_radio_station SET shape=POINTFROMTEXT('"+station.getShape()+"') WHERE id=?", station_db.getId());
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

			updateEntitie(station_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getRadioStationList(FFPRadioStation station, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(FFPRadioStation.class);

		if (StringUtil.isNotEmpty(station)){

			if (!StringUtil.isEmpty(station.getPac())){

				switch (fetchType){
					case 1:
						//只加载下一级
						criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(station.getPac(),true) ) );
						break;
					case 2:
						//加载所有下一级，甚用
						criteria.add( Restrictions.like("pac", FormatPac.formatPac(station.getPac()) + "%" ) );
						break;
					case 3:
						//加载所有下一级，最大到县
						criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(station.getPac()) ) );
						break;
					default:
						//默认： .eq()
						criteria.add( Restrictions.eq("pac", station.getPac() ) );
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

		if (!StringUtil.isEmpty(station.getName())){
			criteria.add( Restrictions.like("name", "%" + station.getName() + "%" ) );
		}

		grid.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPRadioStation> lists = criteria.list();
		for (FFPRadioStation radioStation_ls: lists){
			radioStation_ls.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPRadioStationMedia rsm where mf.id=rsm.mediaFile.id and rsm.radioStation.id=? ", radioStation_ls.getId()));
			Map<String, Object> map =findOneForJdbc("select ASTEXT(shape) shape FROM ffp_radio_station where id=?", radioStation_ls.getId());
			if (StringUtil.isNotEmpty(map) && StringUtil.isNotEmpty(map.get("shape"))){
				radioStation_ls.setShape(map.get("shape").toString());
			}

		}
		grid.setRows(lists);
		return grid;

	}

	@Override
	public ReturnBody addRadioStationMediaFile(FFPRadioStationMedia stationMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(stationMedia.getRadioStation()) && !StringUtil.isNotEmpty(get(FFPRadioStation.class, stationMedia.getRadioStation().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [radioStation.id].");
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

				stationMedia.setMediaFile(mediaFile1);

				save(stationMedia);

				body.setObj(stationMedia.getMediaFile());

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
	public ReturnBody deleteRadioStationMediaFile(FFPRadioStationMedia stationMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(stationMedia)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [id].");
			return body;
		}
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPRadioStationMedia stationMedia_db = get(FFPRadioStationMedia.class, oConvertUtils.getString(stationMedia.getId()));
		if (!StringUtil.isNotEmpty(stationMedia_db)){
			stationMedia_db = findUniqueByProperty(FFPRadioStationMedia.class, "mediaFile.id", oConvertUtils.getString(stationMedia.getId()));
		}
		if (StringUtil.isNotEmpty(stationMedia_db)){
			if (StringUtil.isNotEmpty(stationMedia_db.getMediaFile())){
				delete(stationMedia_db.getMediaFile());
				delete(stationMedia_db);
				String fileUrl = projectDirectory + stationMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + stationMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
