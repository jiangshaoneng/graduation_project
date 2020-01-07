package com.jiang.dao;

import java.util.List;

import com.jiang.beans.Guess;

public interface GuessMapper {
	
	/**插入一条关键字*/
	public boolean insert(Guess guess);
	
	/**查询耨个用户的喜好关键字：最近的前4个关键字*/
	public List<Guess> findListBycusId(String cusId);
	
}
