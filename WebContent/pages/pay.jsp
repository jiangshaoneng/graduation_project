<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>支付宝电脑网站支付</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/distpicker.data.js"></script>
	    <script src="${pageContext.request.contextPath}/js/distpicker.js"></script>
	    <script src="${pageContext.request.contextPath}/js/main.js"></script>
<style>
	#main{
		width:1024px;
		margin: 0px auto;
	}
 	.show div{
		margin-bottom: 10px;
	}
	.payInfo{
		width:80%;
		margin: 40px auto;
		box-shadow: 5px 5px 5px #CCCCCC;
	}
	.foot-ul li {
		list-style-type:none;
		width: 100%;
		text-align: center;
		color: #666;
	}
	
	.orderInfo{
		width:100%;
		height: 160px;
		overflow: hidden;
		border:1px solid #ccc;
		padding: 10px;
	}
	.orderInfo_left{
		float: left;
		width: 50%;
	}
	.orderInfo_right{
		float: right;
		width: 43%;
		margin-right: 7%;
	}
	.orderoptInfo{
		overflow: hidden;
		height: 112px;
		border:1px solid #ccc;
		/* box-shadow: 5px -5px 5px #ccc; */
	}
	.orderoptInfo_left{
		width: 25%;
		float: left;
	}
	.orderopt_img{
		margin:5px 10px;
		width: 160px;
		height: 100px;
	}
	.orderoptInfo_right{
		width: 75%;
		float: right;
	}
	.text{
		float: left;
		width: 25%;
	}
	table {
        width: 100%;
        float: left;
        table-layout:fixed;
        border:1px solid #ccc;
    }
	td{
		white-space: nowrap;text-overflow: ellipsis;overflow: hidden;
		border:8px solid #ffffff;
		margin: 5px 0px;
	}
/* 	.my-btn{
		padding: 5px 40px;
	} */
	#all-price{
		color:red;
		font-size: 20px;
	}
	.price{
		color:red;
		font-size: 20px;
	}
