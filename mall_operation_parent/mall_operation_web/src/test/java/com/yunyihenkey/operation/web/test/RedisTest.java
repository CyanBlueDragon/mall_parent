package com.yunyihenkey.operation.web.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yunyihenkey.Application;
import com.yunyihenkey.common.utils.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
// @ActiveProfiles("uat")
public class RedisTest {

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void test() {
		System.out.println("redis::::::::::::::::::::" + redisUtil);

		boolean set = redisUtil.set("DmSequenceUtil:name", "测试set方法");
		System.out.println("debug...............set=" + redisUtil.get("DmSequenceUtil:name"));

	}

}
