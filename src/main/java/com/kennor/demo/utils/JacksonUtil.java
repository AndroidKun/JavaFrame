package com.kennor.demo.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.text.SimpleDateFormat;


public class JacksonUtil {

//	private static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
	/**
	 * 将对象转换为json字符串
	 * @return
	 */
	public static String beanToJson(Object obj){
		return beanToJson(obj,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 将对象转换为json字符串
	 * @return
	 */
	public static String beanToJson(Object obj,String fmt){
		String responseBody = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			//非空
			mapper.setSerializationInclusion(Include.NON_NULL); 
			SimpleDateFormat format = new SimpleDateFormat(fmt);  
			mapper.setDateFormat(format);  
			responseBody = mapper.writeValueAsString(obj);
		/*	JSONObject json = new JSONObject();
			json.put("json", responseBody);
			responseBody = json.toString();*/
//			logger.info(responseBody);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseBody;
	}
	
	 public static String beanToJson(Object obj,JsonSerializer jsonSerializer){
		String responseBody = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			//非空
		/*	mapper.setSerializationInclusion(Include.NON_NULL); */
			SimpleModule module = new SimpleModule();  
			module.addSerializer(jsonSerializer);
			mapper.registerModule(module);
			responseBody = mapper.writeValueAsString(obj);
		/*	JSONObject json = new JSONObject();
			json.put("json", responseBody);
			responseBody = json.toString();*/
//			logger.info(responseBody);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseBody;
	}
	
	/**
	 * 将json字符串转换为对象
	 * @return
	 */
	public static Object jsonToBean(String json, Object obj,Boolean webChat){
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL); 
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			mapper.setDateFormat(format);  
			obj = mapper.readValue(json, new TypeReference<Object>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
