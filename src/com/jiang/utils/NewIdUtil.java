package com.jiang.utils;

import java.util.Date;
import java.util.Random;

import org.junit.Test;

@SuppressWarnings("all")
public class NewIdUtil {
	
	/**����������*/
	//@Test
	public static String getId() {
		//����һ��20λ��һ�����
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
		//�������һ��12λ����
		Random r = new Random();
		String randomId = "";
		for(int i = 0;i<8;i++) {
			randomId = randomId+r.nextInt(10);
		}
		//ƴ�ӳ�һ��20λ���ַ������
		String id = year+month+day+randomId;
		//System.out.println(id);
		return id;
	}
	
	//����һ����֤��4λ��
	//@Test
	public static String getCheckNumber() {
		//�������һ��12λ����
		Random r = new Random();
		String randomId = "";
		for(int i = 0;i<6;i++) {
			randomId = randomId+r.nextInt(10);
		}
		//System.out.println(randomId);
		return randomId;
	}
	
	
}
