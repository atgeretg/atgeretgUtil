package com.atgeretg.util.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
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
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.atgeretg.util.file.FileUtil;
import com.atgeretg.util.json.ali.JacksonUtil;

/**
 * 这个可以设置时间，HttpClientenctUtil是不能设置时间，按需求选择，功能是一样的
 * 
 * @author atgeretg
 *
 */
public class HttpClientUtilTime {
	private static Logger logger = Logger.getLogger(HttpClientUtilTime.class);
	private static RequestConfig requestConfig = null;
	private static HttpClientUtilTime instance = null;

	private HttpClientUtilTime() {
	}

	public static void main(String[] args) {
//		String pathGet = "http://127.0.0.1:8080/nobodyService/testOutTimeConnect1";// %25E5%2595%2586%25E5%2593%2581%25E4%25B8%258A%25E6%259E%25B61-15.jar";
		// String name = pathGet.split("download=")[1];
		// String decoderString =
		// UrlUtil.getURLDecoderString("%25E5%2595%2586%25E5%2593%2581%25E4%25B8%258A%25E6%259E%25B61-15.jar");
		// String decoderString2 = UrlUtil.getURLDecoderString(name);
//		HttpClientUtilTime.getInstance(500).httpDownload(pathGet + UrlUtil.getURLEncoderString("商品上架1-15.jar", 2),
//				"E:\\tool.jar");

//		String sendHttpGet = HttpClientUtilTime.getInstance(7000).sendHttpGet(pathGet);
//		System.out.println(sendHttpGet);
		
		 FormsInfo formsInfo = new FormsInfo();
//       formsInfo.setPhysicalPhotoPath("E://2018/192.168.1.64_01_20180103_.jpg");
//       formsInfo.setStandardPhotoPath("E://2018/192.168.1.64_01_20180103_.jpg");
       formsInfo.setBedNum("TG-656-FD656265-56235623");
       formsInfo.setCustomerName("张一蛋");
       formsInfo.setHeadSideNum("MD-656-FFD265-56235623");
       formsInfo.setLegSideNum("DF-5DFD_CG5522E5596");
       formsInfo.setInspectionDate(new Date());
       formsInfo.setInspectorName("张全蛋");
       formsInfo.setInspectorNumber("002");
       formsInfo.setProductionLine("01");
       formsInfo.setProductName("King");
       formsInfo.setManufacturingSuperviso("张二蛋");
       formsInfo.setPoNum("PP05");
       formsInfo.setProductType("张三蛋型");
       formsInfo.setQcSupervisor("张四蛋");
       formsInfo.setProductPass(1);
       File file = new File("E://2018/192.168.1.64_01_20180103161016546.jpg");
       String json = JacksonUtil.toJson(formsInfo);
       
       String httpUrl = "http://127.0.0.1:8080/factory/formsInfo/insert/uploadFile/formsInfo";
       Map<String,String> maps = new HashMap<>();
       maps.put("data", json);
       HttpClientUtilTime t = getInstance(15000);
       String sendHttpPost = t.sendHttpPost(httpUrl, maps, file);
       System.out.println("sendHttpPost = "+sendHttpPost);
		// System.out.println(decoderString + "\ndecoderString2 = " +decoderString2);
		// pathGet+"商品上架1-15.jar";
	}

