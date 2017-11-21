package com.jxgis.ffireserver.listener;

import org.springframework.stereotype.Component;

/**
 * Created by YanWei on 2017/7/24.
 *
 * Spring 定时器
 *
 */
@Component
public class Timer
{

	public void setUserOffline() {
		//需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。

		long millis = System.currentTimeMillis();
		// 大于当前时间90秒的用户设为离线

	}

}
