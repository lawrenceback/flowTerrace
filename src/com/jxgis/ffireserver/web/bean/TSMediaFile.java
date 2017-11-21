package com.jxgis.ffireserver.web.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by YanWei on 2017/7/27.
 *
 * 媒体文件表
 *
 */
@Entity
@Table(name = "t_s_media_file")
public class TSMediaFile extends IdEntity implements java.io.Serializable {

	private String fileName;     //文件名称
	private int fileType;        //文件类型；1_图片,2_视频,音频_3
	private String fileSuffix;
	private long length;        //文件长度; 单位kb
	private String fileUrl;     //文件地址
	private String fileThumbUrl; //文件缩略图地址；仅 fileType[1,2] 有效

	@Column(name = "file_name", nullable = false)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "file_type", nullable = false, columnDefinition = "int default 0")
	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	@Column(name = "file_suffix", nullable = false)
	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	@Column(nullable = false, columnDefinition = "bigint default 0")
	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	@Column(name = "file_url", columnDefinition = "LongText", nullable = false)
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Column(name = "file_thumb_url", columnDefinition = "LongText")
	public String getFileThumbUrl() {
		return fileThumbUrl;
	}

	public void setFileThumbUrl(String fileThumbUrl) {
		this.fileThumbUrl = fileThumbUrl;
	}
}
