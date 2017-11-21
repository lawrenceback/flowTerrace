package com.jxgis.ffireserver.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.databinding.types.xsd.DateTime;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.apache.tools.ant.util.*;
import org.json.JSONObject;


/**
 *
 * @author Snow
 * 简单的写入 excel
 * HSSF实现 excel 2003(以 .xls 结尾的文件)
 * XSSF实现 excel 2007(以 .xlsx 结尾的文件)
 */
public class ExcelUtil {
	private static final Logger logger = Logger.getLogger(ExcelUtil.class);

	/**
	 * @Title: createWorkbook
	 * @Description: 判断excel文件后缀名，生成不同的workbook
	 * @param @param is
	 * @param @param excelFileName
	 * @param @return
	 * @param @throws IOException
	 * @return Workbook
	 * @throws
	 */
	public Workbook createWorkbook(InputStream is,String excelFileName) throws IOException{
		if (excelFileName.endsWith(".xls")) {
			return new HSSFWorkbook(is);
		}else if (excelFileName.endsWith(".xlsx")) {
			return new XSSFWorkbook(is);
		}
		return null;
	}

	/**
	 * @Title: getSheet
	 * @Description: 根据sheet索引号获取对应的sheet
	 * @param @param workbook
	 * @param @param sheetIndex
	 * @param @return
	 * @return Sheet
	 * @throws
	 */
	public Sheet getSheet(Workbook workbook,int sheetIndex){
		return workbook.getSheetAt(0);
	}

