package com.yunyihenkey.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang3.StringUtils;

/**
 * 常用日期工具
 * 
 * @author s4tian
 *
 */
public class DateUtil {

	// private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static final String DATE_DIVISION2 = "-";
	public static final String DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String HH_MM_SS = "HH:mm:ss";
	public final static String DATE_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmmss";
	public final static String YYYYMMDD_HHMM = "yyyy/MM/dd HH:mm:ss";

	/** 根据日期获取年 */
	public static final String TYPE_NUM_BY_BIRTHDAY_YEAR = "year";
	/** 根据日期获取月 */
	public static final String TYPE_NUM_BY_BIRTHDAY_MONTH = "month";
	/** 根据日期获取日 */
	public static final String TYPE_NUM_BY_BIRTHDAY_DAY = "day";

	/**
	 * date 获取当前日期
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return currDate;
	}

	/**
	 * str 获取当前日期字符串
	 * 
	 * @return 格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateStr() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return format(currDate);
	}

	/**
	 * str to date
	 * 
	 * @param strDate
	 *            需转换日期日期字串
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Date parse(String strDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT);
		Date newDate = null;
		try {
			newDate = dateFormat.parse(strDate);
		} catch (ParseException pe) {
			newDate = null;
		}
		return newDate;
	}

	/**
	 * 福享定期给小孩投保时小孩最小投保年龄大于60天；(生效日减去60天的日期)
	 * 
	 * @param startDate
	 * @return
	 * @throws ParseException
	 */
	public static String getLimitDate(String startDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date da = sdf.parse(startDate);
		Calendar c = Calendar.getInstance();
		c.setTime(da);
		c.add(Calendar.DATE, -60);
		return sdf.format(c.getTime());

	}

	/**
	 * str to date
	 * 
	 * @param strDate
	 *            需转换日期日期字串
	 * @param pattern
	 *            需转换日期
	 * @return
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date newDate = null;
		try {
			newDate = dateFormat.parse(strDate);
		} catch (ParseException pe) {
			newDate = null;
		}
		return newDate;
	}

	/**
	 * 获取指定日期的开始时间
	 * 
	 * @param date
	 *            需转换日期
	 * @return 如：2016-01-01 00:00:00
	 */
	public static Date getDateStart(Date date) {
		String dateStr = format(date, YYYY_MM_DD);
		dateStr = dateStr + " 00:00:00";
		return parse(dateStr);
	}

	/**
	 * 获取指定日期的结束时间
	 * 
	 * @param date
	 *            需转换日期
	 * @return 如：2016-01-01 23:59:59
	 */
	public static Date getDateEnd(Date date) {
		String dateStr = format(date, YYYY_MM_DD);
		dateStr = dateStr + " 23:59:59";
		return parse(dateStr);
	}

	/**
	 * date to string
	 * 
	 * @param date
	 *            需转换日期
	 * @return
	 */
	public static String format(Date date) {
		return format(date, DEFAULT);
	}

	/**
	 * date to string
	 * 
	 * @param date
	 *            需转换日期
	 * @param pattern
	 *            需转换格式
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null || pattern == null)
			return null;
		SimpleDateFormat dateFromat = new SimpleDateFormat();
		dateFromat.applyPattern(pattern);
		return dateFromat.format(date);
	}

	/**
	 * 获取小时（24小时制）
	 * 
	 * @param date
	 *            需转换日期
	 * @return
	 */
	public static int get24Hours(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取小时（12小时制）
	 * 
	 * @param date
	 *            需转换日期
	 * @return
	 */
	public static int get12Hours(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR);
	}

	/**
	 * 获取date的后year日期
	 * 
	 * @param date
	 *            指定日期
	 * @param years
	 *            之前或之后的月数:如years -1前一年、1后一年
	 * @return
	 */
	public static Date addYears(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	/**
	 * 获取date的后months日期
	 * 
	 * @param date
	 *            指定日期
	 * @param months
	 *            之前或之后的月数:如months -1前一月、1后一月
	 * @return
	 */
	public static Date addMonths(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * 获取date的后weeks日期
	 * 
	 * @param date
	 *            指定日期
	 * @param weeks
	 *            之前或之后的周数:如weeks -1前一周、1后一周
	 * @return
	 */
	public static Date addWeeks(Date date, int weeks) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.WEEK_OF_MONTH, weeks);
		return cal.getTime();
	}

