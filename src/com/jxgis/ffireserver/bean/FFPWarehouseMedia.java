package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanWei on 2017/8/30.
 *
 * 森林防火物资储备库 媒体文件
 *
 */
@Entity
@Table(name = "ffp_warehouse_media")
public class FFPWarehouseMedia extends IdEntity implements java.io.Serializable{
	private FFPWarehouse warehouse;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "warehouse_id")
	public FFPWarehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(FFPWarehouse warehouse) {
		this.warehouse = warehouse;
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
