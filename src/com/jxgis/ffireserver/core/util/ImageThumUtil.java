package com.jxgis.ffireserver.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


/**
 * Created by YanWei on 2017/7/27.
 *
 * 图片缩略图工具
 *
 */
public class ImageThumUtil {
	private Logger log = LoggerFactory.getLogger(getClass());

	private static String DEFAULT_PREVFIX = "thumb_";
	private static Boolean DEFAULT_FORCE = false;

	/**
	 * <p>Title: thumbnailImage</p>
	 * <p>Description:     根据图片路径生成缩略图 </p>
	 * @param imgFile      原图片路径
	 * @param w            缩略图宽
	 * @param h            缩略图高
	 * @param prevfix      生成缩略图的前缀
	 * @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
	 */
	public void thumbnailImage(File imgFile, int w, int h, String prevfix, boolean force){
		if(imgFile.exists()){
			try {
				// ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
				String types = Arrays.toString(ImageIO.getReaderFormatNames());
				String suffix = null;
				// 获取图片后缀
				if(imgFile.getName().indexOf(".") > -1) {
					suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
				}// 类型和图片后缀全部小写，然后判断后缀是否合法
				if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0){
					log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
					return ;
				}
				log.debug("target image's size, width:{}, height:{}.",w,h);
				Image img = ImageIO.read(imgFile);
				if(!force){

					// 根据原图与要求的缩略图比例，找到最合适的缩略图比例
					int width = img.getWidth(null);
					int height = img.getHeight(null);

					if((width*1.0)/w < (height*1.0)/h){
						if(width > w){
							h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
							log.debug("change image's height, width:{}, height:{}.",w,h);
						}
					} else {
						if(height > h){
							w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
							log.debug("change image's width, width:{}, height:{}.",w,h);
						}
					}
				}
				BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				if (suffix.equals("png") || suffix.equals("PNG")){
					bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
				}
				Graphics g = bi.getGraphics();
				g.drawImage(img, 0, 0, w, h, null, null);
				g.dispose();
				String p = imgFile.getPath();
				// 将图片保存在原目录并加上前缀
				ImageIO.write(bi, suffix, new File(p.substring(0,p.lastIndexOf(File.separator)) + File.separator + prevfix +imgFile.getName()));
			} catch (IOException e) {
				log.error("generate thumbnail image failed.",e);
			}
		}else{
			log.warn("the image is not exist.");
		}
	}

	public void thumbnailImage(String imagePath, int w, int h, String prevfix, boolean force){
		File imgFile = new File(imagePath);
		thumbnailImage(imgFile, w, h, prevfix, force);
	}

	public void thumbnailImage(String imagePath, int w, int h, boolean force){
		thumbnailImage(imagePath, w, h, DEFAULT_PREVFIX, force);
	}

	public void thumbnailImage(String imagePath, int w, int h){
		thumbnailImage(imagePath, w, h, DEFAULT_FORCE);
	}

	/**
	 * 获取图片宽度
	 * @param file  图片文件
	 * @return 宽度
	 */
	public static int getImgWidth(File file) {
		InputStream is = null;
		BufferedImage src = null;
		int ret = -1;
		try {
			is = new FileInputStream(file);
			src = javax.imageio.ImageIO.read(is);
			ret = src.getWidth(null); // 得到源图宽
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取图片宽度
	 * @param is  图片文件
	 * @return 宽度
	 */
	public static int getImgWidth(InputStream is) {
		BufferedImage src = null;
		int ret = -1;
		try {
			src = javax.imageio.ImageIO.read(is);
			ret = src.getWidth(null); // 得到源图宽
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取图片高度
	 * @param file  图片文件
	 * @return 高度
	 */
	public static int getImgHeight(File file) {
		InputStream is = null;
		BufferedImage src = null;
		int ret = -1;
		try {
			is = new FileInputStream(file);
			src = javax.imageio.ImageIO.read(is);
			ret = src.getHeight(null); // 得到源图高
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 获取图片高度
	 * @param is  图片文件
	 * @return 高度
	 */
	public static int getImgHeight(InputStream is) {
		BufferedImage src = null;
		int ret = -1;
		try {
			src = javax.imageio.ImageIO.read(is);
			ret = src.getHeight(null); // 得到源图高
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) {
		new ImageThumUtil().thumbnailImage("F:\\20170811154944750754.png", 256, 256);
	}
}
