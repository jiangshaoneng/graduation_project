package com.jiang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiang.beans.Images;

public interface ImagesMapper {

	/**通过商品的编号查找主图*/
	public List<Images> findImgByGoodsId(String goodsId);
	
	/**通过商品的编号该商品所有图*/
	public List<Images> findAllImgByGoodsId(String goodsId);
	
	/**新增一组图片*/
	public boolean addImgs(@Param("imgs") List<Images> imgs);
	
	/**修改图片*/
	public boolean updateImg(Images img);
	
	/**删除,一或多张 图片通过imgsId*/
	public boolean delImgsByIds(@Param("imgIdList")List<String> imgIdList);
}
