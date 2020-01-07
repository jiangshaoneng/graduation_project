package com.jiang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiang.beans.Images;

public interface ImagesMapper {

	/**ͨ����Ʒ�ı�Ų�����ͼ*/
	public List<Images> findImgByGoodsId(String goodsId);
	
	/**ͨ����Ʒ�ı�Ÿ���Ʒ����ͼ*/
	public List<Images> findAllImgByGoodsId(String goodsId);
	
	/**����һ��ͼƬ*/
	public boolean addImgs(@Param("imgs") List<Images> imgs);
	
	/**�޸�ͼƬ*/
	public boolean updateImg(Images img);
	
	/**ɾ��,һ����� ͼƬͨ��imgsId*/
	public boolean delImgsByIds(@Param("imgIdList")List<String> imgIdList);
}
