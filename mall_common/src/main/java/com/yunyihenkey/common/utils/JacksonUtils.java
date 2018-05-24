package com.yunyihenkey.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yunyihenkey.common.constant.MallConstants;

/**
 * 
 * @desc 采用fastxml 不使用老的codehaus
 */
public class JacksonUtils {

	/**
	 * final ObjectMapper mapper = new ObjectMapper(); // can use static singleton,
	 * inject: just make sure to reuse!
	 */
	public static final ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper().setDateFormat(new SimpleDateFormat(MallConstants.DATE_FORMAT_COMMON));

		// 防止js中Long类型丢失精度
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		objectMapper.registerModule(simpleModule);
	}

	public static String writeValueAsString(Object obj) {
		if (obj == null) {
			return null;
		}
		try {
			return JacksonUtils.objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> T readValue(String json, Class<T> valueType) {
		try {
			return objectMapper.readValue(json, valueType);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> T readValue(String json, TypeReference valueTypeRef) {
		try {
			return objectMapper.readValue(json, valueTypeRef);
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

	public static class Aaa {
		private String a;
		private Integer b;
		private Date c;

		public Aaa() {
			super();
		}

		public Aaa(String a, Integer b, Date c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public Integer getB() {
			return b;
		}

		public void setB(Integer b) {
			this.b = b;
		}

		public Date getC() {
			return c;
		}

		public void setC(Date c) {
			this.c = c;
		}

	}
}
