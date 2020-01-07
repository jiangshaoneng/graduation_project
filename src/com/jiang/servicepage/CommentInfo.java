package com.jiang.servicepage;

import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jiang.beans.Comment;
import com.jiang.beans.Customer;

/**��ʾ����������Ϣ��Comment,Customer*/
@Repository
public class CommentInfo{

	/**����*/
	private Comment comment;
	
	/**������*/
	private Customer customer;
	
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "CommentInfo [comment=" + comment + ", customer=" + customer + "]";
	}
}
