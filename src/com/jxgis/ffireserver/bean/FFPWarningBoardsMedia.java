package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/30.
 *
 * 大型警示牌(大型宣传牌) 媒体文件
 *
 */
@Entity
@Table(name = "ffp_warningBoards_media")
public class FFPWarningBoardsMedia extends IdEntity implements java.io.Serializable {

	private FFPWarningBoards warningBoards;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "warningBoards_id")
	public FFPWarningBoards getWarningBoards() {
		return warningBoards;
	}

	public void setWarningBoards(FFPWarningBoards warningBoards) {
		this.warningBoards = warningBoards;
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