	/**
	 * @Title: importDataFromExcel
	 * @Description: 将sheet中的数据保存到list中，
	 * 1、调用此方法时，vo的属性个数必须和excel文件每行数据的列数相同且一一对应，vo的所有属性都为String
	 * 2、在action调用此方法时，需声明
	 *     private File excelFile;上传的文件
	 *     private String excelFileName;原始文件的文件名
	 * 3、页面的file控件name需对应File的文件名
	 * @param @param vo javaBean
	 * @param @param is 输入流
	 * @param @param excelFileName
	 * @param @return
	 * @return List<Object>
	 * @throws
	 */
	public List<Object> importDataFromExcel(Object vo,InputStream is,String excelFileName){
		List<Object> list = new ArrayList<Object>();
		try {
			//创建工作簿
			Workbook workbook = this.createWorkbook(is, excelFileName);
			//创建工作表sheet
			Sheet sheet = this.getSheet(workbook, 0);
			//获取sheet中数据的行数
			int rows = sheet.getPhysicalNumberOfRows();
			//获取表头单元格个数
			int cells = sheet.getRow(0).getPhysicalNumberOfCells();
			//利用反射，给JavaBean的属性进行赋值
			Field[] fields = vo.getClass().getDeclaredFields();
			for (int i = 1; i < rows; i++) {//第一行为标题栏，从第二行开始取数据
				Row row = sheet.getRow(i);
				int index = 0;
				while (index < cells) {
					Cell cell = row.getCell(index);
					if (null == cell) {
						cell = row.createCell(index);
					}
					cell.setCellType(Cell.CELL_TYPE_STRING);
					String value = null == cell.getStringCellValue()?"":cell.getStringCellValue();

					Field field = fields[index];
					String fieldName = field.getName();
					String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
					Method setMethod = vo.getClass().getMethod(methodName, new Class[]{String.class});
					setMethod.invoke(vo, new Object[]{value});
					index++;
				}
				if (isHasValues(vo)) {//判断对象属性是否有值
					list.add(vo);
					vo.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});//重新创建一个vo对象
				}

			}
		} catch (Exception e) {
			logger.error(e);
		}finally{
			try {
				is.close();//关闭流
			} catch (Exception e2) {
				logger.error(e2);
			}
		}
		return list;

	}

	/**
	 * @Title: isHasValues
	 * @Description: 判断一个对象所有属性是否有值，如果一个属性有值(分空)，则返回true
	 * @param @param object
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean isHasValues(Object object){
		Field[] fields = object.getClass().getDeclaredFields();
		boolean flag = false;
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
			Method getMethod;
			try {
				getMethod = object.getClass().getMethod(methodName);
				Object obj = getMethod.invoke(object);
				if (null != obj && "".equals(obj)) {
					flag = true;
					break;
				}
			} catch (Exception e) {
				logger.error(e);
			}

		}
		return flag;

	}

	public <T> void exportDataToExcel(List<T> list,String[] headers,String title,OutputStream os){
		HSSFWorkbook workbook = new HSSFWorkbook();
		//生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		//设置表格默认列宽15个字节
		sheet.setDefaultColumnWidth(15);
		//生成一个样式
		HSSFCellStyle style = this.getCellStyle(workbook);
		//生成一个字体
		HSSFFont font = this.getFont(workbook);
		//把字体应用到当前样式
		style.setFont(font);

		//生成表格标题
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short)300);
		HSSFCell cell = null;

		for (int i = 0; i < headers.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		//将数据放入sheet中
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i+1);
			T t = list.get(i);
			//利用反射，根据JavaBean属性的先后顺序，动态调用get方法得到属性的值
			Field[] fields = t.getClass().getFields();
			try {
				for (int j = 0; j < fields.length; j++) {
					cell = row.createCell(j);
					Field field = fields[j];
					String fieldName = field.getName();
					String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
					Method getMethod = t.getClass().getMethod(methodName,new Class[]{});
					Object value = getMethod.invoke(t, new Object[]{});

					if(null == value)
						value ="";
					cell.setCellValue(value.toString());

				}
			} catch (Exception e) {
				logger.error(e);
			}
		}

		try {
			workbook.write(os);
		} catch (Exception e) {
			logger.error(e);
		}finally{
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				logger.error(e);
			}
		}

	}

	/**
	 * @Title: getCellStyle
	 * @Description: 获取单元格格式
	 * @param @param workbook
	 * @param @return
	 * @return HSSFCellStyle
	 * @throws
	 */
	public HSSFCellStyle getCellStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		return style;
	}

	/**
	 * @Title: getFont
	 * @Description: 生成字体样式
	 * @param @param workbook
	 * @param @return
	 * @return HSSFFont
	 * @throws
	 */
	public HSSFFont getFont(HSSFWorkbook workbook){
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.WHITE.index);
		font.setFontHeightInPoints((short)12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		return font;
	}

	public boolean isIE(HttpServletRequest request){
		return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie")>0?true:false;
	}



	public void export2007(HttpServletResponse response, List<Map<String, Object>> list, String filename, String[] title){
		String[] header = title;

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(filename);
		XSSFRow row = sheet.createRow((int) 0);
		XSSFCellStyle style = wb.createCellStyle();

		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 11);
		font.setFontName("宋体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
		style.setFont(font);

		XSSFCell cell = null;
		for(int i=0;i<header.length;i++){
			cell = row.createCell((short) i);
			cell.setCellValue(header[i]);
			cell.setCellStyle(style);
		}

		if(list == null){
			return;
		}

		//遍历字段
		Map<String, Object> fields = list.get(0);
		int fi = 0;
		for (Map.Entry<String, Object> value : fields.entrySet()) {
			row.createCell((short)fi).setCellValue(oConvertUtils.getString(value.getKey()));
			fi++;
		}

		for (int i = 0; i < list.size(); i++)
		{
			row = sheet.createRow((int) i + 1);

			Map<String, Object> clist = list.get(i);

			int n = 0;
			for (Map.Entry<String, Object> value : clist.entrySet()) {
				if(value.getValue() instanceof Date){
					row.createCell((short)n).setCellValue(DateUtils.date2Str((Date)value.getValue(), DateUtils.datetimeFormat));
				} else if (value.getValue() instanceof Integer){
					row.createCell((short)n).setCellValue(oConvertUtils.getInt(oConvertUtils.getString(value.getValue())) + "");
				} else if (value.getValue() instanceof Double){
					row.createCell((short)n).setCellValue(oConvertUtils.getDouble(oConvertUtils.getString(value.getValue()),0) + "");
				} else if (value.getValue() instanceof String) {
					row.createCell((short)n).setCellValue(value.getValue().toString());
				} else if (value.getValue() instanceof Long) {
					row.createCell((short)n).setCellValue(DateUtils.date2Str(new Date((long)value.getValue()), DateUtils.datetimeFormat));
				} else {
					try
					{
						Map<String, Object> dateMap = JSONHelper.json2Map(value.getValue().toString());
						Date date = new Date((long)dateMap.get("time"));
						String dateStr = "";
						if (StringUtil.isNotEmpty(date)){
							dateStr = DateUtils.date2Str(date, DateUtils.datetimeFormat);
						}
						if (dateStr == null || dateStr.equals(null) || dateStr == "null"){
							dateStr = "";
						}
						row.createCell((short)n).setCellValue(dateStr);
					} catch (Exception e){
						String ddd = oConvertUtils.getString(value.getValue());
						if (ddd == null || ddd.equals(null) || ddd == "null"){
							ddd = "";
						}
						row.createCell((short)n).setCellValue(ddd);
					}

				}

				n++;
			}
		}

		try
		{
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + java.net.URLEncoder.encode(filename, "UTF-8") + ".xlsx" + "\" ");
			wb.write(response.getOutputStream());
			response.getOutputStream().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}
