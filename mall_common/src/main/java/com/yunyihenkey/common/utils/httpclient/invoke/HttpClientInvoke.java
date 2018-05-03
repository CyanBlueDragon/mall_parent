package com.yunyihenkey.common.utils.httpclient.invoke;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

public class HttpClientInvoke
{
	private static final Logger logger = LogManager.getLogger();

	/**
	 * 调用远程方法
	 * @param serviceUrl 调用方法地址
	 * @param jsonParameter 参数jason字符串
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String HttpPostJson(String serviceUrl, String jsonParameter) throws ClientProtocolException, IOException
	{
		logger.traceEntry();

		HttpPost httpPost = new HttpPost(serviceUrl);
		httpPost.addHeader("Content-type", "application/json; charset=utf-8");
		httpPost.setHeader("Accept", "application/json");
		httpPost.setEntity(new StringEntity(jsonParameter, Charset.forName("UTF-8")));
		String strEntity = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try
		{
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			try
			{
				HttpEntity entity = httpResponse.getEntity();
				logger.info("statusCode:" + httpResponse.getStatusLine().getStatusCode());
				strEntity = EntityUtils.toString(entity, "UTF-8");
			} finally
			{
				if (httpResponse != null)
				{
					httpResponse.close();
				}
			}
		} finally
		{
			if (httpClient != null)
			{
				httpClient.close();
			}
		}
//		System.out.println();
//		System.out.println("7");
		return strEntity;
	}
}
