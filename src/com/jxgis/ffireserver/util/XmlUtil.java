/**
 * @class XmlUtil
 * @Describtion XML数据转换工具
 * @Date 2011-04-06
 * @Author yangzebo
 */
package com.jxgis.ffireserver.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XmlUtil {
	
	public static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
	
	public static final String ENTER = "\r\n";

	/**
	 * 将Bean对象转换为XML格式字符串
	 * 
	 * @param bean
	 *            Bean对象
	 * @param rootNodeName
	 *            根节点名
	 * @return
	 */
	public static String toXml(Object bean, String rootNodeName) {
		if (null == bean || StringUtils.isBlank(rootNodeName)) {
			return null;
		}
		XStream xStream = new XStream(new DomDriver());
		// 为Bean取别名，此别名同时作为根节点
		xStream.alias(rootNodeName, bean.getClass());

		return xStream.toXML(bean);
	}

	/**
	 * 将Bean对象转换为XML格式字符串，增加XML头部信息参数
	 * 
	 * @param bean
	 * @param rootNodeName
	 * @param xmlHead
	 * @return
	 */
	public static String toXml(Object bean, String rootNodeName, String xmlHead) {
		String xml = toXml(bean, rootNodeName);
		return xmlHead + xml;
	}

	/**
	 * 对于有下节点名带有下划线"_" 的处理, 并且会删除根节点
	 * 
	 * @param bean
	 *            要转换的Bean
	 * @return
	 */
	public static String beanToXml(Object bean) {
		XStream stream = new XStream(new XppDriver(new XmlFriendlyReplacer(
				"_-", "_")));
		stream.alias("root", bean.getClass());

		// 转换
		String tempBeanXmlStr = stream.toXML(bean);

		// 记录要删除的根节点的开始位置
		int rootBeginIndex = tempBeanXmlStr.indexOf("<root>") + 6;
		// 记录要删除的根节点的结束位置
		int endEndIndex = tempBeanXmlStr.indexOf("</root>");

		// 截取新串 （就是去掉根节点后的串）
		String newBeanXmlStr = tempBeanXmlStr.substring(rootBeginIndex,
				endEndIndex);

		return newBeanXmlStr;
	}

	/**
	 * 对于有下节点名带有下划线"_" 的处理, 并且会删除根节点，增加XML头部信息
	 * 
	 * @param bean
	 * @param xmlHead
	 * @return
	 */
	public static String beanToXmlWithHead(Object bean, String xmlHead) {
		String xml = beanToXml(bean);
		return xmlHead + xml;
	}

	/**
	 * 对于有下节点名带有下划线"_" 的处理, 加根节点
	 * 
	 * @param bean
	 *            实体Bean
	 * @param rootName
	 *            根节点名称
	 * @return
	 */
	public static String beanToXml(Object bean, String rootName) {
		XStream stream = new XStream(new XppDriver(new XmlFriendlyReplacer(
				"_-", "_")));
		stream.alias(rootName, bean.getClass());

		// 转换
		String beanXmlStr = stream.toXML(bean);

		return beanXmlStr;
	}

	/**
	 * 对于有下节点名带有下划线"_" 的处理, 加根节点，增加XML头部信息
	 * 
	 * @param bean
	 * @param rootName
	 * @param xmlHead
	 * @return
	 */
	public static String beanToXml(Object bean, String rootName, String xmlHead) {
		String xml = beanToXml(bean, rootName);
		return xmlHead + xml;
	}

	/**
	 * 将XML格式字符串转换为Bean对象
	 * 
	 * @param xml
	 *            XML字符串，有根节点
	 * @param bean
	 * @return
	 */
	public static Object fromXml(String xml, Class cls) {
		if (null == cls || StringUtils.isBlank(xml)) {
			return null;
		}
		XStream xStream = new XStream(new DomDriver());
		int beginIndex = xml.indexOf("<");
		int endIndex = xml.indexOf(">");
		if (xml.startsWith("<?xml")) {
			xml = xml.substring(endIndex + 1);
			beginIndex = xml.indexOf("<");
			endIndex = xml.indexOf(">");
		}
		// 取得根节点名称
		String firstNode = xml.substring(beginIndex + 1, endIndex);
		// 将根节点作为Bean的别名
		xStream.alias(firstNode, cls);

		return xStream.fromXML(xml);
	}

	/**
	 * 在给定的元素中搜索指定的元素,返回符合条件的元素数组.对于不同级别的同名元素限制作用,即可以
	 * 搜索元素A中的子元素C.而对于元素B中子元素C则过虑,通过多级限定可以准确定位.
	 * 
	 * @param parentElementString
	 *            String
	 * @param tagName
	 *            String
	 * @return String[]
	 */
	public static String[] getElementsByTag(String parentElementString,
			String tagName) {
		Pattern p = Pattern.compile("<" + tagName + "[^>]*?((>.*?</" + tagName
				+ ">)|(/>))");
		Matcher m = p.matcher(parentElementString);
		ArrayList<String> al = new ArrayList<String>();
		while (m.find())
			al.add(m.group());
		String[] arr = al.toArray(new String[al.size()]);
		al.clear();
		return arr;
	}

	public static Map<String, String> getParameter(String parameterName, String str) {
		Map<String, String> ret = new HashMap<String, String>();
		try {
			String id = null;
			String s = "<" + parameterName + ">([\\d]*)</" + parameterName
					+ ">";
			Pattern p = Pattern.compile(s);
			Matcher m = p.matcher(str);
			while (m.find()) {
				id = m.group().replaceAll("<" + parameterName + ">", "")
						.replaceAll("</" + parameterName + ">", "");
				ret.put(id, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;

	}

	public static String getPara(String parameterName, String str) {
		String s = "<" + parameterName + ">([\\d\\D]*)</" + parameterName + ">";
		Pattern pa = Pattern.compile(s);
		Matcher ma = pa.matcher(str);
		while (ma.find()) {
			return ma.group().substring(parameterName.length() + 2,
					ma.group().length() - parameterName.length() - 3);
		}
		return "";
	}

	/**
	 * 获取指定元素的文本内容
	 * 
	 * @param elementString
	 *            String
	 * @return String
	 */
	public static String getElementText(String elementString) {
		Pattern p = Pattern.compile(">([^<>]*)<");
		Matcher m = p.matcher(elementString);
		if (m.find()) {
			return m.group(1);
		}
		return "";
	}

	public static String getAttribute(String elementString, String attributeName) {
		Pattern p = Pattern.compile("<[^>]+>");
		Matcher m = p.matcher(elementString);
		String tmp = m.find() ? m.group() : "";
		p = Pattern.compile("(\\w+)\\s*=\\s*\"([^\"]+)\"");
		m = p.matcher(tmp);
		while (m.find()) {
			if (m.group(1).trim().equals(attributeName)) {
				return m.group(2).trim();
			}
		}
		return "";
	}

}
