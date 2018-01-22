package com.atgeretg.util.http;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.atgeretg.util.file.FileUtil;

/**
 * 这个有向外抛出异常，有个HttpClientenctUtil是没有向外抛出异常的，按需求选择，功能是一样的
 * 
 * @author atgeretg
 *
 */
public class HttpClientenctThrowsUtil {
	private static Logger logger = Logger.getLogger(HttpClientenctThrowsUtil.class);  
	private RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000).build();

	private static HttpClientenctThrowsUtil instance = null;

	private static String encode = FileUtil.UTF8;

	private HttpClientenctThrowsUtil() {
	}

	public static HttpClientenctThrowsUtil getInstance() {
		if (instance == null) {
			instance = new HttpClientenctThrowsUtil();
		}
		return instance;
	}

	/**
	 * 获取访问网络的编码格式
	 * 
	 * @return
	 */
	public String getEncode() {
		return HttpClientenctThrowsUtil.encode;
	}

	/**
	 * 设置访问网络的编码格式
	 * 
	 * @param encode
	 */
	public void setEncode(String encode) {
		HttpClientenctThrowsUtil.encode = encode;
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @throws IOException
	 * @throws ParseException
	 * @throws UnknownHostException
	 * @throws ClientProtocolException
	 */
	public String sendHttpPost(String httpUrl)
			throws ClientProtocolException, UnknownHostException, ParseException, IOException {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param params
	 *            参数(格式:key1=value1&key2=value2)
	 * @throws IOException
	 * @throws ParseException
	 * @throws UnknownHostException
	 * @throws ClientProtocolException
	 */
	public String sendHttpPost(String httpUrl, String params)
			throws ClientProtocolException, UnknownHostException, ParseException, IOException {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			StringEntity stringEntity = new StringEntity(params, encode);
			stringEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			logger.error(e);
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 * @throws IOException
	 * @throws ParseException
	 * @throws UnknownHostException
	 * @throws ClientProtocolException
	 */
	public String sendHttpPost(String httpUrl, Map<String, String> maps)
			throws ClientProtocolException, UnknownHostException, ParseException, IOException, Exception {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		// 创建参数队列
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : maps.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encode));
		} catch (Exception e) {
			throw e;
			// e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求（带文件）
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 * @param fileLists
	 *            附件
	 * @throws IOException
	 * @throws ParseException
	 * @throws UnknownHostException
	 * @throws ClientProtocolException
	 */
	public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists)
			throws ClientProtocolException, UnknownHostException, ParseException, IOException {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		for (String key : maps.keySet()) {
			meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
		}
		for (File file : fileLists) {
			FileBody fileBody = new FileBody(file);
			meBuilder.addPart("files", fileBody);
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送Post请求
	 * 
	 * @param httpPost
	 * @return
	 * @throws ClientProtocolException
	 * @throws java.net.UnknownHostException
	 *             这个异常最重要，没边上网络（主机）时
	 * @throws IOException
	 * @throws ParseException
	 */
	private String sendHttpPost(HttpPost httpPost)
			throws ClientProtocolException, java.net.UnknownHostException, IOException, ParseException {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, encode);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return responseContent;
	}

	/**
	 * 发送 get请求
	 * 
	 * @param httpUrl
	 * @throws IOException
	 * @throws ParseException
	 * @throws UnknownHostException
	 * @throws ClientProtocolException
	 */
	public String sendHttpGet(String httpUrl)
			throws ClientProtocolException, UnknownHostException, ParseException, IOException {
		HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
		return sendHttpGet(httpGet);
	}

	/**
	 * 发送 get请求Https
	 * 
	 * @param httpUrl
	 * @throws IOException
	 * @throws ParseException
	 * @throws UnknownHostException
	 * @throws ClientProtocolException
	 */
	public String sendHttpsGet(String httpUrl)
			throws ClientProtocolException, UnknownHostException, ParseException, IOException {
		HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
		return sendHttpsGet(httpGet);
	}

	/**
	 * 发送Get请求
	 * 
	 * @param httpGet
	 * @return
	 * @throws ClientProtocolException
	 * @throws java.net.UnknownHostException
	 * @throws IOException
	 * @throws ParseException
	 */
	private String sendHttpGet(HttpGet httpGet)
			throws ClientProtocolException, java.net.UnknownHostException, IOException, ParseException {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		// try {
		// 创建默认的httpClient实例.
		httpClient = HttpClients.createDefault();
		httpGet.setConfig(requestConfig);
		// 执行请求
		try {
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, encode);
		} catch (Exception e1) {
			throw e1;
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return responseContent;
	}

	/**
	 * 发送Get请求Https
	 * 
	 * @param httpGet
	 * @return
	 * @throws ClientProtocolException
	 * @throws java.net.UnknownHostException
	 * @throws IOException
	 * @throws ParseException
	 */
	private String sendHttpsGet(HttpGet httpGet)
			throws ClientProtocolException, java.net.UnknownHostException, IOException, ParseException {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader
					.load(new URL(httpGet.getURI().toString()));
			DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
			httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, encode);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return responseContent;
	}
}