package com.jiang.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jiang.beans.Goods;

public interface GoodsMapper {

	/**���һ����Ʒ*/
	public boolean addGoods(Goods goods);
	
	/**�޸���Ʒ����Ϣ;ɾ����Ʒ���ô˷���,�޸���Ʒ��״̬Ϊ(���Ƴ�)*/
	public boolean updateGoods(Goods goods);
	
	/**�����û��������Ʒ��״̬,������Ʒ����������ʱ�� ;
	 * ��������: String cusId,String goodsStatus,String orderBy*/
	public List<Goods> findGoodsBycusIdAndStatus(Map<String,String> map);
	
	/**��ѯһ����Ʒ����Ϣ*/
	public Goods findGoodsById(String goodsId);
	
	/**��ѯ����    ��Ʒ�ĸ߼���ѯ ��������:��Ʒ������ ���׷�ʽ �۸�����ʽ �۸����� ��,�� ��Ʒ�ĵ�ַ ��Ʒ����*/
	public Integer findGoodsCountByClassify(Map<String,Object> map);
	
	/**��ѯĳһ����Ʒ������1,��Ʒ���� 2,��ҳ����ʼ�� 3,ÿҳ������*/
	public List<Goods> findGoodsByClassify(String classify,Integer currentPageStart, Integer currentPageNum);

	/**��Ʒ�ĸ߼���ѯ ��������:��Ʒ������ ���׷�ʽ �۸�����ʽ �۸����� ��,�� ��Ʒ�ĵ�ַ ��Ʒ����*/
	public List<Goods> findGoodsByMycondition(Map<String,Object> map);

	/**����������Ʒ�Ŀ��*/
	public boolean updateBatch(@Param("goodsList") List<Goods> goodsList);
	
	/**����Ա��ѯ��Ʒ����*/
	public Integer managefindGoodsCount(Map<String,Object> map);
	
	/**����Ա��ѯ��Ʒ*/
	public List<Goods> managefindGoods(Map<String,Object> map);
	
	/**����Ա�޸���Ʒ��״̬*/
	public boolean manageUpdateStatus(String status,String goodsId);
	
}
