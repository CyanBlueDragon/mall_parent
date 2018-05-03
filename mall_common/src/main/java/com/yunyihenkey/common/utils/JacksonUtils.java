package com.yunyihenkey.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @desc 采用fastxml 不使用老的codehaus
 */
public class JacksonUtils {
	/**
	 * final ObjectMapper mapper = new ObjectMapper(); // can use static singleton,
	 * inject: just make sure to reuse!
	 */
	public static final ObjectMapper objectMapper = new ObjectMapper();

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
		Aaa aaa = new Aaa("hello", 666);

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
		}

	}

	public static class Aaa {
		private String a;
		private Integer b;

		public Aaa() {
			super();
		}

		public Aaa(String a, Integer b) {
			super();
			this.a = a;
			this.b = b;
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

	}
}
