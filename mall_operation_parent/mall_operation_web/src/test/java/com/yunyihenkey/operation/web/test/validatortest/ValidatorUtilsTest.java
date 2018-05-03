package com.yunyihenkey.operation.web.test.validatortest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yunyihenkey.Application;
import com.yunyihenkey.common.utils.ValidatorUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
// @ActiveProfiles("uat")
public class ValidatorUtilsTest {

	@Autowired
	private ValidatorUtils validatorUtils;

	@Test
	public void test() {

		System.out.println(":::::::::::::::::::::::" + validatorUtils);
		System.out.println("OKKKKKKKK");

	}

}
