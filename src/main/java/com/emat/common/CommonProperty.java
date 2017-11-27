package com.emat.common;

import java.util.ArrayList;
import java.util.List;

import com.couchbase.client.CouchbaseClient;
import com.emat.entity.SystemInfo;

/**
 * <B>公共变量属性</B><br>
 * 
 * @author 
 * @since 
 * 
 */
public class CommonProperty {
	
	public static String SEARCHFIELD = "title";

	/**
	 * 文件路径
	 */
	public static String FILE_PATH = (CommonProperty.class.getResource("/").toString().substring(5));
	/**
	 * 主配置文件名称
	 */
	public static String CONFIG_FILE = "config.properties";
	/**
	 * 配置文件编码,默认为UTF-8 针对所有配置文件生效
	 */
	public static String configFileEncoding = "UTF-8";
	/**
	 * 系统配置信息
	 */
	public static SystemInfo systemInfo = null;
	

	/**
	 * 用来存放上下词内存库对象
	 */
	public static CouchbaseClient thesaurusClient;
	
	/**
	 * 通用CouchbaseClient客户端
	 */
	public static CouchbaseClient couchbaseClient;
	
	/**
	 * bucket(list集合)
	 */
	public static List<String> bucketList = new ArrayList<String>();
	
}
