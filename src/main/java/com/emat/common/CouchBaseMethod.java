package com.emat.common;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import com.couchbase.client.CouchbaseClient;
import com.emat.entity.SystemInfo;


public class CouchBaseMethod {

	/**
	 * 上下词对应系统连接
	 * @param id
	 * @param msi
	 * @return
	 */
	public static CouchbaseClient getThesaurusClient(SystemInfo si) {
		if (CommonProperty.thesaurusClient == null) {
			try {
				CommonProperty.thesaurusClient = new CouchbaseClient(Arrays.asList(URI.create(si.getThesaurusCouchbase().getUrl())), si.getThesaurusCouchbase().getBucket(),
						si.getThesaurusCouchbase().getPassword());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return CommonProperty.thesaurusClient;
	}
	
	/**
	 * couchbase客户端
	 * @param si
	 * @param index
	 * @return
	 */
	public static CouchbaseClient getCouchbaseClient(SystemInfo si, Integer index) {
		List<String> bucketList = CommonProperty.bucketList;
		String bucketName = bucketList.get(0);
		if (bucketList.size() > index) {
			bucketName = bucketList.get(index);
		}
		return getCouchbaseClient(si, bucketName);
	}
	
	/**
	 * couchbase客户端
	 * @param si
	 * @return
	 */
	public static CouchbaseClient getCouchbaseClient(SystemInfo si, String bucketName) {
		try {
			CommonProperty.couchbaseClient = new CouchbaseClient(Arrays.asList(URI.create(si.getCouchbase().getUrl())), bucketName,
					si.getCouchbase().getPassword());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return CommonProperty.couchbaseClient;
	}
	
}
