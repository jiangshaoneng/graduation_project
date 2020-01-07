package com.jiang.beans;

import java.io.Serializable;
import java.util.Date;

public class Guess implements Serializable{
	
	private String guessId;
	private String guessKeyword;
	private Date guessTime;
	private String guessCustomerId;
	
	private Customer guessCustomer;
	
	public Guess() {
		// TODO Auto-generated constructor stub
	}

	public Guess(String guessId, String guessKeyword, Date guessTime, String guessCustomerId) {
		super();
		this.guessId = guessId;
		this.guessKeyword = guessKeyword;
		this.guessTime = guessTime;
		this.guessCustomerId = guessCustomerId;
	}

	public String getGuessId() {
		return guessId;
	}

	public void setGuessId(String guessId) {
		this.guessId = guessId;
	}

	public String getGuessKeyword() {
		return guessKeyword;
	}

	public void setGuessKeyword(String guessKeyword) {
		this.guessKeyword = guessKeyword;
	}

	public Date getGuessTime() {
		return guessTime;
	}

	public void setGuessTime(Date guessTime) {
		this.guessTime = guessTime;
	}

	public String getGuessCustomerId() {
		return guessCustomerId;
	}

	public void setGuessCustomerId(String guessCustomerId) {
		this.guessCustomerId = guessCustomerId;
	}

	public Customer getGuessCustomer() {
		return guessCustomer;
	}

	public void setGuessCustomer(Customer guessCustomer) {
		this.guessCustomer = guessCustomer;
	}
	
	
	
}
