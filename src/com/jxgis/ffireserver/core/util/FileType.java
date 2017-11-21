package com.jxgis.ffireserver.core.util;

import com.jxgis.ffireserver.util.Glossary;

/**
 * Created by YanWei on 2017/7/28.
 *
 */
public class FileType {

	public final static String Img_JPG = "jpg";
	public final static String Img_JPEG = "jpeg";
	public final static String Img_GIF = "gif";
	public final static String Img_PNG = "png";
	public final static String Img_BMP = "bmp";
	public final static String Img_TIFF = "tiff";
	public final static String Img_PSD = "psd";

	public final static String Video_MP4 = "mp4";
	public final static String Video_AVI = "avi";
	public final static String Video_RMVB = "rmvb";
	public final static String Video_MKV = "mkv";
	public final static String Video_FLV = "flv";
	public final static String Video_RM = "rm";
	public final static String Video_3GP = "3gp";
	public final static String Video_WMV = "wmv";


	public final static String Audio_WAV = "wav";
	public final static String Audio_MP3 = "mp3";
	public final static String Audio_WMA = "wma";
	public final static String Audio_OGG = "ogg";
	public final static String Audio_APE = "ape";
	public final static String Audio_ACC = "acc";
	public final static String Audio_AMR = "amr";


	/**
	 * 获得文件类型
	 *
	 * @param extend 文件后缀
	 * @return
	 */
	public static int getAllFileType(String extend){

		switch (extend){
			case Img_BMP:case Img_GIF:case Img_JPEG:case Img_JPG:case Img_PNG:case Img_PSD:case Img_TIFF:
				return Glossary.File_Type_Img;
			case Video_3GP:case Video_AVI:case Video_MP4:case Video_RMVB:case Video_FLV:case Video_RM:case Video_MKV:case Video_WMV:
				return Glossary.File_Type_Video;
			case Audio_WAV:case Audio_MP3:case Audio_WMA:case Audio_OGG:case Audio_APE:case Audio_ACC:case Audio_AMR:
				return Glossary.File_Type_Audio;
			default:
				return 0;
		}

	}
}
