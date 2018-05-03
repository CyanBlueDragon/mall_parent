package com.yunyihenkey.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.activation.FileTypeMap;
import javax.mail.Session;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author josnow
 * @date 2017年4月14日 下午3:34:00
 * @version 1.0.0
 * @desc 邮件发件人配置
 */
public class EmailConfig {

	private static final ConcurrentHashMap<String, JavaMailSender> javaMailSenderMap = new ConcurrentHashMap<>();
	private static final String defaultKey = "";

	private Properties javaMailProperties;
	private Session session;
	private String protocol;
	private String host;
	private Integer port;
	private String username;
	private String password;
	private String defaultEncoding;
	private FileTypeMap defaultFileTypeMap;

	public EmailConfig() {
		super();
	}

	public EmailConfig(String host, String username, String password) {
		super();
		this.host = host;
		this.username = username;
		this.password = password;
	}

	public EmailConfig(String host, String username, String password, Properties javaMailProperties) {
		super();
		this.host = host;
		this.username = username;
		this.password = password;
		this.javaMailProperties = javaMailProperties;
	}

	public Properties getJavaMailProperties() {
		return javaMailProperties;
	}

	public void setJavaMailProperties(Properties javaMailProperties) {
		this.javaMailProperties = javaMailProperties;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDefaultEncoding() {
		return defaultEncoding;
	}

	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}

	public FileTypeMap getDefaultFileTypeMap() {
		return defaultFileTypeMap;
	}

	public void setDefaultFileTypeMap(FileTypeMap defaultFileTypeMap) {
		this.defaultFileTypeMap = defaultFileTypeMap;
	}

	/**
	 * 
	 * @desc 新建发件服务器
	 * @auth josnow
	 * @date 2017年4月14日 下午4:16:35
	 * @param emailSenderConfig
	 *            配置
	 * @return 创建成功的发件服务器
	 */
	public static JavaMailSender createMailSender(EmailConfig emailSenderConfig) {
		return createMailSender(defaultKey, emailSenderConfig);
	}

	/**
	 * 
	 * @desc 新建发件服务器
	 * @auth josnow
	 * @date 2017年4月14日 下午4:17:38
	 * @param keyName
	 *            发件服务器名
	 * @param emailSenderConfig
	 *            配置
	 * @return 创建成功的发件服务器
	 */
	public static JavaMailSender createMailSender(String keyName, EmailConfig emailSenderConfig) {
		if (emailSenderConfig == null) {
			throw new IllegalArgumentException("发件服务器配置不能为空!");
		}
		if (javaMailSenderMap.containsKey(keyName)) {
			throw new IllegalArgumentException("已存在的发件服务器名!");
		}

		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();

		if (StringUtils.isNotEmpty(emailSenderConfig.getDefaultEncoding())) {
			javaMailSenderImpl.setDefaultEncoding(emailSenderConfig.getDefaultEncoding());
		}

		if (emailSenderConfig.getDefaultFileTypeMap() != null) {
			javaMailSenderImpl.setDefaultFileTypeMap(emailSenderConfig.getDefaultFileTypeMap());
		}

		if (StringUtils.isNotEmpty(emailSenderConfig.getHost())) {
			javaMailSenderImpl.setHost(emailSenderConfig.getHost());
		}

		if (emailSenderConfig.getJavaMailProperties() != null) {
			javaMailSenderImpl.setJavaMailProperties(emailSenderConfig.getJavaMailProperties());
		}

		if (StringUtils.isNotEmpty(emailSenderConfig.getPassword())) {
			javaMailSenderImpl.setPassword(emailSenderConfig.getPassword());
		}

		if (emailSenderConfig.getPort() != null) {
			javaMailSenderImpl.setPort(emailSenderConfig.getPort());
		}

		if (StringUtils.isNotEmpty(emailSenderConfig.getProtocol())) {
			javaMailSenderImpl.setProtocol(emailSenderConfig.getProtocol());
		}

		if (emailSenderConfig.getSession() != null) {
			javaMailSenderImpl.setSession(emailSenderConfig.getSession());
		}

		if (StringUtils.isNotEmpty(emailSenderConfig.getUsername())) {
			javaMailSenderImpl.setUsername(emailSenderConfig.getUsername());
		}

		javaMailSenderMap.put(keyName, javaMailSenderImpl);

		return javaMailSenderImpl;
	}

	/**
	 * 
	 * @desc 获取默认发件服务器
	 * @auth josnow
	 * @date 2017年4月14日 下午4:37:55
	 * @return JavaMailSender
	 */
	public static JavaMailSender getMailSender() {
		return javaMailSenderMap.get(defaultKey);
	}

	/**
	 * 
	 * @desc 获取发件服务器
	 * @auth josnow
	 * @date 2017年4月14日 下午4:39:19
	 * @param keyName
	 *            发件服务器名
	 * @return JavaMailSender
	 */
	public static JavaMailSender getMailSender(String keyName) {
		return javaMailSenderMap.get(keyName);
	}

	/**
	 * 
	 * @desc 移除默认发件服务器
	 * @auth josnow
	 * @date 2017年4月14日 下午4:40:03
	 */
	public static void removeMailSender() {
		javaMailSenderMap.remove(defaultKey);
	}

	/**
	 * 
	 * @desc 移除发件服务器
	 * @auth josnow
	 * @date 2017年4月14日 下午4:40:28
	 * @param keyName
	 *            发件服务器名
	 */
	public static void removeMailSender(String keyName) {
		javaMailSenderMap.remove(keyName);
	}

}
