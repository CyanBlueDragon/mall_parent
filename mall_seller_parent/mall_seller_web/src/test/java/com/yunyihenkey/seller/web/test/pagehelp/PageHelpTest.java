package com.yunyihenkey.seller.web.test.pagehelp;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.yunyihenkey.Application;
import com.yunyihenkey.basedao.malldb.commonMapper.MallSnowflakeDatacenterMapper;
import com.yunyihenkey.common.utils.JacksonUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
// @ActiveProfiles("uat")
public class PageHelpTest {

	@Autowired
	private MallSnowflakeDatacenterMapper mallSnowflakeDatacenterMapper;

	@Test
	public void test() {
		PageHelper.startPage(1, 5);
		List<Map<String, Object>> test = mallSnowflakeDatacenterMapper.test();
		System.out.println(test.getClass());
		System.out.println(JacksonUtils.writeValueAsString(test));
	}

}
