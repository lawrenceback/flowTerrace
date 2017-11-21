package com.jxgis.ffireserver.core.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtils {

	private static final int BUFFERED_SIZE = 1024 * 4;

	/**
	 * 创建文件夹
	 * 
	 * @param path
	 *            文件夹路径
	 */
	public static void createDir(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param path
	 *            文件夹路径
	 */
	public static void deleteDir(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			LogUtil.info("--------成功删除文件---------"
					+ sPath);
			return file.delete();
		}
		LogUtil.info("错误: " + sPath + "不存在!");
		return false;
	}

	/**
	 * 删除指定的文件
	 *
	 * @param strFileName
	 *            指定绝对路径的文件名
	 * @return 如果删除成功true否则false
	 */
	public static boolean delete(String strFileName) {
		File fileDelete = new File(strFileName);

		if (!fileDelete.exists() || !fileDelete.isFile()) {
			LogUtil.info("错误: " + strFileName + "不存在!");
			return false;
		}

		LogUtil.info("--------成功删除文件---------"+strFileName);
		return fileDelete.delete();
	}

	/**
	 * 高效拷贝文件
	 */
	public static void copy(InputStream is, OutputStream os) {

		if (is == null || os == null) {
			return;
		}
		BufferedInputStream bis = new BufferedInputStream(is);
		BufferedOutputStream bos = new BufferedOutputStream(os);

		byte[] b = new byte[BUFFERED_SIZE];

		int n = 0;
		try {
			while ((n = bis.read(b)) != -1) {
				try {
					bos.write(b, 0, n);
				} catch (IOException e) {
					System.out.println("file writing exception:文件写入异常");
					close(bis, bos);
					return;
				}
			}
		} catch (IOException e1) {
			System.out.println("file reading exception:文件读取异常");
			close(bis, bos);
			return;
		}
		try {
			bos.flush();
		} catch (IOException e) {
			System.out.println("输入流缓冲异常");
			close(bis, bos);
			return;
		}
		close(bis, bos);
	}

	/**
	 * 
	 * @param s
	 *            源文件
	 * @param t
	 *            新文件
	 */
	public static void fileChannelCopy(File s, File t) {

		FileInputStream fi = null;

		FileOutputStream fo = null;

		FileChannel in = null;

		FileChannel out = null;

		try {

			fi = new FileInputStream(s);

			fo = new FileOutputStream(t);

			in = fi.getChannel();// 得到对应的文件通道

			out = fo.getChannel();// 得到对应的文件通道

			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				fi.close();

				in.close();

				fo.close();

				out.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

	/**
	 * 关闭流
	 */
	public static void close(InputStream is, OutputStream os) {
		try {
			is.close();
		} catch (IOException e) {
			System.out.println("输入流关闭异常");
		}
		try {
			os.close();
		} catch (IOException e) {
			System.out.println("输出流关闭异常");
		}
	}

	/**
	 * 关闭流
	 */
	public static void close(BufferedInputStream bis, BufferedInputStream bos) {
		try {
			bis.close();
		} catch (IOException e) {
			System.out.println("输入流关闭异常");
		}
		try {
			bos.close();
		} catch (IOException e) {
			System.out.println("输出流关闭异常");
		}
	}

	/**
	 * 
	 * @param path
	 *            文件路径
	 * @param type
	 *            需要得到的文件类型 如果为null或者""则得到所有文件
	 * @param needPathDirectory
	 *            是否需要得到路径下子文件夹下的文件
	 * @param list
	 *            装载你得到的结果
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List getSameTypeFile(String path, String type,
			boolean needPathDirectory, List list) {

		if (path == null || "".equals(path.trim()) || list == null) {
			return null;
		}

		File file = new File(path);

		String[] allFiles = file.list();

		if (allFiles != null) {
			File subFile = null;
			for (int i = 0; i < allFiles.length; i++) {
				subFile = new File(file.getAbsolutePath() + File.separator
						+ allFiles[i]);
				if (type != null && !"".equals(type.trim())) {
					if (!subFile.isDirectory()
							&& type.equalsIgnoreCase(allFiles[i]
									.substring(allFiles[i].lastIndexOf(".")))) {
						list.add(file.getAbsolutePath() + File.separator
								+ allFiles[i]);
					}
				} else {
					if (!subFile.isDirectory()) {
						list.add(file.getAbsolutePath() + File.separator
								+ allFiles[i]);
					}
				}

				if (needPathDirectory && subFile.isDirectory()) {
					getSameTypeFile(subFile.getAbsolutePath(), type,
							needPathDirectory, list);
				}
			}
		}

		return list;
	}

	/**
	 * 
	 * @param path
	 *            文件路径
	 * @param encoding
	 *            字符编码
	 * @return
	 */
	public static String readInputStreamToList(String path, String encoding) {

		InputStream is = null;
		Reader rd = null;
		LineNumberReader lr = null;
		StringBuffer sb = new StringBuffer();
		try {
			is = new FileInputStream(path);
			rd = new InputStreamReader(is, encoding);
			lr = new LineNumberReader(rd);

		} catch (IOException e) {
			e.printStackTrace();
		}
		String str = "";
		try {
			while ((str = lr.readLine()) != null) {
				sb.append(str + "\r\n");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			lr.close();
		} catch (IOException e) {
			System.out.println("文件读取异常");
		}

		return sb.toString();
	}

	/**
	 * 
	 * @param str 要写入文档的字符串
	 * @param path
	 *            路径
	 * @param encoding
	 *            编码
	 */
	public static void writeListToJspFile(String str, String path,
			String encoding) {
		OutputStream os = null;
		Writer writer = null;
		try {
			os = new FileOutputStream(path);
			writer = new OutputStreamWriter(os, encoding);

			writer.write(str);

		} catch (Exception e) {
			System.out.println("文件写入异常");
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}





	public static String dirSplit = "\\";// linux windows

	/**
	 * save file accroding to physical directory infomation
	 * 
	 * @param istream
	 *            input stream of destination file
	 * @return
	 */
	public static boolean SaveFileByPhysicalDir(String physicalPath, InputStream istream) {

		boolean flag = false;
		try {
			OutputStream os = new FileOutputStream(physicalPath);
			int readBytes = 0;
			byte buffer[] = new byte[8192];
			while ((readBytes = istream.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, readBytes);
			}
			os.close();
			flag = true;
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return flag;
	}

	public static boolean CreateDirectory(String dir) {
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdirs();
		}
		return true;
	}

	public static void saveAsFileOutputStream(String physicalPath,
			String content) {
		File file = new File(physicalPath);
		boolean b = file.getParentFile().isDirectory();
		if (!b) {
			File tem = new File(file.getParent());
			// tem.getParentFile().setWritable(true);
			tem.mkdirs();// 创建目录
		}
		// Log.info(file.getParent()+";"+file.getParentFile().isDirectory());
		FileOutputStream foutput = null;
		try {
			foutput = new FileOutputStream(physicalPath);

			foutput.write(content.getBytes("UTF-8"));
			// foutput.write(content.getBytes());
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				foutput.flush();
				foutput.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
		// Log.info("文件保存成功:"+ physicalPath);
	}

	/**
	 * COPY文件
	 * 
	 * @param srcFile
	 *            String
	 * @param desFile
	 *            String
	 * @return boolean
	 */
	public boolean copyToFile(String srcFile, String desFile) {
		File scrfile = new File(srcFile);
		if (scrfile.isFile() == true) {
			int length;
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(scrfile);
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
			File desfile = new File(desFile);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(desfile, false);
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
			desfile = null;
			length = (int) scrfile.length();
			byte[] b = new byte[length];
			try {
				fis.read(b);
				fis.close();
				fos.write(b);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			scrfile = null;
			return false;
		}
		scrfile = null;
		return true;
	}

	/**
	 * COPY文件夹
	 * 
	 * @param sourceDir
	 *            String
	 * @param destDir
	 *            String
	 * @return boolean
	 */
	public boolean copyDir(String sourceDir, String destDir) {
		File sourceFile = new File(sourceDir);
		String tempSource;
		String tempDest;
		String fileName;
		File[] files = sourceFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			fileName = files[i].getName();
			tempSource = sourceDir + "/" + fileName;
			tempDest = destDir + "/" + fileName;
			if (files[i].isFile()) {
				copyToFile(tempSource, tempDest);
			} else {
				copyDir(tempSource, tempDest);
			}
		}
		sourceFile = null;
		return true;
	}

	/**
	 * 删除指定目录及其中的所有内容。
	 * 
	 * @param dir
	 *            要删除的目录
	 * @return 删除成功时返回true，否则返回false。
	 */
	public boolean deleteDirectory(File dir) {
		File[] entries = dir.listFiles();
		int sz = entries.length;
		for (int i = 0; i < sz; i++) {
			if (entries[i].isDirectory()) {
				if (!deleteDirectory(entries[i])) {
					return false;
				}
			} else {

				if (!entries[i].delete()) {
					return false;
				}
			}
		}
		if (!dir.delete()) {
			return false;
		}
		return true;
	}

	/**
	 * 删除指定中的所有内容。
	 * 
	 * @param dir
	 *            要删除的目录
	 * @param strs
	 *            过滤的文件名
	 * @return 删除成功时返回true，否则返回false。
	 */
	public boolean deleteDirectory(File dir, String... strs) {
		File[] entries = dir.listFiles();
		int sz = entries.length;
		for (int i = 0; i < sz; i++) {
			if (entries[i].isDirectory()) {
				if (!deleteDirectory(entries[i])) {
					return false;
				}
			} else {
				if (contains(strs, entries[i].getName())) {
					continue;
				}
				if (!entries[i].delete()) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 
	 * 判断数组是否包含元素
	 * 
	 * @param array
	 * @param v
	 * @return
	 */
	public static <T> boolean contains(final T[] array, final T v) {
		for (final T e : array)
			if (e == v || v != null && v.equals(e))
				return true;

		return false;
	}

	/**
	 * File exist check
	 * 
	 * @param sFileName
	 *            File Name
	 * @return boolean true - exist<br>
	 *         false - not exist
	 */
	public static boolean checkExist(String sFileName) {

		boolean result = false;

		try {
			File f = new File(sFileName);

			// if (f.exists() && f.isFile() && f.canRead()) {
			if (f.exists() && f.isFile()) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			result = false;
		}

		/* return */
		return result;
	}

	/**
	 * Get File Size
	 * 
	 * @param sFileName
	 *            File Name
	 * @return long File's size(byte) when File not exist return -1
	 */
	public static long getSize(String sFileName) {

		long lSize = 0;

		try {
			File f = new File(sFileName);

			// exist
			if (f.exists()) {
				if (f.isFile() && f.canRead()) {
					lSize = f.length();
				} else {
					lSize = -1;
				}
				// not exist
			} else {
				lSize = 0;
			}
		} catch (Exception e) {
			lSize = -1;
		}
		/* return */
		return lSize;
	}

	/**
	 * File Delete
	 * 
	 * @param sFileName
	 *            File Nmae
	 * @return boolean true - Delete Success<br>
	 *         false - Delete Fail
	 */
	public static boolean deleteFromName(String sFileName) {

		boolean bReturn = true;

		try {
			File oFile = new File(sFileName);

			// exist
			if (oFile.exists()) {
				// Delete File
				boolean bResult = oFile.delete();
				// Delete Fail
				if (bResult == false) {
					bReturn = false;
				}

				// not exist
			} else {

			}

		} catch (Exception e) {
			bReturn = false;
		}

		// return
		return bReturn;
	}

	/**
	 * File Unzip
	 * 
	 * @param sToPath
	 *            Unzip Directory path
	 * @param sZipFile
	 *            Unzip File Name
	 */
	@SuppressWarnings("rawtypes")
	public static void releaseZip(String sToPath, String sZipFile)
			throws Exception {

		if (null == sToPath || ("").equals(sToPath.trim())) {
			File objZipFile = new File(sZipFile);
			sToPath = objZipFile.getParent();
		}
		ZipFile zfile = new ZipFile(sZipFile);
		Enumeration zList = zfile.entries();
		ZipEntry ze = null;
		byte[] buf = new byte[1024];
		while (zList.hasMoreElements()) {
			ze = (ZipEntry) zList.nextElement();
			if (ze.isDirectory()) {
				continue;
			}

			OutputStream os = new BufferedOutputStream(new FileOutputStream(
					getRealFileName(sToPath, ze.getName())));
			InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
			int readLen = 0;
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				os.write(buf, 0, readLen);
			}
			is.close();
			os.close();
		}
		zfile.close();
	}

	/**
	 * getRealFileName
	 * 
	 * @param baseDir
	 *            Root Directory
	 * @param absFileName
	 *            absolute Directory File Name
	 * @return java.io.File Return file
	 */
	@SuppressWarnings( { "rawtypes", "unchecked" })
	private static File getRealFileName(String baseDir, String absFileName)
			throws Exception {

		File ret = null;
		List dirs = new ArrayList();
		StringTokenizer st = new StringTokenizer(absFileName, System
				.getProperty("file.separator"));
		while (st.hasMoreTokens()) {
			dirs.add(st.nextToken());
		}
		ret = new File(baseDir);
		if (dirs.size() > 1) {
			for (int i = 0; i < dirs.size() - 1; i++) {
				ret = new File(ret, (String) dirs.get(i));
			}
		}
		if (!ret.exists()) {
			ret.mkdirs();
		}
		ret = new File(ret, (String) dirs.get(dirs.size() - 1));
		return ret;
	}

	/**
	 * copyFile
	 * 
	 * @param srcFile
	 *            Source File
	 * @param targetFile
	 *            Target file
	 */
	@SuppressWarnings("resource")
	static public void copyFile(String srcFile, String targetFile)
			throws IOException {

		FileInputStream reader = new FileInputStream(srcFile);
		FileOutputStream writer = new FileOutputStream(targetFile);
		byte[] buffer = new byte[4096];
		int len;
		try {
			reader = new FileInputStream(srcFile);
			writer = new FileOutputStream(targetFile);

			while ((len = reader.read(buffer)) > 0) {
				writer.write(buffer, 0, len);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (writer != null)
				writer.close();
			if (reader != null)
				reader.close();
		}
	}

	/**
	 * renameFile
	 * 
	 * @param srcFile
	 *            Source File
	 * @param targetFile
	 *            Target file
	 */
	static public void renameFile(String srcFile, String targetFile)
			throws IOException {
		try {
			copyFile(srcFile, targetFile);
			deleteFromName(srcFile);
		} catch (IOException e) {
			throw e;
		}
	}

	public static void write(String tivoliMsg, String logFileName) {
		try {
			byte[] bMsg = tivoliMsg.getBytes();
			FileOutputStream fOut = new FileOutputStream(logFileName, true);
			fOut.write(bMsg);
			fOut.close();
		} catch (IOException e) {
			// throw the exception
		}
	}

	/**
	 * This method is used to log the messages with timestamp,error code and the
	 * method details
	 * 
	 * @param logFile
	 *            String
	 * @param batchId
	 *            String
	 * @param exceptionInfo
	 *            String
	 */
	public static void writeLog(String logFile, String batchId, String exceptionInfo) {

		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
				DateFormat.DEFAULT, Locale.JAPANESE);

		Object args[] = { df.format(new Date()), batchId, exceptionInfo };

		String fmtMsg = MessageFormat.format("{0} : {1} : {2}", args);

		try {

			File logfile = new File(logFile);
			if (!logfile.exists()) {
				logfile.createNewFile();
			}

			FileWriter fw = new FileWriter(logFile, true);
			fw.write(fmtMsg);
			fw.write(System.getProperty("line.separator"));
			fw.flush();
			fw.close();
		} catch (Exception e) {
		}
	}

	public static String readTextFile(String realPath) throws Exception {
		File file = new File(realPath);
		if (!file.exists()) {
			System.out.println("File not exist!");
			return null;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(realPath), "UTF-8"));
		String temp = "";
		String txt = "";
		while ((temp = br.readLine()) != null) {
			txt += temp;
		}
		br.close();
		return txt;
	}

	/**
	 * 获取文件名称[不含后缀名] 不去掉文件目录的空格
	 * 
	 * @param
	 * @return String
	 */
	public static String getFilePrefix2(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0, splitIndex);
	}


	/**
	 * 获取文件名称[不含后缀名]
	 * 
	 * @param
	 * @return String
	 */
	public static String getFilePrefix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0, splitIndex).replaceAll("\\s*", "");
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename) {
		return getExtend(filename, "");
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return (filename.substring(i + 1)).toLowerCase();
			}
		}
		return defExt.toLowerCase();
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println( Base64.encode("111".getBytes()));
		// String str = readInputStreamToList("E:\\index.jsp", "UTF-8");
		// String newStr = str.replace("body", "fuck");
		// writeListToJspFile(newStr, "e:\\aa.jsp", "UTF-8");
		System.out
				.println(deleteFile("E:\\Apache Software Foundation\\Tomcat 7.0\\webapps\\wisdomFZ\\files\\fzinfo\\gc\\2014101618265412qtGZuT.mp3"));
		// deleteFile("E:\Apache Software Foundation\Tomcat
		// 7.0\webapps\wisdomFZ\files\fzinfo\gc\20141016172740gfSs2hYV.mp3");
	}
}
