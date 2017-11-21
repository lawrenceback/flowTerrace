package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/17.
 *
 * 火灾档案媒体文件
 *
 */
@Entity
@Table(name = "ffp_fire_document_media")
public class FFPFireDocumentMedia extends IdEntity implements java.io.Serializable {

	private FFPFireDocument fireDocument;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fire_document_id")
	public FFPFireDocument getFireDocument() {
		return fireDocument;
	}

	public void setFireDocument(FFPFireDocument fireDocument) {
		this.fireDocument = fireDocument;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "media_file_id")
	public TSMediaFile getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(TSMediaFile mediaFile) {
		this.mediaFile = mediaFile;
	}
}
