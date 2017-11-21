package com.jxgis.ffireserver.util;

import java.io.File;
import java.io.IOException;

/**
 * Base64工具类
 * @author 何桂明
 *
 */
public class Base64Util {

	public static String encodeFile(File file) throws Exception {
		byte[] bytes = FileUtil.fileToByte(file);
		String encodedBase64 = encode(bytes);
		return encodedBase64;
	}

	/**
	 * 保存Base64 的文件
	 * 
	 * @param fileBase
	 * 
	 * @param filePath
	 * 
	 * @return
	 */
	public static File decodeFile(String fileBase, String filePath) {
		byte[] bytes = decode(fileBase);
		return FileUtil.byteToFile(bytes, filePath);
	}

	public static File decodeFile(byte[] fileByte, String fileName) {
		byte[] bytes = Base64.decode(fileByte);
		return FileUtil.byteToFile(bytes, fileName);
	}

	/**
	 * Base64 encoder
	 */
	public static String encode(byte[] input) {
		byte[] ret = Base64.encode(input);
		return new String(ret);
	}

	/**
	 * Base64 decoder
	 */
	public static byte[] decode(String stringacod) {
		byte[] ret = Base64.decode(stringacod);
		return ret;
	}

	/**
	 * 读取文件转成Base64
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String fileToBase64Str(String filePath) {
		try {
			File file = new File(filePath);
			byte[] bytes = FileUtil.fileToByte(file);
			String encodedBase64 = encode(bytes);
			return encodedBase64;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
