<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的收藏</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

<style>
.myCollection {
	width: 1024px;
	margin: 70px auto;
}

.collection_item {
	width: 100%;
	height: 180px;
	border: 1px solid #CCCCCC;
	margin: 20px auto;
	box-shadow: 5px 5px 5px #CCCCCC;
	overflow: hidden;
}

.collection_item:hover {
	border: 1px solid #337AB7;
}

.collection_item_img {
	float: left;
}

.collection_item_img img {
	width: 200px;
	height: 133px;
	margin: 20px 20px;
}

.collection_item_info {
	float: left;
	width: 400px;
	height: 133px;
	margin: 10px;
	padding: 5px 10px;
}

.item_info_price span {
	padding-right: 20px;
}

.price {
	color: red;
	font-size: 18px;
}

.collection_item_handle {
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

#collection_result {
	float: left;
	width: 78%;
}

#pay {
	float: right;
	width: 20%;
	position: fixed;
	left: 980px;
}
.goodsId{
	font-size:0px; 
}
</style>
</head>
<body>

	<div class="myCollection">
		<!-- 顶部栏 -->
		<div id="top_nav">
			<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand"
						href="${pageContext.request.contextPath}/showIndex">校园闲置物品交易平台</a>
				</div>
				<div>
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">我的收藏</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> 个人中心 <b class="caret"></b>
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

		<div id="collection_result">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">我的收藏</h3>
				</div>
				<div class="panel-body" id="collection_result_panel">
					<!-- 查询出的商品列表 -->

					<c:forEach items="${collectionPage.list}" var="collection">
						<div class="collection_item">
							<div class="collection_item_img">
								<img
									src="${pageContext.request.contextPath}/goodsImgs/${collection.collectionGoods.images[0].imgUrl}" />
							</div>
							<div class="collection_item_info">
								<div class="item_title">
									<span>${collection.collectionGoods.goodsName}</span>
								</div>
								<div class="item_info_price">
									价格：￥<span class="price">${collection.collectionGoods.goodsPrice}</span>
									数量：<span class="total_num">${collection.collectionGoods.goodsTotal}件</span>
									购买数量：<input class="num" type="number" value="1" oninput="check_number(this)"; style="width: 50px;" />
										<span class="goodsId">${collection.collectionGoods.goodsId}</span>
									<!-- <a href="#"><span>留言：22条</span></a> -->
								</div>
								<div class="goods_des">
									<span>描述：${collection.collectionGoods.goodsInfo}</span>
								</div>
								<div class="sellcustomer_info">
									<a href="#"><span>卖家昵称：${collection.collectionGoods.goodsCustomer.cusName}</span></a>
									<span class="score">用户类型：${collection.collectionGoods.goodsCustomer.cusType}</span><br>
									<span>地址：${collection.collectionGoods.goodsAddress}</span><br>
									<span>交易方式：${collection.collectionGoods.goodsPaytype}</span>
								</div>
							</div>
							<div class="collection_item_handle">
								<a class="btn btn-default btn-block choose" onclick="choose(this)">选择</a> 
								<a class="btn btn-danger btn-block" 
								onclick="removeCollection(${sessionScope.customer.cusId},${collection.collectionGoods.goodsId},this)">移除</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

		<div id="pay">
			<div class="search_info">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">支付栏</h3>
					</div>
					<div class="panel-body">
						<form class="bs-example bs-example-form" role="form">

							<div class="input-group input-group-sm">
								<span class="input-group-addon">总计</span> <input type="text"
									class="form-control" id="total_price" value="0"
									readonly="readonly">
							</div>
							<div class="input-group input-group-sm">
								<span class="input-group-addon">地址</span>
								<!--<input type="text" class="form-control" placeholder="商品描述">-->
								<select class="form-control">
									<c:forEach items="${addressList}" var="address">
										<option value="${address.addressId}">
											${address.addressProvince}
											${address.addressCity}
											${address.addressDistrict}
											${address.addressDescInfo}
										</option>									
									</c:forEach>
								</select>
							</div>
							<br>
							<button class="btn btn-primary btn-block" type="button" onclick="pay()">确认支付</button>
						</form>
					</div>
				</div>


			</div>
		</div>

	</div>

	<script type="text/javascript">
		$(function() {
			init();
		})

		//初始化方法
		function init() {
			//初始化时隐藏 商品的操作选项
			/*$(".collection_item_handle").hide();*/
			//添加滚动事件
			bindScrollEvent();
		}
		
		var currentPageNo = 1;
		//单用户拖动到底部的时候加载下一页数
		function bindScrollEvent() {
			//添加滚动事件
			$(window).scroll(function() {
				var docHeight = $(document).height();//获取页面文档的高度
				var winHeight = $(window).height();//获取当前窗体的高度
				var winScorllHeight = $(window).scrollTop();//获取滚动条滚动的距离
				if (docHeight == winHeight + winScorllHeight) {
				//加载更多的学生数据
				//alert("到底了,正在加载下一页的数据");
				currentPageNo += 1;
				$.ajax({
					type : "get", //提交方式 
					url : "${pageContext.request.contextPath}/moreCustomerCollection",//路径
					dateType : "JSON",//返回的数据类型
					data : {
						"currentPageNo" : currentPageNo,
					},
					success : function(data) {
						if(data.list==""){//如果没有数据了
							alert("已经到底了");
							return false;
						}
						
						for ( var index in data.list) {
							var temp = "";
							temp = temp +"<div class='collection_item'>";
							temp = temp +"<div class='collection_item_img'>";
							temp = temp +"<img src='${pageContext.request.contextPath}/img/apple-touch-icon.png' />";
							temp = temp +"</div>";
							temp = temp +"<div class='collection_item_info'>";
							temp = temp +"<div class='item_title'>";
							temp = temp +"<span>"+data.list[index].collectionGoods.goodsName+"</span>";
							temp = temp +"</div>";
							temp = temp +"<div class='item_info_price'>";
							temp = temp +"价格：￥<span class='price'>"+data.list[index].collectionGoods.goodsPrice+"</span>";
							temp = temp +"数量：<span class='total_num'>"+data.list[index].collectionGoods.goodsTotal+"件</span>";
							temp = temp +"购买数量：<input class='num' type='number' value='1' oninput='check_number(this)' ; style='width: 50px;' />";
							temp = temp +"<span class='goodsId'>"+data.list[index].collectionGoods.goodsId+"</span>"
							temp = temp +"</div>";
							temp = temp +"<div class='goods_des'>";
							temp = temp +"<span>描述："+data.list[index].collectionGoods.goodsInfo+"</span>";	
							temp = temp +"</div>";
							temp = temp +"<div class='sellcustomer_info'>";
							temp = temp +"<a href='#'><span>卖家昵称："+data.list[index].collectionGoods.goodsCustomer.cusName+"</span></a>";	
							temp = temp +"<span class='score'>用户类型："+data.list[index].collectionGoods.goodsCustomer.cusType+"</span><br>";	
							temp = temp + "<span>地址："+data.list[index].collectionGoods.goodsAddress+"</span><br>";	
							temp = temp + "<span>交易方式："+data.list[index].collectionGoods.goodsPaytype+"</span>";	
							temp = temp + "</div>";	
							temp = temp + "</div>";	
							temp = temp + "<div class='collection_item_handle'>";	
							temp = temp + "<a class='btn btn-default btn-block choose' onclick='choose(this)'>选择</a>";	
							temp = temp + "<a class='btn btn-danger btn-block' onclick='removeCollection(${sessionScope.customer.cusId},"+data.list[index].collectionGoods.goodsId+",this)'>移除</a>";	
							temp = temp + "</div>";	
							temp = temp + "</div>";	
							//把新加的信息接在原来的后面
							$("#collection_result_panel").find(".collection_item :last").after(temp);
							}
						}
					});
				}
			});
		}
		var data = "";
		//选择商品将其的价格累计到总计栏中   choose 添加新的class
		function choose(e) {
			data = "[";
			var $this = $(e);
			//alert($this.text());
			var total_price = 0; /*$("#total_price").val();*/
			if ($this.text() == "选择") {
				$this.text("已选择");
				$this.addClass("btn-primary");
			} else {
				$this.text("选择");
				$this.removeClass("btn-primary");
			}
			//选中所有已选择的商品
			var choose = $(".collection_item .collection_item_handle .choose");
			var n = choose.length;
			for (i = 0; i < n; i++) {
				if (choose[i].innerText == "已选择") {
				var price = $(choose[i]).parent().parent()
					.children(".collection_item_info").children(".item_info_price").children(".price").text();
				var num = $(choose[i]).parent().parent()
					.children(".collection_item_info").children(".item_info_price").children(".num").val();
				var goodsId = $(choose[i]).parent().parent()
					.children(".collection_item_info").children(".item_info_price").children(".goodsId").text();
				total_price = total_price + (price * num);
				data = data + "{goodsId:"+goodsId+",payNum:"+num+"},";
				}
			}
			data = data.substring(0,data.length-1) + "]";console.log(data);
			$("#total_price").val(total_price);
		}

		//改变商品栏的数量 刷新金额栏
		function choose_num() {
			$(".item_info_price > input").change(function() {
				data = "[";
				var total_price = 0;
				//选中所有已选择的商品
				var choose = $(".collection_item .collection_item_handle .choose");
				var n = choose.length;
				for (i = 0; i < n; i++) {
					if (choose[i].innerText == "已选择") {
						var price = $(choose[i]).parent().parent().children(".collection_item_info").children(".item_info_price").children(".price").text();
						var num = $(choose[i]).parent().parent().children(".collection_item_info").children(".item_info_price").children(".num").val();
						var goodsId = $(choose[i]).parent().parent().children(".collection_item_info").children(".item_info_price").children(".goodsId").text();
						total_price = total_price+ (price * num);
						data = data + "{goodsId:"+goodsId+",payNum:"+num+"},";
					}
				}
				data = data.substring(0,data.length-1) + "]";console.log(data);
				$("#total_price").val(total_price);
			});
		}

		//检查商品数量选择栏
		function check_number(e) {
			//改变数量刷新金额栏
			choose_num();
			//检查数量的合法性
			var total_num = $(e).parent().children(".total_num").text();
			if ($(e).val() > total_num)
				$(e).val(total_num);
			if ($(e).val() < 1)
				$(e).val(1);
		}
		
		//移除收藏
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
					   $(e).removeClass("btn-danger");
					   $(e).addClass("btn-warning");
					   $(e).text("收藏");
					   $(e).attr("onclick","addCollection("+info[0]+","+info[1]+",this)");
				   }
				});
		}
		//收藏商品【把刚刚移除的商品 添加回来】
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
					$(e).removeClass("btn-warning");
					$(e).addClass("btn-danger");
					$(e).text("移除");
					$(e).attr("onclick","removeCollection("+info[0]+","+info[1]+",this)");
					}
				});
		}	
		
		//跳到订单页面
		function pay(){
			console.log(data);
			if(data.length<5){
				alert("请选择商品！");
				return false;
			}
			$.ajax({ 
				   type : "post", //提交方式 
				   url : "${pageContext.request.contextPath}/createOrder",//路径
				   dateType:"text",//返回的数据类型
				   data : {"jsonStr":data},
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
		
	</script>

</body>
</html>
