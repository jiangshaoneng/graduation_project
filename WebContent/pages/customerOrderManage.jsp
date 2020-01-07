<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>我的商品</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		
		<style type="text/css">
			.myOrderManage{
				width: 1024px;
				margin: 70px auto;
			}
			
			.orderopt{
				border: 1px solid #ccc;
				height: 120px;
				margin-top:10px;
				overflow: hidden;
			}
			.orderopt-img{
				margin:4px 10px;
				width: 160px;
				height: 110px;
			}
			.orderopt-left{
				float: left;
				width: 20%;
			}
			.orderopt-right{
				float: left;
				width: 80%;
			}
			.table-info{
				width: 100%;
			}
			.table-info tbody tr{
				height: 25px;
			}
			.orderoptfoot{
				height: 60px;
				overflow: hidden;
			}
			.orderoptfoot-left{
				width:85%;
				float: left;
				overflow: hidden;
			}
			.orderoptfoot-right{
				width:10%;
				margin-left:20px;
				float: right;
			}
			.price{
				color:red;
			}
			.status{
				color:orange;
			}
		</style>
		
	</head>
	<body>
		
		<!-- 我的商品管理 -->
		<div class="myOrderManage">
			<!-- 顶部栏 -->
			<div id="top_nav">
				<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
				    <div class="container-fluid">
					    <div class="navbar-header">
					        <a class="navbar-brand" href="${pageContext.request.contextPath}/showIndex">校园闲置物品交易平台</a>
					    </div>
					    <div>
					        <ul class="nav navbar-nav">
					            <li class="active"><a href="#">我的订单</a></li>
					            <li class="dropdown">
					                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
					                    	个人中心 <b class="caret"></b>
					                </a>
					                <ul class="dropdown-menu">
					                    <li><a href="${pageContext.request.contextPath}/customerInfo"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;个人信息</a></li>
						                <li><a href="${pageContext.request.contextPath}/customerCollection"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;我的收藏</a></li>
						                <li><a href="${pageContext.request.contextPath}/customerGoodsManage"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;我的商品</a></li>
						                <li><a href="${pageContext.request.contextPath}/customerOrderManage"><span class="glyphicon glyphicon-bookmark"></span>&nbsp;&nbsp;我的订单</a></li>
						                <li><a href="${pageContext.request.contextPath}/customerMsgManage"><span class="glyphicon glyphicon-bullhorn"></span>&nbsp;&nbsp;我的消息</a></li>
						                <li class="divider"></li>
						                <li><a href="${pageContext.request.contextPath}/nofilter_customerLogout"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
					                </ul>
					            </li>
					        </ul>
					    </div>
				    </div>
				</nav>
			</div>
			
			
			<div>
				<div class="panel panel-primary">
				    <div class="panel-heading">
				        <h3 class="panel-title">我的订单</h3>
				    </div>
				    <div class="panel-body">
			<!-- 查询条件 -->			
				        <div>
							<form class="form-inline" role="form">
							  <div class="form-group">
							  	<!-- 订单的状态 ：未支付,已支付, -->
							    <select class="form-control" id="select-status">
							    	<option value="">全部</option>
							    	<option value="待付款">待付款</option>
							    	<option value="待发货" selected="selected">待发货</option>
							    	<option value="待收货">待收货</option>
							    	<option value="待评价">待评价</option>
							    	<option value="已完成">已完成</option>
							    </select>
							  </div>
							  <button type="button" class="btn btn-primary" onclick="findByStatus()">查询</button>
							</form>
						</div>
			<!-- 订单信息 -->
						<div id="order">
						<c:forEach items="${page.list}" var="orderopt">
							<div class="orderopt">
								<div class="orderopt-left">
									<img class="orderopt-img" src="${pageContext.request.contextPath}/goodsImgs/${orderopt.orderoptGoods.images[0].imgUrl}">
								</div>
								<div class="orderopt-right">
									<table class="table-info">
										<tr>
											<td colspan="4">
												<span>订单编号：${orderopt.orderoptId}</span>
												&nbsp;&nbsp;&nbsp;
												<span class="status">${orderopt.orderoptStatus}</span>
												&nbsp;&nbsp;&nbsp;
												
												<button type="button" class="btn btn-info btn-xs" title="联系电话"  
														data-container="body" data-toggle="popover" data-placement="top" 
														data-content="${orderopt.orderoptGoods.goodsCustomer.cusPhone}">
													联系卖家
												</button>

											</td>
										</tr>
										<tr>
											<td width="25%">商品名称:${orderopt.orderoptGoods.goodsName}</td>
											<td width="25%">价格:<span class="price">￥${orderopt.orderoptGoods.goodsPrice}</span></td>
											<td width="25%">数量:×${orderopt.orderoptNum}</td>
											<td width="25%">总价:<span class="price">￥${orderopt.orderoptPrice}</span></td>
										</tr>
									</table>
									<div class="orderoptfoot">
										<div class="orderoptfoot-left">
											备注信息：${orderopt.orderoptComment}
										</div>
										<div class="orderoptfoot-right">
											<c:if test="${orderopt.orderoptStatus =='待付款'}">
												<button type="button" onclick="goPay('${orderopt.orderoptId}',this)" class="btn btn-primary btn-xs">立即支付</button>
												<hr style="margin: 2px"/>
  												<button type="button" onclick="canclePay('${orderopt.orderoptId}',this)" class="btn btn-default btn-xs">取消支付</button>
											</c:if>
											<c:if test="${orderopt.orderoptStatus =='待收货'}">
  												<button type="button" onclick="sureDelivery('${orderopt.orderoptId}',this)" class="btn btn-danger btn-xs">确认收货</button>
											</c:if>
											<c:if test="${orderopt.orderoptStatus =='待评价'}">
  												<button type="button" onclick="goComment('${orderopt.orderoptId}',this)" class="btn btn-warning btn-xs">去评价</button>
											</c:if>
											
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						</div>
					
				    </div>
				</div>
			</div>
			
		</div>

