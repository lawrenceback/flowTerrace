package com.jxgis.ffireserver.core.util;

import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by YanWei on 2017/6/21.
 *
 * 数据写出，工具类
 *
 * @author 言伟
 *
 */
public class PageWrite {


	public void outputJSON(Object o, HttpServletResponse res) {


		try {
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			PrintWriter pw = res.getWriter();
			ObjectMapper mapper = null;
			JsonMapper ob = new JsonMapper();
			mapper = ob.get("yyyy-MM-dd HH:mm:ss");

			pw.write(mapper.writeValueAsString(o));
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void outputJSON(Object o, HttpServletResponse res, HttpServletRequest req) {

		try {
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			PrintWriter pw = res.getWriter();
			ObjectMapper mapper = null;
			JsonMapper ob = new JsonMapper();
			String datetimeFormat;
			int date_sdf = oConvertUtils.getInt(req.getParameter("date_sdf"));
			switch (date_sdf){
				case 2:
					datetimeFormat = "yyyy-MM-dd";
					break;
				case 3:
					datetimeFormat = "yyyy-MM-dd HH:mm";
					break;
				case 4:
					datetimeFormat = "yyyy年MM月dd日";
					break;
				case 5:
					datetimeFormat = "yyyy年MM月dd日 HH时";
					break;
				case 6:
					datetimeFormat = "yyyy年MM月dd日 HH时mm分";
					break;
				case 7:
					datetimeFormat = "yyyy年MM月dd日 HH时mm分ss秒";
					break;
				case 8:
					datetimeFormat = "yyyyMMdd";
					break;
				case 9:
					datetimeFormat = "yyyyMMddHH";
					break;
				case 10:
					datetimeFormat = "yyyyMMddHHmm";
					break;
				case 11:
					datetimeFormat = "yyyyMMddHHmmss";
					break;
				case 12:
					datetimeFormat = "yyyy/MM/dd";
					break;
				case 13:
					datetimeFormat = "yyyy/MM/dd HH";
					break;
				case 14:
					datetimeFormat = "yyyy/MM/dd HH/mm";
					break;
				case 15:
					datetimeFormat = "yyyy/MM/dd HH/mm/ss";
					break;
				case 16:
					datetimeFormat = "HH:mm";
					break;
				default:
					datetimeFormat = "yyyy-MM-dd HH:mm:ss";
					break;
			}

			if (date_sdf == 1){
				mapper = ob.get();
			} else {
				mapper = ob.get(datetimeFormat);
			}

			pw.write(mapper.writeValueAsString(o));
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
