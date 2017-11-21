package com.jxgis.ffireserver.core.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


/**
 * Created by 操作文件工具类 on 2017/7/11.
 */
public class OperationFileUtil {

	private static final String ENCODING = "utf-8";

	/**
	 * 文件下载
	 *
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static ResponseEntity<byte[]> download(String filePath) throws UnsupportedEncodingException, IOException {
		String fileName = FilenameUtils.getName(filePath);
		return downloadAssist(filePath, fileName);
	}

	/**
	 * 文件下载
	 *
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            文件名
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static ResponseEntity<byte[]> download(String filePath, String fileName) throws IOException {
		return downloadAssist(filePath, fileName);
	}

	/**
	 * 文件下载辅助
	 *
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            文件名
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private static ResponseEntity<byte[]> downloadAssist(String filePath, String fileName) throws IOException {
		File file = new File(filePath);
		if (!file.isFile() || !file.exists()) {
			throw new IllegalArgumentException("filePath 参数必须是真实存在的文件路径:" + filePath);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, ENCODING));
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

}
