package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPFireHot;
import com.jxgis.ffireserver.bean.FFPHotFeedback;
import com.jxgis.ffireserver.bean.FFPHotFeedbackMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.HotFeedbackService;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.TSMediaFile;
import com.jxgis.ffireserver.web.bean.TSUser;
import com.jxgis.ffireserver.web.service.impl.SystemServiceImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
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
 * Created by YanWei on 2017/8/14.
 *
 */
@Service("hotFeedbackService")
@Transactional
public class HotFeedbackServiceImpl extends SystemServiceImpl implements HotFeedbackService {
	@Override
	public ReturnBody createHotFeedback(FFPHotFeedback feedback, Map<String, MultipartFile> mediaFiles) throws Exception {
		ReturnBody body = new ReturnBody();

		FFPFireHot hot = get(FFPFireHot.class, oConvertUtils.getString(feedback.getHot_id()));
		if (!StringUtil.isNotEmpty(hot)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [hot_id]");
			return body;
		}

		FFPHotFeedback feedback_db = findUniqueByProperty(FFPHotFeedback.class, "hot_id", feedback.getHot_id());
		if (StringUtil.isNotEmpty(feedback_db)){
			body.setStatus(Glossary.Return_State_Data_Exist);
			body.setMsg("parameter error [hot_id],Repetitive operation.");
			return body;
		}

		if (!StringUtil.isNotEmpty(feedback.getInitiator())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [initiator].");
			return body;
		}


		if (StringUtil.isNotEmpty(feedback)){
			TSUser user_hly_id = get(TSUser.class, feedback.getInitiator());
			if (StringUtil.isNotEmpty(user_hly_id)){

				feedback.setState(1);
				save(feedback);


				List<FFPHotFeedbackMedia> feedbackMedias = new ArrayList<>();
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

							FFPHotFeedbackMedia feedbackMedia = new FFPHotFeedbackMedia();
							feedbackMedia.setHotFeedback(feedback);
							feedbackMedia.setMediaFile(mediaFile_save);
							feedbackMedias.add(feedbackMedia);


						} catch (Exception e){
							e.printStackTrace();
						}
					}

				}


