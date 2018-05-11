package com.yunyihenkey.common.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @desc 验证器工具
 * @author josnow
 * @date 2017年11月28日 下午2:47:33
 * @version 1.0.0
 */
@Component
public class ValidatorUtils {

	/** 验证器工厂 */
	public static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

	public @Value("${spring.profiles.active}") String active;

	/**
	 * 
	 * @desc 开始验证参数
	 * @auth wulm
	 * @date 2018年5月11日 下午12:00:14
	 * @param object
	 * @param groups
	 * @return 返回错误信息，错误信息为空表示正确，不为空表示错误
	 */
	public <T> String validateAndGetErrorInfo(T object, Class<?>... groups) {
		Validator validator = validatorFactory.getValidator();

		Set<ConstraintViolation<T>> set = validator.validate(object, groups);

		return set == null || set.isEmpty() ? null : makeErrorInfo(set);
	}

	public <T> String validatePropertyAndGetErrorInfo(T object, String propertyName, Class<?>... groups) {

		Validator validator = validatorFactory.getValidator();

		Set<ConstraintViolation<T>> set = validator.validateProperty(object, propertyName, groups);

		return set == null || set.isEmpty() ? null : makeErrorInfo(set);
	}

	public <T> String validateValueAndGetErrorInfo(Class<T> beanType, String propertyName, Object value,
			Class<?>... groups) {

		Validator validator = validatorFactory.getValidator();

		Set<ConstraintViolation<T>> set = validator.validateValue(beanType, propertyName, value, groups);

		return set == null || set.isEmpty() ? null : makeErrorInfo(set);
	}

	/**
	 * 
	 * @desc 统一构造错误信息
	 * @auth josnow
	 * @date 2017年11月20日 下午5:02:21
	 * @param set
	 * @return 错误信息
	 */
	private <T> String makeErrorInfo(Set<ConstraintViolation<T>> set) {

		StringBuilder sb = new StringBuilder();
		for (ConstraintViolation<T> constraintViolation : set) {
			if ("sit".equals(active) /* || "uat".equals(active) ---uat环境不显示错误信息具体字段名 */) {
				// sit uat 环境增加参数字段
				sb.append("(").append(constraintViolation.getPropertyPath().toString()).append(")");
			}
			sb.append(constraintViolation.getMessage()).append(";");
		}
		return sb.toString();
	}

}
