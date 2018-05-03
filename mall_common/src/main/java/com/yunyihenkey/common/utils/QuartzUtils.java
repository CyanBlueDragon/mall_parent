package com.yunyihenkey.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author josnow quartz工具类
 * @date 2017年5月31日 下午12:19:25
 * @version 1.0.0
 * @desc
 */
public class QuartzUtils {

	/**
	 * 
	 * @desc 计算表达式近20次时间
	 * @auth josnow
	 * @date 2017年5月31日 下午12:16:25
	 * @param cron
	 * @return
	 */
	public static List<String> seeExcuteTime(String cron) throws ParseException, IllegalArgumentException {
		if (StringUtils.isEmpty(cron)) {
			throw new IllegalArgumentException("参数不能为空");
		}

		CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<String> list = new ArrayList<>(20);

		Date nextTimePoint = new Date();
		for (int i = 0; i < 20; i++) {
			// 计算下次时间点的开始时间
			nextTimePoint = cronSequenceGenerator.next(nextTimePoint);
			list.add(sdf.format(nextTimePoint));
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		List<String> list = seeExcuteTime("0 0 * * * ?");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
