package com.jiang.servicepage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiang.beans.Collection;
import com.jiang.beans.Goods;
import com.jiang.dao.CollectionMapper;
import com.jiang.dao.CommentMapper;
import com.jiang.dao.GoodsMapper;

@Repository
public class GoodsPage2 {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private CollectionMapper collectionMapper;
	
	public Page getPage(Map<String,Object> map,String cusId) {//, currentPageStart, currentPageNum
		
		Page page = new Page();
		//����ÿҳ������
		Integer currentPageNum = (Integer)map.get("currentPageNum");
		page.setCurrentPageNum(currentPageNum);
		//���õ�ǰҳ
		Integer currentPageNo = (Integer)map.get("currentPageNo");
		page.setCurrentPageNo(currentPageNo);
		
		//ҳ������
		int currentPageStart = currentPageNum*(currentPageNo-1);
		map.put("currentPageStart", currentPageStart);
		List<Goods> goodsList = goodsMapper.findGoodsByMycondition(map);
		List<GoodsInfo> goodsInfoList = new ArrayList<GoodsInfo>();
		for (Goods goods : goodsList) {
			GoodsInfo goodsInfo = new GoodsInfo();
			String goodsId = goods.getGoodsId();
			//��ѯ������Ʒ��������
			Integer commentNum = commentMapper.findCountByGoodsId(goodsId);
			//��ѯ������Ʒ�Ƿ񱻸��û��ղ�
			if(goodsId!=null) {//�û���Ų�Ϊ��
				Collection collection = collectionMapper.findByCusIdAndGoodsId(cusId, goodsId);
				goodsInfo.setCollection(collection);
			}
			goodsInfo.setGoods(goods);
			goodsInfo.setcommentNum(commentNum);
			goodsInfoList.add(goodsInfo);
		}
		page.setList(goodsInfoList);
		
		//����������
		//String goodsType = (String)map.get("goodsType");
		int totalCount = goodsMapper.findGoodsCountByClassify(map);
		page.setTotalCount(totalCount);
		
		//������ҳ��
		int totalPage;
		if(totalCount<currentPageNum) {
			totalPage = 1;
		}else if(totalCount%currentPageNum==0){
			totalPage = totalCount/currentPageNum;
		}else {
			totalPage = (totalCount/currentPageNum)+1;
		}
		page.setTotalPage(totalPage);
		
		return page;
	}
}
