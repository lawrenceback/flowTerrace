package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.*;

/**
 * Created by YanWei on 2017/8/14.
 *
 * 热点反馈表
 *
 */
@Entity
@Table(name = "ffp_hot_feedback")
public class FFPHotFeedback extends IdEntity implements java.io.Serializable{

	private Date createTime = new Date();   //发起时间
	private Integer state;                  //未反馈_1,县级_2,市级_3,省级_4

	private String hot_id;              //热点ID
	private Integer initiator;          //发起人ID
	private Integer examineUser;        //审核用户
	private String examineOption;       //县级用户审核处理意义
	private Date examineTime;           //审核时间

	private Double area;
	private Integer fireType;       //1-火灾，2-违规炼山，3-农用火，4-计划烧除，5-其他，6-境外火
	private Integer fireFighting;   //1-没有人,2-少许人,3-很多人
	private String description; //描述信息

	private List<TSMediaFile> mediaFiles = new ArrayList<>();

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getInitiator() {
		return initiator;
	}

	public void setInitiator(Integer initiator) {
		this.initiator = initiator;
	}

	public Integer getExamineUser() {
		return examineUser;
	}

	public void setExamineUser(Integer examineUser) {
		this.examineUser = examineUser;
	}

	public String getExamineOption() {
		return examineOption;
	}

	public void setExamineOption(String examineOption) {
		this.examineOption = examineOption;
	}

	public Date getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(Date examineTime) {
		this.examineTime = examineTime;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Integer getFireType() {
		return fireType;
	}

	public void setFireType(Integer fireType) {
		this.fireType = fireType;
	}

	public Integer getFireFighting() {
		return fireFighting;
	}

	public void setFireFighting(Integer fireFighting) {
		this.fireFighting = fireFighting;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	public List<TSMediaFile> getMediaFiles() {
		return mediaFiles;
	}

	public void setMediaFiles(List<TSMediaFile> mediaFiles) {
		this.mediaFiles = mediaFiles;
	}

	public String getHot_id() {
		return hot_id;
	}

	public void setHot_id(String hot_id) {
		this.hot_id = hot_id;
	}

}
