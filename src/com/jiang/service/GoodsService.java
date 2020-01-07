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
	
	/**����ϲ��*/
	@SuppressWarnings("all")
	public Page guessYouLike(Customer customer) {
		Page page = new Page();
		List goodsList = new ArrayList<Goods>();
		if(customer == null) {
			//���δ��¼�û�,�����ѯ��8����Ʒ
			goodsList.addAll(findGoodsByType("����ר��",4));
			goodsList.addAll(findGoodsByType("Ů��ר��",4));
		}else {
			List<Guess> guessList = guessMapper.findListBycusId(customer.getcusId());
			if(guessList.size() > 0){
				//����û���¼,�ؼ����������ݣ���ѯ��Ӧ���ݶ�Ӧ����Ʒ����8���ٴ�����/Ů��ר������8��
				for (Guess guess : guessList) {
					goodsList.addAll(findGoodsByType(guess.getGuessKeyword(),2));
				}
			}
			if(goodsList.size()<8) {
				Integer num = 8 - goodsList.size();
				if("��".equals(customer.getcusGender())) {
					goodsList.addAll(findGoodsByType("����ר��",num));
				}else {
					goodsList.addAll(findGoodsByType("Ů��ר��",num));
				}
			}
		}
		page.setList(goodsList);
		
		return page;
	}
	
	/**������Ʒ�����Ͳ�ѯ��Ʒ*/
	public List<Goods> findGoodsByType(String goodsType,Integer num){
		List<Goods> list = new ArrayList<Goods>();
		list.addAll(goodsMapper.findGoodsByClassify(goodsType, 0, num));
		return list;
	}
	
	/**����Ʒ������ʾ����ҳ*/
	public Page showGoodsInfo(String goodsType,Integer currentPageNo,Integer currentPageNum){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goodsType", goodsType);
		map.put("currentPageNo", currentPageNo);
		map.put("currentPageNum", currentPageNum);
		return goodsPage.getPage(map);
	}
	
	/**��ѯĳ����Ʒ������*/
	public Goods showGoodsDetail(String goodsId,String cusId) {
		//��ѯ����Ʒ�Ļ�����Ϣ
		Goods goods = goodsMapper.findGoodsById(goodsId);
		//ͨ����Ʒ����Ŀ��¼�û���ϲ��
		if(cusId != null) {
			Guess guess = new Guess();
			guess.setGuessId(NewIdUtil.getId());
			guess.setGuessKeyword(goods.getGoodsType());
			guess.setGuessCustomerId(cusId);
			guessMapper.insert(guess);
		}
		return goods;
	}
	
	/**��ѯ���˼���Ʒ�����ߵ�Ĭ�ϵ�ַ*/
	public Address findDefAddressByCusId(String cusId) {
		return addressMapper.findDefAddressByCusId(cusId);
	}
	
	/**��ѯ��ĳ����Ʒ��������Ϣ*/
	public Page showCommet(String goodsId,Integer currentPageNo,Integer currentPageNum) {
		return commentPage.getPage(goodsId, currentPageNo, currentPageNum);
	}
	
	/**���һ������*/
	public boolean addComment(String cusId,String goodsId,String addCommentInfo) {
		Comment comment = new Comment();
		//���ñ��
		comment.setcommentId(NewIdUtil.getId());
		//����������
		Customer customer = customerMapper.findCustomerById(cusId);
		comment.setcommentCustomer(customer);
		//�������۵���Ʒ
		Goods goods = goodsMapper.findGoodsById(goodsId);
		comment.setcommentGoods(goods);
		//�������۵�����
		comment.setcommentInfo(addCommentInfo);
		
		return commentMapper.addComment(comment);
	}
	
	/**ɾ��һ������*/
	public boolean delComment(String commentId) {
		return commentMapper.delCommentById(commentId);
	}
	
	/**��������ѯ��Ʒ*/
	public Page customerSearch(String goodsType,String payType,String priceOrderBy,String lowPrice,String topPrice,
			String goodsName,String goodsAddress,Integer currentPageNo,Integer currentPageNum,String cusId) {
		//�����еĲ�ѯ��������һ��map��
		Map<String,Object> map=new HashMap<String,Object>();
		
		if(goodsType!=null&&!goodsType.trim().equals("����")&&!goodsType.trim().equals("")&&!goodsType.equals("null")) {
			map.put("goodsType", goodsType);
		}
		if(payType!=null&&!payType.trim().equals("����")&&!payType.trim().equals("")&&!payType.equals("null")) {
			map.put("payType", payType );  
		}
		if(priceOrderBy!=null&&!priceOrderBy.trim().equals("")&&!priceOrderBy.equals("null")) {
			if(priceOrderBy.equals("�ɸߵ���")) {
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
        
		return goodsPage2.getPage(map,cusId);//cusId�����ж��Ƕ����ղع�ϵ
	}
	
	/**���һ���ղع�ϵ*/
	public boolean addCollection(String cusId,String goodsId) {
		String collectionId = NewIdUtil.getId();//��ȡһ�����
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("collectionId", collectionId);
		map.put("cusId", cusId);
		map.put("goodsId", goodsId);
		
		return collectionMapper.addCollection(map);
	}
	
	/**ɾ��һ���ղع�ϵ*/
	public boolean removeCollection(String cusId,String goodsId) {
		return collectionMapper.removeCollection(cusId,goodsId);
	}
	
	/**��ѯ���˿ͳ����е���Ʒ*/
	public List<Goods> findByBycusIdAndStatus(String cusId,String status,String orderBy){
		Map<String,String> map = new HashMap<String,String>();
		map.put("cusId", cusId);
		map.put("goodsStatus", status);
		map.put("orderBy", orderBy);
		return goodsMapper.findGoodsBycusIdAndStatus(map);
	}
	
	/**ˢ��һ����Ʒ*/
	public Boolean refreshGoods(String goodsId) {
		Goods goods = goodsMapper.findGoodsById(goodsId);
		goods.setGoodsStatus("������");
		if(Integer.parseInt(goods.getGoodsTotal())<=0) {
			goods.setGoodsTotal("1");			
		}
		goods.setGoodsAddtime(new Date());
		return goodsMapper.updateGoods(goods);
	}
	
	/**ɾ��һ����Ʒ������Ʒ��״̬��Ϊ�����Ƴ�*/
	public Boolean delGoods(String goodsId) {
		Goods goods = goodsMapper.findGoodsById(goodsId);
		goods.setGoodsStatus("���Ƴ�");
		goods.setGoodsAddtime(new Date());
		return goodsMapper.updateGoods(goods);
	}
	
	/**��������һ����Ʒ*/
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
			newGoods.setGoodsStatus("������");
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
				String tempStatus = (status =="1")?"������":(status =="2"?"���۳�":"���Ƴ�");
				goods.setGoodsStatus(tempStatus);
			}
			
			goodsMapper.updateGoods(goods);
		}
		return gid;
	}
	
	//�����ܵ��ķ���ת������������
	public String classfiyNumberToString(String numberCode) {
		String str = ""; 
		switch (numberCode) {
		case "01":str = "�鼮";
			break;
		case "02":str = "�����豸";
			break;
		case "03":str = "����";
			break;
		case "04":str = "����Ʒ";
			break;
		case "05":str = "����ר��";
			break;
		case "06":str = "Ů��ר��";
			break;
		default:str = "�޷���";
			break;
		}
		return str;
	}
	
	//��up downת������������
	public String payTypeNumberToString(String numberCode) {
		String str = ""; 
		switch (numberCode) {
		case "up":str = "���Ͻ���";
			break;
		case "down":str = "���½���";
			break;
		default:str = "�޽�������";
			break;
		}
		return str;
	}
}
