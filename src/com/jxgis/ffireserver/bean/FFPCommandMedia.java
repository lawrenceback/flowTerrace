package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/1.
 *
 * 森林防火指挥部媒体文件表
 *
 */
@Entity
@Table(name = "ffp_command_media")
public class FFPCommandMedia extends IdEntity implements java.io.Serializable {

	private FFPCommand command;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "command_id")
	public FFPCommand getCommand() {
		return command;
	}

	public void setCommand(FFPCommand command) {
		this.command = command;
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
