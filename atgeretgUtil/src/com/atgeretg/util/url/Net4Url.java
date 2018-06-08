package com.atgeretg.util.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.atgeretg.util.string.StrUtil;

public class Net4Url {

	// public static void main(String[] args) {
	// Map<String, String> param = new HashMap<>();
	// param.put("num", "5");
	// param.put("page", "1");
	// param.put("pageTes", "在的&&fd好");
	// param.put("pageT好es", "在的&&fd*￥￥$$");
	// System.out.println(netPost("http://127.0.0.1:8080/circle/user/getUserInfo",
	// param));
	// }
	
	/**
	 * URL POST请求
	 * 
	 * @param urlPath
	 *            地址
	 * @param param
	 *            参数
	 * @return null | 网络数据
	 */
	public static String netPost(String urlPath, Map<String, String> param) {
		return getNetData(urlPath, param, "POST");
	}

	/**
	 * URL GET请求
	 * 
	 * @param urlPath
	 *            地址
	 * @param param
	 *            参数
	 * @return null | 网络数据
	 */
	public static String netGet(String urlPath, Map<String, String> param) {
		return getNetData(urlPath, param, "GET");
	}

	/**
	 * 获取网络数据
	 * 
	 * @param urlPath
	 *            地址
	 * @param param
	 *            参数
	 * @param method
	 *            请求方式（POST或GET）
	 * @return
	 */
	private static String getNetData(String urlPath, Map<String, String> param, String method) {
		if (StrUtil.isEmpty(method))
			return null;
		if (StrUtil.isEmpty(urlPath))
			return null;
		method = method.toUpperCase();
		HttpURLConnection url_con = null;
		String responseContent = null;
		InputStream in = null;
		BufferedReader rd = null;
		try {
			StringBuilder builder = new StringBuilder();
			if (param != null) {
				for (Map.Entry<String, String> entry : param.entrySet()) {
					builder.append(UrlUtil.getURLEncoderString(entry.getKey())).append("=")
							.append(UrlUtil.getURLEncoderString(entry.getValue())).append("&");
				}

			}
			String stringParam = builder.toString();

			if ("GET".equals(method) && !StrUtil.isEmpty(stringParam)) {// get请求直接拼接
				urlPath = StrUtil.stringBuilder(urlPath, "?", builder.toString());
			}
			System.out.println(urlPath + " method = " + method);
			URL url = new URL(urlPath);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setConnectTimeout(5000);
			url_con.setReadTimeout(15000);
			if ("POST".equals(method)) {// post请求
				url_con.setRequestMethod(method);
				url_con.setDoOutput(true);
				url_con.setDoInput(true);
				if (!StrUtil.isEmpty(stringParam)) {
					byte[] b = builder.toString().getBytes();
					url_con.getOutputStream().write(b, 0, b.length);
				}
				url_con.getOutputStream().flush();
				url_con.getOutputStream().close();
			} else {
				url_con.setRequestMethod("GET");
			}
			in = url_con.getInputStream();
			rd = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();

		} catch (IOException e) {
			e.printStackTrace();
			// logger.error("网络故障", e);//System.out.print("网络故障", e);
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
			if (rd != null) {
				try {
					rd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseContent;
	}

}
