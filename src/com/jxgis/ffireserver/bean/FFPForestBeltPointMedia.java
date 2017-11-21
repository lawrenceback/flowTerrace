package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/2.
 *
 * 防火林带媒体文件
 *
 */
@Entity
@Table(name = "ffp_forest_beltPoint_media")
public class FFPForestBeltPointMedia extends IdEntity implements java.io.Serializable {

	private FFPForestBeltPoint forestBeltPoint;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "forest_beltPoint_id")
	public FFPForestBeltPoint getForestBeltPoint() {
		return forestBeltPoint;
	}

	public void setForestBeltPoint(FFPForestBeltPoint forestBeltPoint) {
		this.forestBeltPoint = forestBeltPoint;
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
