package com.jiang.servicepage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jiang.beans.Goods;
import com.jiang.dao.GoodsMapper;

@Repository
public class GoodsPage {
	@Autowired
	private GoodsMapper goodsMapper;
	
	public Page getPage(Map<String,Object> map) {//, currentPageStart, currentPageNum
		
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
		
		page.setList(goodsList);
		
		//����������
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
