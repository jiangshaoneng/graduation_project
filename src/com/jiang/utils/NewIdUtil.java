package com.jiang.utils;

import java.util.Date;
import java.util.Random;

import org.junit.Test;

@SuppressWarnings("all")
public class NewIdUtil {
	
	/**主键生成器*/
	//@Test
	public static String getId() {
		//生成一个20位的一个编号
		Date date = new Date();
		
		String year = ""+(date.getYear()+1900);
		String month;
		String day;
		if((date.getMonth()+1)<10) {
			month = "0"+((date.getMonth()+1));			
		}else {
			month = ""+((date.getMonth()+1));
		}
		
		if(date.getDate()<10) {
			day = "0"+(date.getDate());
		}else {
			day = ""+(date.getDate());
		}
		//随机生成一个12位的数
		Random r = new Random();
		String randomId = "";
		for(int i = 0;i<8;i++) {
			randomId = randomId+r.nextInt(10);
		}
		//拼接成一个20位的字符串编号
		String id = year+month+day+randomId;
		//System.out.println(id);
		return id;
	}
	
	//生成一个验证码4位数
	//@Test
	public static String getCheckNumber() {
		//随机生成一个12位的数
		Random r = new Random();
		String randomId = "";
		for(int i = 0;i<6;i++) {
			randomId = randomId+r.nextInt(10);
		}
		//System.out.println(randomId);
		return randomId;
	}
	
	
}
