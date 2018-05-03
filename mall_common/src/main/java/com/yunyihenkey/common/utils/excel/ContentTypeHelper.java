package com.yunyihenkey.common.utils.excel;

import org.apache.commons.lang3.StringUtils;

/**
 * 处理请求头内容类型
 * @author:john zhang
 * @date:2016-03-07
 * 
 */
public final class ContentTypeHelper {
	/**
	 * 根据文件名来获取ContentType类型值
	 * 
	 * @param strFileName
	 * @return
	 */
	public static String getContentType(String strFileName) {
		String strRet = "application/octet-stream";
		if (StringUtils.isEmpty(strFileName) || strFileName.lastIndexOf('.') == -1) {
			return strRet;
		}
		strFileName = strFileName.toLowerCase();
		String strExtendName = strFileName.substring(strFileName.lastIndexOf('.'));
		if (StringUtils.isEmpty(strExtendName)) {
			return strRet;
		} else {
			strExtendName = strExtendName.toLowerCase();
		}
		if (strExtendName.equals(".*")) {
			strRet = "application/octet-stream";
		} else if (strExtendName.equals(".ws")) {
			strRet = "application/x-ws";
		} else if (strExtendName.equals(".ws2")) {
			strRet = "application/x-ws";
		} else if (strExtendName.equals(".wsc")) {
			strRet = "text/scriptlet";
		} else if (strExtendName.equals(".wsdl")) {
			strRet = "text/xml";
		} else if (strExtendName.equals(".wvx")) {
			strRet = "video/x-ms-wvx";
		} else if (strExtendName.equals(".xdp")) {
			strRet = "application/vnd.adobe.xdp";
		} else if (strExtendName.equals(".xdr")) {
			strRet = "text/xml";
		} else if (strExtendName.equals(".xfd")) {
			strRet = "application/vnd.adobe.xfd";
		} else if (strExtendName.equals(".xfdf")) {
			strRet = "application/vnd.adobe.xfdf";
		} else if (strExtendName.equals(".xhtml")) {
			strRet = "text/html";
		} else if (strExtendName.equals(".xls")) {
			strRet = "application/vnd.ms-excel";
		} else if (strExtendName.equals(".xlw")) {
			strRet = "application/x-xlw";
		} else if (strExtendName.equals(".xml")) {
			strRet = "text/xml";
		} else if (strExtendName.equals(".xpl")) {
			strRet = "audio/scpls";
		} else if (strExtendName.equals(".xq")) {
			strRet = "text/xml";
		} else if (strExtendName.equals(".xql")) {
			strRet = "text/xml";
		} else if (strExtendName.equals(".xquery")) {
			strRet = "text/xml";
		} else if (strExtendName.equals(".xsd")) {
			strRet = "text/xml";
		} else if (strExtendName.equals(".xsl")) {
			strRet = "text/xml";
		} else if (strExtendName.equals(".xslt")) {
			strRet = "text/xml";
		} else if (strExtendName.equals(".xwd")) {
			strRet = "application/x-xwd";
		} else if (strExtendName.equals(".x_b")) {
			strRet = "application/x-x_b";
		} else if (strExtendName.equals(".x_t")) {
			strRet = "application/x-x_t";
		} else if (strExtendName.equals(".docx")) { // Office 2007 Word 2007
			strRet = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		} else if (strExtendName.equals(".pptx")) {// PowerPoint 2007
			strRet = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
		} else if (strExtendName.equals(".xlsx")) { // Excel 2007
			strRet = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		} else {
			strRet = "application/octet-stream";
		}
		return strRet;
	}
}
