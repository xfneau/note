package com.neau.note.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 验证工具类
 * 
 * @author Administrator
 *
 */
public class ValidateUtils {

	/**
	 * 对象
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isValidate(Object object) {
		if (object == null) {
			return false;
		}
		return true;
	}

	/**
	 * String
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isValidate(String object) {
		if (object == null || "".equals(object.trim())) {
			return false;
		}
		return true;
	}

	/**
	 * 集合
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValidate(Collection object) {
		if (object == null || object.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * map
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValidate(Map object) {
		if (object == null || object.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 数组
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isValidate(Object[] object) {
		if (object == null || object.length == 0) {
			return false;
		}
		return true;
	}

	public String validate(int year, int month, int day, int hour, int minute) {
		if( year < 2015 || month < 0 || month > 12 ){
			return "日期错误";
		}
		if (month == 2) {
			if( year % 400 == 0 || (year % 4 == 0 && year % 100 != 0) ){
				if( day > 29 ){
					return "日错误";
				} 
			} else{
				if( day > 28 ){
					return "日错误";
				} 
			}
		}
		if( (month<8 &&month%2==1) || month==8 || month==10 || month==12 ){
			if( day > 31 ){
				return "日错误";
			}
		} else{
			if( day > 30 ){
				return "日错误";
			}
		}
		if ( hour > 24 || hour < 0 || minute > 60 || minute < 0 ) {
			return "时间错误";
		}
		return "";
	}

}
