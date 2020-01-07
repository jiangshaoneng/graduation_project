package com.jiang.beans;

import java.io.Serializable;

/** 评论表 */
public class Comment implements Serializable{
	
	private String commentId;//评论编号
	private String commentInfo;//评论内容
	private String commentAddtime;//评论时间
	
	/**评论的商品*/
	private Goods commentGoods;
	/**评论者*/
	private Customer commentCustomer;
	
	/////////////////////构造器
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(String commentId, String commentInfo, String commentAddtime) {
		super();
		this.commentId = commentId;
		this.commentInfo = commentInfo;
		this.commentAddtime = commentAddtime;
	}

	/////////////////////get/set
	public String getcommentId() {
		return commentId;
	}

	public void setcommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getcommentInfo() {
		return commentInfo;
	}

	public void setcommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}

	public String getcommentAddtime() {
		return commentAddtime;
	}

	public void setcommentAddtime(String commentAddtime) {
		this.commentAddtime = commentAddtime;
	}

	public Goods getcommentGoods() {
		return commentGoods;
	}

	public void setcommentGoods(Goods commentGoods) {
		this.commentGoods = commentGoods;
	}

	public Customer getcommentCustomer() {
		return commentCustomer;
	}

	public void setcommentCustomer(Customer commentCustomer) {
		this.commentCustomer = commentCustomer;
	}
	
	/////////////////////toString
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentInfo=" + commentInfo + ", commentAddtime="
				+ commentAddtime + "]";
	}
	
}
