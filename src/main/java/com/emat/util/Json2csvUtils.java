package com.emat.util;

import org.json.CDL;

import org.json.JSONException;

import net.sf.json.JSONArray;

/**
 * @author DESC: 通过org.json包来实现jsonarray转换成CSV字符串
 */
public class Json2csvUtils {
	/**
	 * 
	 * @return 返回csv格式的字符串
	 * @throws JSONException
	 */
	public static String getCSVString(JSONArray jsonArray) throws JSONException {
		// 将jsonArray转换成纯字符串（涵盖所有符号）
		String jsonString = jsonArray.toString();
		// 利用字符串生成org.json.JSONArray,实现net.sf.json.jsonArray与org.json.JSONArray转换
		org.json.JSONArray orgjsonarray = new org.json.JSONArray(jsonString);
		// 利用org.json工具类生成CSV格式要求的String。
		String csv = CDL.toString(orgjsonarray);
		return csv;
	}
}