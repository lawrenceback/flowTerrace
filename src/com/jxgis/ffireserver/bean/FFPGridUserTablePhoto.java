package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanWei on 2017/9/14.
 *
 * 网格人员管理照片表
 *
 */
@Entity
@Table(name = "ffp_gridUserTable_photo")
public class FFPGridUserTablePhoto extends IdEntity implements java.io.Serializable {

	private FFPGridUserTable gridUserTable;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gridUserTable_id")
	public FFPGridUserTable getGridUserTable() {
		return gridUserTable;
	}

	public void setGridUserTable(FFPGridUserTable gridUserTable) {
		this.gridUserTable = gridUserTable;
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
