<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
<link href="../css/bootstrap.css" rel="stylesheet">
<link type="text/css" href="../css/pagination.css" rel="stylesheet" />
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/jquery.pagination.js"></script>

<style>
.main_show {
	width: 1024px;
	margin: 70px auto;
	margin-bottom: 20px;
	height: 500px;
	box-shadow: 5px 5px 5px #CCCCCC;
	border: 2px solid #337AB7;
	border-radius: 10px;
}

.goods_img {
	width: 600px;
	height: 500px;
	float: left;
}

.goods_img_main {
	margin: 20px 50px;
}

.goods_img_items {
	text-align: center;
	margin: 0px 40px;
}

.goods_info {
	color: #666;
	width: 400px;
	height: 500px;
	float: right;
}

.text_info div {
	margin-bottom: 10px;
	overflow: hidden;
}

.goods_name {
	font-size: 24px;
}

.goods_addtime {
	color: #CCCCCC;
	font-size: 12px;
	margin-left: 240px;
	display: block;
}

.text_red {
	color: red;
	font-size: 20px;
}

.deail_info {
	width: 100%;
	height: 146px;
	overflow: auto;
}
/*评论*/
.comment {
	width: 1024px;
	margin: 20px auto;
	box-shadow: 5px 5px 5px #CCCCCC;
	border: 1px solid #CCCCCC;
}

.comment_info {
	color: #666;
	padding: 10px 50px;
	margin-top: 10px;
	box-shadow: 5px 5px 20px #CCCCCC;
	border: 1px solid #CCCCCC;
}

