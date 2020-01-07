<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>更多商品</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		
		<style type="text/css">
			#more_goods{
				width: 1024px;
				margin: 70px auto;
			}
			.search_fliter{
				width: 100%;
				margin: 0px auto;
				box-shadow: 5px 5px 5px #CCCCCC;
			}
			.classify_info{
				float: left;
				width: 70%;
				padding: 6px 20px;
				margin-right:5% ;
				border: 1px solid #CCCCCC;
			}
			.classify_info div{
				margin-top: 5px;
			}
			.search_info{
				float: right;
				width: 25%;
			}
			/*<!-- 查询结果 -->*/
			.search_result{
				width: 100%;
			}
			.goods_itme{
				text-align: center;
				width: 200px;
				height: 240px;
				box-shadow: 5px 5px 5px #CCCCCC;
				margin: 0px 2.4px 20px 2.4px;
				float: left;
				overflow: hidden;
				border: 1px solid #CCCCCC;
			}
			.goods_itme:hover{
				border: 1px solid green;
			}
			.text_info{
				margin: 10px 0px;
			}
			.price{
				color: red;
			}
			.select_it{
				background-color: #000;
			}
			#low_right 
			{ 
			position: fixed; 
			width: 80px; 
			height: 80px; 
			bottom: 40px; 
			right: 20px;  
			text-align: center; 
			padding: 10px; 
			margin: 10px; 
			} 
			.go-top{
				width: 40px;
				height: 40px;
			}
		</style>
	</head>
	<body>
		
		<!-- 顶部栏 -->
		<div id="top_nav">
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
									data-toggle="dropdown"> ${sessionScope.customer.cusName} <b class="caret"></b>
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
		
		<div id="more_goods">
			<!-- 查询条件:高级搜索 -->
			<div class="search_fliter">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title"><span style="font-size: 8px;">一共找到：${moreGoodsPage.totalCount}件商品</span></h3>
					</div>
					<div class="panel-body">
						<!-- 商品类别 -->
						<div class="classify_info">
							<div class="productName">
								<a class="btn btn-primary">教材书籍</a>
								<a class="btn btn-link">考研</a>|
								<a class="btn btn-link">公务员</a>|
								<a class="btn btn-link">语言类</a>|
								<a class="btn btn-link">课外小说</a>|
								<a class="btn btn-link">二手教材</a>|
								<a class="btn btn-link">编程语言</a>|
								<a class="btn btn-link">生活百科</a>
							</div>
							<div class="productName">
								<a class="btn btn-primary">电子产品</a>
								<a class="btn btn-link ">手机</a>|
								<a class="btn btn-link">电脑</a>|
								<a class="btn btn-link">键盘</a>|
								<a class="btn btn-link">鼠标</a>|
								<a class="btn btn-link">相机</a>|
								<a class="btn btn-link">耳机</a>|
								<a class="btn btn-link">音响</a>|
								<a class="btn btn-link">充电宝</a>
							</div>
							<div class="productName">
								<a class="btn btn-primary">日常用品</a>
								<a class="btn btn-link">单车</a>|
								<a class="btn btn-link">帐篷</a>|
								<a class="btn btn-link">被子</a>|
								<a class="btn btn-link">风扇</a>|
								<a class="btn btn-link">水壶</a>|
								<a class="btn btn-link">挂件</a>|
								<a class="btn btn-link">行李箱</a>
							</div>
							<div class="productName">
								<a class="btn btn-primary">服装衣物</a>
								<a class="btn btn-link">衣服</a>|
								<a class="btn btn-link">裤子</a>|
								<a class="btn btn-link">鞋子</a>|
								<a class="btn btn-link">卫衣</a>|
								<a class="btn btn-link">帽子</a>|
								<a class="btn btn-link">围巾</a>
							</div>
							<div class="productName">
								<a class="btn btn-primary">其他物品</a>
								<a class="btn btn-link">篮球</a>|
								<a class="btn btn-link">吉他</a>|
								<a class="btn btn-link">沙袋</a>
							</div>
						</div>
						
						<!-- 高级查询 -->
						<div class="search_info">
							 <form class="bs-example bs-example-form" id="serach_form" role="form" action="${pageContext.request.contextPath}/nofilter_moreGoods">
						        <input type="hidden" name="goodsType" id="goodsType"/>
						        <div class="input-group input-group-sm">
						            <span class="input-group-addon">交易方式</span>
						            <select class="form-control" name="payType" id="payType">
						            	<option value="">不限</option>
						            	<option value="线上交易">线上交易</option>
						            	<option value="线下交易">线下交易</option>
						            </select>
						        </div>
						        <div class="input-group input-group-sm">
						            <span class="input-group-addon">价格区间</span>
						            <select class="form-control" name="priceOrderBy" id="priceOrderBy">
						            	<option value="由低到高">由低到高</option>
						            	<option value="由高到低">由高到低</option>
						            </select>
						            <input type="text" class="form-control" placeholder="最低价" name="lowPrice" id="lowPrice">
						            <input type="text" class="form-control" placeholder="最高价" name="topPrice" id="topPrice">
						        </div>
						        <div class="input-group input-group-sm">
						            <span class="input-group-addon">商品地址</span>
						            <input type="text" class="form-control" placeholder="商品地址" name="goodsAddress" id="goodsAddress">
						        </div>
						        <div class="input-group input-group-sm">
						            <span class="input-group-addon">商品名称</span>
						            <input type="text" class="form-control" placeholder="商品描述" name="goodsName" id="goodsName">
						        </div>
						        <br>
						        <button class="btn btn-primary btn-block">查找商品</button>
						    </form>
						</div>
						
					</div>
				</div>
			</div>
			
			<!-- 查询结果 -->
			<div class="search_result">
				<c:forEach items="${moreGoodsPage.list}" var="goodsInfo">
				<div class="goods_itme">
					<a href="showGoodsDetail/${goodsInfo.goods.goodsId}" target="_blank">
						<img src="${pageContext.request.contextPath}/goodsImgs/${goodsInfo.goods.images[0].imgUrl}" width="200px" height="133px" />
					</a>
					<div class="text_info">
						<span>${goodsInfo.goods.goodsName}</span><br>
						<span class="price">￥：${goodsInfo.goods.goodsPrice}</span>
					</div>
					<div class="pay">
						<a href="showGoodsDetail/${goodsInfo.goods.goodsId}" target="_blank" class="btn btn-success">查看详情</a>
					</div>
				</div>
				</c:forEach>
				<c:if test="${moreGoodsPage.totalCount == 0}">没有符合条件的商品！</c:if>
		</div>
	</div>
		<div id="low_right">
			<a href="#more_goods" class="btn btn-info btn-lg">
          		<span class="glyphicon glyphicon-arrow-up"></span>
          	</a>
        </div>