<script type="text/javascript">
	$(function(){
		bindScrollEvent();
		//提示框
		$("[data-toggle='popover']").popover();
	})
	
	var currentPageNo = 1;//记录当前的页数
	overFlag = false;
	//滚动获取更多数据
	 function bindScrollEvent(){ 
		//添加滚动事件
		$(window).scroll(function(){
			if(overFlag)
				return false;
			
			var docHeight = $(document).height();//获取页面文档的高度
			var winHeight = $(window).height();//获取当前窗体的高度
			var winScorllHeight = $(window).scrollTop();//获取滚动条滚动的距离
			if(docHeight==winHeight+winScorllHeight){
				var status = $("#select-status").val();
				//加载下一页的数据currentPageNo表示页码
				currentPageNo++;
				//加载更多
				$.ajax({ 
					   type : "POST", //提交方式 
					   url : "${pageContext.request.contextPath}/findOrderoptByStaus",//路径
					   dateType:"JSON",//返回的数据类型
					   data : {
						   "pageNo":currentPageNo,
						   "status":status
					   },
					   success : function(data) {
						   var temp = "";
						   if(data.list.length>0){
							   temp = returnTemp(data);
						   }else{
							   temp = temp + "<div id='myAlert' class='alert alert-success'>";
		    				   temp = temp + "<a href=''#' class='close' data-dismiss='alert'>&times;</a>";
		    				   temp = temp + "<strong>提示:</strong>没有更多订单数据了！";
		    				   temp = temp + "</div>";
		    				   overFlag = true;
						   }
						   $("#order:last-child").append(temp);
						   $("[data-toggle='popover']").popover();
					   }
				});
			}
		});
	} 
	
    	//拼接字符串
    	function returnTemp(data){
    		var temp = "";
			for(var i = 0;i < data.list.length;i++){
				temp = temp + "<div class='orderopt'>";
				temp = temp + "<div class='orderopt-left'>";
				temp = temp + "<img class='orderopt-img' src='${pageContext.request.contextPath}/goodsImgs/"+data.list[i].orderoptGoods.images[0].imgUrl+"'>";
				temp = temp + "</div>";
				temp = temp + "<div class='orderopt-right'>";
				temp = temp + "<table class='table-info'>";
				temp = temp + "<tr>";
				temp = temp + "<td colspan='4'>";
				temp = temp + "<span>订单编号："+data.list[i].orderoptId+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				temp = temp + "<span class='status'>"+data.list[i].orderoptStatus+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				temp = temp + "<button type='button' class='btn btn-info btn-xs' title='联系电话'";
				temp = temp + "data-container='body' data-toggle='popover' data-placement='top'";
				temp = temp + "data-content='"+data.list[i].orderoptGoods.goodsCustomer.cusPhone+"'>";
				temp = temp + "联系卖家</button>";
				temp = temp + "</td>";
				temp = temp + "</tr>";
				temp = temp + "<tr>";
				temp = temp + "<td width='25%'>商品名称:"+data.list[i].orderoptGoods.goodsName+"</td>";
				temp = temp + "<td width='25%'>价格:<span class='price'>￥"+data.list[i].orderoptGoods.goodsPrice+"</span></td>";
				temp = temp + "<td width='25%'>数量:×"+data.list[i].orderoptNum+"</td>";
				temp = temp + "<td width='25%'>总价:<span class='price'>￥"+data.list[i].orderoptPrice+"</span></td>";
				temp = temp + "</tr>";	
				temp = temp + "</table>";	
				temp = temp + "<div class='orderoptfoot'>";
				temp = temp + "<div class='orderoptfoot-left'>";
				if(typeof(data.list[i].orderoptComment)=="undefined")
					temp = temp + "备注信息：";					
				else
					temp = temp + "备注信息："+data.list[i].orderoptComment;
				temp = temp + "</div>";
				temp = temp + "<div class='orderoptfoot-right'>";
				if(data.list[i].orderoptStatus=="待付款"){
					temp = temp + '<button type="button" onclick="goPay('+"'"+data.list[i].orderoptId+"'"+',this)" class="btn btn-primary btn-xs">立即支付</button>';
					temp = temp + "<hr style='margin: 2px;'/>";
					temp = temp + '<button type="button" onclick="canclePay('+"'"+data.list[i].orderoptId+"'"+',this)" class="btn btn-default btn-xs">取消支付</button>';
				}
				if(data.list[i].orderoptStatus=="待收货"){
					temp = temp + '<button type="button" onclick="sureDelivery('+"'"+data.list[i].orderoptId+"'"+',this)" class="btn btn-danger btn-xs">确认收货</button>';
				}
				if(data.list[i].orderoptStatus=="待评价"){
					temp = temp + '<button type="button" onclick="goComment('+"'"+data.list[i].orderoptId+"'"+',this)" class="btn btn-warning btn-xs">去评价</button>';
				}
				temp = temp + "</div>";
				temp = temp + "</div>";
				temp = temp + "</div>";
				temp = temp + "</div>";
			}
			return temp;
    	}
    	
    	//查询订单项
        function findByStatus(){
        	currentPageNo = 1;
        	overFlag = false;
        	var status = $("#select-status").val();
        	$.ajax({
    			type : "POST", //提交方式 
    			url : "${pageContext.request.contextPath}/findOrderoptByStaus",//路径
    			dateType : "json",//返回的数据类型
    			data : {
    				"status":status
    			},
    			success : function(data) {
    				$(".orderopt").remove();
    				var temp = "";
    				if(data.list.length>0){
	    				temp = returnTemp(data);
    				}
    				else{
    					temp = temp + "<div id='myAlert' class='alert alert-success'>";
    					temp = temp + "<a href=''#' class='close' data-dismiss='alert'>&times;</a>";
    					temp = temp + "<strong>提示:</strong>无此状态订单！";
    					temp = temp + "</div>";
    				}
	    			$("#order").html(temp);
	    			$("[data-toggle='popover']").popover();
    			}
    		});
    }
    
    //立即支付
    function goPay(orderoptId,_this){
    	var _this = $(_this);
    	$.ajax({
			type : "POST", //提交方式 
			url : "${pageContext.request.contextPath}/goPay",//路径
			dateType : "json",//返回的数据类型
			data : {
				"orderoptId":orderoptId
			},
			success : function(orderId) {
				if(orderId.length>0){
					//跳转到支付页面
				 	window.location.href="${pageContext.request.contextPath}/pay?orderId="+orderId;
					//alert("订单提交成功！"+data);
				}else{
					alert("系统内部异常！");
				}
			}
		});
    }
    
    //取消支付
    function canclePay(orderoptId,_this){
    	var _this = $(_this);
    	$.ajax({
			type : "POST", //提交方式 
			url : "${pageContext.request.contextPath}/canclePay",//路径
			dateType : "json",//返回的数据类型
			data : {
				"orderoptId":orderoptId
			},
			success : function(data) {
				if(data == 'success'){
					_this.parent().parent().parent().parent().slideUp();
				}else{
					alert("系统内部异常！");
				}
			}
		});
    }
    
    //确认收货
    function sureDelivery(orderoptId,_this){
    	var _this = $(_this);
    	$.ajax({
			type : "POST", //提交方式 
			url : "${pageContext.request.contextPath}/sureDelivery",//路径
			dateType : "json",//返回的数据类型
			data : {
				"orderoptId":orderoptId
			},
			success : function(data) {
				if(data == 'success'){
					_this.parent().parent().parent().parent().slideUp();
				}else{
					alert("系统内部异常！");
				}
			}
		});
    }
    
    //去评价
    function goComment(orderoptId,_this){
    	var _this = $(_this);
    	$.ajax({
			type : "POST", //提交方式 
			url : "${pageContext.request.contextPath}/goComment",//路径
			dateType : "json",//返回的数据类型
			data : {
				"orderoptId":orderoptId
			},
			success : function(data) {
				if(data == 'success'){
					_this.parent().parent().parent().parent().slideUp();
				}else{
					alert("系统内部异常！");
				}
			}
		});
    }
    
    
</script>
	
</body>
</html>