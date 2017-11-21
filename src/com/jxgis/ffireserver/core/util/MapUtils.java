package com.jxgis.ffireserver.core.util;

import com.jxgis.ffireserver.web.bean.TSMediaFile;
import com.jxgis.ffireserver.web.bean.TSUser;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/5.
 */
public class MapUtils extends org.apache.commons.collections.MapUtils {

	/**
	 * 将Map转换为Object
	 *
	 * @param clazz
	 *            目标对象的类
	 * @param map
	 *            待转换Map
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static <T, V> T toObject(Class<T> clazz, Map<String, V> map) throws InstantiationException, IllegalAccessException, InvocationTargetException {
		T object = clazz.newInstance();
		return toObject((Class<T>) object, map);
	}


	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;

		Object obj = beanClass.newInstance();

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			Method setter = property.getWriteMethod();
			if (setter != null) {
				setter.invoke(obj, map.get(property.getName()));
			}
		}

		return obj;
	}

	public static Object mapToObjectApp(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;

		Object obj = beanClass.newInstance();

		org.apache.commons.beanutils.BeanUtils.populate(obj, map);

		return obj;
	}

	public static Map<?, ?> objectToMap(Object obj) {
		if(obj == null)
			return null;

		return new org.apache.commons.beanutils.BeanMap(obj);
	}

	/**
	 * 遍历实体类的属性和数据类型以及属性值
	 * @param model
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Map<String, Object> reflectTest(Object model) throws NoSuchMethodException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Map<String, Object> map = new HashMap<>();
		// 获取实体类的所有属性，返回Field数组
		Field[] field = model.getClass().getDeclaredFields();
		// 遍历所有属性
		for (int j = 0; j < field.length; j++) {
			// 获取属性的名字
			String name = field[j].getName();
			// 将属性的首字符大写，方便构造get，set方法
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
			// 获取属性的类型
			String type = field[j].getGenericType().toString();
			// 如果type是类类型，则前面包含"class "，后面跟类名
			//System.out.println("属性为：" + name);
			if (type.equals("class java.lang.String")) {
				Method m = model.getClass().getMethod("get" + name);
				// 调用getter方法获取属性值
				String value = (String) m.invoke(model);
				//System.out.println("数据类型为：String");
				if (value != null) {
					//System.out.println("属性值为：" + value);
					map.put(name, value);
				} else {
					//System.out.println("属性值为：空");
					map.put(name, null);
				}
			}
			if (type.equals("class java.lang.Integer")) {
				Method m = model.getClass().getMethod("get" + name);
				Integer value = (Integer) m.invoke(model);
				//System.out.println("数据类型为：Integer");
				if (value != null) {
					//System.out.println("属性值为：" + value);
					map.put(name, value);
				} else {
					//System.out.println("属性值为：空");
					map.put(name, null);
				}
			}
			if (type.equals("class java.lang.Short")) {
				Method m = model.getClass().getMethod("get" + name);
				Short value = (Short) m.invoke(model);
				//System.out.println("数据类型为：Short");
				if (value != null) {
					//System.out.println("属性值为：" + value);
					map.put(name, value);
				} else {
					//System.out.println("属性值为：空");
					map.put(name, null);
				}
			}
			if (type.equals("class java.lang.Double")) {
				Method m = model.getClass().getMethod("get" + name);
				Double value = (Double) m.invoke(model);
				//System.out.println("数据类型为：Double");
				if (value != null) {
					//System.out.println("属性值为：" + value);
					map.put(name, value);
				} else {
					System.out.println("属性值为：空");
					map.put(name, null);
				}
			}
			if (type.equals("class java.lang.Boolean")) {
				Method m = model.getClass().getMethod("get" + name);
				Boolean value = (Boolean) m.invoke(model);
				//System.out.println("数据类型为：Boolean");
				if (value != null) {
					//System.out.println("属性值为：" + value);
					map.put(name, value);
				} else {
					//System.out.println("属性值为：空");
					map.put(name, null);
				}
			}
			if (type.equals("class java.util.Date")) {
				Method m = model.getClass().getMethod("get" + name);
				Date value = (Date) m.invoke(model);
				//System.out.println("数据类型为：Date");
				if (value != null) {
					//System.out.println("属性值为：" + value);
					map.put(name, value);
				} else {
					//System.out.println("属性值为：空");
					map.put(name, null);
				}
			}
			if (type.equals("class java.lang.Long")) {
				Method m = model.getClass().getMethod("get" + name);
				Date value = (Date) m.invoke(model);
				//System.out.println("数据类型为：Date");
				if (value != null) {
					//System.out.println("属性值为：" + value);
					map.put(name, value);
				} else {
					//System.out.println("属性值为：空");
					map.put(name, null);
				}
			}
		}
		return map;
	}

	public static void main(String[] args) throws Exception {
//		Map<String, Object> userMap = new HashMap<>();
//		userMap.put("id", 1);
//		userMap.put("longitude", "3233");
//		userMap.put("name", "1111");
//		userMap.put("password", "1111");
//		TSUser user = (TSUser)mapToObjectApp(userMap, TSUser.class);
//		System.out.println(user.toString());



	}

}
