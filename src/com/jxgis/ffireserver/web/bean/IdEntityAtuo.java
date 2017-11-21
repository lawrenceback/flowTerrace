package com.jxgis.ffireserver.web.bean;

import javax.persistence.*;

/**
 * Created by 言伟 on 2017/6/8.
 * 自增-表主键
 */
@MappedSuperclass
public abstract class IdEntityAtuo {

	private Integer id;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
