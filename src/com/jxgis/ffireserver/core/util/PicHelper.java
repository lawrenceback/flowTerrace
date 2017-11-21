package com.jxgis.ffireserver.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanWei on 2017/7/21.
 *
 * 视频文件截取图片工具类
 *
 */
public class PicHelper {

	public static void handler(String ffmpegPath,String upFilePath,String mediaPicPath, String w, String h) throws Exception{
		List<String> cutpic = new ArrayList<>();
		cutpic.add(ffmpegPath);
		cutpic.add("-i");
		cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
		cutpic.add("-y");
		cutpic.add("-f");
		cutpic.add("image2");
		cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
		cutpic.add("3"); // 添加起始时间为第17秒
		cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
		cutpic.add("0.001"); // 添加持续时间为1毫秒
		cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
		cutpic.add( w + "*" + h); // 添加截取的图片大小为350*240
		cutpic.add(mediaPicPath); // 添加截取的图片的保存路径
		exit(cutpic);
	}

	public static void handler(String ffmpegPath,String upFilePath,String mediaPicPath) throws Exception{
		List<String> cutpic = new ArrayList<>();
		cutpic.add(ffmpegPath);
		cutpic.add("-i");
		cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
		cutpic.add("-y");
		cutpic.add("-f");
		cutpic.add("image2");
		cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
		cutpic.add("3"); // 添加起始时间为第17秒
		cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
		cutpic.add("0.001"); // 添加持续时间为1毫秒
		cutpic.add(mediaPicPath); // 添加截取的图片的保存路径
		exit(cutpic);
	}

	public static void exit(List<String> cutpic) throws Exception{
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(cutpic);
		builder.redirectErrorStream(true);
		// 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
		//因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
		builder.start();
	}
	public static void main(String[] args) throws Exception{
		handler("F:\\迅雷下载\\ffmpeg-20170718-012620a-win64-static\\bin\\ffmpeg.exe","F:\\迅雷下载\\jiaocheng\\安装教程.avi","F:\\迅雷下载\\jiaocheng\\uu.jpg");
	}
}
