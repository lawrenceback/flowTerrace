package com.jxgis.ffireserver.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"GBK\" ?>";
	
	public static final String ENTER = "\r\n";
	public static final String T = "\t";

	/**
	 * 判断字符串是否为NULL或空字符
	 */
	public static boolean isNullOrEmpty(String str) {
		return null == str || str.trim().isEmpty();
	}
	
	public static boolean isEmail(String email) {
		if (!isNullOrEmpty(email)) {
			String strPattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
			Pattern pattern = Pattern.compile(strPattern);
			Matcher m = pattern.matcher(email);
			return m.matches();
		}
		return false;
	}
	
	public static boolean isPhone(String phone) {
		if (!isNullOrEmpty(phone)) {
			Pattern p = Pattern.compile("^1\\d{10}$");  
			Matcher m = p.matcher(phone);
			return m.matches();
		}
		return false;
	}
	
	/**
	 * 判断ip是否正确
	 * @param ip
	 * @return
	 */
	public static boolean isIpAddress(String ip) {
		if (!isNullOrEmpty(ip)) {
			String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
			
			return ip.matches(regex);
		}
		return false;
	}
	
	/**
	 * 根据文件后缀获取文件的ContentType
	 * @param filename
	 * @return
	 */
	public static String getContentTypeByFileSuffix(String fileSuffix) {
		if (!isNullOrEmpty(fileSuffix)) {
			String suffix = fileSuffix.toLowerCase();
			// 图片
			if (suffix.equals("jpg") || suffix.equals("jpeg")) {
				return "image/jpeg";
			} else if (suffix.equals("gif")) {
				return "image/gif";
			} else if (suffix.equals("bmp")) {
				return "application/x-bmp";
			} else if (suffix.equals("png")) {
				return "image/png";
			}
			// 音频
			else if (suffix.equals("mp3")) {
				return "audio/mp3";
			} else if (suffix.equals("wma")) {
				return "audio/x-ms-wma";
			} else if (suffix.equals("wav")) {
				return "audio/wav";
			} else if (suffix.equals("mid")) {
				return "audio/mid";
			}
			// 视频
			else if (suffix.equals("avi")) {
				return "video/avi";
			} else if (suffix.equals("rmvb")) {
				return "application/vnd.rn-realmedia-vbr";
			} else if (suffix.equals("rm")) {
				return "application/vnd.rn-realmedia";
			} else if (suffix.equals("wmv")) {
				return "video/x-ms-wmv";
			}  else if (suffix.equals("mp4")) {
				return "video/mp4";
			}
			else {
				return "application/octet-stream";
			}
		}
		return null;
	}
	
	/**
	 * 是否是图片文件
	 * @param filename
	 * @return
	 */
	public static boolean isPicture(String filename) {
		if (!StringUtil.isNullOrEmpty(filename)) {
			if (filename.toLowerCase().endsWith(".jpg")
					|| filename.toLowerCase().endsWith(".jpeg")
					|| filename.toLowerCase().endsWith(".png")
					|| filename.toLowerCase().endsWith(".gif")
					|| filename.toLowerCase().endsWith(".bmp")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 是否是语音文件
	 * @param filename
	 * @return
	 */
	public static boolean isAudio(String filename) {
		if (!StringUtil.isNullOrEmpty(filename)) {
			if (filename.toLowerCase().endsWith(".mp3")
					|| filename.toLowerCase().endsWith(".wma")
					|| filename.toLowerCase().endsWith(".wav")
					|| filename.toLowerCase().endsWith(".mid")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 是否是语音文件
	 * @param filename
	 * @return
	 */
	public static boolean isVideo(String filename) {
		if (!StringUtil.isNullOrEmpty(filename)) {
			if (filename.toLowerCase().endsWith(".avi")
					|| filename.toLowerCase().endsWith(".rmvb")
					|| filename.toLowerCase().endsWith(".rm")
					|| filename.toLowerCase().endsWith(".wmv")
					|| filename.toLowerCase().endsWith(".mp4")) {
				return true;
			}
		}
		return false;
	}
	
	public static String getFileSuffix(String str) {
		if (!isNullOrEmpty(str)) {
			return str.substring(str.indexOf(".") + 1);
		}
		return null;
	}
	
	/**
	 * 校验行政区划代码合法性
	 * @return
	 */
	public static boolean verifyAreaCode(String areaCode) {
		// 12位行政区划代码
		if (!StringUtil.isNullOrEmpty(areaCode)) {
			if (areaCode.length() == 6 || areaCode.length() == 9 || areaCode.length() == 12) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据行政区划代码获取上级行政区代码
	 * @param areaCode
	 * @return
	 */
	public static String getSuperiorAreaCode(String areaCode) {
		if (!StringUtil.isNullOrEmpty(areaCode)) {
			if (areaCode.length() == 6) {
				// 省--无上级
				if (areaCode.endsWith("0000")) {
					return areaCode;
				} 
				// 市--上级为省
				else if (areaCode.endsWith("00")) {
					return areaCode.substring(0, 2) + "0000";
				}
				// 县--上级为市
				else {
					return areaCode.substring(0, 4) + "00";
				}
			} else if (areaCode.length() == 9) {
				return areaCode.substring(0, 6);
			} else if (areaCode.length() == 12) {
				return areaCode.substring(0, 9);
			}
		}
		return areaCode;
	}
	
	public static String getCourse(float lenMeter) {
		if (lenMeter > 1000) {
			float dis = (float) lenMeter / 1000;
			DecimalFormat fnum = new DecimalFormat("##0.0");
			String dstr = fnum.format(dis);
			return dstr + "千米";
		}

		int dis = (int)(lenMeter / 10 * 10);

		return dis + "米";
	}
	
	public static final long SECOND_IN_MILLIS = 1000;
	public static final long MINUTE_IN_MILLIS = 60 * SECOND_IN_MILLIS;
	public static final long HOUR_IN_MILLIS = 60 * MINUTE_IN_MILLIS;
	public static final long DAY_IN_MILLIS = 24 * HOUR_IN_MILLIS;
	public static final long MONTH_IN_MILLIS = 30 * DAY_IN_MILLIS;
	
	 /**
     * 格式化时长 中文tag
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getReplaceTime(long abs){
    	abs = abs < 0 ? 0 : abs;
    	
    	long days = abs / DAY_IN_MILLIS;
		long hours = (abs % DAY_IN_MILLIS) / HOUR_IN_MILLIS;
		long minutes = ((abs % DAY_IN_MILLIS) % HOUR_IN_MILLIS) / MINUTE_IN_MILLIS;
		long seconds = (((abs % DAY_IN_MILLIS) % HOUR_IN_MILLIS) % MINUTE_IN_MILLIS) / SECOND_IN_MILLIS;
		hours += days * 24;
		
		String result = "";
		if (hours > 0) {
			result += hours + "小时";
		}
		if (minutes > 0) {
			result += minutes + "分钟";
		}
		if (seconds > 0) {
			result += seconds + "秒";
		}
		return  result;
    }
    
	public static StringBuffer getFileCloudPath() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(File.separator).append("usr");
		buffer.append(File.separator).append("local");
		buffer.append(File.separator).append("OneCardCloud");
		buffer.append(File.separator);
		return buffer;
	}

}
