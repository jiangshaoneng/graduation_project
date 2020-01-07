package com.jiang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**时间格式处理*/
public class TimeUtil {

	/**格式化时间为:一小时前，一天前等*/
	public static String fromatTime(Date date) {
		Date nowDate = new Date();
		//时间差:单位分钟
		Long time = (nowDate.getTime()-date.getTime())/60000;
		//格式化后返回的字符串
		String showTime = "";
		
		if(time<60) {//60分钟以内
			showTime = time+"分钟前发布";
		}else if(time>=60&&time<1440) {//一天以内
			time = time/60;
			showTime = time+"小时前发布";
		}else if(time>=1440&&time<43200) {//一月内
			time = time/1440;
			showTime = time+"天前发布";
		}else {//超出一个月的
			time = time/43200;
			showTime = time+"个月前发布";
		}
		return showTime;
	}
	
	/**将字符型日期格式转化成date*/
	//@Test
	public static Date formatDate(String dateStr) {
		//String dateStr = "2018-1-8";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("日期字符串有误！");
		}
		//System.out.println(d);
		return d;
	}
	
	/**将date格式转化成字符串格式 2018-1-8*/
	//@Test
	public static String dateToString(Date date) {
		//Date date = new Date();
		int year= 1900+date.getYear();
		int month = 1+date.getMonth();
		int day = date.getDate();
		
		String dateStr = year+"-";
		if(month<10) {
			dateStr = dateStr+"0"+month+"-";
		}else {
			dateStr = dateStr+month+"-";
		}
		if(day<10) {
			dateStr = dateStr+"0"+day;
		}else {
			dateStr = dateStr+day;
		}
		return dateStr;
	}
	
	
}
