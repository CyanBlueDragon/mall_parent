package com.yunyihenkey.seller.web.test.validator;

import javax.validation.groups.Default;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yunyihenkey.Application;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetTakeCashLogListParam;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
// @ActiveProfiles("uat")
public class ValidatorUtilsTest {

	@Autowired
	private ValidatorUtils validatorUtils;

	@Test
	public void test() {

		System.out.println(validatorUtils.active + ":::::::::::::::::::::::" + validatorUtils);
		System.out.println("OKKKKKKKK");

		GetTakeCashLogListParam a = new GetTakeCashLogListParam();
		a.setPageNum(0);
		a.setPageSize(0);
		String validate = validatorUtils.validateAndGetErrorInfo(a,
				Default.class);

		System.out.println(validate);

	}

}
