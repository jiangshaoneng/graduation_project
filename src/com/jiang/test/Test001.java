package com.jiang.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jiang.beans.Address;
import com.jiang.beans.Collection;
import com.jiang.beans.Customer;
import com.jiang.beans.Goods;
import com.jiang.beans.Images;
import com.jiang.dao.AddressMapper;
import com.jiang.dao.CollectionMapper;
import com.jiang.dao.CustomerMapper;
import com.jiang.dao.GoodsMapper;
import com.jiang.dao.ImagesMapper;
import com.jiang.utils.SendJMail;

public class Test001 {
	
	/*@Autowired
	CustomerMapper mapper;*/
	
	@Test
	public void test11() {
		SendJMail.sendMail("846762523@qq.com", "【校园闲置物品交易平台】用户绑定邮箱需要您的认证:463723");
	}
	
	@Test
	public void test10() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory= (SqlSessionFactory) ctx.getBean("sqlSessionFactoryBean");  
		SqlSession openSession = sqlSessionFactory.openSession();
		 
		GoodsMapper mapper = openSession.getMapper(GoodsMapper.class);
		Map<String,Object> map=new HashMap<String,Object>();  
        map.put("goodsType", null);  
        map.put("payType", null );  
        map.put("priceOrderBy", null ); 
        map.put("lowPrice", null);  
        map.put("topPrice", null );  
        map.put("goodsName", "%小米%" ); 
        map.put("goodsAddress", null ); 
        map.put("currentPageStart", 0);
        map.put("currentPageNum", 5);
        System.out.println(mapper.findGoodsCountByClassify(map));
		List<Goods> gg= mapper.findGoodsByMycondition(map);
		System.out.println(gg.size());
		for (Goods goods : gg) {
			System.out.println(goods);
			Customer customer = goods.getGoodsCustomer();
			System.out.println(customer);
			List<Address> address = customer.getAddress();
			System.out.println(address.get(0));
		}
	}
	
	
	@Test
	public void test09() {
		
		/*ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory= (SqlSessionFactory) ctx.getBean("sqlSessionFactoryBean");  
		SqlSession openSession = sqlSessionFactory.openSession();
		 
		ImagesMapper mapper = openSession.getMapper(ImagesMapper.class);
		Goods goods = new Goods();
		goods.setGoodsId("2001");
		List<Images> imgs = new ArrayList<Images>();
		Images img1 = new Images("111", "../111.jpg");
		img1.setimgGoods(goods);
		Images img2 = new Images("222", "../222.jpg");
		img2.setimgGoods(goods);
		Images img3 = new Images("333", "../333.jpg");
		img3.setimgGoods(goods);
		imgs.add(img1);
		imgs.add(img2);
		imgs.add(img3);
		System.out.println(mapper.addImg(imgs));*/
	}
	
	@Test
	public void test08() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory= (SqlSessionFactory) ctx.getBean("sqlSessionFactoryBean");  
		SqlSession openSession = sqlSessionFactory.openSession();
		 
		GoodsMapper mapper = openSession.getMapper(GoodsMapper.class);
		Goods goods = mapper.findGoodsById("2001");
		System.out.println(goods);
		
/*		for (Images images : imgs) {
			System.out.println(images);
		}*/
	}
	
	
	@Test
	public void test07() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory= (SqlSessionFactory) ctx.getBean("sqlSessionFactoryBean");  
		SqlSession openSession = sqlSessionFactory.openSession();
		 
		CollectionMapper mapper = openSession.getMapper(CollectionMapper.class);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cusId", 1001);
		map.put("currentPageNum", 5);
		int currentPageStart = 5*(1-1);//通过页码计算出开始的位置
		map.put("currentPageStart", currentPageStart);
		
		List<Collection> list = mapper.findCollectionByCusId(map);
		for (Collection collection : list) {
			System.out.println(collection);
			
			Goods goods= collection.getCollectionGoods();
			Customer cus = goods.getGoodsCustomer();
			System.out.println(cus);
			System.out.println(goods);
			
			List<Images> images = goods.getImages();
			for (Images images2 : images) {
				System.out.println(images2);
			}
		}
	}
	
	
	@Test
	public void test06() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory= (SqlSessionFactory) ctx.getBean("sqlSessionFactoryBean");  
		SqlSession openSession = sqlSessionFactory.openSession();
		 
		GoodsMapper mapper = openSession.getMapper(GoodsMapper.class);
		Map<String,String> map=new HashMap<String,String>();  
        map.put("cusId", "1001");  
        map.put("goodsStatus", "出售中" );  
        map.put("orderBy", "DESC" ); 
        
		List<Goods> gg= mapper.findGoodsBycusIdAndStatus(map);
		for (Goods goods : gg) {
			System.out.println(goods);
		}
	}
	
	@Test
	public void test05() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory= (SqlSessionFactory) ctx.getBean("sqlSessionFactoryBean");  
		SqlSession openSession = sqlSessionFactory.openSession();
		 
		CustomerMapper mapper = openSession.getMapper(CustomerMapper.class);
		Customer customer = new Customer("1003", "知非", "春花", "123444", "女", "15674348769",null, null, "", "", "","","");
		
		System.out.println(mapper.addCustomer(customer)); 
		
	}
	
	@Test
	public void test04() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory= (SqlSessionFactory) ctx.getBean("sqlSessionFactoryBean");  
		SqlSession openSession = sqlSessionFactory.openSession();
		 
		CustomerMapper mapper = openSession.getMapper(CustomerMapper.class);
		Customer cus= mapper.findCustomerByName("jiang");
		//cus.setcusPassword("666666");
		//mapper.updateCustomerBycusId(cus);
		
		System.out.println(cus); 
		
	}
	 
	@Test
	public void test03() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory= (SqlSessionFactory) ctx.getBean("sqlSessionFactoryBean");  
		SqlSession openSession = sqlSessionFactory.openSession();
		 
		AddressMapper mapper = openSession.getMapper(AddressMapper.class);
		CustomerMapper mapper1 = openSession.getMapper(CustomerMapper.class);
	/*	Address address= mapper.findDefAddressByCusId("1001");
		
		System.out.println(mapper.updateAddress(address));
		/*System.out.println(mapper.delAddress("10"));
		
		Address a = new Address("10","湖南省永州市江永县","默认地址");
		a.setAddressCustomer(mapper1.findCustomerByName("jiang"));
		System.out.println(mapper.insertAddress(a));*/
	}
	
	@Test
	public void test02() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory= (SqlSessionFactory) ctx.getBean("sqlSessionFactoryBean");  
		SqlSession openSession = sqlSessionFactory.openSession();
		 
		AddressMapper mapper = openSession.getMapper(AddressMapper.class);
		List<Address> address= mapper.findAddressByCusId("1001");
		for (Address address2 : address) {
			System.out.println(address2);
			System.out.println(address2.getAddressCustomer());
		}
		
	}
	
	@Test
	public void test01() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory= (SqlSessionFactory) ctx.getBean("sqlSessionFactoryBean");  
		SqlSession openSession = sqlSessionFactory.openSession();
		 
		CustomerMapper mapper = openSession.getMapper(CustomerMapper.class);
		Customer cus= mapper.findCustomerById("1001");
		System.out.println(cus); 
		
	}
	
}
