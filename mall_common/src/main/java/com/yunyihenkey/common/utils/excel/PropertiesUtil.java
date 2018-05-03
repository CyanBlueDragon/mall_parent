package com.yunyihenkey.common.utils.excel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {

	private static Properties properties = new Properties();
	private static Log LOGGER = LogFactory.getLog(PropertiesUtil.class);
	private static InputStream inputStream = null;

	private PropertiesUtil() {
	}

	static {
		try {
			loadFile("webservice.properties");
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public static void loadFile(String filename) throws IOException {
		try {
			inputStream = PropertiesUtil.class.getResourceAsStream("/" + filename);
			BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			properties.load(bf);
			inputStream.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			if (inputStream != null) {
				inputStream.close();
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	/* public static void main(String[] args) { System.out.println(PropertiesUtil.getProperty("redis0.host")); } */
}