</style>
</head>
<body text=#000000 bgColor="#ffffff" leftMargin=0 topMargin=4>
	<!-- 一个隐藏表单 -->
	<form id="payInfo-form" action=${pageContext.request.contextPath}/pages/alipay/alipay.trade.page.pay.jsp method="post" target="_blank">
		<input type="hidden" id="WIDout_trade_no" name="WIDout_trade_no" value="${orderInfo.orderId}"/>
		<input type="hidden" id="WIDsubject" name="WIDsubject" value="${orderInfo.orderId}"/>
		<input type="hidden" id="WIDtotal_amount" name="WIDtotal_amount" value="${orderInfo.orderTotalprice}"/>
		<input type="hidden" id="WIDbody" name="WIDbody" />
	</form>
	
	<div id="main">
		<div class="panel panel-primary payInfo">
		    <div class="panel-heading">
		        <h3 class="panel-title">支付页面</h3>
		    </div>
		    <div class="panel-body">
					<div id="body1" class="show" name="divcontent">
						<div class="orderInfo">
							<div class="orderInfo_left">
							<div class="">
					            <span class="">订单编号 ：</span>
					            <span>${orderInfo.orderId}</span>
					        </div>
					        
					        <div class="">
					            <span class="">订单名称 ：</span>
					            <span>orderName</span>
					        </div>
							
							<div class="">
					            <span class="">付款金额 ：</span>
					            <span id="all-price">${orderInfo.orderTotalprice}</span>
					        </div>

							<div class="">
					            <span class="">商品描述 ：</span>
					            <span>描述详情</span>
					        </div>
					        </div>
					        					        
					        <div class="orderInfo_right">
					        	<div>
						        	<button class="btn btn-primary my-btn" type="button" onclick="pay('alipay')">支付宝支付</button>
						        	<button class="btn btn-success my-btn" type="button" onclick="pay('balance')">余额支付</button>
					        	</div>
					        	<div class="input-group">
						        	<select class="form-control" id="addressInfo">
						        		<c:forEach items="${addressList}" var="address">
						        			<option value="${address.addressId}">${address.addressProvince}${address.addressCity}${address.addressDistrict}${address.addressDescInfo}</option>					        		
						        		</c:forEach>
						        	</select>
						        	<span class="input-group-addon" onclick="showAddAddress()">新建地址</span>
					        	</div>
					        	<div class="alert alert-warning">
								    <a href="#" class="close" data-dismiss="alert">
								        &times;
								    </a>
								    	确认您的购买的商品无误后,进行支付~
								</div>
					        </div>
					    </div>
					        <!-- 订单详情 -->
					    <c:forEach items="${orderInfo.orderoptList}" var="orderopt">
					        <div class="orderoptInfo">
					        	<div class="orderoptInfo_left">
					        		<img class="orderopt_img" alt="" src="${pageContext.request.contextPath}/goodsImgs/${orderopt.orderoptGoods.images[0].imgUrl}" />
					        	</div>
					        	<div class="orderoptInfo_right">
					        		<table>
					        			<tbody>
						        			<tr>
						        				<td width="25%" title="${orderopt.orderoptGoods.goodsName}">商品:${orderopt.orderoptGoods.goodsName}</td>
						        				<td width="25%" title="${orderopt.orderoptGoods.goodsPrice}">单价:${orderopt.orderoptGoods.goodsPrice}</td>
						        				<td width="25%" title="${orderopt.orderoptNum}">数量:${orderopt.orderoptNum}</td>
						        				<td width="25%" title="${orderopt.orderoptGoods.goodsPrice*orderopt.orderoptNum}">
						        					总价:<span class="price"><fmt:formatNumber value="${orderopt.orderoptGoods.goodsPrice*orderopt.orderoptNum}" pattern="0.00"/></span>
						        				</td>
						        			</tr>
						        			<tr>
						        				<td colspan="4">
						        					<textarea class="form-control comment" id="${orderopt.orderoptId}" style="width: 520px;" placeholder="给卖家留言"></textarea>
						        				</td>
						        			</tr>
					        			</tbody>
					        		</table>
					        	</div>
					        </div>
					     </c:forEach>
					</div>
		    </div>
		</div>
		
		<div id="foot">
			<ul class="foot-ul">
				<li>支付宝版权所有 2015-2018 ALIPAY.COM</li>
			</ul>
		</div>
	
	</div>
	
	<!-- 模态框 -->
	<div class="modal fade" id="add-Address" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">新增地址</h4>
	            </div>
	            <div class="modal-body">
				    <form class="form-inline">
				      <div data-toggle="distpicker">
				        <div class="form-group">
				          <label class="sr-only" for="province1">Province</label>
				          <select class="form-control" id="province1"></select>
				        </div>
				        <div class="form-group">
				          <label class="sr-only" for="city1">City</label>
				          <select class="form-control" id="city1"></select>
				        </div>
				        <div class="form-group">
				          <label class="sr-only" for="district1">District</label>
				          <select class="form-control" id="district1"></select>
				        </div><hr/>
				        <div class="input-group" style="display: block;">
				          <input class="form-control" id="descInfo" placeholder="详细地址"/>
				        </div>
				      </div>
				    </form>
				</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" onclick="addAddress()">确认</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<script language="javascript">
	
	//弹出模态框
	function showAddAddress(){
		$("#add-Address").modal("show");
	}
	
	//新增地址
	function addAddress(){//此处的新地址没有进入数据库
		var province1 = $("#province1").val();
		var city1 = $("#city1").val();
		var district1 = $("#district1").val();
		var descInfo = $("#descInfo").val();
		$.ajax({
			type : "POST", //提交方式 
			url : "${pageContext.request.contextPath}/orderAddAddress",//路径
			dateType : "text",//返回的数据类型
			data : {
				"addressProvince":province1,
				"addressCity":city1,
				"addressDistrict":district1,
				"addressDescInfo":descInfo
			},
			success : function(data) {
				if(data == "success"){
					var addressInfo = province1+city1+district1+descInfo;
					//把填写的信息添加到select的第一个位置
					var temp = "<option value=''>"+addressInfo+"</option>";
					$("#addressInfo option:first").before(temp);
					$("#addressInfo option:first").attr("selected", "selected");
					$("#add-Address").modal("hide");				
				}else{
					alert("新增地址失败！");
				}
			}
		}); 
	}
	
	//点击支付宝支付按钮
	function pay(payType){
		if(payType=="balance"&&${customer.cusBalance}<${orderInfo.orderTotalprice}){
			alert("余额不足");
			return false;			
		}

		var orderId = $("#WIDout_trade_no").val();
		var addressId = $("#addressInfo").val();
		var addressInfo = $("#addressInfo option:selected").text().trim();
		console.log(addressInfo);
		//获取留言
		var data = "[";
		var comment = $(".comment");
		for(var i = 0;i<comment.length;i++){
			var orderoptId = $(comment[i]).attr("id");
			var commentInfo = $(comment[i]).val();//留言信息
			data = data + "{orderoptId:'"+orderoptId+"',commentInfo:'"+commentInfo+"'},";
		}
		data = data.substring(0,data.length-1) + "]";
		console.log(data);
		//设置配送地址
		$.ajax({
			type : "POST", //提交方式 
			url : "${pageContext.request.contextPath}/addAddressAndCommentToOrder",//路径
			dateType : "text",//返回的数据类型
			data : {
				"addressId":addressId,//地址Id
				"addressInfo":addressInfo,//地址信息
				"orderId": orderId, //订单id
				"data":data,
				"payType":payType //alipay:支付宝支付;	balance：余额支付
			},
			success : function(data) {
				if(data == "alipay_success"){
					$("#payInfo-form").attr("action","${pageContext.request.contextPath}/pages/alipay/alipay.trade.page.pay.jsp")
					$("#payInfo-form").submit();//提交表单到支付宝页面					
				}else if(data == "balance_success"){
					$("#payInfo-form").attr("action","${pageContext.request.contextPath}/balancePay")
					$("#payInfo-form").submit();//提交表单到支付
				}else{
					alert("系统内部异常！");
				}
			}
		});
	}
	
	
</script>
</html>