package com.yunyihenkey.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.yunyihenkey.common.constant.MallConstants;
import com.yunyihenkey.common.utils.JacksonUtils.Aaa;

/**
 * 
 * @desc xml对象互转工具，采用fastxml 不使用老的codehaus
 * @author josnow
 * @date 2018年1月10日 下午6:19:58
 * @version 1.0.0
 */
public class XmlUtils {

	public static final XmlMapper xmlMapper;

	static {
		xmlMapper = (XmlMapper) new XmlMapper().setDateFormat(new SimpleDateFormat(MallConstants.DATE_FORMAT_COMMON));
	}

	public static String writeValueAsString(Object obj) {
		if (obj == null) {
			return null;
		}
		try {
			return XmlUtils.xmlMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> T readValue(String xmlStr, Class<T> valueType) {
		try {
			return xmlMapper.readValue(xmlStr, valueType);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> T readValue(String xmlStr, TypeReference valueTypeRef) {
		try {
			return xmlMapper.readValue(xmlStr, valueTypeRef);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static void main(String[] args) {

		Aaa aaa = new Aaa("hello", 666, new Date());

		// 对象转json
		String json = writeValueAsString(aaa);
		System.out.println(json);

		// json转对象
		Aaa he = readValue(json, Aaa.class);
		System.out.println(he);

		List<Aaa> list = new ArrayList<>();
		list.add(aaa);
		list.add(aaa);
		String j = writeValueAsString(list);

		// json转集合
		List<Aaa> aaaList = readValue(j, new TypeReference<List<Aaa>>() {
		});

		System.out.println(aaaList);
		for (Aaa aaa2 : aaaList) {
			System.out.println(aaa2.getA());
			System.out.println(aaa2.getB());
			System.out.println(aaa2.getC());
		}

	}

}
