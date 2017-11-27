package com.emat.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.emat.entity.Couchbase;
import com.emat.entity.SystemInfo;
import com.emat.util.CommonUtils;

/**
 * <B>常用处理方法</B><br>
 * 
 * @author 
 * @since
 * 
 */
public class CommonMethod {

	/**
	 * 获取系统基本信息
	 * 
	 * @return
	 */
	public static SystemInfo getSystemInfo() {

		SystemInfo si = CommonProperty.systemInfo;

		if(null == si){
			si = new SystemInfo();
			
			String buckets = ConfigFile.getProfileString(CommonProperty.CONFIG_FILE,
					"couchbase", "buckets", "");
			if(StringUtils.isBlank(buckets)){
				throw new RuntimeException("请设置buckets");
			}
			CommonProperty.bucketList = Arrays.asList(buckets.split(","));
			
			Couchbase couchbase= new Couchbase();
			
			couchbase.setUrl(ConfigFile.getProfileString(
					CommonProperty.CONFIG_FILE, "couchbase", "url", ""));
			couchbase.setPassword(ConfigFile.getProfileString(CommonProperty.CONFIG_FILE,
					"couchbase", "password", ""));
			si.setCouchbase(couchbase);
			
			
			CommonProperty.systemInfo = si;
			
		}
		
		return si;

	}

	/**
	 * hasmMap to list
	 * 
	 * @param hs
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<String> hashMapToList(HashMap<String, String> hs) {

		List<String> list = new ArrayList<String>();

		Iterator it = hs.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) it.next();

			list.add(entry.getValue());
		}

		return list;

	}

	/**
	 * <B>去除连续空格</B>
	 * 
	 * @param str
	 * @return String
	 */
	public static String clearBlank(String str) {
		String key = "", tempstr = "";
		boolean state = false;

		for (int i = 0; i < str.length(); i++) {
			tempstr = str.substring(i, i + 1);
			if (tempstr.equals(" ") || tempstr.equals("%20")) {
				if (!state) {
					state = true;
				} else {
					tempstr = "";
				}
			} else {
				state = false;
			}
			key += tempstr;
		}
		return key;
	}

	/**
	 * 格式化多个搜索关键词
	 * 按照搜索语法拼接
	 * @param str
	 * @return
	 */
	public static String formatSearchWord(String str){
		String qt = "";
		
		if(!CommonUtils.isEmpty(str)){
			str = str.replace(",", " ");
			str = str.replace("，", " ");
			String[] array = StringUtils.split(str, " ");
			if(array.length >0){
				int index = 0;
				for(int i=0; i<array.length; i++){
					index++;
					if(index < array.length){
						qt += array[i] + " OR ";
					}else{
						qt += array[i];
					}
				}
			}else{
				qt = str;
			}
		}
		return qt;
	}
	
	 public static String passerDreTitile(String str, int strLength)
	   {
	     if (str == null) {
	       str = "";
	     }
	     str = str.replace("<br>", "");
	     str = str.replace("<BR>", "");
	     str = str.replace("<br />", "");
	     str = str.replace("<BR />", "");
	     str = str.replace("\"", "&quot;");
	     str = str.replace("<", "&lt;");
	     str = str.replace(">", "&gt;");
	 
	     str = str.replace("&lt;font color=red&gt;", "<font color=red>");
	     str = str.replace("&lt;/font&gt;", "</font>");
	 
	     if (str.indexOf("<font color=red>") != -1)
	     {
	       String removeredstr = str.replace("<font color=red>", "");
	       removeredstr = removeredstr.replace("</font>", "");
	 
	       if (removeredstr.length() > strLength)
	       {
	         String tempstr = "";
	         int tmp = 0;
	         List<String> list = new ArrayList<String>();
	 
	         tmp = str.indexOf("<font color=red>");
	         while (tmp != -1) {
	           if (tmp != -1) {
	             tempstr = str.substring(str.indexOf("<font color=red>") + 16, str.indexOf("</font>"));
	             list.add(tempstr);
	             str = str.substring(str.indexOf("</font>") + 7);
	           }
	           tmp = str.indexOf("<font color=red>");
	         }
	 
	         str = removeredstr.substring(0, strLength) + "...";
	 
	         for (String s : list) {
	           str = str.replace(s, "<font color=red>" + s + "</font>");
	         }
	       }
	 
	     }
	     else if (str.length() > strLength) {
	       str = str.substring(0, strLength) + "...";
	     }
	 
	     return str;
	   }
	 
}
