package com.emat.couchbase;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.View;
import com.couchbase.client.protocol.views.ViewResponse;
import com.couchbase.client.protocol.views.ViewRow;
import com.emat.common.CommonMethod;
import com.emat.common.CommonProperty;
import com.emat.common.CouchBaseMethod;
import com.emat.entity.SystemInfo;
import com.emat.util.CommonUtils;
import com.emat.util.Json2csvUtils;

import net.sf.json.JSONArray;

public class App {
	
	public static void main(String[] args) {
		
		SystemInfo si = CommonMethod.getSystemInfo();
		CouchbaseClient couchbaseClient = null;
		List<String> bucketList = CommonProperty.bucketList;
		for (int i = 0; i < bucketList.size(); i++) {
			couchbaseClient = CouchBaseMethod.getCouchbaseClient(si, i);
			writeBucketDocument(couchbaseClient, bucketList.get(i));
		}
		System.out.println("转换完成。。。");
	}

	private static void writeBucketDocument(CouchbaseClient couchbaseClient, String bucketName) {
		
		System.out.println(bucketName + "开始。。。");
		View view = couchbaseClient.getView(bucketName, "view_" + bucketName);
		Query query = new Query();
		ViewResponse q = couchbaseClient.query(view, query);
		Iterator<ViewRow> iterator = q.iterator();
		String readJson = "";
		JSONArray jSONArray = new JSONArray();
		while (iterator.hasNext()) {
			ViewRow vr = iterator.next();
			readJson = (String) couchbaseClient.get(vr.getId());
			if (!CommonUtils.isEmpty(readJson)) {
				jSONArray.add(readJson);
			}

		}
		File file = new File("D:/couchbase/" + bucketName + ".csv");
		String csv = Json2csvUtils.getCSVString(jSONArray);
		try {
			FileUtils.writeStringToFile(file, csv, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(bucketName + "结束。。。");
	}
}