	/**
	 * 获取date的之前或之后days天
	 * 
	 * @param date
	 *            指定日期
	 * @param days
	 *            之前或之后天数:如days -1前一天、1后一天
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 获取date的后hours小时
	 * 
	 * @param date
	 *            指定日期
	 * @param hours
	 *            之前或之后的小时数:如hours -1前一小时、1后一小时
	 * @return
	 */
	public static Date addHours(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}

	/**
	 * 获取date的后mins分钟数
	 * 
	 * @param date
	 *            指定日期
	 * @param mins
	 *            之前或之后的分钟数:如mins -1前一分钟、1后一分钟
	 * @return
	 */
	public static Date addMinutes(Date date, int mins) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, mins);
		return calendar.getTime();
	}

	/**
	 * 获取date的后seconds秒数
	 * 
	 * @param date
	 *            指定日期
	 * @param seconds
	 *            之前或之后的秒数:如seconds -1前一秒、1后一秒
	 * @return
	 */
	public static Date addSeconds(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}

	/**
	 * 获取date是当月的几号
	 * 
	 * @param date
	 * @return 如:2016
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取date的月份
	 * 
	 * @param date
	 * @return 如: 1 一月份
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取date是星期几
	 * 
	 * @param date
	 * @return 如: 0星期日 1星期一 2星期二 3星期三 4星期四 5星期五 6星期六
	 */
	public static int getWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 获取date是当月的几号
	 * 
	 * @param date
	 * @return 如: 15 十五号
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		return today;
	}

	/**
	 * 获取date 分钟数
	 * 
	 * @param date
	 * @return 如: 15 第十五分钟
	 */
	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 获取date 秒数
	 * 
	 * @param date
	 * @return 如: 15 第十五秒
	 */
	public static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**
	 * 获取某日期是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayOfWeekShortString(Date date) {
		int dayOfWeek = getWeek(date);
		switch (dayOfWeek) {
		case 1:
			return "Sun";
		case 2:
			return "Mon";
		case 3:
			return "Tue";
		case 4:
			return "Wed";
		case 5:
			return "Thu";
		case 6:
			return "Fri";
		case 7:
			return "Sat";
		}
		return null;
	}

	/**
	 * 获取某日期是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayOfWeekString(Date date) {
		int dayOfWeek = getWeek(date);
		switch (dayOfWeek) {
		case 1:
			return "Sunday";
		case 2:
			return "Monday";
		case 3:
			return "Tuesday";
		case 4:
			return "Wednesday";
		case 5:
			return "Thursday";
		case 6:
			return "Friday";
		case 7:
			return "Saturday";
		}
		return null;
	}

	/**
	 * 根據出生日期，計算出某日期的周歲
	 * 
	 * @param birthday（yyyy-MM-dd）
	 * @param date
	 * @return
	 */
	public static int getRealAge(String birthday, Date date) {

		Calendar dateC = Calendar.getInstance();
		dateC.setTime(date);

		Date birthDate = parse(birthday, YYYY_MM_DD);// 转换生日字符串为日期
		return getRealAge(birthDate, date);
	}

	/**
	 * 
	 * @desc 根據出生日期，計算出某日期的周歲
	 */
	public static int getRealAge(Date birthDate, Date date) {

		Calendar dateC = Calendar.getInstance();
		dateC.setTime(date);

		Calendar birthDateC = Calendar.getInstance();
		birthDateC.setTime(birthDate);

		if (dateC.before(birthDateC)) {
			throw new IllegalArgumentException("The date is before birthDate.It's unbelievable!");
		}
		int yearNow = dateC.get(Calendar.YEAR);
		int monthNow = dateC.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = dateC.get(Calendar.DAY_OF_MONTH);

		int yearBirth = birthDateC.get(Calendar.YEAR);
		int monthBirth = birthDateC.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = birthDateC.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age;
	}

	/**
	 * 根據出生日期，計算出某日期的周歲
	 * 
	 * @param birthday
	 * @param date
	 * @return
	 */
	public static int getRealAge(String birthday, String date) {
		Date d = parse(date, YYYY_MM_DD);
		return getRealAge(birthday, d);
	}

	/**
	 * 根據出生日期，計算出某日期的周歲
	 * 
	 * @param birthday
	 * @return
	 */
	public static int getRealAge(String birthday) {
		return getRealAge(birthday, new Date());
	}

	/**
	 * 获取当天剩余的秒数
	 * 
	 * @return
	 */
	public static int getTodayRemainSeconds() {
		long slong = new Date().getTime();
		long elong = getDateEnd(new Date()).getTime();
		long result = (elong - slong) / 1000;
		return (int) result;
	}

	/**
	 * 计算两个日期之间相差的天数 (自然日)
	 * 
	 * @param date1
	 * @param date2
	 * @param type=0
	 *            按日期计算天数 1按60*60*24秒计算天数
	 * @return date1<date2返回正数 反之则负数
	 */
	public static int getDiffDays(Date date1, Date date2) {
		date1 = parse(format(date1), YYYY_MM_DD);
		date2 = parse(format(date2), YYYY_MM_DD);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 两个人日期相差天数，格式YYYY-MM-DD
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDiffDays(String startDate, String endDate) {
		Date date1 = parse(startDate, YYYY_MM_DD);
		Date date2 = parse(endDate, YYYY_MM_DD);
		return getDiffDays(date1, date2);
	}

	/**
	 * 计算两个日期之间相差的小时数
	 * 
	 * @param date1
	 * @param date2
	 * @return date1<date2返回正数 反之则负数
	 */
	public static int getDiffHours(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		return (int) ((time2 - time1) / 3600000L);
	}

	/**
	 * 计算两个日期之间相差的分钟数
	 * 
	 * @param date1
	 * @param date2
	 * @return date1<date2返回正数 反之则负数
	 */
	public static int getDiffMinutes(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		return (int) ((time2 - time1) / 60000L);
	}

	/**
	 * 计算两个日期之间相差的秒数
	 * 
	 * @param date1
	 * @param date2
	 * @return date1<date2返回正数 反之则负数
	 */
	public static int getDiffSeconds(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		return (int) ((time2 - time1) / 1000L);
	}

	/**
	 * 判断年龄是否在年龄段之间,(按照次日为生效日计算)
	 * 
	 * @param insuredIdentifyNo身份证号
	 * @param minAge
	 * @param maxAge
	 * @param CalDate
	 * @return
	 */
	public static boolean isAgeBetween(String insuredIdentifyNo, int minAge, int maxAge, Date CalDate) {

		if (StringUtils.isBlank(insuredIdentifyNo)) {
			return false;
		}

		String birth = "";
		if (insuredIdentifyNo.length() == 18) {
			birth = insuredIdentifyNo.substring(6, 10) + "-" + insuredIdentifyNo.substring(10, 12) + "-"
					+ insuredIdentifyNo.substring(12, 14);
		} else if (insuredIdentifyNo.length() == 15) {
			birth = insuredIdentifyNo.substring(6, 8) + "-" + insuredIdentifyNo.substring(8, 10) + "-"
					+ insuredIdentifyNo.substring(10, 12);
		} else {
			return false;
		}

		if (StringUtils.isEmpty(birth)) {
			return false;
		}
		System.out.println(birth);

		int age = getRealAge(birth, CalDate);
		if (age >= minAge && age <= maxAge) {
			return true;
		}
		return false;

	}

	/**
	 * 方法名称: getNumByBirthday<br>
	 * 描述：根据日期获取年、月、日 作者: mxl 修改日期：2013-4-22下午03:18:40
	 * 
	 * @param birthday
	 * @param flag
	 * @return
	 */
	public static String getNumByBirthday(Date birthday, String flag) {
		String Num = null;
		String birthdayStr = DateUtil.format(birthday, DateUtil.YYYY_MM_DD);
		if (StringUtils.isNotBlank(birthdayStr)) {
			String[] birthdaySplit = birthdayStr.split("-");
			if (birthdaySplit != null) {
				if (birthdaySplit.length == 3) {
					if (TYPE_NUM_BY_BIRTHDAY_YEAR.equals(flag)) {
						Num = birthdaySplit[0];
					}
					if (TYPE_NUM_BY_BIRTHDAY_MONTH.equals(flag)) {
						Num = birthdaySplit[1];
					}
					if (TYPE_NUM_BY_BIRTHDAY_DAY.equals(flag)) {
						Num = birthdaySplit[2];
					}
				}
			}
		}
		return Num;
	}

	/**
	 * 获取今天0点0分0秒的Date,
	 * 
	 * @return
	 */
	public static Date getTodayDate() {
		return getDate("yyyy-MM-dd", getToday());
	}

	/**
	 * 取得日期
	 */
	public static String getToday() {
		return getFormatDate("yyyy-MM-dd", new Date());
	}

	/**
	 * 根据格式和日期获取按格式显示的日期字符串
	 * 
	 * @param sFormat
	 *            格式
	 * @param date
	 *            日期
	 */
	public static String getFormatDate(String sFormat, Date date) {
		if (date == null)
			return null;

		if ((sFormat == "yy") || (sFormat == "yyyy") || (sFormat == "yyyy-MM") || (sFormat == "yy.MM.dd")
				|| (sFormat == "yyyy.MM.dd") || (sFormat == "MM") || (sFormat == "dd") || (sFormat == "MM-dd")
				|| (sFormat == "MM.dd") || (sFormat == "M/dd") || (sFormat == "MM/dd") || (sFormat == "yyyyMM")
				|| (sFormat == "yyyyMMdd") || (sFormat == "yyyy/MM") || (sFormat == "yy/MM/dd")
				|| (sFormat == "yyyy/MM/dd") || (sFormat == "yyyy-MM-dd") || (sFormat == "yyyy-MM-dd HH:mm:ss")
				|| (sFormat == "yyyy/MM/dd HH:mm:ss") || (sFormat == "yyyyMMddHHmmss")
				|| (sFormat == "yyyyMMddHHmmssSSS") || (sFormat == "yyyy年MM月dd日") || (sFormat == "yyyy年M月d日")
				|| (sFormat == "yyyy年") || (sFormat == "yyyy年MM月") || (sFormat == "MM月dd日") || (sFormat == "M月d日")
				|| (sFormat == "dd日") || (sFormat == "HH") || (sFormat == "H") || (sFormat == "mm") || (sFormat == "ss")
				|| (sFormat == "SSS") || (sFormat == "yyyy/MM/dd HH:mm") || (sFormat == "yyyy.MM.dd HH:mm")
				|| (sFormat == "yyyy年M月d日 HH:mm")) {
			SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
			return formatter.format(date);
		} else {
			if ((sFormat == "HH:mm")) {
				sFormat = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
				return formatter.format(date).substring(11, 16);
			}
		}
		return null;
	}

	/**
	 * 根据格式和日期获取按格式显示的日期
	 * 
	 * @param sFormat
	 *            格式
	 * @param date
	 *            日期
	 */
	public static Date getDate(String sFormat, String date) {
		if ((date == null) || ("".equals(date)))
			return null;

		if ((sFormat == "yy") || (sFormat == "yyyy") || (sFormat == "MM") || (sFormat == "dd") || (sFormat == "MM/dd")
				|| (sFormat == "yyyyMM") || (sFormat == "yyyyMMdd") || (sFormat == "yyyy/MM") || (sFormat == "yy/MM/dd")
				|| (sFormat == "yyyy/MM/dd") || (sFormat == "yyyy-MM-dd") || (sFormat == "yyyy/MM/dd HH:mm:ss")
				|| (sFormat == "yyyy-MM-dd HH:mm:ss") || (sFormat == "yyyyMMddHHmm") || (sFormat == "yyyyMMddHHmmss")
				|| (sFormat == "yyyyMMdd-HHmmss") || (sFormat == "yyyy年MM月dd日") || (sFormat == "yyyy年MM月")
				|| (sFormat == "MM月dd日") || (sFormat == "dd日") || (sFormat == "HH") || (sFormat == "mm")) {
			SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
			try {
				return formatter.parse(date);
			} catch (Exception e) {
			}
		} else {
			if (sFormat == "HH:mm") {
				sFormat = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
				try {
					return formatter.parse("1970-01-01 " + date + ":00");
				} catch (Exception e) {
				}
			}
		}
		return null;
	}

	/**
	 * 判断日期格式是否合法 必须为YYYY-MM-DD
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValidDate(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = (Date) formatter.parse(str);
			return str.equals(formatter.format(date));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 
	 * @desc 获取第二天零点时间
	 * @auth josnow
	 * @date 2018年1月15日 上午11:30:49
	 * @return
	 */
	public static Date getNextDayZero() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 
	 * @desc 获取当天零点时间
	 * @auth josnow
	 * @date 2018年2月7日 下午7:59:32
	 * @return
	 */
	public static Date getCurrentDayZero() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	// public static void main(String args[]){
	// String birth = "1990-04-10";
	//
	// System.out.println(getRealAge(birth,parse("2018-04-09", YYYY_MM_DD)));
	// System.out.println(isAgeBetween("632321199004100075",28,30,parse("2018-04-10",
	// YYYY_MM_DD)));;
	//
	// }

}
