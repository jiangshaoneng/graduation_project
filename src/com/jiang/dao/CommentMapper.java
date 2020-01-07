package com.jiang.dao;

import java.util.List;

import com.jiang.beans.Comment;

public interface CommentMapper {

	/**查询一件商品的留言数*/
	public Integer findCountByGoodsId(String goodsId);
	
	/**查询一件商品的所有留言(分页)：商品编号,当前页的起始页,当前页的条数*/
	public List<Comment> findPageCommentByGoodsId(String goodsId,Integer currentPageStart,Integer currentPageCount); 
	
	/**删除评论:通过评论编号*/
	public boolean delCommentById(String commentId);
	
	/**添加评论*/
	public boolean addComment(Comment comment);
}
