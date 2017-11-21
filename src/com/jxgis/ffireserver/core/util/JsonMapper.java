package com.jxgis.ffireserver.core.util;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.StdSerializerProvider;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class JsonMapper {
	ObjectMapper mapper = new ObjectMapper();

	public ObjectMapper get() {
		StdSerializerProvider sp = new StdSerializerProvider();
//		sp.setNullValueSerializer(new JsonSerializer<Object>() {
//
//			@Override
//			public void serialize(Object arg0, JsonGenerator arg1,
//			                      SerializerProvider arg2) throws IOException,
//					JsonProcessingException {
//				arg1.writeString("");// null 序列化为 ""
//
//			}
//		});

		mapper.setSerializerProvider(sp);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 解析器支持解析单引号
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		// 解析器支持解析结束符
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		//mapper.getSerializationConfig().setDateFormat(sdf);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		return mapper;
	}

	public ObjectMapper get(String strFormat) {
		StdSerializerProvider sp = new StdSerializerProvider();
//		sp.setNullValueSerializer(new JsonSerializer<Object>() {
//
//			@Override
//			public void serialize(Object arg0, JsonGenerator arg1,
//			                      SerializerProvider arg2) throws IOException,
//					JsonProcessingException {
//				arg1.writeString("");// null 序列化为 ""
//
//			}
//		});

		mapper.setSerializerProvider(sp);
		// 解析器支持解析单引号
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		// 解析器支持解析结束符
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		if(StringUtil.isNotEmpty(strFormat)) {
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			mapper.getSerializationConfig().setDateFormat(sdf);
		}
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		return mapper;
	}

	public static void main(String[] args) {
		JsonMapper ob = new JsonMapper();
		ObjectMapper o = ob.get();
		Map<String, String> map = new HashMap<String, String>();
		map.put("111", "ddd");
		map.put("222", null);
		map.put("333", null);
	}
}
