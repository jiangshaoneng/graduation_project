<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

<style>
#search {
	width: 1024px;
	margin: 70px auto;
	margin-bottom: 0px;
}

#search_filter {
	width: 15%;
	float: left;
	position: fixed;
	padding: 10px;
}

#search_result {
	width: 80%;
	float: right;
	/*margin: 70px auto;
				margin-bottom: 0px;
				width: 1024px;*/
}

.search_count {
	font-size: 8px;
	margin-left: 20px;
}

.search_item {
	width: 90%;
	height: 180px;
	border: 1px solid #CCCCCC;
	margin: 20px auto;
	box-shadow: 5px 5px 5px #CCCCCC;
	overflow: hidden;
}

.search_item:hover {
	border: 1px solid #337AB7;
}

.search_item_img {
	float: left;
}

.search_item_img img {
	width: 200px;
	height: 133px;
	margin: 20px 20px;
}

.search_item_info {
	float: left;
	width: 400px;
	height: 133px;
	margin: 10px;
	padding: 5px 10px;
}

.item_info_price span {
	padding-right: 30px;
}

.price {
	color: red;
	font-size: 18px;
}

.search_item_handle {
	float: left;
	width: 100px;
	height: 133px;
	margin: 20px 0px;
	padding: 10px 5px;
}

.item_title {
	font-size: 18px;
}

.goods_des {
	margin: 5px 0px;
	color: #666;
	height: 34px;
	font-size: 8px;
	overflow: hidden;
}

.sellcustomer_info {
	margin-top: 10px;
	font-size: 8px;
	overflow: hidden;
}

.score {
	padding-left: 60px;
	color: #EE9C3F;
}

.sellcustomer_info2 span {
	font-size: 8px;
	padding-right: 80px;
}

.search_input {
	width: 100px;
	height: 26px;
}
</style>

