package com.atgeretg.util.http;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UploadFile {
//	/formsInfo/insert/uploadFile/formsInfo
	
	public static void main(String[] args) {
		String httpUrl = "http://127.0.0.1:8080/factory/formsInfo/insert/uploadFile/formsInfo";
		Map<String,String> maps = new HashMap<>();
		maps.put("data", "fdfdafadsfa董遥胜");
		maps.put("id", "1");
		File file = new File("C:\\Users\\atgeretg_com\\Desktop\\5个图标.png");
		String sendHttpPost = HttpClientUtil.getInstance().sendHttpPost(httpUrl, maps,file);
		System.out.println("sendHttpPost = " + sendHttpPost);
	}

}
