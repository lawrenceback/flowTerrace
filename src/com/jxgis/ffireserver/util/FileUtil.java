package com.jxgis.ffireserver.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件帮助类
 * @author 何桂明
 *
 */
public class FileUtil {
	
	// 保存图片及视频的文件夹路径
	public static final String MEDIA_DIR = "D:/heguiming/jxhy/ffire/media/";

	public static byte[] fileToByte(File file) throws IOException {
		InputStream in = new FileInputStream(file);
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			byte[] barr = new byte[1024];
			while (true) {
				int r = in.read(barr);
				if (r <= 0) {
					break;
				}
				buffer.write(barr, 0, r);
			}
			return buffer.toByteArray();
		}
		finally {
			in.close();
		}
	}

	public static File byteToFile(byte[] b, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			file = new File(fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(b);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (bos != null) {
				try {
					bos.close();
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}
	
	/**
	 * 读取文件成字符串
	 * @param [filePath] 文件地址
	 * @return
	 */
	public static String fileToStr(String filePath) {
		BufferedReader br = null;
		FileReader fr = null;
		String s = null;
		StringBuffer buf = new StringBuffer();
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			while ((s = br.readLine()) != null) {
				buf.append(s).append("\r\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != fr) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buf.toString();
	}

	/**
	 * 删除文件
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
			return true;
		}
		return false;
	}
	
	/**
	 * 保存文件
	 * @param is
	 * @param filepath
	 */
	public static void saveFile(InputStream is, String path) {
		File file = new File(path);
		FileOutputStream out = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			
			if (file.exists()) {
				file.delete();
			}
			
			out = new FileOutputStream(file);
			bos = new BufferedOutputStream(out);
			bis = new BufferedInputStream(is);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
				bos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bos) {
					bos.close();
				}
				if (null != bis) {
					bis.close();
				}
				if (null != out) {
					out.close();
				}
				if (null != is) {
					is.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