	/**
	 * 设置获取数据等待时间，最小10秒，最大10分,单位：ms
	 * @param socketOutTime ms
	 * @return
	 */
	public static HttpClientUtilTime getInstance(int socketOutTime) {
		if (instance == null) {
			int outTime = 10000,max = 1000*60*10;
			if(socketOutTime > outTime)
				outTime = socketOutTime;
			if(socketOutTime > max)
				outTime = max;
			requestConfig = RequestConfig.custom().setSocketTimeout(outTime)//数据传输过程中数据包之间间隔的最大时间
					.setConnectTimeout(15000)//连接建立时间，三次握手完成时间
			.setConnectionRequestTimeout(15000)//httpclient使用连接池来管理连接，这个时间就是从连接池获取连接的超时时间，可以想象下数据库连接池
			.build();
//			System.out.println("outTime = "+outTime);
			instance = new HttpClientUtilTime();
		}
		return instance;
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 */
	public String sendHttpPost(String httpUrl) {
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
	 */
	public String sendHttpPost(String httpUrl, String params) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
			stringEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			logger.error(e);
			// e.printStackTrace();
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
	 */
	public String sendHttpPost(String httpUrl, Map<String, String> maps) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		// 创建参数队列
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : maps.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		} catch (Exception e) {
			logger.error(e);
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
	 */
	public String sendHttpPost(String httpUrl, Map<String, String> maps, File file) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		// httpPost.addHeader("Content-type","charset=utf-8");
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		meBuilder.setCharset(Charset.forName("utf-8"));
		meBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
		for (String key : maps.keySet()) {
			meBuilder.addPart(key, new StringBody(maps.get(key), contentType));
		}
		if (file != null) {
			FileBody fileBody = new FileBody(file);
			meBuilder.addPart("file", fileBody);
		}

		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
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
	 */
	public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		meBuilder.setCharset(Charset.forName("utf-8"));
		meBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
//		Image i= Image.
		for (String key : maps.keySet()) {
//			meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));//ISO-8859-1
			meBuilder.addPart(key, new StringBody(maps.get(key), contentType));
		}
		if (fileLists != null) {
			for (File file : fileLists) {
				FileBody fileBody = new FileBody(file);
				meBuilder.addPart("files", fileBody);
			}
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
	 */
	private String sendHttpPost(HttpPost httpPost) {
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
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			logger.error(e);
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
	 */
	public String sendHttpGet(String httpUrl) {
		HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
		return sendHttpGet(httpGet);
	}

	/**
	 * 发送 get请求Https
	 * 
	 * @param httpUrl
	 */
	public String sendHttpsGet(String httpUrl) {
		HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
		return sendHttpsGet(httpGet);
	}

	/**
	 * 发送Get请求
	 * 
	 * @param httpPost
	 * @return
	 */
	private String sendHttpGet(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			logger.error(e);
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
	 * @param httpPost
	 * @return
	 */
	private String sendHttpsGet(HttpGet httpGet) {
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
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			logger.error(e);
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
	 * 下载文件
	 * 
	 * @param url
	 *            网络URL
	 * @param savePath
	 *            保存文件路径（要全路径，即包括文件名，路径不存在会被创建）
	 * @return null | 保存的文件路径
	 */
	public String httpDownload(String url, String savePath) {
		
		String fileSavePath = null;
		HttpGet httpGet2 = new HttpGet(url);
		httpGet2.setConfig(requestConfig);
		OutputStream os = null;
		InputStream is = null;
		try {
			HttpResponse response = HttpClients.createDefault().execute(httpGet2);
			is = response.getEntity().getContent();
			File file = FileUtil.reNameFile(savePath);
			fileSavePath = file.getAbsolutePath();
			if (!file.exists()) {
				file.createNewFile();
			}
			os = new FileOutputStream(file);

			int read = 0;
			byte[] temp = new byte[1024 * 1024];

			while ((read = is.read(temp)) > 0) {
				byte[] bytes = new byte[read];
				System.arraycopy(temp, 0, bytes, 0, read);
				os.write(bytes);
			}
			os.flush();
			return fileSavePath;
		} catch (ClientProtocolException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
		return null;
	}
	
	
	
	public static class FormsInfo {
	    private Integer formsId;

	    private Date formsDate;

	    private String inspectorNumber;

	    private String inspectorName;

	    private String customerName;

	    private String productionLine;

	    private String poNum;

	    private String productType;

	    private String productName;

	    private String manufacturingSuperviso;

	    private String qcSupervisor;

	    private String bedNum;

	    private String headSideNum;

	    private String legSideNum;

	    private Date inspectionDate;

	    private String standardPhotoPath;

	    private String physicalPhotoPath;

	    private String pdfStandardPhotoPath;

	    private String pdfPhysicalPhotoPath;

	    private String pdfFilePath;

	    private Integer productPass;

	    private String formsRemark;


	    public String getPdfStandardPhotoPath() {
	        return pdfStandardPhotoPath;
	    }

	    public void setPdfStandardPhotoPath(String pdfStandardPhotoPath) {
	        this.pdfStandardPhotoPath = pdfStandardPhotoPath;
	    }

	    public String getPdfPhysicalPhotoPath() {
	        return pdfPhysicalPhotoPath;
	    }

	    public void setPdfPhysicalPhotoPath(String pdfPhysicalPhotoPath) {
	        this.pdfPhysicalPhotoPath = pdfPhysicalPhotoPath;
	    }

	    public Integer getFormsId() {
	        return formsId;
	    }

	    public void setFormsId(Integer formsId) {
	        this.formsId = formsId;
	    }

	    public Date getFormsDate() {
	        return formsDate;
	    }

	    public void setFormsDate(Date formsDate) {
	        this.formsDate = formsDate;
	    }

	    public String getInspectorNumber() {
	        return inspectorNumber;
	    }

	    public void setInspectorNumber(String inspectorNumber) {
	        this.inspectorNumber = inspectorNumber == null ? null : inspectorNumber.trim();
	    }

	    public String getInspectorName() {
	        return inspectorName;
	    }

	    public void setInspectorName(String inspectorName) {
	        this.inspectorName = inspectorName == null ? null : inspectorName.trim();
	    }

	    public String getCustomerName() {
	        return customerName;
	    }

	    public void setCustomerName(String customerName) {
	        this.customerName = customerName == null ? null : customerName.trim();
	    }

	    public String getProductionLine() {
	        return productionLine;
	    }

	    public void setProductionLine(String productionLine) {
	        this.productionLine = productionLine == null ? null : productionLine.trim();
	    }

	    public String getPoNum() {
	        return poNum;
	    }

	    public void setPoNum(String poNum) {
	        this.poNum = poNum == null ? null : poNum.trim();
	    }

	    public String getProductType() {
	        return productType;
	    }

	    public void setProductType(String productType) {
	        this.productType = productType == null ? null : productType.trim();
	    }

	    public String getProductName() {
	        return productName;
	    }

	    public void setProductName(String productName) {
	        this.productName = productName == null ? null : productName.trim();
	    }

	    public String getManufacturingSuperviso() {
	        return manufacturingSuperviso;
	    }

	    public void setManufacturingSuperviso(String manufacturingSuperviso) {
	        this.manufacturingSuperviso = manufacturingSuperviso == null ? null : manufacturingSuperviso.trim();
	    }

	    public String getQcSupervisor() {
	        return qcSupervisor;
	    }

	    public void setQcSupervisor(String qcSupervisor) {
	        this.qcSupervisor = qcSupervisor == null ? null : qcSupervisor.trim();
	    }

	    public String getBedNum() {
	        return bedNum;
	    }

	    public void setBedNum(String bedNum) {
	        this.bedNum = bedNum == null ? null : bedNum.trim();
	    }

	    public String getHeadSideNum() {
	        return headSideNum;
	    }

	    public void setHeadSideNum(String headSideNum) {
	        this.headSideNum = headSideNum == null ? null : headSideNum.trim();
	    }

	    public String getLegSideNum() {
	        return legSideNum;
	    }

	    public void setLegSideNum(String legSideNum) {
	        this.legSideNum = legSideNum == null ? null : legSideNum.trim();
	    }

	    public Date getInspectionDate() {
	        return inspectionDate;
	    }

	    public void setInspectionDate(Date inspectionDate) {
	        this.inspectionDate = inspectionDate;
	    }

	    public String getStandardPhotoPath() {
	        return standardPhotoPath;
	    }

	    public void setStandardPhotoPath(String standardPhotoPath) {
	        this.standardPhotoPath = standardPhotoPath == null ? null : standardPhotoPath.trim();
	    }

	    public String getPhysicalPhotoPath() {
	        return physicalPhotoPath;
	    }

	    public void setPhysicalPhotoPath(String physicalPhotoPath) {
	        this.physicalPhotoPath = physicalPhotoPath == null ? null : physicalPhotoPath.trim();
	    }

	    public String getPdfFilePath() {
	        return pdfFilePath;
	    }

	    public void setPdfFilePath(String pdfFilePath) {
	        this.pdfFilePath = pdfFilePath == null ? null : pdfFilePath.trim();
	    }

	    public Integer getProductPass() {
	        return productPass;
	    }

	    public void setProductPass(Integer productPass) {
	        this.productPass = productPass;
	    }

	    public String getFormsRemark() {
	        return formsRemark;
	    }

	    public void setFormsRemark(String formsRemark) {
	        this.formsRemark = formsRemark == null ? null : formsRemark.trim();
	    }
	}
	
	
	
	
	

}