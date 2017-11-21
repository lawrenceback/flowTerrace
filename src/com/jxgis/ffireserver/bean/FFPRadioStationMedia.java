package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/7/31.
 *
 * 无线电台媒体文件表
 *
 */
@Entity
@Table(name = "ffp_radio_station_media")
public class FFPRadioStationMedia extends IdEntity implements java.io.Serializable {

	private FFPRadioStation radioStation;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "radio_station_id")
	public FFPRadioStation getRadioStation() {
		return radioStation;
	}

	public void setRadioStation(FFPRadioStation radioStation) {
		this.radioStation = radioStation;
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
