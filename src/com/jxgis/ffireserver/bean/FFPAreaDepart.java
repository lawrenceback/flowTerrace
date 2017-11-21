package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSDepart;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/9/6.
 *
 * 行政单位
 *
 */
@Entity
@Table(name = "ffp_area_depart")
public class FFPAreaDepart extends IdEntity implements java.io.Serializable {

	private FFPArea area;
	private TSDepart depart;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "area_id")
	public FFPArea getArea() {
		return area;
	}

	public void setArea(FFPArea area) {
		this.area = area;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depart_id")
	public TSDepart getDepart() {
		return depart;
	}

	public void setDepart(TSDepart depart) {
		this.depart = depart;
	}
}
