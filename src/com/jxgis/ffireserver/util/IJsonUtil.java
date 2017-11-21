package com.jxgis.ffireserver.util;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Json工具类
 * 
 * @author Heguiming
 * 
 */
public class IJsonUtil {
	
	public static JSONObject newJSONObject() {
		return new JSONObject();
	}
	
	public static JSONArray newJSONArray() {
		return new JSONArray();
	}
	
	public static void putObj(JSONObject jsonObj, String key, Object value) {
		try {
			jsonObj.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void putString(JSONObject jsonObj, String key, String value) {
		try {
			jsonObj.put(key, StringUtil.isNullOrEmpty(value) ? "" : value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void put(JSONArray jsonAry, Object value) {
		try {
			jsonAry.put(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void putInt(JSONObject jsonObj, String key, int value) {
		try {
			jsonObj.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void putLong(JSONObject jsonObj, String key, long value) {
		try {
			jsonObj.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void putDouble(JSONObject jsonObj, String key, double value) {
		try {
			jsonObj.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject newJSONObject(String jsonStr) {
		try {
			return new JSONObject(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getString(String key, JSONObject json) {
		try {
			if (!json.isNull(key)) {
				return json.getString(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getString(int index, JSONArray json) {
		try {
			return json.getString(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getInt(String key, JSONObject json) {
		try {
			if (!json.isNull(key)) {
				return json.getInt(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean getBoolean(String key, JSONObject json) {
		try {
			if (!json.isNull(key)) {
				return json.getBoolean(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static long getLong(String key, JSONObject json) {
		try {
			if (!json.isNull(key)) {
				return json.getLong(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	public static float getFloat(String key, JSONObject json) {
		try {
			if (!json.isNull(key)) {
				return Float.parseFloat(json.getString(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	public static double getDouble(String key, JSONObject json) {
		try {
			if (!json.isNull(key)) {
				return json.getDouble(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	public static JSONObject getJSONObject(String key, JSONObject json) {
		try {
			if (!json.isNull(key)) {
				return json.getJSONObject(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject getJSONObject(Object data) {
		try {
			if (null != data && data instanceof JSONObject) {
				return (JSONObject)data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONArray getJSONArray(Object data) {
		try {
			if (null != data && data instanceof JSONArray) {
				return (JSONArray)data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONArray getJSONArray(String key, JSONObject json) {
		try {
			if (!json.isNull(key)) {
				return json.getJSONArray(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject getJSONObject(int index, JSONArray jsonArray) {
		try {
			return jsonArray.getJSONObject(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object getObject(String key, JSONObject json) {
		try {
			if (!json.isNull(key)) {
				return json.get(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
