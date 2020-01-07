package com.jiang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**ʱ���ʽ����*/
public class TimeUtil {

	/**��ʽ��ʱ��Ϊ:һСʱǰ��һ��ǰ��*/
	public static String fromatTime(Date date) {
		Date nowDate = new Date();
		//ʱ���:��λ����
		Long time = (nowDate.getTime()-date.getTime())/60000;
		//��ʽ���󷵻ص��ַ���
		String showTime = "";
		
		if(time<60) {//60��������
			showTime = time+"����ǰ����";
		}else if(time>=60&&time<1440) {//һ������
			time = time/60;
			showTime = time+"Сʱǰ����";
		}else if(time>=1440&&time<43200) {//һ����
			time = time/1440;
			showTime = time+"��ǰ����";
		}else {//����һ���µ�
			time = time/43200;
			showTime = time+"����ǰ����";
		}
		return showTime;
	}
	
	/**���ַ������ڸ�ʽת����date*/
	//@Test
	public static Date formatDate(String dateStr) {
		//String dateStr = "2018-1-8";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("�����ַ�������");
		}
		//System.out.println(d);
		return d;
	}
	
	/**��date��ʽת�����ַ�����ʽ 2018-1-8*/
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
