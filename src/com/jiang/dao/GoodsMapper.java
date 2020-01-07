package com.jiang.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jiang.beans.Goods;

public interface GoodsMapper {

	/**添加一件商品*/
	public boolean addGoods(Goods goods);
	
	/**修改商品的信息;删除商品公用此方法,修改商品的状态为(已移除)*/
	public boolean updateGoods(Goods goods);
	
	/**根据用户编号与商品的状态,查找商品，并按排序时间 ;
	 * 三个参数: String cusId,String goodsStatus,String orderBy*/
	public List<Goods> findGoodsBycusIdAndStatus(Map<String,String> map);
	
	/**查询一件商品的信息*/
	public Goods findGoodsById(String goodsId);
	
	/**查询数量    商品的高级查询 条件包括:商品的类型 交易方式 价格排序方式 价格区间 高,低 商品的地址 商品描述*/
	public Integer findGoodsCountByClassify(Map<String,Object> map);
	
	/**查询某一类商品的数量1,商品种类 2,分页的起始条 3,每页的数量*/
	public List<Goods> findGoodsByClassify(String classify,Integer currentPageStart, Integer currentPageNum);

	/**商品的高级查询 条件包括:商品的类型 交易方式 价格排序方式 价格区间 高,低 商品的地址 商品描述*/
	public List<Goods> findGoodsByMycondition(Map<String,Object> map);

	/**批量更新商品的库存*/
	public boolean updateBatch(@Param("goodsList") List<Goods> goodsList);
	
	/**管理员查询商品数量*/
	public Integer managefindGoodsCount(Map<String,Object> map);
	
	/**管理员查询商品*/
	public List<Goods> managefindGoods(Map<String,Object> map);
	
	/**管理员修改商品的状态*/
	public boolean manageUpdateStatus(String status,String goodsId);
	
}
