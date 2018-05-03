package com.yunyihenkey.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtil {

	private static final Log logger = LogFactory.getLog(HttpUtil.class);

	/** 默认字符集编码 */
	private static final String DEFAULT_CHARSET = "UTF-8";

	/** 设置请求连接超时时间(设置请求超时100秒钟) */
	private static final int CONNECTION_REQUEST_TIMEOUT = 100 * 1000;

	/** 设置套接字连接超时时间(设置等待数据超时时间100秒钟) */
	private static final int SOCKET_TIMEOUT = 100 * 1000;

	/** 设置等待连接超时时间(设置等待数据超时时间100秒钟) */
	private static final int CONNECTION_TIMEOUT = 100 * 1000;

	public static void setRequestConfig(HttpPost httpPost) {
		Builder builder = RequestConfig.custom();
		builder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
		builder.setConnectTimeout(CONNECTION_TIMEOUT);
		builder.setSocketTimeout(SOCKET_TIMEOUT);
		httpPost.setConfig(builder.build());
	}

	public static String sendHttpPost(String url, Map<String, String> params) {
		return sendHttpPost(url, buildNameValuePair(params));
	}

	public static String sendHttpPost(String requestUrl, List<NameValuePair> params) {
		logger.info("=========采用POST方式发送HTTP请求开始=========");
		logger.info("=========requestUrl:" + requestUrl + "=========");
		String result = null;
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost httpPost = new HttpPost(requestUrl);
			logger.info("post message info =" + JacksonUtils.writeValueAsString(params));
			if (isNotNullList(params)) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, DEFAULT_CHARSET);
				httpPost.setEntity(entity);
			}
			setRequestConfig(httpPost);
			response = httpClient.execute(httpPost);
			result = getResult(response);
		} catch (ClientProtocolException e) {
			logger.error("=========Http通信过程中客户端通信协议错误=========", e);
		} catch (ParseException e) {
			logger.error("=========Http通信过程中数据解析错误=========", e);
		} catch (SocketTimeoutException e) {
			logger.error("=========Http通信请求超时=========", e);
		} catch (IOException e) {
			logger.error("=========Http通信过程中数据流输入输出错误=========", e);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("=========关闭HttpResponse对象过程中数据流输入输出错误=========", e);
				}
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				logger.error("=========关闭HttpClient对象过程中数据流输入输出错误=========", e);
			}
		}
		logger.info("=========采用POST方式发送HTTP请求结束=========");
		return result;
	}

	public static String sendHttpPostJson(String url, Map<String, String> params, Map<String, String> headerParams) {
		logger.info("=========采用POST方式发送HTTP请求开始=========");
		logger.info("=========requestUrl:" + url + "=========");
		String result = null;
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Content-type", "application/json; charset=UTF-8");
			if (headerParams != null && headerParams.size() > 0) {
				for (String key : params.keySet()) {
					String value = params.get(key);
					httpPost.addHeader(key, value);
				}
			}
			String requestJson = JacksonUtils.writeValueAsString(params);
			logger.info("post message info =" + requestJson);

			StringEntity se = new StringEntity(requestJson);
			se.setContentType("application/json; charset=UTF-8");

			httpPost.setEntity(se);
			setRequestConfig(httpPost);
			response = httpClient.execute(httpPost);
			result = getResult(response);
		} catch (ClientProtocolException e) {
			logger.error("=========Http通信过程中客户端通信协议错误=========", e);
		} catch (ParseException e) {
			logger.error("=========Http通信过程中数据解析错误=========", e);
		} catch (SocketTimeoutException e) {
			logger.error("=========Http通信请求超时=========", e);
		} catch (IOException e) {
			logger.error("=========Http通信过程中数据流输入输出错误=========", e);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("=========关闭HttpResponse对象过程中数据流输入输出错误=========", e);
				}
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				logger.error("=========关闭HttpClient对象过程中数据流输入输出错误=========", e);
			}
		}
		logger.info("=========采用POST方式发送HTTP请求结束=========");
		return result;
	}

	public static List<NameValuePair> buildNameValuePair(Map<String, String> params) {
		logger.info("buildNameValuePair message info =" + JacksonUtils.writeValueAsString(params));
		List<NameValuePair> pariList = new ArrayList<NameValuePair>();
		if (null != params && params.size() > 0) {
			for (String key : params.keySet()) {
				pariList.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		return pariList;
	}

	@SuppressWarnings("rawtypes")
	private static final boolean isNotNullList(List list) {
		return null != list && list.size() > 0;
	}

	public static String getResult(CloseableHttpResponse response) throws ParseException, IOException {
		String result = null;
		StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = response.getEntity();
			if (null != httpEntity) {
				result = EntityUtils.toString(response.getEntity());
				logger.info("=========请求成功，返回的信息为：" + result);
			} else {
				logger.info("=========请求成功，但没有响应信息返回=========");
			}
		} else {
			logger.error("=========请求失败，状态响应信息为：" + statusLine + "=========");
		}
		return result;
	}

	/**
	 * HttpClient连接SSL
	 */
	public static String sslRequest(String url, Map<String, String> params) {

		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		String result = null;
		logger.info("=========采用POST方式发送HTTPS请求开始=========");
		logger.info("=========requestUrl:" + url + "=========");
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}

			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

			// 创建httppost
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// 创建http请求(post方式)
			HttpPost httppost = new HttpPost(url);
			// httppost.setHeader("Content-Type", "application/json");
			setRequestConfig(httppost);
			// 创建参数队列
			if (params != null && !params.isEmpty()) {
				Set<Map.Entry<String, String>> paramsSet = params.entrySet();
				List<NameValuePair> formparams = new ArrayList<NameValuePair>();

				for (Map.Entry<String, String> entry : paramsSet) {
					formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}

				httppost.setEntity(new UrlEncodedFormEntity(formparams, DEFAULT_CHARSET));
			}

			logger.info("executing request" + httppost.getRequestLine());
			response = httpclient.execute(httppost);
			result = getResult(response);

		} catch (ClientProtocolException e) {
			logger.error("=========Http通信过程中客户端通信协议错误=========", e);
		} catch (ParseException e) {
			logger.error("=========Http通信过程中数据解析错误=========", e);
		} catch (SocketTimeoutException e) {
			logger.error("=========Http通信请求超时=========", e);
		} catch (IOException e) {
			logger.error("=========Http通信过程中数据流输入输出错误=========", e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("=========错误=========", e);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("=========关闭HttpResponse对象过程中数据流输入输出错误=========", e);
				}
			}
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("=========关闭HttpClient对象过程中数据流输入输出错误=========", e);
			}
		}
		logger.info("=========采用POST方式发送HTTP请求结束=========");

		return result;
	}

	/**
	 * HttpClient连接SSL
	 */
	public static String sslRequestJson(String url, Map<String, String> params) {

		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		String result = null;
		logger.info("=========采用POST方式发送HTTPS请求开始=========");
		logger.info("=========requestUrl:" + url + "=========");
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}

			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

			// 创建httppost
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// 创建http请求(post方式)
			HttpPost httppost = new HttpPost(url);

			httppost.addHeader("Content-type", "application/json; charset=UTF-8");
			String requestJson = JacksonUtils.writeValueAsString(params);
			logger.info("post message info =" + requestJson);

			StringEntity se = new StringEntity(requestJson);
			se.setContentType("application/json; charset=UTF-8");

			httppost.setEntity(se);
			setRequestConfig(httppost);
			// // 创建参数队列
			// if (params != null && !params.isEmpty()) {
			// Set<Map.Entry<String, String>> paramsSet = params.entrySet();
			// List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			//
			// for (Map.Entry<String, String> entry : paramsSet) {
			// formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			// }
			//
			// httppost.setEntity(new UrlEncodedFormEntity(formparams, DEFAULT_CHARSET));
			// }
			//
			// logger.info("executing request" + httppost.getRequestLine());
			response = httpclient.execute(httppost);
			result = getResult(response);

		} catch (ClientProtocolException e) {
			logger.error("=========Http通信过程中客户端通信协议错误=========", e);
		} catch (ParseException e) {
			logger.error("=========Http通信过程中数据解析错误=========", e);
		} catch (SocketTimeoutException e) {
			logger.error("=========Http通信请求超时=========", e);
		} catch (IOException e) {
			logger.error("=========Http通信过程中数据流输入输出错误=========", e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("=========错误=========", e);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("=========关闭HttpResponse对象过程中数据流输入输出错误=========", e);
				}
			}
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("=========关闭HttpClient对象过程中数据流输入输出错误=========", e);
			}
		}
		logger.info("=========采用POST方式发送HTTP请求结束=========");
		return result;
	}
}
