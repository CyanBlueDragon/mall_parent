//package com.yunyihenkey.utils.excel;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.URL;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.poi.ss.usermodel.Workbook;
//
//public class DownloadHelper {
//
//	private static final Logger log = LogManager.getLogger();
//
//	/**
//	 * 输入压缩后的文件流
//	 * 
//	 * @param zos
//	 * @param fileName
//	 * @throws IOException
//	 */
//	public static void putNextZipEntry(ZipOutputStream zos, String fileName) throws IOException {
//		ZipEntry ze = new ZipEntry(fileName);
//		zos.putNextEntry(ze);
//	}
//
//	/**
//	 * 输出文件流
//	 * @param out
//	 * @param attachment
//	 * @throws IOException
//	 */
//	public static void write(OutputStream out, String fileName) throws IOException {
//		byte[] db = new byte[1024];
//		BufferedInputStream bis = null;
//		try {
//			bis = new BufferedInputStream(new FileInputStream(fileName));
//			int s = -1;
//			while ((s = bis.read(db)) != -1) {
//				out.write(db, 0, s);
//			}
//		} finally {
//			if (bis != null) {
//				try {
//					bis.close();
//				} catch (IOException e) {
//				}
//			}
//		}
//	}
//
//	/**
//	 * 输出文件流
//	 * @param out
//	 * @param attachment
//	 * @throws IOException
//	 */
//	public static void write(Workbook wb, String fileName) {
//		FileOutputStream fileOutputStream = null;
//		try {
//			fileOutputStream = new FileOutputStream(fileName);
//			wb.write(fileOutputStream);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (fileOutputStream != null) {
//				try {
//
//					fileOutputStream.close();
//				} catch (IOException e) {
//				}
//			}
//		}
//	}
//
//	/**
//	 * 下载文件
//	 * @param fileName 文件名包括文件路径的，例：e:\xx\xx\xx\123.jpg
//	 * @param defaultFile 默认文件
//	 * @param response
//	 * @throws IOException
//	 */
//	public static void downloadFile(String fileName, String defaultFile, String contentType, HttpServletResponse response) throws IOException {
//		InputStream ins = null;// 文件输入流
//		String name = null;// 文件名
//		String file = null;// 将要下载的文件
//		try {
//			if (StringUtils.isNotEmpty(fileName) || StringUtils.isNotEmpty(defaultFile)) {
//				// 截取文件名
//				fileName = fileName.replace('\\', '/');
//				name = fileName.substring(fileName.lastIndexOf("/") + 1);
//				file = fileName;
//				// 获取文件输入流
//				ins = getInputStream(fileName);
//				if (ins == null) {
//					log.info("cmd=DownloadHelper:downloadFile | msg=file is null try to download default file...");
//					// 尝试下载默认文件
//					// 截取文件名
//					if (StringUtils.isNotEmpty(defaultFile)) {
//						defaultFile = defaultFile.replace('\\', '/');
//						name = defaultFile.substring(defaultFile.lastIndexOf("/") + 1);
//						file = defaultFile;
//						ins = getInputStream(defaultFile);
//					}
//				}
//			}
//
//			// 开始下载
//			if (ins != null) {
//
//				name = new String(name.getBytes(), "UTF-8");
//				byte[] buf = new byte[1024];
//				int len = 0;
//				BufferedInputStream br = null;
//				OutputStream ut = null;
//				response.reset();// 必须加，不然保存不了临时文件
//				response.setContentType(contentType);
//				response.addHeader("Content-Disposition", "attachment; filename=" + name);
//				br = new BufferedInputStream(ins);
//				ut = response.getOutputStream();
//				while ((len = br.read(buf)) != -1) {
//					ut.write(buf, 0, len);
//					ut.flush();
//				}
//				log.info("cmd=DownloadHelper:downloadFile | result=OK | msg=downloadFile success | fileName=" + file);
//			} else {
//				log.info("cmd=DownloadHelper:downloadFile | result=FAIL | msg=inputstream is null");
//			}
//		} catch (Exception ex) {
//			log.error("cmd=DownloadHelper:downloadFile | result=FAIL | msg=downloadFile failed | fileName=" + fileName, ex);
//		} finally {
//			if (ins != null)
//				ins.close();
//		}
//	}
//
//	/**
//	 * 下载文件
//	 * @param wb
//	 * @param fileName
//	 * @param contentType
//	 * @param response
//	 * @throws IOException
//	 */
//	public static void downloadFile(Workbook wb, String fileName, String contentType, HttpServletResponse response) throws IOException {
//		try {
//			fileName = new String(fileName.getBytes(), "UTF-8");
//			response.reset();// 必须加，不然保存不了临时文件
//			response.setContentType(contentType);
//			response.addHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
//			OutputStream ut = response.getOutputStream();
//			wb.write(ut);
//		} catch (Exception ex) {
//			log.error("cmd=DownloadHelper:downloadFile | result=FAIL | msg=downloadFile failed | fileName=" + fileName, ex);
//		}
//	}
//
//	/**
//	 * 下载文件
//	 * @param response
//	 * @param content
//	 * @param fileName
//	 * @param contentType
//	 * @throws IOException
//	 */
//	public static void downloadFile(HttpServletResponse response, String content, String fileName, String contentType) throws IOException {
//		OutputStream ut = null;
//		try {
//			fileName = new String(fileName.getBytes(), "UTF-8");
//			response.reset();// 必须加，不然保存不了临时文件
//			response.setContentType(contentType);
//			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
//			ut = response.getOutputStream();
//			ut.write(content.getBytes("UTF-8"));
//		} catch (Exception ex) {
//			log.error("cmd=DownloadHelper:downloadFile | result=FAIL | msg=downloadFile failed | fileName=" + fileName, ex);
//		}
//	}
//
//	/**
//	 * 获取文件输入流
//	 * @param file 文件名称(包括路径)
//	 * @return
//	 */
//	public static InputStream getInputStream(String file) {
//		InputStream ins = null;
//		if (StringUtils.isNotEmpty(file)) {
//			try {
//				if (file.startsWith("http://")) { // 如果是网络文件
//					log.info("cmd=getInputStream | msg=get file inputstream from network starting...");
//					try {
//						URL url = new URL(file);
//						ins = url.openStream();
//					} catch (Exception ex) {
//						log.error("cmd=DownloadHelper:downloadFile| result=FAIL | msg=net error", ex);
//					}
//				} else {
//					// 非网络文件
//					File f = new File(file);
//					if (f.exists()) {
//						ins = new FileInputStream(f);
//					} else {
//						log.info("cmd=DownloadHelper:downloadFile | result=FAIL | msg=file from filesystem,get null return | file=" + file);
//					}
//				}
//			} catch (Exception ex) {
//				log.info("cmd=DownloadHelper:downloadFile | result=FAIL | msg=get inputstream error | file=" + file);
//			}
//		} else {
//			log.info("cmd=DownloadHelper:downloadFile | result=FAIL | msg=get inputstream error,file is null | file=" + file);
//		}
//		return ins;
//
//	}
//
//	/**
//	 * 格式化下载文件的文件名
//	 * 
//	 * @param s
//	 * @return
//	 */
//	public static String formatDownloadFileName(String s) {
//		if (s == null || "".equals(s))
//			return s;
//		s = s.replaceAll("[\\/\\*\\?\\:\\\\\"\\|\\<\\>]+", "_");
//		// s = s.replaceAll("...", "");
//		return s;
//	}
//}
