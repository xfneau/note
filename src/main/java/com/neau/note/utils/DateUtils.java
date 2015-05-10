package com.neau.note.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author ruijie
 * @date 2013-11-14
 * @version V1.0
 */
public class DateUtils {
	
	/**
	 * 获得多少天前的日期
	 * @param n
	 * @return
	 */
	public static String getForwardDate(int n){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, - n);
		return sdf.format(c.getTime());
	}
	
	/**
	 * 获得今天的日期
	 * @return
	 */
	public static String getToday(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	/**
	 * 获取昨天日期
	 * @return
	 */
	public static String getYesterday(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, - 1);
		
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
	/**
	 * 时间减去一定秒数
	 * @param now
	 * @param sec
	 * @return
	 */
	public static Date minus(Date now, int sec){
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.SECOND, - sec);
		return c.getTime();
	}
	
	/**
	 * 通过开始时间和结束时间获得相差秒数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int minus(Date startTime, Date endTime){
		
		long sec = (endTime.getTime() - startTime.getTime()) / 1000;
		
		return new Long(sec).intValue();
	}
	
	/**
	 * 当前时间戳
	 * @return
	 */
	public static long nowTimes(){
		
		return System.currentTimeMillis();
	}
}