</body>

<script type="text/javascript">
		$(function(){
			//回显
			showBack();
			//
			selectGoodsType();
			//到低加载下一页
			bindScrollEvent();
		});
		
		//选择物品种类
		function selectGoodsType(){
			$(".productName a").click(function(){
				var productName = $(this).text();
				$(this).addClass("select_it");
				console.log(productName);
				$("#goodsName").val(productName);
				$("#serach_form").submit();
			});
		}
		
		function showBack(){
			$("#payType option[value=${payType}]").prop("selected", "selected");
			$("#priceOrderBy option[value=${priceOrderBy}]").prop("selected", "selected");

			if("${lowPrice}"!=""){
			$("#lowPrice").val("${lowPrice}");					
			}
			if("${topPrice}"!=""){
			$("#topPrice").val("${topPrice}");					
			}
			if("${goodsAddress}"!=""){
			$("#goodsAddress").val("${goodsAddress}");					
			}
			if("${goodsName}"!=""){
			$("#goodsName").val("${goodsName}");					
			}
		}
		var currentPageNo = 1;
		var overFlag = false;
		//滑动加载下一页数据
		function bindScrollEvent(){
			//添加滚动事件
			$(window).scroll(function(){
				var docHeight = $(document).height();//获取页面文档的高度
				var winHeight = $(window).height();//获取当前窗体的高度
				var winScorllHeight = $(window).scrollTop();//获取滚动条滚动的距离
				if(docHeight==winHeight+winScorllHeight&&!overFlag){
					currentPageNo++;
					 $.ajax({ 
						   type : "get", //提交方式 
						   url : "${pageContext.request.contextPath}/nofilter_showNextGoods",//路径
						   dateType:"JSON",//返回的数据类型
						   data : {
						   		goodsType:"${goodsType}",
						   		payType:"${payType}",
						   		priceOrderBy:"${priceOrderBy}",
						   		lowPrice:"${lowPrice}",
						  		topPrice:"${topPrice}",
						  		goodsAddress:"${goodsAddress}",
						  	 	goodsName:"${goodsName}",
						  	 	currentPageNo: currentPageNo
						   },
						   success : function(data) {
							 console.log(data);
							 if(data.list.length>0){
								 var temp = "";
								 for(var i = 0;i<data.list.length;i++){
									 temp = temp + "<div class='goods_itme'>";
									 temp = temp + "<a href='showGoodsDetail/"+data.list[i].goods.goodsId+"' target='_blank'>";
									 temp = temp + "<img src='${pageContext.request.contextPath}/goodsImgs/"+data.list[i].goods.images[0].imgUrl+"' width='200px' height='133px' />";
									 temp = temp + "</a>";
									 temp = temp + "<div class='text_info'>";
									 temp = temp + "<span>"+data.list[i].goods.goodsName+"</span><br>";
									 temp = temp + "<span class='price'>￥："+data.list[i].goods.goodsPrice+"</span>";
									 temp = temp + "</div>";
									 temp = temp + "<div class='pay'>";
									 temp = temp + "<a href='showGoodsDetail/"+data.list[i].goods.goodsId+"' target='_blank' class='btn btn-success'>查看详情</a>";
									 temp = temp + "</div>";
									 temp = temp + "</div>";
								 }
								 $(".goods_itme :last").after(temp);
							 }else{
								 overFlag = true;//没有数据了
							 }
							 
						   }
						}); 
				}
			});
		}
		</script>
</html>