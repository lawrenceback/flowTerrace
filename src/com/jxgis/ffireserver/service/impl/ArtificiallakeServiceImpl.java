package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPArtificiallake;
import com.jxgis.ffireserver.bean.FFPArtificiallakeMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.ArtificiallakeService;
import com.jxgis.ffireserver.util.FormatPac;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.TSMediaFile;
import com.jxgis.ffireserver.web.service.impl.SystemServiceImpl;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
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
 * Created by YanWei on 2017/8/3.
 *
 */
@Service("artificiallakeService")
@Transactional
public class ArtificiallakeServiceImpl extends SystemServiceImpl implements ArtificiallakeService {

	@Override

	public ReturnBody addArtificiallake(FFPArtificiallake artificiallake, Map<String, MultipartFile> mediaFiles) {
		ReturnBody body = new ReturnBody();

		if (StringUtil.isEmpty(artificiallake.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [pac] must not be null.");
			return body;
		}
		if (StringUtil.isEmpty(artificiallake.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg( "Parameter [name] must not be null.");
			return body;
		}


		if (!StringUtil.isEmpty(artificiallake.getShape())){
			try{
				WKTReader fromText = new WKTReader();
				fromText.read(artificiallake.getShape());
			} catch (ParseException e) {
				body.setStatus(Glossary.Return_State_Geometry_Format_Error);
				body.setMsg( "Parameter [shape] must not be null.");
				return body;
			}
		}

		save(artificiallake);


		List<FFPArtificiallakeMedia> artificiallakeMedias = new ArrayList<>();
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

					FFPArtificiallakeMedia artificiallakeMedia = new FFPArtificiallakeMedia();
					artificiallakeMedia.setArtificiallake(artificiallake);
					artificiallakeMedia.setMediaFile(mediaFile_save);
					artificiallakeMedias.add(artificiallakeMedia);


				} catch (Exception e){
					e.printStackTrace();
				}
			}

		}


