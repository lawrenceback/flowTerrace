package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/14.
 *
 */
@Entity
@Table(name = "ffp_hou_feedback_media")
public class FFPHotFeedbackMedia extends IdEntity implements java.io.Serializable{
	private FFPHotFeedback hotFeedback;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hou_feedback_id")
	public FFPHotFeedback getHotFeedback() {
		return hotFeedback;
	}

	public void setHotFeedback(FFPHotFeedback hotFeedback) {
		this.hotFeedback = hotFeedback;
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
