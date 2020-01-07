package com.jiang.controller;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.jiang.beans.Address;
import com.jiang.beans.Customer;
import com.jiang.beans.Goods;
import com.jiang.beans.Images;
import com.jiang.service.CollectionService;
import com.jiang.service.GoodsService;
import com.jiang.service.ImagesService;
import com.jiang.servicepage.Page;
import com.jiang.utils.NewIdUtil;

@Controller
public class CustomerGoodsController {
	
	@Autowired
	private CollectionService collectionService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ImagesService imagesService;
	
	/**顾客收藏的商品信息*/
	@RequestMapping("customerCollection")
	public String customerCollection(HttpServletRequest request,Map<String,Object> map) {
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String cusId = customer.getcusId();
		
		//查询出收藏的商品信息
		Page collectionPage = collectionService.findCustomerCollection(cusId, 1,5);
		map.put("collectionPage", collectionPage);
		
		//查询出该顾客的所有地址,默认地址在最前面
		List<Address> addressList = collectionService.findCusAddress(cusId);
		map.put("addressList", addressList);
		
		return "customerCollection";
	}
	
	/**异步加载下一页信息,顾客收藏的商品信息*/
	@RequestMapping("moreCustomerCollection")
	public @ResponseBody Page moreCustomerCollection(HttpServletRequest request) {
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String cusId = customer.getcusId();
		String currentPageNo_str  = (String)request.getParameter("currentPageNo");
		Page collectionPage = null;
		if(currentPageNo_str!=null) {
			//分页查询用户已经收藏的商品(5个一页)
			Integer currentPageNo = Integer.parseInt(currentPageNo_str);
			collectionPage = collectionService.findCustomerCollection(cusId, currentPageNo,5);
		}
		return collectionPage;
	}
	
	/**顾客商品管理列表*/
	@RequestMapping("customerGoodsManage")
	public String customerGoodsManage(HttpServletRequest request,Map<String,Object> map) {
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String cusId = customer.getcusId();
		String goodsStatus = (String)request.getParameter("goodsStatus");
		List<Goods> goodsInfo = goodsService.findByBycusIdAndStatus(cusId, goodsStatus,"desc");
		map.put("goodsInfo", goodsInfo);
		map.put("goodsStatus", goodsStatus);
		return "customerGoodsManage";
	}
	
	@RequestMapping(value="refreshGoods",method=RequestMethod.GET)
	public @ResponseBody String refreshGoods(HttpServletRequest request) {
		String goodsId = (String)request.getParameter("goodsId");
		Boolean refreshFlag = goodsService.refreshGoods(goodsId);
		if(refreshFlag) 
			return "success";			
		else
			return "error";
	}
	
	/**顾客删除商品*/
	@RequestMapping(value="delGoods",method=RequestMethod.GET)
	public @ResponseBody String delGoods(HttpServletRequest request,Map<String,Object> map) {
		String goodsId = (String)request.getParameter("goodsId");
		Boolean delFlag = goodsService.delGoods(goodsId);
		if(delFlag) 
			return "success";			
		else
			return "error";
	}
	
	/**保存商品信息*/
	@RequestMapping(value="/addGoodsInfo",method=RequestMethod.POST)
	public @ResponseBody String addGoodsInfo(HttpServletRequest request) {		
		Customer customer = (Customer)request.getSession().getAttribute("customer");

		Map<String,Object> map = new HashMap<String,Object>(); 

		map.put("customer", customer);
		map.put("goodsId", (String)request.getParameter("goodsId"));
		map.put("goodsType", (String)request.getParameter("classfiy"));
		map.put("payType", (String)request.getParameter("payType"));
		map.put("name", (String)request.getParameter("name"));
		map.put("price", (String)request.getParameter("price"));
		map.put("num", (String)request.getParameter("num"));
		map.put("desc", (String)request.getParameter("desc"));
		
		String gid = goodsService.addOrUpdate(map);//添加或保存成功,返回goodsId
		return gid;
	}
	
	/**显示修改商品*/
	@RequestMapping(value="/showGoodsInfo",method=RequestMethod.POST)
	public @ResponseBody Goods showGoodsInfo(HttpServletRequest request) {
		String goodsId = (String)request.getParameter("goodsId");
		return goodsService.showGoodsDetail(goodsId,null);
	}
	
	/**上传商品图片 */
	@RequestMapping(value="/addGoodsImgs",method=RequestMethod.POST)
	public String addGoodsImgs(HttpServletRequest request,
			@RequestParam(value="file",required=false) MultipartFile[] file,
			@RequestParam(value="goodsId",required=false)String goodsId,
			@RequestParam(value="imgId",required=false)String imgId) throws NoSuchAlgorithmException {
		//删除用户选择的图片
		if(imgId!=null&&!imgId.trim().equals("")) {
			String imgIds = imgId.substring(0, imgId.length()-1);
			imagesService.batchDelImgs(imgIds);
		}
		//上传图片
		Boolean uplodeFlag = getFile(request, file,goodsId);
		if(uplodeFlag) 
			return "customerGoodsManage";
		else 
			return "error";
	} 
	
	@SuppressWarnings("all")
	private Boolean getFile(HttpServletRequest request,MultipartFile[] imgFiles,String goodsId) throws NoSuchAlgorithmException {
		
		if(imgFiles==null)
			return true;
		
		//保存添加的图片
		List<Images> imgs = new ArrayList<Images>();
		
		for(int i = 0;i<imgFiles.length;i++) {
			//获取文件的名称，再截取其扩展名
			String fileName = imgFiles[i].getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
			//对扩展名进行大小写转化
			ext = ext.toLowerCase();
		
			//得到保存的路径
			String path = request.getSession().getServletContext().getRealPath("goodsImgs");
			//获取MD5算法的MessageDigest对象
			MessageDigest mdinst = MessageDigest.getInstance("MD5");
			String pic_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String md5Code = mdinst.digest(pic_time.getBytes()).toString();
			//新的图片放入名称
			String newPicName =md5Code+"."+ext;
			
			File file = new File(path,newPicName);
			if(!file.exists()) {
				file.mkdirs();
			}
			//保存上传
			try {
				imgFiles[i].transferTo(file);
				Images img = new Images();
				img.setImgId(NewIdUtil.getId());
				img.setImgUrl(newPicName);
				img.setImgGoodsId(goodsId);
				imgs.add(img);
			} catch (IllegalStateException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return imagesService.batchInsertImgs(imgs);
	}
	
}
