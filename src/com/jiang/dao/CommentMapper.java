package com.jiang.dao;

import java.util.List;

import com.jiang.beans.Comment;

public interface CommentMapper {

	/**��ѯһ����Ʒ��������*/
	public Integer findCountByGoodsId(String goodsId);
	
	/**��ѯһ����Ʒ����������(��ҳ)����Ʒ���,��ǰҳ����ʼҳ,��ǰҳ������*/
	public List<Comment> findPageCommentByGoodsId(String goodsId,Integer currentPageStart,Integer currentPageCount); 
	
	/**ɾ������:ͨ�����۱��*/
	public boolean delCommentById(String commentId);
	
	/**�������*/
	public boolean addComment(Comment comment);
}
