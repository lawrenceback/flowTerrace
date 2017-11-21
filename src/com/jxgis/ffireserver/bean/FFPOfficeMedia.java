package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/28.
 *
 * 森林防火办公室 媒体文件
 */
@Entity
@Table(name = "ffp_office_media")
public class FFPOfficeMedia extends IdEntity implements java.io.Serializable{

	private FFPOffice office;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "office_id")
	public FFPOffice getOffice() {
		return office;
	}

	public void setOffice(FFPOffice office) {
		this.office = office;
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
