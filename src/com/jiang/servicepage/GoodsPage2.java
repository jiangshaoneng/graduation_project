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
		//设置每页的数量
		Integer currentPageNum = (Integer)map.get("currentPageNum");
		page.setCurrentPageNum(currentPageNum);
		//设置当前页
		Integer currentPageNo = (Integer)map.get("currentPageNo");
		page.setCurrentPageNo(currentPageNo);
		
		//页面数据
		int currentPageStart = currentPageNum*(currentPageNo-1);
		map.put("currentPageStart", currentPageStart);
		List<Goods> goodsList = goodsMapper.findGoodsByMycondition(map);
		List<GoodsInfo> goodsInfoList = new ArrayList<GoodsInfo>();
		for (Goods goods : goodsList) {
			GoodsInfo goodsInfo = new GoodsInfo();
			String goodsId = goods.getGoodsId();
			//查询出该商品的评论数
			Integer commentNum = commentMapper.findCountByGoodsId(goodsId);
			//查询出该商品是否被该用户收藏
			if(goodsId!=null) {//用户编号不为空
				Collection collection = collectionMapper.findByCusIdAndGoodsId(cusId, goodsId);
				goodsInfo.setCollection(collection);
			}
			goodsInfo.setGoods(goods);
			goodsInfo.setcommentNum(commentNum);
			goodsInfoList.add(goodsInfo);
		}
		page.setList(goodsInfoList);
		
		//设置总条数
		//String goodsType = (String)map.get("goodsType");
		int totalCount = goodsMapper.findGoodsCountByClassify(map);
		page.setTotalCount(totalCount);
		
		//设置总页数
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
