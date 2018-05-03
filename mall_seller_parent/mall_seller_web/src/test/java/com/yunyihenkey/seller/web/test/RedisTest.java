package com.yunyihenkey.seller.web.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yunyihenkey.Application;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.utils.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
// @ActiveProfiles("uat")
public class RedisTest {

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void test() {
		LogUtils.getLogger().info("redis::::::::::::::::::::" + redisUtil);

		boolean set = redisUtil.set("test:name", "测试set方法");
		LogUtils.getLogger().info("debug...............set=" + redisUtil.get("test:name"));

	}

}
