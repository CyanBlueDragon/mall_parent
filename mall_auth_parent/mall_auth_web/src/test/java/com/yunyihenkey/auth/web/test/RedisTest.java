package com.yunyihenkey.auth.web.test;

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

		boolean set = redisUtil.set("DmSequenceUtil:namejjj", "你好大猪头");
		System.out.println("debug...............set=" + redisUtil.get("DmSequenceUtil:namejjj"));

	}

}
