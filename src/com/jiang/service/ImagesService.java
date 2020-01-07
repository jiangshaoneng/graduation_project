package com.jiang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.beans.Images;
import com.jiang.dao.ImagesMapper;

@Service
public class ImagesService {
	
	@Autowired
	private ImagesMapper imagesMapper;
	
	/**�������ͼƬ*/
	public Boolean batchInsertImgs(List<Images> imgs) {
		return imagesMapper.addImgs(imgs);
	}
	
	/**ɾ��һ�Ż����ͼƬ��ͨ��imgIds*/
	public Boolean batchDelImgs(String imgIds) {
		
		String[] ids = imgIds.split(",");
		List<String> imgIdList = new ArrayList<String>();
		for (String id : ids) {
			imgIdList.add(id);
		}
		return imagesMapper.delImgsByIds(imgIdList);
	}
}
