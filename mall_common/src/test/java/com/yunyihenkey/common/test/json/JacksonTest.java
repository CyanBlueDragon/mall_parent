package com.yunyihenkey.common.test.json;

import com.yunyihenkey.common.utils.JacksonUtils;
import com.yunyihenkey.common.vo.base.BaseVo;

public class JacksonTest extends BaseVo {
	private String a;
	private String b;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public static void main(String[] args) {
		JacksonTest jacksonTest = new JacksonTest();
		jacksonTest.setA("nimei");
		jacksonTest.setB("dazhutou");

		String jsonStr = JacksonUtils.writeValueAsString(jacksonTest);
		System.out.println(jsonStr);

		String j = "{\"a\":\"nimei\",\"b\":\"dazhutou\",\"c\":\"dazhutou\"}";

		JacksonTest readValue = JacksonUtils.readValue(j, JacksonTest.class);

		System.out.println(readValue.getA());
		System.out.println(readValue.getB());

	}

}