				try{
					hot.setStatus(1);
					updateEntitie(hot);
					batchSave(feedbackMedias);
					body.setObj(feedback.getId());
					return body;
				} catch (Exception e){
					delete(feedback);

					for (TSMediaFile mediaFile_save: mediaFile_saves){
						FileUtils.delete(mediaFile_save.getFileUrl());
						if (StringUtil.isEmpty(mediaFile_save.getFileThumbUrl())){
							FileUtils.delete(mediaFile_save.getFileThumbUrl());
						}
					}
					deleteAllEntitie(mediaFile_saves);
					deleteAllEntitie(feedbackMedias);
				}
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.");
				return body;

			}

		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [initiator]");
		return body;
	}

	@Override
	public ReturnBody editHotFeedback(FFPHotFeedback feedback) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPHotFeedback feedback_db = get(FFPHotFeedback.class, oConvertUtils.getString(feedback.getId()));
		if (StringUtil.isNotEmpty(feedback_db)){

			MyBeanUtils.copyBeanNotNull2Bean(feedback, feedback_db);

			updateEntitie(feedback_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}

	@Override
	public ReturnBody deleteHotFeedback(FFPHotFeedback feedback) throws Exception {
		ReturnBody body = new ReturnBody();
		feedback = get(FFPHotFeedback.class, oConvertUtils.getString(feedback.getId()));
		if (StringUtil.isNotEmpty(feedback)){
			List<FFPHotFeedbackMedia> feedbackMedias = findByProperty(FFPHotFeedbackMedia.class, "hotFeedback.id", oConvertUtils.getString(feedback.getId()));
			if (StringUtil.isNotEmpty(feedbackMedias) && feedbackMedias.size() > 0){
				for (FFPHotFeedbackMedia feedbackMedia: feedbackMedias){
					deleteHotFeedbackMedia(feedbackMedia);
				}
			}
			delete(feedback);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("Parameter must not be null [id].");
		return body;
	}

	@Override
	public ReturnBody examineHotFeedback(FFPHotFeedback feedback) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(feedback.getState())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [state]");
			return body;
		}
		switch (feedback.getState()){
			case 2:case 3:case 4:case 5:case 6:
				FFPHotFeedback feedback_db = get(FFPHotFeedback.class, oConvertUtils.getString(feedback.getId()));
				if (StringUtil.isNotEmpty(feedback_db)){
					if (StringUtil.isNotEmpty(feedback.getExamineUser())){
						TSUser user = get(TSUser.class, feedback.getExamineUser());
						if (StringUtil.isNotEmpty(user)){
							user = get(TSUser.class, user.getId());
							if (StringUtil.isNotEmpty(user)){
								feedback_db.setExamineUser(user.getId());
								feedback_db.setExamineTime(new Date());
								if (!StringUtil.isEmpty(feedback.getExamineOption())){
									feedback_db.setExamineOption(feedback.getExamineOption());
								}
								feedback_db.setState(feedback.getState());
								updateEntitie(feedback_db);
								return body;
							}
						}
					}

					body.setStatus(Glossary.Return_State_Parameter_Error);
					body.setMsg("parameter error [examineUser]");
					return body;
				}
				body.setStatus(Glossary.Return_State_Parameter_Error);
				body.setMsg("parameter error [id]");
				return body;
			default:
				body.setStatus(Glossary.Return_State_Parameter_Error);
				body.setMsg("parameter error [state]");
				return body;
		}

	}

	@Override
	public ReturnDataGrid getHotFeedbackList(FFPHotFeedback feedback, DataGrid dataGrid) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(FFPHotFeedback.class);

		if (StringUtil.isNotEmpty(feedback)){


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
		List<FFPHotFeedback> lists = criteria.list();
		for (FFPHotFeedback hotFeedback_ls: lists){
			hotFeedback_ls.setMediaFiles(this.<TSMediaFile>findHql("select distinct mf from TSMediaFile mf,FFPHotFeedbackMedia hfm where mf.id=hfm.mediaFile.id and hfm.ffpHotFeedback.id=? ", hotFeedback_ls.getId()));

		}
		grid.setRows(lists);
		return grid;
	}

	@Override
	public ReturnBody addHotFeedbackMedia(FFPHotFeedbackMedia feedbackMedia, MultipartFile mediaFile) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(feedbackMedia.getHotFeedback()) && !StringUtil.isNotEmpty(get(FFPHotFeedback.class, feedbackMedia.getHotFeedback().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [hotFeedback.id].");
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

				feedbackMedia.setMediaFile(mediaFile1);

				save(feedbackMedia);

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
	public ReturnBody deleteHotFeedbackMedia(FFPHotFeedbackMedia feedbackMedia) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(feedbackMedia)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [id].");
			return body;
		}
		// 得到工程目录 D:\Users\Administrator\workspace\driving\WebRoot\
		String projectDirectory = ResourceUtil.getSysPath();
		FFPHotFeedbackMedia feedbackMedia_db = get(FFPHotFeedbackMedia.class, feedbackMedia.getId());
		if (!StringUtil.isNotEmpty(feedbackMedia_db)){
			feedbackMedia_db = findUniqueByProperty(FFPHotFeedbackMedia.class, "mediaFile.id", oConvertUtils.getString(feedbackMedia.getId()));
		}
		if (StringUtil.isNotEmpty(feedbackMedia_db)){
			if (StringUtil.isNotEmpty(feedbackMedia_db.getMediaFile())){
				delete(feedbackMedia_db.getMediaFile());
				delete(feedbackMedia_db);
				String fileUrl = projectDirectory + feedbackMedia_db.getMediaFile().getFileUrl();
				FileUtils.delete(fileUrl);
				String fileThumbUrl = projectDirectory + feedbackMedia_db.getMediaFile().getFileThumbUrl();
				FileUtils.delete(fileThumbUrl);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id].");
		return body;
	}
}
