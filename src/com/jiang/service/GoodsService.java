package com.jiang.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.beans.Address;
import com.jiang.beans.Collection;
import com.jiang.beans.Comment;
import com.jiang.beans.Customer;
import com.jiang.beans.Goods;
import com.jiang.beans.Guess;
import com.jiang.beans.Images;
import com.jiang.dao.AddressMapper;
import com.jiang.dao.CollectionMapper;
import com.jiang.dao.CommentMapper;
import com.jiang.dao.CustomerMapper;
import com.jiang.dao.GoodsMapper;
import com.jiang.dao.GuessMapper;
import com.jiang.servicepage.CommentPage;
import com.jiang.servicepage.GoodsInfo;
import com.jiang.servicepage.GoodsPage;
import com.jiang.servicepage.GoodsPage2;
import com.jiang.servicepage.Page;
import com.jiang.utils.NewIdUtil;

@Service
public class GoodsService {
	
	@Autowired
	GoodsPage goodsPage;
	@Autowired
	GoodsPage2 goodsPage2;
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	AddressMapper addressMapper;
	@Autowired
	CommentPage commentPage;
	@Autowired
	CommentMapper commentMapper;
	@Autowired
	CustomerMapper customerMapper;
	@Autowired
	CollectionMapper collectionMapper;
	@Autowired
	GuessMapper guessMapper;
	
	/**猜你喜欢*/
	@SuppressWarnings("all")
	public Page guessYouLike(Customer customer) {
		Page page = new Page();
		List goodsList = new ArrayList<Goods>();
		if(customer == null) {
			//如果未登录用户,随机查询出8件商品
			goodsList.addAll(findGoodsByType("男生专区",4));
			goodsList.addAll(findGoodsByType("女生专区",4));
		}else {
			List<Guess> guessList = guessMapper.findListBycusId(customer.getcusId());
			if(guessList.size() > 0){
				//如果用户登录,关键词用有数据，查询相应数据对应的商品不足8件再从男生/女生专区补齐8件
				for (Guess guess : guessList) {
					goodsList.addAll(findGoodsByType(guess.getGuessKeyword(),2));
				}
			}
			if(goodsList.size()<8) {
				Integer num = 8 - goodsList.size();
				if("男".equals(customer.getcusGender())) {
					goodsList.addAll(findGoodsByType("男生专区",num));
				}else {
					goodsList.addAll(findGoodsByType("女生专区",num));
				}
			}
		}
		page.setList(goodsList);
		
		return page;
	}
	
	/**根据商品的类型查询商品*/
	public List<Goods> findGoodsByType(String goodsType,Integer num){
		List<Goods> list = new ArrayList<Goods>();
		list.addAll(goodsMapper.findGoodsByClassify(goodsType, 0, num));
		return list;
	}
	
	/**将商品分类显示在首页*/
	public Page showGoodsInfo(String goodsType,Integer currentPageNo,Integer currentPageNum){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goodsType", goodsType);
		map.put("currentPageNo", currentPageNo);
		map.put("currentPageNum", currentPageNum);
		return goodsPage.getPage(map);
	}
	
	/**查询某件商品的详情*/
	public Goods showGoodsDetail(String goodsId,String cusId) {
		//查询出商品的基本信息
		Goods goods = goodsMapper.findGoodsById(goodsId);
		//通过商品的名目记录用户的喜好
		if(cusId != null) {
			Guess guess = new Guess();
			guess.setGuessId(NewIdUtil.getId());
			guess.setGuessKeyword(goods.getGoodsType());
			guess.setGuessCustomerId(cusId);
			guessMapper.insert(guess);
		}
		return goods;
	}
	
	/**查询出此件商品发布者的默认地址*/
	public Address findDefAddressByCusId(String cusId) {
		return addressMapper.findDefAddressByCusId(cusId);
	}
	
	/**查询出某件商品的评论信息*/
	public Page showCommet(String goodsId,Integer currentPageNo,Integer currentPageNum) {
		return commentPage.getPage(goodsId, currentPageNo, currentPageNum);
	}
	
