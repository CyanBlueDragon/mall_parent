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

		boolean set = redisUtil.set("DmSequenceUtil:name", "测试set方法");
		
		Object o= redisUtil.get("DmSequenceUtil:name");
		LogUtils.getLogger().info("debug...............set=" + o);
		
		System.out.println(o.getClass());
		System.out.println(32222);
	}

}
