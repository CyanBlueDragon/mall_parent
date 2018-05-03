package com.yunyihenkey.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

public class CertUtil {
	private static final Logger LOGGER = LogManager.getLogger(CertUtil.class.getName());

	public static final String KEY_STORE = "JKS";
	public static final String X509 = "X.509";
	public static final String PKCS12 = "PKCS12";
	public static final String CERTYPE_JKS = "JKS";
	public static final String CERTYPE_PFX = "PFX";
	public static final String CERTYPE_CER = "CER";
	private static final String DATE_FORMAT_PATTEN = "yyyy-MM-dd";



	/**
	 * 通过CER文件提取公钥
	 * 
	 * @param certFile
	 * @return
	 * @throws CertificateException
	 * @throws IOException 
	 */
	public static PublicKey getPublicKey(String certFile) throws CertificateException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance(X509);
		
		org.springframework.core.io.Resource fileRource = new ClassPathResource(certFile); 
		
		Certificate cert = cf.generateCertificate(fileRource.getInputStream());
		return cert.getPublicKey();
	}

	/**
	 * 从pfx文件中获取公钥或私钥
	 * 
	 * @param pfxFile
	 * @param keystore_password
	 * @param isPrivateKey true私钥 false公钥
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 */
	public static Key getKey(String pfxFile, String fileType, String keystore_password, boolean isPrivateKey)
			throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, UnrecoverableKeyException {
		KeyStore inputKeyStore = KeyStore.getInstance(getKSType(fileType));
		org.springframework.core.io.Resource fileRource = new ClassPathResource(pfxFile);
		InputStream fis = fileRource.getInputStream();
		char[] nPassword = keystore_password.toCharArray();
		inputKeyStore.load(fis, nPassword);
		fis.close();

		Enumeration<String> enumas = inputKeyStore.aliases();
		String keyAlias = null;
		if (enumas.hasMoreElements()) {
			keyAlias = enumas.nextElement();
		}

		if (isPrivateKey) {
			return inputKeyStore.getKey(keyAlias, nPassword);
		}
		Certificate cert = inputKeyStore.getCertificate(keyAlias);
		return cert.getPublicKey();
	}

	public static String getKSType(String fileType) {
		if (CERTYPE_JKS.equalsIgnoreCase(fileType)) {
			return KEY_STORE;
		}
		return PKCS12;
	}

	/**
	 * 加载 CertificateConfigVO中的公私钥
	 * 
	 * @param certificateConfigVO
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 */
//	public static void loadCertificateConfigVO(CertificateConfigVO certificateConfigVO) {
//		if (certificateConfigVO == null) {
//			return;
//		}
//		try {
//
//			PrivateKey epayPrivateKey = (PrivateKey) getKey(PropertityUtil.getPath(certificateConfigVO.getEpay_cer_name()),
//					certificateConfigVO.getEpay_cer_type(), certificateConfigVO.getEpay_cer_password(), true);
//			PublicKey epayPublicKey = (PublicKey) getKey(PropertityUtil.getPath(certificateConfigVO.getEpay_cer_name()), certificateConfigVO.getEpay_cer_type(),
//					certificateConfigVO.getEpay_cer_password(), false);
//			certificateConfigVO.setEpayPrivateKey(epayPrivateKey);
//			certificateConfigVO.setEpayPublicKey(epayPublicKey);
//			certificateConfigVO.setSysPublicKey(getPublicKey(PropertityUtil.getPath(certificateConfigVO.getSys_cer_url())));
//		} catch (Exception e) {
//			LOGGER.error("certificateConfigVO load err id = {}", certificateConfigVO.getCertificate_config_id());
//			LOGGER.error("loadCertificateConfigVO err", e);
//		}
//	}

	public static void printValidateDate(String keyStorePath, String alias, String password) throws Exception {
		Certificate ceof = getCertificate(keyStorePath, alias, password);
		X509Certificate t = (X509Certificate) ceof;
		LOGGER.debug("开始：" + DateFormatUtils.format(t.getNotAfter(), DATE_FORMAT_PATTEN));
		LOGGER.debug("结束:" + DateFormatUtils.format(t.getNotBefore(), DATE_FORMAT_PATTEN));
	}

	public static void printValidateDate(String certificatePath) throws Exception {
		Certificate ceof = getCertificate(certificatePath);
		X509Certificate t = (X509Certificate) ceof;
		LOGGER.debug("开始：" + DateFormatUtils.format(t.getNotAfter(), DATE_FORMAT_PATTEN));
		LOGGER.debug("结束:" + DateFormatUtils.format(t.getNotBefore(), DATE_FORMAT_PATTEN));
	}

	/**
	 * 获得Certificate
	 * 
	 * @param keyStorePath
	 * @param keyStorePassword
	 * @param alias
	 * @return
	 * @throws Exception
	 */
	private static Certificate getCertificate(String keyStorePath, String keyStorePassword, String alias) throws Exception {
		KeyStore ks = getKeyStore(keyStorePath, keyStorePassword);
		return ks.getCertificate(alias);
	}

	private static Certificate getCertificate(String certificatePath) throws Exception {
		CertificateFactory certificateFactory = CertificateFactory.getInstance(X509);
		FileInputStream in = new FileInputStream(certificatePath);

		Certificate certificate = certificateFactory.generateCertificate(in);
		in.close();

		return certificate;
	}

	/**
	 * 获得KeyStore
	 * 
	 * @param keyStorePath
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
		FileInputStream is = new FileInputStream(keyStorePath);
		KeyStore ks = KeyStore.getInstance(KEY_STORE);
		ks.load(is, password.toCharArray());
		is.close();
		return ks;
	}



}