	/**添加一条评论*/
	public boolean addComment(String cusId,String goodsId,String addCommentInfo) {
		Comment comment = new Comment();
		//设置编号
		comment.setcommentId(NewIdUtil.getId());
		//设置评论者
		Customer customer = customerMapper.findCustomerById(cusId);
		comment.setcommentCustomer(customer);
		//设置评论的商品
		Goods goods = goodsMapper.findGoodsById(goodsId);
		comment.setcommentGoods(goods);
		//设置评论的内容
		comment.setcommentInfo(addCommentInfo);
		
		return commentMapper.addComment(comment);
	}
	
	/**删除一条评论*/
	public boolean delComment(String commentId) {
		return commentMapper.delCommentById(commentId);
	}
	
	/**按条件查询商品*/
	public Page customerSearch(String goodsType,String payType,String priceOrderBy,String lowPrice,String topPrice,
			String goodsName,String goodsAddress,Integer currentPageNo,Integer currentPageNum,String cusId) {
		//将所有的查询条件放在一个map中
		Map<String,Object> map=new HashMap<String,Object>();
		
		if(goodsType!=null&&!goodsType.trim().equals("不限")&&!goodsType.trim().equals("")&&!goodsType.equals("null")) {
			map.put("goodsType", goodsType);
		}
		if(payType!=null&&!payType.trim().equals("不限")&&!payType.trim().equals("")&&!payType.equals("null")) {
			map.put("payType", payType );  
		}
		if(priceOrderBy!=null&&!priceOrderBy.trim().equals("")&&!priceOrderBy.equals("null")) {
			if(priceOrderBy.equals("由高到低")) {
				map.put("priceOrderBy", "DESC"); 				
			}else {
				map.put("priceOrderBy", "ASC"); 	
			}
		}
		if(lowPrice!=null&&!lowPrice.trim().equals("")&&!lowPrice.equals("null")) {
			map.put("lowPrice", lowPrice);  
		}
		if(topPrice!=null&&!topPrice.trim().equals("")&&!topPrice.equals("null")) {
			map.put("topPrice", topPrice );
		}
        if(goodsName!=null&&!goodsName.trim().equals("")&&!goodsName.equals("null")) {
        	map.put("goodsName", "%"+goodsName+"%");        	
        }
        if(goodsAddress!=null&&!goodsAddress.trim().equals("")&&!goodsAddress.equals("null")) {
        	map.put("goodsAddress", "%"+goodsAddress+"%" );
        }
        
        map.put("currentPageNo", currentPageNo);
        map.put("currentPageNum", currentPageNum);
        
        /*System.out.println(map.get("goodsType"));
        System.out.println(map.get("payType"));
        System.out.println(map.get("priceOrderBy"));
        System.out.println(map.get("lowPrice"));
        System.out.println(map.get("topPrice"));
        System.out.println(map.get("goodsName"));
        System.out.println(map.get("goodsAddress"));
        System.out.println(map.get("currentPageNo"));
        System.out.println(map.get("currentPageNum"));*/
        
		return goodsPage2.getPage(map,cusId);//cusId用于判断是都有收藏关系
	}
	
	/**添加一队收藏关系*/
	public boolean addCollection(String cusId,String goodsId) {
		String collectionId = NewIdUtil.getId();//获取一个编号
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("collectionId", collectionId);
		map.put("cusId", cusId);
		map.put("goodsId", goodsId);
		
		return collectionMapper.addCollection(map);
	}
	
	/**删除一队收藏关系*/
	public boolean removeCollection(String cusId,String goodsId) {
		return collectionMapper.removeCollection(cusId,goodsId);
	}
	
	/**查询出顾客出售中的商品*/
	public List<Goods> findByBycusIdAndStatus(String cusId,String status,String orderBy){
		Map<String,String> map = new HashMap<String,String>();
		map.put("cusId", cusId);
		map.put("goodsStatus", status);
		map.put("orderBy", orderBy);
		return goodsMapper.findGoodsBycusIdAndStatus(map);
	}
	
