package com.jiang.dao;

import java.util.List;

import com.jiang.beans.Guess;

public interface GuessMapper {
	
	/**����һ���ؼ���*/
	public boolean insert(Guess guess);
	
	/**��ѯ����û���ϲ�ùؼ��֣������ǰ4���ؼ���*/
	public List<Guess> findListBycusId(String cusId);
	
}
