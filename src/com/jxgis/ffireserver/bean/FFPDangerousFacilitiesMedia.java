package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/4.
 *
 * 林区危险及重要性
 *
 */
@Entity
@Table(name = "ffp_dangerous_facilities_media")
public class FFPDangerousFacilitiesMedia extends IdEntity implements java.io.Serializable{

	private FFPDangerousFacilities facilities;
	private TSMediaFile mediaFile;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dangerous_facilities_id")
	public FFPDangerousFacilities getFacilities() {
		return facilities;
	}

	public void setFacilities(FFPDangerousFacilities facilities) {
		this.facilities = facilities;
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
