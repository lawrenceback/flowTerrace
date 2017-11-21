package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/30.
 *
 * 卫星地面站 媒体文件
 */
@Entity
@Table(name = "ffp_satelliteGroundStation_media")
public class FFPSatelliteGroundStationMedia extends IdEntity implements java.io.Serializable {

	private FFPSatelliteGroundStation satelliteGroundStation;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "satelliteGroundStation_id")
	public FFPSatelliteGroundStation getSatelliteGroundStation() {
		return satelliteGroundStation;
	}

	public void setSatelliteGroundStation(FFPSatelliteGroundStation satelliteGroundStation) {
		this.satelliteGroundStation = satelliteGroundStation;
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
