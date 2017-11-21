package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPWarningBoards;
import com.jxgis.ffireserver.bean.FFPWarningBoardsMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.WarningBoardsService;
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
 * 大型警示牌(大型宣传牌) 业务层 实现类
 *
 */
@Service("warningBoardsService")
@Transactional
public class WarningBoardsServiceImpl extends SystemServiceImpl implements WarningBoardsService {
	@Override
	public ReturnBody addBoards(FFPWarningBoards boards, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isEmpty(boards.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [pac],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(boards.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [name],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(boards.getManager())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [manager],must not be empty.");
			return body;
		}
		if (StringUtil.isEmpty(boards.getPhone())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [phone],must not be empty.");
			return body;
		}
		if (!StringUtil.isNotEmpty(boards.getLatitude())) {
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [latitude],must not be empty.");
			return body;
		}
		if (!StringUtil.isNotEmpty(boards.getLongitude())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [longitude],must not be empty.");
			return body;
		}
		if (!StringUtil.isEmpty(boards.getShape())){
			try{
				WKTReader fromText = new WKTReader();
				fromText.read(boards.getShape());
			} catch (ParseException e) {
				body.setStatus(Glossary.Return_State_Geometry_Format_Error);
				body.setMsg( "Parameter [shape] error.");
				return body;
			}
		}

		save(boards);

		List<FFPWarningBoardsMedia> boardsMedias = new ArrayList<>();
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

					FFPWarningBoardsMedia boardsMedia = new FFPWarningBoardsMedia();
					boardsMedia.setWarningBoards(boards);
					boardsMedia.setMediaFile(mediaFile_save);
					boardsMedias.add(boardsMedia);


				} catch (Exception e){
					e.printStackTrace();
				}
			}

		}

		try{
			batchSave(boardsMedias);
			body.setObj(boards.getId());
			return body;
		} catch (Exception e){
			delete(boards);

			for (TSMediaFile mediaFile_save: mediaFile_saves){
				FileUtils.delete(mediaFile_save.getFileUrl());
				if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
					FileUtils.delete(mediaFile_save.getFileThumbUrl());
				}
			}
			deleteAllEntitie(mediaFile_saves);
			deleteAllEntitie(boardsMedias);
		}
		body.setStatus(Glossary.Return_State_Exception_Operation);
		body.setMsg("operation failed.");
		return body;
	}

	@Override
	public ReturnBody deleteBoards(FFPWarningBoards boards) throws Exception {
		ReturnBody body = new ReturnBody();
		boards = get(FFPWarningBoards.class, boards.getId());
		if (StringUtil.isNotEmpty(boards)){
			List<FFPWarningBoardsMedia> boardsMedias = findByProperty(FFPWarningBoardsMedia.class, "warningBoards.id", oConvertUtils.getString(boards.getId()));
			if (StringUtil.isNotEmpty(boardsMedias) && boardsMedias.size() > 0){
				for (FFPWarningBoardsMedia boardsMedia: boardsMedias){
					deleteBoardsMedia(boardsMedia);
				}
			}
			delete(boards);
			return body;
		}


		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Parameter error [id].");
		return body;
	}

	@Override
	public ReturnBody editBoards(FFPWarningBoards boards) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPWarningBoards boards_db = get(FFPWarningBoards.class, boards.getId());
		if (StringUtil.isNotEmpty(boards_db)){
			MyBeanUtils.copyBeanNotNull2Bean(boards, boards_db);
			if (!StringUtil.isEmpty(boards.getShape())){
				try{
					WKTReader fromText = new WKTReader();
					fromText.read(boards.getShape());
					updateEntitie(boards_db);
					executeSql("UPDATE ffp_warningBoards SET shape=POINTFROMTEXT('"+boards.getShape()+"') WHERE id=?", boards_db.getId());
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
			updateEntitie(boards_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Parameter error [id].");
		return body;
	}

	@Override
	public ReturnDataGrid getBoardsList(FFPWarningBoards boards, DataGrid dataGrid, int fetchTypt) throws Exception {
		ReturnDataGrid body = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(FFPWarningBoards.class);


		if (!StringUtil.isEmpty(boards.getPac())){

			switch (fetchTypt){
				case 1:
					//只加载下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(boards.getPac(),true) ) );
					break;
				case 2:
					//加载所有下一级，甚用
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(boards.getPac()) + "%" ) );
					break;
				case 3:
					//加载所有下一级，最大到县
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(boards.getPac()) ) );
					break;
				default:
					//默认： .eq()
					criteria.add( Restrictions.eq("pac", boards.getPac() ) );
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

		if (!StringUtil.isEmpty(boards.getName())){
			criteria.add( Restrictions.eq("name", "%" + boards.getName() + "%" ) );
		}

		body.setTotal(criteria.list().size());
		criteria.setFirstResult((dataGrid.getPage() - 1) * dataGrid.getRows());
		criteria.setMaxResults(dataGrid.getRows());
		List<FFPWarningBoards> lists = criteria.list();
		for (FFPWarningBoards boards1: lists){
			boards1.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPWarningBoardsMedia wbm where mf.id=wbm.mediaFile.id and wbm.warningBoards.id=? ", boards1.getId()));
			Map<String, Object> map =findOneForJdbc("select ASTEXT(shape) shape FROM ffp_warningBoards where id=?", boards1.getId());
			if (StringUtil.isNotEmpty(map) && StringUtil.isNotEmpty(map.get("shape"))){
				boards1.setShape(map.get("shape").toString());
			}

		}
		body.setRows(lists);
		return body;
	}

	@Override
	public ReturnBody addBoardsMedia(FFPWarningBoardsMedia boardsMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(boardsMedia.getWarningBoards()) && !StringUtil.isNotEmpty(get(FFPWarningBoards.class, boardsMedia.getWarningBoards().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [warningBoards.id].");
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

				boardsMedia.setMediaFile(mediaFile1);

				save(boardsMedia);

				body.setObj(boardsMedia.getMediaFile());
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
	public ReturnBody deleteBoardsMedia(FFPWarningBoardsMedia boardsMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(boardsMedia)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [id].");
			return body;
		}
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPWarningBoardsMedia boardsMedia_db = get(FFPWarningBoardsMedia.class, oConvertUtils.getString(boardsMedia.getId()));
		if (!StringUtil.isNotEmpty(boardsMedia_db)){
			boardsMedia_db = findUniqueByProperty(FFPWarningBoardsMedia.class, "mediaFile.id", oConvertUtils.getString(boardsMedia.getId()));
		}
		if (StringUtil.isNotEmpty(boardsMedia_db)){
			if (StringUtil.isNotEmpty(boardsMedia_db.getMediaFile())){
				delete(boardsMedia_db.getMediaFile());
				delete(boardsMedia_db);
				String fileUrl = projectDirectory + boardsMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + boardsMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
