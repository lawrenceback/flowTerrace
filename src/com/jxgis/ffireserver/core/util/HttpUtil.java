package com.jxgis.ffireserver.core.util;

import com.jxgis.ffireserver.util.Glossary;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by YanWei on 2017/6/13.
 *
 */
public class HttpUtil {


	static String testPost(String urlStr, String xmlInfo) {
		try {
			StringBuffer sb = new StringBuffer();
			URL url = new URL(urlStr);
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Pragma:", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "text/xml");

			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			out.write(new String(xmlInfo.getBytes(Glossary.Char_UTF_8)));
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				sb.append(line);
			}
			return sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) throws Exception {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
				"<userLogin>" +
					"<user>" +
						"<account>207</account>" +
						"<password>123456</password>" +
						"<imei>323242342</imei>" +
						"<longitude>116.404</longitude>" +
						"<latitude>39.915</latitude>" +
						"<deviceType>1</deviceType>" +
						"<deviceId>867247024715749</deviceId>" +
						"<deviceName>MX5</deviceName>" +
					"</user>" +
				"</userLogin>";
		//System.out.println( testPost("http://192.168.1.106/Server/userService/login",xml) ) ;
		System.out.println( testPost("https://maps.googleapis.com/maps/api/js?key=AIzaSyDk_bOqPj0RnQdpjJ01aPHeiyr20feUTMI&callback=initMap","") ) ;
	}
}
