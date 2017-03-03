package app.android.oyb.com.myapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间的处理类
 */
public class TimeUtil {

	public final static String FORMAT_TODAY = "今天 HH:mm";
	public final static String FORMAT_YESTERDAY = "昨天 HH:mm";
	public final static String FORMAT_THIS_YEAR = "M 月 d 日";
	public final static String FORMAT_OTHER_YEAR = "yyyy-MM-dd";
	public final static String FORMAT_YEAR_MONTH = "yyyy 年 M 月";
	public final static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	public final static String FORMAT_M = "MM-dd HH:mm:ss";
	public final static String FORMAT_TO_M = "yyyy-MM-dd HH:mm";
	public final static String FORMAT_H_M_S = "HH:mm:ss";
	public final static String FORMAT_H_M = "HH:mm";

	private final static int SECOND_MILLISECONDS = 1000;
	private final static int YEAR_BASE = 1900;
	// "2011-01-05T15:19:21+00:00" to long
	public static long parseStringTolong(String s) {
		long result = (long) 0;
		String s1 = s.replace("T", " ");
		String s2 = s1.replace("+", " ");
		System.out.println(s2);
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			result = sf1.parse(s2).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取当前时间 小时:分;秒 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurTimeH_M_S() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_H_M_S);
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * 获取当前时间 小时:分;秒 YY-MM-DD HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurTimeToMin() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_TO_M);
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * 获取当前时间 小时:分;秒 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurTimeH_M() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_H_M);
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	// 将long型的时间转成xxxx年xx月xx日 星期x
	public static String parselongToString(long time) {
		Date date = new Date();
		date.setTime(time);
		int year = date.getYear() + YEAR_BASE;
		int month = date.getMonth() + 1;
		int date2 = date.getDate();
		int day = date.getDay();
		String s = year + "年" + month + "月" + date2 + "日" + " "
				+ getDayOfWeek(day);
		return s;
	}

	/**
	 * 使用给定的formatter格式化时间
	 * 
	 * @param aSeconds
	 * @return
	 */
	public static String formatTime(long aSeconds, String aFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(aFormat);
		Date date = new Date();
		date.setTime(aSeconds);
		String formatDate = sdf.format(date);
		return formatDate;
	}

	/**
	 * 获取年数字
	 * 
	 * @param aSeconds
	 * @return
	 */
	public static int getYear(long aSeconds) {
		Date date = new Date();
		date.setTime(aSeconds * SECOND_MILLISECONDS);

		int year = date.getYear() + YEAR_BASE;

		return year;
	}

	/**
	 * 获取月数字
	 * 
	 * @param aSeconds
	 * @return
	 */
	public static int getMonth(long aSeconds) {
		Date date = new Date();
		date.setTime(aSeconds * SECOND_MILLISECONDS);

		int month = date.getMonth() + 1;

		return month;
	}

	/**
	 * 获取日数字
	 * 
	 * @param aSeconds
	 * @return
	 */
	public static int getDate(long aSeconds) {
		Date date = new Date();
		date.setTime(aSeconds * SECOND_MILLISECONDS);

		return date.getDate();
	}

	/**
	 * 获取小时数字
	 * 
	 * @param aSeconds
	 * @return
	 */
	public static int getHour(long aSeconds) {
		Date date = new Date();
		date.setTime(aSeconds * SECOND_MILLISECONDS);

		return date.getHours();
	}

	/**
	 * 获取分钟数字
	 * 
	 * @param aSeconds
	 * @return
	 */
	public static int getMinute(long aSeconds) {
		Date date = new Date();
		date.setTime(aSeconds * SECOND_MILLISECONDS);

		return date.getMinutes();
	}

	/**
	 * 获取秒数字
	 * 
	 * @param aSeconds
	 * @return
	 */
	public static int getSecond(long aSeconds) {
		Date date = new Date();
		date.setTime(aSeconds * SECOND_MILLISECONDS);

		return date.getSeconds();
	}

	/**
	 * 格式化时间
	 * 
	 * @param aSeconds
	 * @return
	 */
	public static String getFormattedDateString(long aSeconds) {
		Date date = new Date();
		date.setTime(aSeconds * SECOND_MILLISECONDS);
		String formatter = FORMAT_FULL;
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}

	public static String getFormattedDateString(long aSeconds, String formatter) {
		Date date = new Date();
		date.setTime(aSeconds * SECOND_MILLISECONDS);
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}

	/**
	 * 获取当前epoch时间
	 * 
	 * @return
	 */
	public static long getNowTime() {
		Date current = new Date();
		return current.getTime();
	}

	/**
	 * 获取当前epoch时间
	 * 
	 * @return
	 */
	public static long getNowTicks() {
		return getNowTime() / SECOND_MILLISECONDS;
	}

	public static String getDayOfWeek(int mWeek) {
		String days[] = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
		String dayOfWeek = "";
		if (mWeek >= 1 && mWeek <= 7) {
			dayOfWeek = days[mWeek - 1];
		}else if ( mWeek == 0){
			dayOfWeek = days[6];
		}
		return dayOfWeek;
	}

	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
		}
	}

	/**
	 * 智能显示时间差值
	 *
	 * 如 2小时前 / 10分钟前 / 3天前
	 *
	 * @param longTime
	 * @return
	 */
	public static String autoShowLastTime(String longTime){
		String result = "";
		try {
			long ltime = Long.parseLong(longTime);
			long curr = System.currentTimeMillis();

			float hoursF = (curr - ltime * 1000) / (60*60*1000);  // 小时
			int hours = (int)hoursF; // 四舍五入
			if(hours > 0) {
                int day = 24;
                if(hours < day) {
                    result += String.valueOf(hours) + "小时前";
                } else if(hours >= day && hours < day * 2) {
                    result = "1天前";
                } else if(hours >= day*2 && hours < day*3) {
                    result = "2天前";
                } else {
                    result = TimeUtil.getFormattedDateString(ltime, TimeUtil.FORMAT_OTHER_YEAR);
                }
            }
            else
            {
                float minusF = (curr - ltime * 1000) / (60*1000);  // 分钟
                int minus = (int)minusF; // 四舍五入
                if(minus > 0)
                    result += String.valueOf(minus) + "分钟前";
                else
                    result = "刚刚";
            }
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int daysBetween(long smdate, long bdate) {
		return daysBetween(new Date(smdate), new Date(bdate));
	}

}