.add_comment {
	color: #666;
	padding: 10px 100px;
	margin-top: 10px;
	border: 1px solid #CCCCCC;
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
					href="${pageContext.request.contextPath}/showIndex">校园闲置物品交易平台</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">商品详情</a></li>
					<c:if test="${empty sessionScope.customer}" var="cus">
						<li><a
							href="${pageContext.request.contextPath}/pages/customerLogin.jsp">登录</a></li>
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
				</ul>
			</div>
		</div>
		</nav>
	</div>

	<div class="main_show">
		<!-- 商品图片 -->
		<div class="goods_img">
			<div class="goods_img_main">
				<img src="${pageContext.request.contextPath}/goodsImgs/${goods.images[0].imgUrl}" id="main_img_show" width="500px"
					height="300px" />
			</div>
			<hr />
			<div class="goods_img_items">
				<ul id="goods_img_item" class="pagination">
					<c:forEach items="${goods.images}" var="image">
						<li><img src="${pageContext.request.contextPath}/goodsImgs/${image.imgUrl}" width="100px" height="60px" /></li>
					</c:forEach>
					
					<!-- <li><img src="../img/b.jpg" width="100px" height="60px" /></li>
					<li><img src="../img/c.jpg" width="100px" height="60px" /></li>
					<li><img src="../img/b.jpg" width="100px" height="60px" /></li>
					<li><img src="../img/a.jpg" width="100px" height="60px" /></li> -->
				</ul>
			</div>
		</div>

		<!-- 商品信息 -->
		<div class="goods_info">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">商品详情</h3>
				</div>
				<div class="panel-body text_info">
					<div>
						<span class="goods_name">${goods.goodsName}</span> <span
							class="goods_addtime">${fromatTime}</span>
					</div>
					<hr width="80%" style="margin: 5px auto;" />
					<div>
						￥：<span class="text_red" id="price">${goods.goodsPrice}</span>&nbsp;&nbsp;元
						&nbsp;&nbsp;&nbsp;&nbsp; 数量：<span class="text_red">${goods.goodsTotal}</span>&nbsp;&nbsp;件
						&nbsp;&nbsp;&nbsp;&nbsp; 购买数量：<input type="number" oninput="if(value<1||value>${goods.goodsTotal})value=1"; 
							onkeypress="javascript:return false" ; value="1"
							style="width: 50px; height: 26px; margin: 0px; float: none;" id="pay_num"/>
					</div>
					<div>
						<span>卖家昵称：${goods.goodsCustomer.cusName}</span><br> <span>联系方式：${goods.goodsCustomer.cusPhone}</span><br>
						<span>所在地区：${goods.goodsAddress}</span><br> <span>交易方式：${goods.goodsPaytype}</span>
					</div>
					<div class="deail_info">
						<span>描述：${goods.goodsInfo}</span>
					</div>
					<hr />
					<div style="text-align: center;">
						<c:if test="${empty sessionScope.customer}" var="cus">
							<a href="${pageContext.request.contextPath}/pages/customerLogin.jsp" class="btn btn-success">立即购买</a>
							<a href="${pageContext.request.contextPath}/pages/customerLogin.jsp" class="btn btn-default">收藏商品</a>
						</c:if>
						<c:if test="${!cus}">
							<c:if test="${empty collection}" var="collection">
								<c:if test="${sessionScope.customer.cusId == goods.goodsCustomer.cusId}" var="flag">
								<a href="${pageContext.request.contextPath}/customerGoodsManage" class="btn btn-success">编辑商品</a>
								</c:if>
								<c:if test="${!flag}">								
								<button type="button" class="btn btn-success" onclick="createOrder(${goods.goodsId})">立即购买</button>
								<button class="btn btn-default" onclick="addCollection(${sessionScope.customer.cusId},${goods.goodsId},this)">收藏商品</button>
								</c:if>
								
							</c:if>
							<c:if test="${!collection}">
								<button type="button" class="btn btn-success" onclick="createOrder(${goods.goodsId})">立即购买</button>
								<button class="btn btn-warning" onclick="removeCollection(${sessionScope.customer.cusId},${goods.goodsId},this)">已收藏</button>
							</c:if>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="comment">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">商品评论信息</h3>
			</div>
			<div class="panel-body" id="GoodsComment">
				<!-- 添加评论栏 -->
				<div class="add_comment">
					<form class="bs-example bs-example-form" role="form">
						<div class="row">
							<div class="col-lg-10">
								<div class="input-group">
									<textarea class="form-control" id="addCommentInfo" rows="1"
										placeholder="想说点什么" style="resize: none;"></textarea>
									<span class="input-group-btn"> <c:if
											test="${empty sessionScope.customer}" var="cus">
											<a class="btn btn-primary"
												href="${pageContext.request.contextPath}/pages/customerLogin.jsp">
												先登录 </a>
										</c:if> <c:if test="${!cus}">
											<button class="btn btn-primary" type="button"
												onclick="addComment()">
												<c:if test="${empty commentPage.list}" var="flag">抢第一个沙发</c:if>
												<c:if test="${!flag}">留言</c:if>
											</button>
										</c:if>
									</span>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>

			<div class="M-box3" style="margin: 10px auto; width: 80%;"></div>
		</div>
			<form id="form_pay" action="" method="post">
				<input type="hidden" id = "hidden_goodsId" name="goodsId" />
				<input type="hidden" id = "hidden_num" name="num" />
			</form>
	</div>

	<script type="text/javascript">
		var flagPage = 1;//几下这是第几页评论
		$(function() {
			//鼠标移动在一个缩略图上,该图片就显示在主图的位置
			$("#goods_img_item >li").hover(function() {
				$("#main_img_show")[0].src = $(this).children("img")[0].src;
			})

			//初始化显示第一页评论
			showComment(1);
		})

		//按页码显示评论信息
		function showComment(currentPageNo) {//currentPageNo当前的页码
			$
					.ajax({
						type : "GET", //提交方式 
						url : "${pageContext.request.contextPath}/nofilter_showCommet",//路径
						dateType : "JSON",//返回的数据类型
						data : {
							"goodsId" : "${goods.goodsId}",//商品的Id
							"currentPageNo" : currentPageNo//当前的页码
						},
						success : function(data) {
							//得到评论面板的div,删除里面的留言消息
							$("#GoodsComment").find(".comment_info").remove();
							for ( var comment in data.list) {
								var commentCusName = data.list[comment].commentCustomer.cusName;

								var temp = "<div class='comment_info'>";
								temp = temp + "<div style='height: 20px;'>";
								temp = temp
										+ "<div class='comment_customer' style='float: left; font-size: 8px;'>";
								temp = temp
										+ "<span>"
										+ data.list[comment].commentCustomer.cusName
										+ "</span>";
								temp = temp + "</div>";
								temp = temp
										+ "<div class='comment_time' style='float: right; font-size: 8px;'>";
								if (commentCusName == "${sessionScope.customer.cusName}") {
									temp = temp
											+ "<a href='javascript:void(0)' onclick='delComment("
											+ data.list[comment].commentId
											+ ")'>删除</a>";
								}
								temp = temp + "&nbsp;&nbsp;&nbsp;&nbsp;";
								temp = temp + "<span>"
										+ data.list[comment].commentAddtime
										+ "</span>";
								temp = temp + "</div>";
								temp = temp + "</div>";
								temp = temp + "<hr style='margin: 2px;'/>";
								temp = temp + "<div>";
								temp = temp + "<span>"
										+ data.list[comment].commentInfo
										+ "</span>";
								temp = temp + "</div>";
								temp = temp + "</div>";
								//把temp接在#GoodsComment的后面
								$("#GoodsComment").append(temp);
							} //显示选择的页码
							choosePage(data);
						}
					});
		}

		//分页显示选择的页码
		function choosePage(data) {
			$('.M-box3').pagination({
				pageCount : data.totalPage,
				current : data.currentPageNo,
				/*  pageCount:"${commentPage.totalPage}",
				 current:"${commentPage.currentPageNo}", */
				isHide : false,
				jump : true,
				coping : true,
				count : 3,
				showData : 5,
				homePage : '首页',
				endPage : '末页',
				prevContent : '上页',
				nextContent : '下页',
				callback : function(api) {
					//将选择的页码传给AJAX
					var currentPageNo = api.getCurrent();
					flagPage = currentPageNo;
					showComment(currentPageNo);
				}
			})
		}

		//添加评论信息
		function addComment() {
			//添加评论的内容
			var addCommentInfo = $("#addCommentInfo").val();
			//判断用户是否输入内容
			if (addCommentInfo.trim() == "") {
				alert("请输入内容");
				return false;
			}
			//获取商品的编号
			var goodId = "${goods.goodsId}";
			//顾客的编号
			var cusId = "${sessionScope.customer.cusId}";

			$
					.ajax({
						type : "POST", //提交方式 
						url : "${pageContext.request.contextPath}/addComment",//路径
						dateType : "JSON",//返回的数据类型
						data : {
							"cusId" : cusId,//评论的顾客
							"goodsId" : goodId,//商品的Id
							"addCommentInfo" : addCommentInfo
						//评论的内容
						},
						success : function(data) {
							if (data == "success") {//评论成功
								//清空评论栏
								$("#addCommentInfo").val("");

								//将新评论的内容添加到评论栏中
								var temp = "<div class='comment_info'>";
								temp = temp + "<div style='height: 20px;'>";
								temp = temp
										+ "<div class='comment_customer' style='float: left; font-size: 8px;'>";
								temp = temp
										+ "<span>${sessionScope.customer.cusName}</span>";
								temp = temp + "</div>";
								temp = temp
										+ "<div class='comment_time' style='float: right; font-size: 8px;'>";
								temp = temp + "<span>添加评论中...</span>";
								temp = temp + "</div>";
								temp = temp + "</div>";
								temp = temp + "<hr style='margin: 2px;'/>";
								temp = temp + "<div>";
								temp = temp + "<span>" + addCommentInfo
										+ "</span>";
								temp = temp + "</div>";
								temp = temp + "</div>";

								//将新的留言模块添加到留言板中
								$("#GoodsComment").find(".comment_info:first")
										.before(temp);
								$("#GoodsComment").find(".comment_info:first")
										.hide().slideDown();
								//如果有5个留言栏的时候,清除最后一个留言块
								if ($("#GoodsComment").find(".comment_info")
										.size() > 5) {
									$("#GoodsComment").find(
											".comment_info:last").hide();
								}

								//添加修养成功后刷新留言栏
								setTimeout("showComment(1)", 1000);
								flagPage = 1;//执行添加操作后，将当前的页码设置为1

							} else {
								alert("留言失败！");
							}
						}
					});
		}

		//删除请求
		function delComment(commentId) {

			if (!confirm('确实要删除该留言吗?')) {
				return false;
			}

			$.ajax({
				type : "GET", //提交方式 
				url : "${pageContext.request.contextPath}/delComment",//路径
				dateType : "text",//返回的数据类型
				data : {
					"commentId" : commentId,//评论的编号
				},
				success : function(data) {
					//异步刷新评论栏
					showComment(flagPage);
				}
			});
		}
		
		//添加收藏
		function addCollection(cusId,goodsId,e){
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
		
		//创建订单
		function createOrder(goodsId){
			//单价，数量，goodsId
			var payNum = $("#pay_num").val();
			var data = "[{goodsId:"+goodsId+",payNum:"+payNum+"}]";
			
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
