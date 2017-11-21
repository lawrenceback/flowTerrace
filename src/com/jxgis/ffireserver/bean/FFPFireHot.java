package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.*;

/**
 * Created by YanWei on 2017/6/26.
 *
 * 热点表
 *
 */
@Entity
@Table(name = "ffp_fire_hot")
public class FFPFireHot extends IdEntity implements java.io.Serializable {

	private String pac;         //地区代码
	private String no;          //编号
	private String satellite;   //接收卫星
	private Double longitude;   //径度
	private Double latitude;    //纬度
	private Integer pixels;     //像素数
	private Integer smoke;      //有烟否 有_0,没有_1
	private Integer continuous; //是否连续林火事件： 是_0,不是_1
	private String landtype;    //地类
	private Date receiptt;      //接收时间
	private Integer type;       //火灾类型：火灾_5,炼山_1,农用火_2,境外火_3,其他_4(默认)
	private String reporter;    //报告人
	private Date reporttime;    //报告时间
	private String opinion;     //处理意见
	private String duty;        //值班人
	private String note;        //备注
	private String source;
	private String shape;       //几何
	private String county;
	private Integer status;         //状态;1_已反馈

	private List<TSMediaFile> mediaFiles = new ArrayList<>();
	private Map<String, Object> hotFeedback = new HashMap<>();



	private Date createTime = new Date();



	@Column(length = 10, nullable=false)
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Column(length = 20)
	public String getSatellite() {
		return satellite;
	}

	public void setSatellite(String satellite) {
		this.satellite = satellite;
	}

	@Column(length = 6)
	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	@Column(name = "landtype",length = 20)
	public String getLandtype() {
		return landtype;
	}

	public void setLandtype(String landType) {
		this.landtype = landType;
	}

	@Column(length = 50)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(length = 8)
	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	@Column(length = 50)
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Column(length = 8)
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(nullable=false)
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(nullable = false)
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(nullable = false)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(nullable = false)
	public Integer getPixels() {
		return pixels;
	}

	public void setPixels(Integer pixels) {
		this.pixels = pixels;
	}

	@Column(nullable = false)
	public Integer getSmoke() {
		return smoke;
	}

	public void setSmoke(Integer smoke) {
		this.smoke = smoke;
	}

	@Column(nullable = false)
	public Integer getContinuous() {
		return continuous;
	}

	public void setContinuous(Integer continuous) {
		this.continuous = continuous;
	}

	public Date getReceiptt() {
		return receiptt;
	}

	public void setReceiptt(Date receiptt) {
		this.receiptt = receiptt;
	}

	public Date getReporttime() {
		return reporttime;
	}

	public void setReporttime(Date reporttime) {
		this.reporttime = reporttime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Transient
	public List<TSMediaFile> getMediaFiles() {
		return mediaFiles;
	}

	public void setMediaFiles(List<TSMediaFile> mediaFiles) {
		this.mediaFiles = mediaFiles;
	}

	@Transient
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Transient
	public Map<String, Object> getHotFeedback() {
		return hotFeedback;
	}

	public void setHotFeedback(Map<String, Object> hotFeedback) {
		this.hotFeedback = hotFeedback;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