</head>
<body>

	<!-- 顶部栏 -->
	<div id="search_top">
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/showIndex"> 校园闲置物品交易平台</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<!-- 对于用户是否登录给出两总不同的显示 -->
				<c:if test="${empty sessionScope.customer}" var="cus">
					<li><a
						href="${pageContext.request.contextPath}/pages/customerLogin.jsp"><span
							class="glyphicon glyphicon-log-in"></span> 登录</a></li>
				</c:if>
				<c:if test="${!cus}">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> ${sessionScope.customer.cusName} <b
							class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/customerInfo"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;个人信息</a></li>
						    <li><a href="${pageContext.request.contextPath}/customerCollection"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;我的收藏</a></li>
						    <li><a href="${pageContext.request.contextPath}/customerGoodsManage"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;我的商品</a></li>
						    <li><a href="${pageContext.request.contextPath}/customerOrderManage"><span class="glyphicon glyphicon-bookmark"></span>&nbsp;&nbsp;我的订单</a></li>
						    <li><a href="${pageContext.request.contextPath}/customerMsgManage"><span class="glyphicon glyphicon-bullhorn"></span>&nbsp;&nbsp;我的消息</a></li>
						    <li class="divider"></li>
						    <li><a href="${pageContext.request.contextPath}/nofilter_customerLogout"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
						</ul></li>
				</c:if>
				<li><a
					href="${pageContext.request.contextPath}/pages/customerRegister.jsp"><span
						class="glyphicon glyphicon-user"></span> 注册</a></li>

			</ul>
		</div>
		</nav>

	</div>

	<div id="search">
		<!-- 查询条件-->
		<div id="search_filter">
			<div class="search_info">
				<form class="bs-example bs-example-form" role="form" method="post"
					action="${pageContext.request.contextPath}/nofilter_customerSearch">
					<div class="input-group input-group-sm">
						<span class="input-group-addon">商品类别</span> <select
							class="form-control" name="goodsType">
							<option>不限</option>
							<option>电子设备</option>
							<option>日用品</option>
							<option>衣物</option>
							<option>书籍</option>
							<option>男生专区</option>
							<option>女生专区</option>
						</select>
					</div>
					<div class="input-group input-group-sm">
						<span class="input-group-addon">交易方式</span> <select
							class="form-control" name="payType">
							<option>不限</option>
							<option>线上交易</option>
							<option>线下交易</option>
						</select>
					</div>
					<div class="input-group input-group-sm">
						<span class="input-group-addon">价格区间</span> <select
							class="form-control" name="priceOrderBy">
							<option>由低到高</option>
							<option>由高到低</option>
						</select> <input type="text" name="lowPrice" class="form-control"
							placeholder="最低价" onkeyup="value=value.replace(/[^\d]/g,'') "
							ng-pattern="/[^a-zA-Z]/" /> <input type="text" name="topPrice"
							class="form-control" placeholder="最高价"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							ng-pattern="/[^a-zA-Z]/" />
					</div>
					<div class="input-group input-group-sm">
						<span class="input-group-addon">商品地址</span> <input type="text"
							name="goodsAddress" class="form-control" placeholder="商品地址">
					</div>
					<div class="input-group input-group-sm">
						<span class="input-group-addon">商品名称</span> <input type="text"
							name="goodsName" class="form-control" placeholder="商品名称">
					</div>
					<br> <input class="btn btn-primary btn-block" type="submit"
						value="查找商品" onclick="handlerPrice()" />
				</form>
			</div>
		</div>
		<!-- 查询结果 -->
		<div id="search_result">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						查询结果<span class="search_count">共${searchGoodsPage.totalCount}件商品符合您的条件</span>
					</h3>
				</div>
				<div class="panel-body" id="search_result_panel">
					<!-- 查询出的商品列表 -->
					<c:forEach items="${searchGoodsPage.list}" var="goodsInfo"
						varStatus="i">
						<div class="search_item">

							<div class="search_item_img">
								<a href="showGoodsDetail/${goodsInfo.goods.goodsId}" target="_blank">
								<img
									src="${pageContext.request.contextPath}/goodsImgs/${goodsInfo.goods.images[0].imgUrl}" />
								</a>	
							</div>
							<div class="search_item_info">
								<div class="item_title">
									<span>${goodsInfo.goods.goodsName}</span>
								</div>
								<div class="item_info_price">
									价格：<span class="price">${goodsInfo.goods.goodsPrice}元</span><span>数量：${goodsInfo.goods.goodsTotal}件</span><a
										href="#"><span>留言：${goodsInfo.commentNum}条</span></a>
								</div>
								<div class="goods_des">
									<span>描述：${goodsInfo.goods.goodsInfo}</span>
								</div>
								<div class="sellcustomer_info">
									<a href="#"><span>卖家昵称：${goodsInfo.goods.goodsCustomer.cusName}</span></a>
									<span class="score">卖家类型：${goodsInfo.goods.goodsCustomer.cusType}</span><br>
									<span>地址：${goodsInfo.goods.goodsAddress}</span><br> <span>交易方式：${goodsInfo.goods.goodsPaytype}</span>
								</div>
							</div>
							<div class="search_item_handle">

								<c:if test="${empty sessionScope.customer}" var="cus">
									<a
										href="${pageContext.request.contextPath}/pages/customerLogin.jsp"
										class="btn btn-default btn-block">收藏商品</a>
								</c:if>
								<c:if test="${!cus}">
									<c:if test="${empty goodsInfo.collection}" var="collection">
										<c:if test="${sessionScope.customer.cusId != goodsInfo.goods.goodsCustomer.cusId}" var="flag">										
										<button class="btn btn-default btn-block"
											onclick="addCollection(${sessionScope.customer.cusId},${goodsInfo.goods.goodsId},this)">收藏商品</button>
										</c:if>
									</c:if>
									<c:if test="${!collection}">
										<button class="btn btn-warning btn-block"
											onclick="removeCollection(${sessionScope.customer.cusId},${goodsInfo.goods.goodsId},this)">已收藏</button>
									</c:if>
								</c:if>
								<a href="showGoodsDetail/${goodsInfo.goods.goodsId}" target="_blank"
									class="btn btn-success btn-block">查看详情</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
			
			$(function(){
				init();
			})
			
			//初始化方法
			function init(){
				//添加滚动事件
				bindScrollEvent();
				//初始化鼠标移动在商品上的动画
				addAnimate();
				//初始化查询条件
				showMyCondition();
			}
			
			function addAnimate(){
				//鼠标移动到查询出的商品上时
				$(".search_item").hover(function(){
					$(this).stop();
					$(this).animate({width:"100%"});
					/*$(this).children(".search_item_handle").show();*/
				},
				function(){
					$(this).stop();
					$(this).animate({width:"90%"});
					/*$(".search_item_handle").hide();*/
				});
			}
			
			//用于记录加载到第几页
			var currentPageNo = 1;
			var actionFlag = true;
			//单用户拖动到底部的时候加载下一页数
			function bindScrollEvent(){
				//添加滚动事件
				$(window).scroll(function(){
					var docHeight = $(document).height();//获取页面文档的高度
					var winHeight = $(window).height();//获取当前窗体的高度
					var winScorllHeight = $(window).scrollTop();//获取滚动条滚动的距离
					if(docHeight==winHeight+winScorllHeight){
						//是否继续请求
						if(!actionFlag){return false;}
						//加载下一页的数据currentPageNo表示页码
						currentPageNo++;
						//加载更多的学生数据
						//alert("到底了,正在加载下一页的数据");
						var myCondition = "${myCondition}".split("||");
						//alert(myCondition[0]+"=="+myCondition[1]+"=="+myCondition[2]+"=="+myCondition[3]+"=="+myCondition[4]+"=="+myCondition[5]+"=="+myCondition[6]);
						$.ajax({ 
							   type : "get", //提交方式 
							   url : "${pageContext.request.contextPath}/nofilter_showNextGoods",//路径
							   dateType:"JSON",//返回的数据类型
							   data : {
							   		goodsType:myCondition[0],
							   		payType:myCondition[1],
							   		priceOrderBy:myCondition[2],
							   		lowPrice:myCondition[3],
							  		topPrice:myCondition[4],
							  		goodsAddress:myCondition[5],
							  	 	goodsName:myCondition[6],
							  	 	currentPageNo:currentPageNo
							   },
							   success : function(data) {
								   
								   if(data.list==""&&actionFlag){
									   var temp = "";
									   //alert("没有商品了");
									   temp = temp +"<div style='width: 70%px;margin: 0px auto;padding:10px;border: 1px solid #CCCCCC; text-align:center; box-shadow: 5px 5px 5px #CCCCCC;'>";
									   temp = temp +"我也是有底线的";
									   temp = temp +"</div>";
									   $("#search_result_panel").find(".search_item :last").after(temp);
									   actionFlag = false;//是否继续请求
								   }
								   ////////
								   for(var index in data.list){
									   var temp = "";
									   temp = temp +"<div class='search_item'>";
									   temp = temp +"<div class='search_item_img'>";
									   temp = temp +"<a href='showGoodsDetail/"+data.list[index].goods.goodsId+"' target='_blank'>"
									   temp = temp +"<img src='${pageContext.request.contextPath}/goodsImgs/"+data.list[index].goods.images[0].imgUrl+"'/>";
									   temp = temp +"</a>";
									   temp = temp +"</div>";
									   temp = temp +"<div class='search_item_info'>";
									   temp = temp +"<div class='item_title'>";
									   temp = temp +"<span>"+data.list[index].goods.goodsName+"</span>";
									   temp = temp +"</div>";
									   temp = temp +"<div class='item_info_price'>";
									   temp = temp +"价格：<span class='price'>"+data.list[index].goods.goodsPrice+"元</span><span>数量："+data.list[index].goods.goodsTotal+"件</span><a href='#'><span>留言："+data.list[index].commentNum+"条</span></a>";
									   temp = temp +"</div>";
									   temp = temp +"<div class='goods_des'>";
									   temp = temp +"<span>描述："+data.list[index].goods.goodsInfo+"</span>";
									   temp = temp +"</div>";
									   temp = temp +"<div class='sellcustomer_info'>";
									   temp = temp +"<a href='#'><span>卖家昵称："+data.list[index].goods.goodsCustomer.cusName+"</span></a>";
									   temp = temp +"<span class='score'>卖家类型："+data.list[index].goods.goodsCustomer.cusType+"</span><br>";
									   temp = temp +"<span>地址："+data.list[index].goods.goodsAddress+"</span><br>";
									   temp = temp +"<span>交易方式："+data.list[index].goods.goodsPaytype+"</span>";
									   temp = temp +"</div>";
									   temp = temp +"</div>";
									   temp = temp +"<div class='search_item_handle'>";
									   temp = temp +"<c:if test='${empty sessionScope.customer}' var='cus'>";
									   temp = temp +"<a href='${pageContext.request.contextPath}/pages/customerLogin.jsp' class='btn btn-default btn-block'>收藏商品</a>";
									   temp = temp +"</c:if>";
									   temp = temp +"<c:if test='${!cus}'>";
									   if(typeof(data.list[index].collection)=="undefined"){
										    if('${sessionScope.customer.cusId}' != data.list[index].goods.goodsCustomer.cusId){
											temp = temp +"<button class='btn btn-default btn-block' onclick='addCollection(${sessionScope.customer.cusId},"+data.list[index].goods.goodsId+",this)'>收藏商品</button>";										    	
										    }
									   }else{
											temp = temp +"<button class='btn btn-warning btn-block' onclick='removeCollection(${sessionScope.customer.cusId},"+data.list[index].goods.goodsId+",this)'>已收藏</button>";
									   }
									   temp = temp +"</c:if>";
									   temp = temp +"<a href='showGoodsDetail/"+data.list[index].goods.goodsId+"' target='_blank' class='btn btn-success btn-block'>查看详情</a>";
									   temp = temp +"</div>";
									   temp = temp +"</div>";
									   
									   $("#search_result_panel").find(".search_item :last").after(temp);
								   }
								   
								   //添加动画
								   addAnimate();
							   }
							});
					}
				});
			}
			
			//添加收藏
			function addCollection(cusId,goodsId,e){
				//alert(cusId+" "+goodsId);
				$.ajax({ 
					   type : "post", //提交方式 
					   url : "${pageContext.request.contextPath}/addCollection",//路径
					   dateType:"text",//返回的数据类型
					   data : { 
						  "cusId" : cusId,
						  "goodsId":goodsId
					   },
					   success : function(data) {
						   //alert(e);
						   var info = data.split(",")
						   //收藏成功
						   $(e).removeClass("btn-default");
						   $(e).addClass("btn-warning");
						   $(e).text("已收藏");
						   $(e).attr("onclick","removeCollection("+info[0]+","+info[1]+",this)");
					   }
					});
			}
			
			//取消收藏
			function removeCollection(cusId,goodsId,e){
				//alert(cusId+" remove"+goodsId);
				$.ajax({ 
					   type : "post", //提交方式 
					   url : "${pageContext.request.contextPath}/removeCollection",//路径
					   dateType:"text",//返回的数据类型
					   data : { 
						  "cusId" : cusId,
						  "goodsId":goodsId
					   },
					   success : function(data) {
						   //alert(data);
						   var info = data.split(",")
						   //收藏成功
						   $(e).removeClass("btn-warning");
						   $(e).addClass("btn-default");
						   $(e).text("收藏商品");
						   $(e).attr("onclick","addCollection("+info[0]+","+info[1]+",this)");
					   }
					});
			}
			
			//显示查询的条件在条件栏
			function showMyCondition(){
				
				var myCondition = "${myCondition}";
				var myConditionInfo= myCondition.split("||");
				
				var option1 = $("[name='goodsType']").find("option");
				for(var index in option1){
					if(myConditionInfo[0]==$(option1[index]).text()){
						$(option1[index]).attr("selected", "selected");
					}
				}
				var option2 = $("[name='payType']").find("option");
				for(var index in option2){
					if(myConditionInfo[1]==$(option2[index]).text()){
						$(option2[index]).attr("selected", "selected");
					}
				}
				var option3 = $("[name='priceOrderBy']").find("option");
				for(var index in option3){
					if(myConditionInfo[2]==$(option3[index]).text()){
						$(option3[index]).attr("selected", "selected");
					}
				}
				if(myConditionInfo[3]!="null"){
				$("[name='lowPrice']").val(myConditionInfo[3]);					
				}
				if(myConditionInfo[4]!="null"){
				$("[name='topPrice']").val(myConditionInfo[4]);					
				}
				if(myConditionInfo[5]!="null"){
				$("[name='goodsAddress']").val(myConditionInfo[5]);					
				}
				if(myConditionInfo[6]!="null"){
				$("[name='goodsName']").val(myConditionInfo[6]);					
				}
			}
			
			//价格输入处理
			function handlerPrice(){
				//最低价大于最高价时
				if($("[name='lowPrice']").val()>$("[name='topPrice']").val()){
					var lowPrice = $("[name='lowPrice']").val();
					$("[name='lowPrice']").val($("[name='topPrice']").val());
					$("[name='topPrice']").val(lowPrice);
				}
			}
			
		</script>

</body>
</html>
