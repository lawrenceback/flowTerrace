package com.jxgis.ffireserver.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;

public class SystemConfig {
	
	public static final String IP = "220.248.209.32";
	
	public static Map<Object, Object> map = new HashMap<Object, Object>();
	public static final String PROP_FILE = "system.properties";

	public static PropertiesConfiguration propconfig = null;

	public static String get(String key) {
		return propconfig.getString(key);
	}

	public static void init() {
		try {
			propconfig = new PropertiesConfiguration(PROP_FILE);
			// 设置编码
			propconfig.setEncoding("UTF-8");
			// 设置自动冲加载机制
			propconfig.setReloadingStrategy(new FileChangedReloadingStrategy());

		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return
	 */
	public static void readProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		InputStream inputbak = null;
		try {
			input = SystemConfig.class.getClassLoader().getResourceAsStream(
					PROP_FILE);
			prop.load(input);

			Enumeration<Object> enums = prop.keys();
			while (enums.hasMoreElements()) {
				String key = (String) enums.nextElement();
				String value = prop.getProperty(key);
				map.put(key, value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();

				}
				if (inputbak != null) {
					inputbak.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public static List<Object> getServices(String key, String[] services) {

		List<Object> serviceList = new ArrayList<Object>();
		String sysService = (String) SystemConfig.get(key);
		String[] sysServices = StringUtils.split(sysService, ",");
		for (String service : services) {
			for (String sysSer : sysServices) {
				if (service != null && service.equals(sysSer)) {
					serviceList.add(service);
				}
			}
		}
		return serviceList;
	}

	public static void main(String[] args) {

		SystemConfig.init();
		System.out.println(SystemConfig.get("BMSuiteServerIp"));
	}
}
