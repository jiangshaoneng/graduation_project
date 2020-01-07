package com.jiang.servicepage;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jiang.beans.Collection;
import com.jiang.dao.CollectionMapper;

@Repository
public class CollectionPage {
	@Autowired
	private Page page;
	@Autowired
	private CollectionMapper collectionMapper;
	
	public Page getPage(String cusId,Integer currentPageNo,Integer currentPageNum) {
		HashMap<String,Object> map =new HashMap<String,Object>();
		map.put("cusId", cusId);
		map.put("currentPageNum", currentPageNum);
		
		//����ÿҳ������
		page.setCurrentPageNum(currentPageNum);
		//���õ�ǰҳ
		page.setCurrentPageNo(currentPageNo);
		
		//ҳ������
		int currentPageStart = currentPageNum*(currentPageNo-1);//ͨ��ҳ��������ʼ��λ��
		map.put("currentPageStart", currentPageStart);
		List<Collection> collectionList = collectionMapper.findCollectionByCusId(map);
		/*List<CommentInfo> commentInfoList = new ArrayList<CommentInfo>();
		for (Comment comment : commentList) {
			CommentInfo commentInfo = new CommentInfo();
			Customer cus = comment.getcommentCustomer();
			commentInfo.setComment(comment);
			commentInfo.setCustomer(cus);
			commentInfoList.add(commentInfo);
		}*/
		page.setList(collectionList);
		
		//����������
		int totalCount = collectionMapper.countCollectionByCusId(cusId);
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
