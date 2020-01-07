package com.jiang.servicepage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jiang.beans.Comment;
import com.jiang.beans.Customer;
import com.jiang.dao.CommentMapper;

@Repository
public class CommentPage {
	@Autowired
	private Page page;
	@Autowired
	private CommentMapper commentMapper;
	
	public Page getPage(String goodsId,Integer currentPageNo,Integer currentPageNum) {
		
		//����ÿҳ������
		page.setCurrentPageNum(currentPageNum);
		//���õ�ǰҳ
		page.setCurrentPageNo(currentPageNo);
		
		//ҳ������
		int currentPageStart = currentPageNum*(currentPageNo-1);
		List<Comment> commentList = commentMapper.findPageCommentByGoodsId(goodsId,currentPageStart, currentPageNum);

		page.setList(commentList);
		
		//����������
		int totalCount = commentMapper.findCountByGoodsId(goodsId);
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