	/**刷新一件商品*/
	public Boolean refreshGoods(String goodsId) {
		Goods goods = goodsMapper.findGoodsById(goodsId);
		goods.setGoodsStatus("出售中");
		if(Integer.parseInt(goods.getGoodsTotal())<=0) {
			goods.setGoodsTotal("1");			
		}
		goods.setGoodsAddtime(new Date());
		return goodsMapper.updateGoods(goods);
	}
	
	/**删除一件商品，将商品的状态改为：已移除*/
	public Boolean delGoods(String goodsId) {
		Goods goods = goodsMapper.findGoodsById(goodsId);
		goods.setGoodsStatus("已移除");
		goods.setGoodsAddtime(new Date());
		return goodsMapper.updateGoods(goods);
	}
	
	/**保存或更新一件商品*/
	public String addOrUpdate(Map<String,Object> map) {
		Goods goods = goodsMapper.findGoodsById((String)map.get("goodsId"));
		Customer customer = (Customer)map.get("customer");
		String gid = "";
		
		if(goods == null) {//add
			Goods newGoods = new Goods();
			Address goodsAddress = addressMapper.findDefAddressByCusId(customer.getcusId());
			String goodsId = NewIdUtil.getId();
			newGoods.setGoodsId(goodsId);
			newGoods.setGoodsName((String)map.get("name"));
			newGoods.setGoodsPrice((String)map.get("price"));
			newGoods.setGoodsInfo((String)map.get("desc"));
			newGoods.setGoodsType(classfiyNumberToString((String)map.get("goodsType")));
			newGoods.setGoodsStatus("出售中");
			newGoods.setGoodsTotal((String)map.get("num"));
			newGoods.setGoodsPaytype(payTypeNumberToString((String)map.get("payType")));
			newGoods.setGoodsAddress(goodsAddress.getAddressProvince()+goodsAddress.getAddressCity());
			newGoods.setGoodsCustomerId(customer.getcusId());
			goodsMapper.addGoods(newGoods);
			gid = goodsId;
		}else {//uadate
			gid = goods.getGoodsId();
			
			String goodsType = (String)map.get("goodsType");
			if(goodsType != null && goodsType.trim() != "")
				goods.setGoodsType(classfiyNumberToString(goodsType));
			
			String payType = (String)map.get("payType");
			if(payType != null && payType.trim() != "") {
				goods.setGoodsPaytype(payTypeNumberToString(payType));
			}
			
			String name = (String)map.get("name");
			if(name != null && name.trim() != "")
				goods.setGoodsName(name);
			
			String price = (String)map.get("price");
			if(price != null && price.trim() != "")
				goods.setGoodsPrice(price);
			
			String num = (String)map.get("num");
			if(num != null && num.trim() != "")
				goods.setGoodsTotal(num);
			
			String desc = (String)map.get("desc");
			if(desc != null && desc.trim() != "")
				goods.setGoodsInfo(desc);
			
			String status = (String)map.get("status");
			if(status != null && status.trim() != "") {
				String tempStatus = (status =="1")?"出售中":(status =="2"?"已售出":"已移除");
				goods.setGoodsStatus(tempStatus);
			}
			
			goodsMapper.updateGoods(goods);
		}
		return gid;
	}
	
	//将接受到的分类转化成文字描述
	public String classfiyNumberToString(String numberCode) {
		String str = ""; 
		switch (numberCode) {
		case "01":str = "书籍";
			break;
		case "02":str = "电子设备";
			break;
		case "03":str = "衣物";
			break;
		case "04":str = "日用品";
			break;
		case "05":str = "男生专区";
			break;
		case "06":str = "女生专区";
			break;
		default:str = "无分类";
			break;
		}
		return str;
	}
	
	//将up down转化成文字描述
	public String payTypeNumberToString(String numberCode) {
		String str = ""; 
		switch (numberCode) {
		case "up":str = "线上交易";
			break;
		case "down":str = "线下交易";
			break;
		default:str = "无交易类型";
			break;
		}
		return str;
	}
}
