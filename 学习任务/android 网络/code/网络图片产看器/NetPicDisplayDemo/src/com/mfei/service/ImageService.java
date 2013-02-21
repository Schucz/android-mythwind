package com.mfei.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.mfei.utils.StreamTool;

public class ImageService {

	/**
	 *  ��ȡ����ͼƬ����
	 * @param path
	 * @return
	 * @throws Exception 
	 */
	public static byte[] getNetImage(String path) throws Exception {
		URL url = new URL(path);
		// ���� HTTP Э������Ӷ���
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(5000);
		connection.setRequestMethod("GET");
		if(connection.getResponseCode() == 200) {
			InputStream in = connection.getInputStream();
			return StreamTool.read(in);
		}
		return null;
	}

}
