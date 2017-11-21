package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPFireDocument;
import com.jxgis.ffireserver.bean.FFPFireDocumentMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.FireDocumentService;
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
 * Created by YanWei on 2017/8/17.
 *
 */
@Service("fireDocumentService")
@Transactional
public class FireDocumentServiceImpl extends SystemServiceImpl implements FireDocumentService {

	@Override
	public ReturnBody addFireDocument(FFPFireDocument fireDocument, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(fireDocument)){

			if (StringUtil.isEmpty(fireDocument.getPac())){
				body.setStatus(Glossary.Return_State_Parameter_Error);
				body.setMsg("parameter error [pac].");
				return body;
			}
			if (StringUtil.isEmpty(fireDocument.getNo())){
				body.setStatus(Glossary.Return_State_Parameter_Error);
				body.setMsg("parameter error [no].");
				return body;
			}
			if (StringUtil.isEmpty(fireDocument.getName())){
				body.setStatus(Glossary.Return_State_Parameter_Error);
				body.setMsg("parameter error [name].");
				return body;
			}

			if (!StringUtil.isEmpty(fireDocument.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(fireDocument.getShape());
				} catch (ParseException e) {
					body.setStatus(Glossary.Return_State_Geometry_Format_Error);
					body.setMsg( "Parameter error [shape].");
					return body;
				}
			}
			save(fireDocument);

			List<FFPFireDocumentMedia> fireDocumentMedias = new ArrayList<>();
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

						FFPFireDocumentMedia fireDocumentMedia = new FFPFireDocumentMedia();
						fireDocumentMedia.setFireDocument(fireDocument);
						fireDocumentMedia.setMediaFile(mediaFile_save);
						fireDocumentMedias.add(fireDocumentMedia);


					} catch (Exception e){
						e.printStackTrace();
					}
				}

			}


			try{
				batchSave(fireDocumentMedias);
				body.setObj(fireDocument.getId());
				return body;
			} catch (Exception e){
				delete(fireDocument);

				for (TSMediaFile mediaFile_save: mediaFile_saves){
					FileUtils.delete(mediaFile_save.getFileUrl());
					if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
						FileUtils.delete(mediaFile_save.getFileThumbUrl());
					}
				}
				deleteAllEntitie(mediaFile_saves);
				deleteAllEntitie(fireDocumentMedias);
			}
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			return body;

		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error.");
		return body;
	}

	@Override
	public ReturnBody deleteFireDocument(FFPFireDocument fireDocument) throws Exception {
		ReturnBody body = new ReturnBody();
		fireDocument = get(FFPFireDocument.class, oConvertUtils.getString(fireDocument.getId()));
		if (StringUtil.isNotEmpty(fireDocument)){
			List<FFPFireDocumentMedia> documentMedias = findByProperty(FFPFireDocumentMedia.class, "fireDocument.id", oConvertUtils.getString(fireDocument.getId()));
			if (StringUtil.isNotEmpty(documentMedias) && documentMedias.size() > 0){
				for (FFPFireDocumentMedia documentMedia: documentMedias){
					deleteFireDocumentMedia(documentMedia);
				}
			}
			delete(fireDocument);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter must not be null [id].");
		return body;
	}

	@Override
	public ReturnBody editFireDocument(FFPFireDocument fireDocument) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPFireDocument fireDocument_db = get(FFPFireDocument.class, oConvertUtils.getString(fireDocument.getId()));
		if (StringUtil.isNotEmpty(fireDocument_db)){

			MyBeanUtils.copyBeanNotNull2Bean(fireDocument, fireDocument_db);

			if (!StringUtil.isEmpty(fireDocument.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(fireDocument.getShape());
					updateEntitie(fireDocument_db);
					executeSql("UPDATE ffp_dangerous_facilities SET shape=POINTFROMTEXT('"+fireDocument.getShape()+"') WHERE id=?", fireDocument_db.getId());
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


			updateEntitie(fireDocument_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getFireDocumentList(FFPFireDocument fireDocument, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();
		Criteria criteria = this.getSession().createCriteria(FFPFireDocument.class);


		if (!StringUtil.isEmpty(fireDocument.getPac())){

			switch (fetchType){
				case 1:
					//只加载下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(fireDocument.getPac(),true) ) );
					break;
				case 2:
					//加载所有下一级，甚用
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(fireDocument.getPac()) + "%" ) );
					break;
				case 3:
					//加载所有下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(fireDocument.getPac()) ) );
					break;
				default:
					//默认： .eq()
					criteria.add( Restrictions.eq("pac", fireDocument.getPac() ) );
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

		if (!StringUtil.isEmpty(fireDocument.getName())){
			criteria.add( Restrictions.like("name", "%" + fireDocument.getPac() + "%" ) );
		}

		grid.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPFireDocument> lists = criteria.list();
		for (FFPFireDocument document: lists){
			document.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPFireDocumentMedia fdm where mf.id=fdm.mediaFile.id and fdm.fireDocument.id=? ", document.getId()));
			Object shape = findOneForJdbc("select ASTEXT(shape) shape FROM ffp_fire_document where id=?", document.getId()).get("shape");
			if (StringUtil.isNotEmpty(shape)){
				document.setShape(shape.toString());
			}
		}
		grid.setRows(lists);
		return grid;
	}

	@Override
	public ReturnBody addFireDocumentMedia(FFPFireDocumentMedia fireDocumentMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(fireDocumentMedia.getFireDocument()) && !StringUtil.isNotEmpty(get(FFPFireDocument.class, fireDocumentMedia.getFireDocument().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [fireDocument.id].");
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

				fireDocumentMedia.setMediaFile(mediaFile1);

				save(fireDocumentMedia);

				body.setObj(fireDocumentMedia.getMediaFile());

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
	public ReturnBody deleteFireDocumentMedia(FFPFireDocumentMedia fireDocumentMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPFireDocumentMedia fireDocumentMedia_db = get(FFPFireDocumentMedia.class, oConvertUtils.getString(fireDocumentMedia.getId()));
		if (!StringUtil.isNotEmpty(fireDocumentMedia_db)){
			fireDocumentMedia_db = findUniqueByProperty(FFPFireDocumentMedia.class, "mediaFile.id", oConvertUtils.getString(fireDocumentMedia.getId()));
		}
		if (StringUtil.isNotEmpty(fireDocumentMedia_db)){
			if (StringUtil.isNotEmpty(fireDocumentMedia_db.getMediaFile())){
				delete(fireDocumentMedia_db.getMediaFile());
				delete(fireDocumentMedia_db);
				String fileUrl = projectDirectory + fireDocumentMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + fireDocumentMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
