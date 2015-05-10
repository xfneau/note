package com.neau.note.utils;

import java.util.Collection;
import java.util.Map;
 
/**
 * 验证工具类
 * @author Administrator
 *
 */
public class ValidateUtils {

	/**
	 * 对象
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
	 * @param object
	 * @return
	 */
	public static boolean isValidate(String object) {
		if (object == null || "".equals(object.trim()) ) {
			return false;
		}
		return true;
	}
	
	/**
	 * 集合
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValidate(Collection object) {
		if (object == null || object.isEmpty() ) {
			return false;
		}
		return true;
	}
	
	/**
	 * map
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValidate(Map object) {
		if (object == null || object.isEmpty() ) {
			return false;
		}
		return true;
	}
	
	/**
	 * 数组
	 * @param object
	 * @return
	 */
	public static boolean isValidate(Object[] object) {
		if (object == null || object.length==0 ) {
			return false;
		}
		return true;
	}
	
	public String validate(int year, int month, int day, int hour, int minute){
		
		return "";
	}
	

}