		try{
			batchSave(artificiallakeMedias);
			body.setObj(artificiallake.getId());
			return body;
		} catch (Exception e){
			delete(artificiallake);

			for (TSMediaFile mediaFile_save: mediaFile_saves){
				FileUtils.delete(mediaFile_save.getFileUrl());
				if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
					FileUtils.delete(mediaFile_save.getFileThumbUrl());
				}
			}
			deleteAllEntitie(mediaFile_saves);
			deleteAllEntitie(artificiallakeMedias);
		}
		body.setStatus(Glossary.Return_State_Exception_Operation);
		body.setMsg("operation failed.");
		return body;
	}

	@Override
	public ReturnBody deleteArtificiallake(FFPArtificiallake artificiallake) throws Exception {
		ReturnBody body = new ReturnBody();
		artificiallake = get(FFPArtificiallake.class, oConvertUtils.getString(artificiallake.getId()));
		if (StringUtil.isNotEmpty(artificiallake)){
			List<FFPArtificiallakeMedia> artificiallakeMedias = findByProperty(FFPArtificiallakeMedia.class, "artificiallake.id", oConvertUtils.getString(artificiallake.getId()));
			if (StringUtil.isNotEmpty(artificiallakeMedias) && artificiallakeMedias.size() > 0){
				for (FFPArtificiallakeMedia artificiallakeMedia: artificiallakeMedias){
					deleteArtificiallakeMedia(artificiallakeMedia);
				}
			}
			delete(artificiallake);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter must not be null [id].");
		return body;
	}
	@Override
	public ReturnBody deleteArtificiallakes(List<FFPArtificiallake> artificiallakes) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(artificiallakes)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		for (FFPArtificiallake artificiallake: artificiallakes){
			deleteArtificiallake(artificiallake);
		}
		return body;
	}

	@Override
	public ReturnBody editArtificiallake(FFPArtificiallake artificiallake) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPArtificiallake artificiallake_db = get(FFPArtificiallake.class, oConvertUtils.getString(artificiallake.getId()));
		if (StringUtil.isNotEmpty(artificiallake_db)){

			MyBeanUtils.copyBeanNotNull2Bean(artificiallake, artificiallake_db);

			if (!StringUtil.isEmpty(artificiallake.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(artificiallake.getShape());
					updateEntitie(artificiallake_db);
					executeSql("UPDATE ffp_artificiallake SET shape=POINTFROMTEXT('"+artificiallake.getShape()+"') WHERE id=?", artificiallake.getId());
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


			updateEntitie(artificiallake_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getArtificiallakeList(FFPArtificiallake artificiallake, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();
		Criteria criteria = this.getSession().createCriteria(FFPArtificiallake.class);

		if (!StringUtil.isEmpty(artificiallake.getPac())){

			switch (fetchType){
				case 1:
					//只加载下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(artificiallake.getPac(),true) ) );
					break;
				case 2:
					//加载所有下一级，甚用
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(artificiallake.getPac()) + "%" ) );
					break;
				case 3:
					//加载所有下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(artificiallake.getPac()) ) );
					break;
				default:
					//默认： .eq()
					criteria.add( Restrictions.eq("pac", artificiallake.getPac() ) );
					break;
			}

		}

		if (!StringUtil.isEmpty(artificiallake.getName())){
			criteria.add( Restrictions.like("name",  "%" + artificiallake.getName() + "%" ) );
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
		List<FFPArtificiallake> lists = criteria.list();
		for (FFPArtificiallake artificiallake1: lists){
			artificiallake1.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPArtificiallakeMedia am where mf.id=am.mediaFile.id and am.artificiallake.id=? ", artificiallake1.getId()));
			Object shape = findOneForJdbc("select ASTEXT(shape) shape FROM ffp_artificiallake where id=?", artificiallake1.getId()).get("shape");
			if (StringUtil.isNotEmpty(shape)){
				artificiallake1.setShape(shape.toString());
			}
		}
		grid.setRows(lists);
		return grid;
	}

	@Override
	public ReturnBody addArtificiallakeMedia(FFPArtificiallakeMedia artificiallakeMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(artificiallakeMedia.getArtificiallake()) && !StringUtil.isNotEmpty(get(FFPArtificiallake.class, artificiallakeMedia.getArtificiallake().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [artificiallake.id].");
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

				artificiallakeMedia.setMediaFile(mediaFile1);

				save(artificiallakeMedia);

				body.setObj(artificiallakeMedia.getMediaFile());

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
	public ReturnBody deleteArtificiallakeMedia(FFPArtificiallakeMedia artificiallakeMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPArtificiallakeMedia artificiallakeMedia_db = get(FFPArtificiallakeMedia.class, artificiallakeMedia.getId());
		if (!StringUtil.isNotEmpty(artificiallakeMedia_db)){
			artificiallakeMedia_db = findUniqueByProperty(FFPArtificiallakeMedia.class, "mediaFile.id", artificiallakeMedia.getId());
		}
		if (StringUtil.isNotEmpty(artificiallakeMedia_db)){
			if (StringUtil.isNotEmpty(artificiallakeMedia_db.getMediaFile())){
				delete(artificiallakeMedia_db.getMediaFile());
				delete(artificiallakeMedia_db);
				String fileUrl = projectDirectory + artificiallakeMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + artificiallakeMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	private void insterInToGeometry(FFPArtificiallake artificiallake) throws Exception{
		int rows = executeSql("UPDATE ffp_artificiallake SET shape=POINTFROMTEXT('POINT(50 60)') WHERE id=");
		WKTReader fromText = new WKTReader();
		//Geometry shape = fromText.read(artificiallake.getShape());
	}

	private Geometry wktToGeometry(String wktPoint) {
		WKTReader fromText = new WKTReader();
		Geometry geom = null;
		try {
			geom = fromText.read(wktPoint);
		} catch (ParseException e) {
			throw new RuntimeException("Not a WKT string:" + wktPoint);
		}
		return geom;
	}

	public Point createPoint(String lon, String lat){
		String wktPoint = "POINT(10 5)";
		WKTReader fromText = new WKTReader();
		Geometry geom = null;
		try {
			geom = fromText.read(wktPoint);
		} catch (ParseException e) {
			throw new RuntimeException("Not a WKT string:" + wktPoint);
		}
		if (!geom.getGeometryType().equals("Point")) {
			throw new RuntimeException("GeometryPro must be a point. Got a " + geom.getGeometryType());
		}
		return (Point)geom;
	}
}
