<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>校园闲置物品交易平台</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/style2.css" rel="stylesheet"> 
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.lazyload.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/isotope.pkgd.min.js"></script> 
		<style>
			body{
				padding: 50px 50px 0px 50px;
			}
			#main {
				margin: 0px auto;
				width: 1024px;
			}
			/*<!-- 顶部菜单栏    -->*/
			#top_nav{
				width: 1000px;
			}
			#search{
				margin-right: 50px;
			}
			/*<!-- 首页海报 -->*/
			#show_poster{
				width: 1024px;
				height: 400px;
				margin: 20px auto;
			}
			#notic{
				width: 200px;
				float: left;
			}
			.addtime{
				font-size: 4px;
				float: right;
			}
			#myCarousel{
				width: 800px;
				height: 400px;
				overflow: hidden;
				float: right;
			}
			.carousel-caption{
				margin-bottom: 150px;
			}
			.mainPic_1{
				margin-left:60px;
				width:200px;
				height:300px;
				border: 4px solid #fff;
			}
			.mainPic_2{
				width:600px;
				height:200px;
				border: 4px solid #fff;
			}
			.mainPic_3{
			    margin-left:200px;
				width:400px;
				height:200px;
				border: 4px solid #fff;
			}
			/*<!-- 猜你喜欢 -->*/
			.show_itme{
				width: 200px;
				height: 240px;
				/*display: inline-block;*/
				float: left;
				margin: 0px 22px;
				box-shadow: 5px 5px 5px #CCCCCC;
				overflow: hidden;				
			}
			.show_itme img{cursor: pointer;transition: all 0.6s;}
			.show_itme img:hover{ transform: scale(1.4);}
			.pay{
				text-align: center;
			}
			.text_info{
				margin: 10px 0px;
				text-align: center;
			}
			.price{
				color: red;				
			}
			.more{
				color:#ffffff;
				float: right;
			}
			/*实现滑动效果*/
			.show_hot_items{
				overflow: hidden;
				width: 99%;
			}
			.long_div{
				width: 200%;
				height: 240px;
				position: relative;
				top: 0px;
				left: 0px;
			}
			/*<!-- 分类信息 -->*/
			a{
				text-decoration:none;
				
			}
			#classify_all{
				text-align: center;
				width: 780px;
				margin: 0px auto;
			}
			.classify_item{
				/*display: inline-block;*/
				float: left;
				margin: 4px;
				/*background: url(img/a.jpg);*/
			}
			#classify_books,
			#classify_ele_product,#classify_man,#classify_woman
			{
				width: 288px;
				height: 200px;
			}
			#classify_daily,#classify_clothes{
				width: 144px;
				height: 200px;
			}
			/*<!-- 底部栏 -->*/
			#fooler{
				text-align: center;
				width: 100%;
				height: 100px;
				background: #444;
				color: #CCCCCC;
			}
			#fooler_info{
				padding-top: 40px;
			}
		</style>

	</head>

	<body>
		<div id="main">
			<!-- 顶部菜单栏    -->
			<div id="top_nav">

				<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
				    <div class="container-fluid">
					    <div class="navbar-header">
					        <a class="navbar-brand" href="#">校园闲置物品交易平台</a>
					    </div>
					    <div>
					        <ul class="nav navbar-nav">
					            <li class="active"><a href="#">首页</a></li>
					            <li><a href="#show_hot_hide">最热商品</a></li>
					            <li><a href="#classify_info_hide">商品分类</a></li>
					            <li><a href="${pageContext.request.contextPath}/pages/customerRegister.jsp">注册新用户</a></li>
					            
					            <!-- 对于用户是否登录给出两总不同的显示 -->
					            <c:if test="${empty sessionScope.customer}" var="cus">
					            	<li><a href="pages/customerLogin.jsp">登录</a></li>
					            </c:if>
					            <c:if test="${!cus}">
						            <li class="dropdown">
						                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>&nbsp;:
						                    	${sessionScope.customer.cusName} <b class="caret"></b>
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
					            </c:if>
					        </ul>
					    </div>
					    <div id="search">
					    	<form class="navbar-form navbar-right" role="search" method="post" action="${pageContext.request.contextPath}/nofilter_customerSearch">
					            <div class="form-group">
					                <input type="text" name="goodsName" class="form-control" maxlength="20" placeholder="描述商品">
					            </div>
					            <!-- <input type="submit" class="btn btn-primary" value="查询商品"/> -->
					            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;查询</button>
					        </form>
					    </div>
				    </div>
				</nav>

			</div>

			<!-- 首页海报 -->
			<div id="show_poster">
				<!-- 公告信息 -->
				<div id="notic">
					<div class="panel-group" id="accordion">
					<!-- 列表显示公告信息 -->
					<c:forEach items="${notices.list}" var="notice" varStatus="i">
						<div class="panel notice">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" 
									   href="#collapse${i.count}">
										${notice.noticeTitle}
									</a>
									<span class="addtime">${notice.noticeAddtime}</span>
								</h4>
							</div>
							<div id="collapse${i.count}" class="panel-collapse collapse">
								<div class="panel-body">
									${notice.noticeInfo}
								</div>
							</div>
						</div>
					</c:forEach>
						
					</div>
					<div id="more_notice">
						<div id="show_hot_hide"></div>
						<a href="${pageContext.request.contextPath}/nofilter_moreNotice" class="btn btn-link">更多公告>></a>
					</div>
					
				</div>
				
				<div id="myCarousel" class="carousel slide">
				    <!-- 轮播（Carousel）指标 -->
				    <ol class="carousel-indicators">
				        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				        <li data-target="#myCarousel" data-slide-to="1"></li>
				        <li data-target="#myCarousel" data-slide-to="2"></li>
				    </ol>   
				    <!-- 轮播（Carousel）项目 -->
				    <div class="carousel-inner">
				        <div class="item active">
				            <img src="${pageContext.request.contextPath}/img/a.jpg" alt="First slide"> 
				            <div class="carousel-caption">
				            	
				            </div>
				        </div>
				        <div class="item">
				            <img src="${pageContext.request.contextPath}/img/d.jpg" alt="Second slide">
				            <div class="carousel-caption">
								
							</div>
				        </div>
				        <div class="item">
				            <img src="${pageContext.request.contextPath}/img/c.jpg" alt="Third slide">
				            <div class="carousel-caption">
				            	
				            </div>
				        </div>
				    </div>
				    <!-- 轮播（Carousel）导航 -->
				    <a class="carousel-control left" href="#myCarousel" 
				        data-slide="prev">&lsaquo;
				    </a>
				    <a class="carousel-control right" href="#myCarousel" 
				        data-slide="next">&rsaquo;
				    </a>
				</div>
			</div>

			<!-- 猜你喜欢 -->
			<div id="show_hot">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h3 class="panel-title">猜你喜欢</h3>
					</div>
					<div class="panel-body show_hot_items">
						<!-- 具体商品信息 -->
						<div class="long_div">
							
							<c:forEach items="${guessGoodsInfo.list}" var="goods" varStatus="i">
							<div class="show_itme">
								<a href="showGoodsDetail/${goods.goodsId}" target="_blank">
									<img src="${pageContext.request.contextPath}/goodsImgs/${goods.images[0].imgUrl}" width="200px" height="133px" />
								</a>
								<div class="text_info">
									<span>${goods.goodsName}</span><br>
									<span class="price">￥：${goods.goodsPrice}元</span>
								</div>
								<div class="pay">
									<a href="showGoodsDetail/${goods.goodsId}" target="_blank" class="btn btn-success"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;&nbsp;查看详情</a>
								</div>
							</div>
							</c:forEach>
							
						</div>
						
						<div style="margin: 10px; text-align: center;">
							<div id="classify_info_hide"></div>
							<button type="button" class="btn btn-primary btn-xs" onclick="perv_hot_goods()">
								<span class="glyphicon glyphicon-menu-left"></span>
							</button>
							<button type="button" class="btn btn-primary btn-xs" onclick="next_hot_goods()">
								<span class="glyphicon glyphicon-menu-right"></span>
							</button>
						</div>
					</div>
				</div>
			</div>

			<!-- 分类信息 -->
		    <div class="portfolio-div" style="margin-top:0px; height: auto;">
		        <div class="portfolio">
		            <div class="no-padding portfolio_container">
					
		                <!-- single work -->
		                <div class="col-md-3 col-sm-6  fashion logo">
		                    <a href="${pageContext.request.contextPath}/nofilter_moreGoods?goodsType=书籍" class="portfolio_item">
		                        <img src="${pageContext.request.contextPath}/img/4.jpg" alt="image" class="img-responsive" />
		                        <div class="portfolio_item_hover">
		                            <div class="portfolio-border clearfix">
		                                <div class="item_info">
		                                    <span>书籍</span>
		                                    <em style="background: #FED71A;">进去看看</em>
		                                </div>
		                            </div>
		                        </div>
		                    </a>
		                </div>
		                <!-- single work -->
		                <div class="col-md-3 col-sm-6  fashion logo">
		                    <a href="${pageContext.request.contextPath}/nofilter_moreGoods?goodsType=电子设备" class="portfolio_item">
		                        <img src="${pageContext.request.contextPath}/img/1.jpg" alt="image" class="img-responsive" />
		                        <div class="portfolio_item_hover">
		                            <div class="portfolio-border clearfix">
		                                <div class="item_info">
		                                    <span>电子设备</span>
		                                    <em style="background: #ccc;">进去看看</em>
		                                </div>
		                            </div>
		                        </div>
		                    </a>
		                </div>
		                <!-- single work -->
		                <div class="col-md-6 col-sm-6  fashion logo">
		                    <a href="${pageContext.request.contextPath}/nofilter_moreGoods?goodsType=男生专区" class="portfolio_item">
		                        <img src="${pageContext.request.contextPath}/img/6.jpg" alt="image" class="img-responsive" />
		                        <div class="portfolio_item_hover">
		                            <div class="portfolio-border clearfix">
		                                <div class="item_info">
		                                    <span>男生专区</span>
		                                    <em style="background: #13AFC8;">进去看看</em>
		                                </div>
		                            </div>
		                        </div>
		                    </a>
		                </div>
		                <!-- single work -->
		                <div class="col-md-6 col-sm-6  fashion logo">
		                    <a href="${pageContext.request.contextPath}/nofilter_moreGoods?goodsType=女生专区" class="portfolio_item">
		                        <img src="${pageContext.request.contextPath}/img/5.jpg" alt="image" class="img-responsive" />
		                        <div class="portfolio_item_hover">
		                            <div class="portfolio-border clearfix">
		                                <div class="item_info">
		                                    <span>女生专区</span>
		                                    <em style="background: #EC9BAD;">进去看看</em>
		                                </div>
		                            </div>
		                        </div>
		                    </a>
		                </div>
		                <!-- single work -->
		                <div class="col-md-3 col-sm-6  fashion logo">
		                    <a href="${pageContext.request.contextPath}/nofilter_moreGoods?goodsType=衣物" class="portfolio_item">
		                        <img src="${pageContext.request.contextPath}/img/2.jpg" alt="image" class="img-responsive" />
		                        <div class="portfolio_item_hover">
		                            <div class="portfolio-border clearfix">
		                                <div class="item_info">
		                                    <span>衣物</span>
		                                    <em style="background: #EDC3AE;">进去看看</em>
		                                </div>
		                            </div>
		                        </div>
		                    </a>
		                </div>
		                <!-- single work -->
		                <div class="col-md-3 col-sm-6  fashion logo">
		                    <a href="${pageContext.request.contextPath}/nofilter_nofilter_moreGoods?goodsType=日用品" class="portfolio_item">
		                        <img src="${pageContext.request.contextPath}/img/3.jpg" alt="image" class="img-responsive" />
		                        <div class="portfolio_item_hover">
		                            <div class="portfolio-border clearfix">
		                                <div class="item_info">
		                                    <span>日用品</span>
		                                    <em style="background: #F2E6CE;">进去看看</em>
		                                </div>
		                            </div>
		                        </div>
		                    </a>
		                </div>
		                
		                <!-- 清除浮动 -->
		                <div style="clear: both;"></div>
		                
		             </div>
		         </div>
            </div>
			
			
			<!-- 展示分类 -->
			<div id="show_classify">
				<!-- "教材/课外书" -->
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">书籍<a href="${pageContext.request.contextPath}/nofilter_moreGoods?goodsType=书籍" class="more">更多>></a></h3>
					</div>
					<div class="panel-body show_hot_items">
						<!-- 具体商品信息 -->
						<c:forEach items="${booksGoodsInfo.list}" var="goods" varStatus="i">
							<div class="show_itme">
								<a href="showGoodsDetail/${goods.goodsId}" target="_blank">
									<img class="lazy" data-original="${pageContext.request.contextPath}/goodsImgs/${goods.images[0].imgUrl}" width="200px" height="133px" />
								</a>
								<div class="text_info">
									<span>${goods.goodsName}</span><br>
									<span class="price">￥：${goods.goodsPrice}元</span>
								</div>
								<div class="pay">
									<a href="showGoodsDetail/${goods.goodsId}" target="_blank" class="btn btn-success"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;&nbsp;查看详情</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- 手机/电脑等 -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">电子设备<a href="${pageContext.request.contextPath}/nofilter_moreGoods?goodsType=电子设备" class="more">更多>></a></h3>
					</div>
					<div class="panel-body show_hot_items">
						<!-- 具体商品信息 -->
						<c:forEach items="${eleProductGoodsInfo.list}" var="goods" varStatus="i">
							<div class="show_itme">
								<a href="showGoodsDetail/${goods.goodsId}" target="_blank">
									<img class="lazy" data-original="${pageContext.request.contextPath}/goodsImgs/${goods.images[0].imgUrl}" width="200px" height="133px" />
								</a>
								<div class="text_info">
									<span>${goods.goodsName}</span><br>
									<span class="price">￥：${goods.goodsPrice}元</span>
								</div>
								<div class="pay">
									<a href="showGoodsDetail/${goods.goodsId}" target="_blank" class="btn btn-success"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;&nbsp;查看详情</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>				
				<!-- 衣物 -->
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">衣物<a href="${pageContext.request.contextPath}/nofilter_moreGoods?goodsType=衣物" class="more">更多>></a></h3>
					</div>
					<div class="panel-body show_hot_items">
						<!-- 具体商品信息 -->
						<c:forEach items="${clothesGoodsInfo.list}" var="goods" varStatus="i">
							<div class="show_itme">
								<a href="showGoodsDetail/${goods.goodsId}" target="_blank">
									<img class="lazy" data-original="${pageContext.request.contextPath}/goodsImgs/${goods.images[0].imgUrl}" width="200px" height="133px" />
								</a>
								<div class="text_info">
									<span>${goods.goodsName}</span><br>
									<span class="price">￥：${goods.goodsPrice}元</span>
								</div>
								<div class="pay">
									<a href="showGoodsDetail/${goods.goodsId}" target="_blank" class="btn btn-success"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;&nbsp;查看详情</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- 日用品 -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">日用品<a href="${pageContext.request.contextPath}/nofilter_moreGoods?goodsType=日用品" class="more">更多>></a></h3>
					</div>
					<div class="panel-body show_hot_items">
						<!-- 具体商品信息 -->
						<c:forEach items="${dailyGoodsInfo.list}" var="goods" varStatus="i">
							<div class="show_itme">
								<a href="showGoodsDetail/${goods.goodsId}" target="_blank">
									<img class="lazy" data-original="${pageContext.request.contextPath}/goodsImgs/${goods.images[0].imgUrl}" width="200px" height="133px" />
								</a>
								<div class="text_info">
									<span>${goods.goodsName}</span><br>
									<span class="price">￥：${goods.goodsPrice}元</span>
								</div>
								<div class="pay">
									<a href="showGoodsDetail/${goods.goodsId}" target="_blank" class="btn btn-success"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;&nbsp;查看详情</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- 男生专区 -->
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">男生专区 <a href="${pageContext.request.contextPath}/nofilter_moreGoods?goodsType=男生专区" class="more">更多>></a></h3>
					</div>
					<div class="panel-body show_hot_items">
						<!-- 具体商品信息 -->
						<c:forEach items="${manGoodsInfo.list}" var="goods" varStatus="i">
						<div class="show_itme">
							<a href="showGoodsDetail/${goods.goodsId}" target="_blank">
								<img class="lazy" data-original="${pageContext.request.contextPath}/goodsImgs/${goods.images[0].imgUrl}" width="200px" height="133px" />
							</a>
							<div class="text_info">
								<span>${goods.goodsName}</span><br>
								<span class="price">￥：${goods.goodsPrice}元</span>
							</div>
							<div class="pay">
								<a href="showGoodsDetail/${goods.goodsId}" target="_blank" class="btn btn-success"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;&nbsp;查看详情</a>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
				<!-- 女生专区 -->
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h3 class="panel-title">女生专区 <a href="${pageContext.request.contextPath}/nofilter_moreGoods?goodsType=女生专区" class="more">更多>></a></h3>
					</div>
					<div class="panel-body show_hot_items">
						<!-- 具体商品信息 -->
						<c:forEach items="${womanGoodsInfo.list}" var="goods" varStatus="i">
						<div class="show_itme">
							<a href="showGoodsDetail/${goods.goodsId}" target="_blank">
								<img class="lazy" data-original="${pageContext.request.contextPath}/goodsImgs/${goods.images[0].imgUrl}" width="200px" height="133px" />
							</a>
							<div class="text_info">
								<span>${goods.goodsName}</span><br>
								<span class="price">￥：${goods.goodsPrice}元</span>
							</div>
							<div class="pay">
								<a href="showGoodsDetail/${goods.goodsId}" target="_blank" class="btn btn-success"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;&nbsp;查看详情</a>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
				
			</div>
			
			<!-- 一个隐藏表单 -->
			<form action="${pageContext.request.contextPath}/moreGoods" method="post">
				<input type="hidden" name="goodsType" id="goodsType"/>
			</form>
			
			<!-- 底部栏 -->
			<div id="fooler">
				<div id="fooler_info">
					Copyright © 2017-2018  校园闲置物品交易平台  jiangshaoneng.com All Rights Reserved. 备案号：湘ICP备15012807号-1
					<hr width="80%"/>
				</div>
			</div>
		</div>
		
		<script>
			$(function() { // 初始化轮播
				init();
				window.onload = function(){$('#myCarousel').carousel({'interval':5000});};
				window.onload = function(){var $container = $('.portfolio_container');
				    $container.isotope({filter: '*',});
				}
				
				//鼠标移入商品项的时候
				$(".show_itme").hover(function(){
						$(this).children(".pay").show();
					},
					function(){
						$(this).children(".pay").hide();
					})
				
				//加载时打开第一条公告
				$($(".collapse")[0]).addClass("in");
				
				//懒加载图片
				$("img.lazy").lazyload({
					 event : "sporty"
			        });
				$(window).bind("load", function() {
					var i = 0;
					var timeout = setInterval(function() {
						$($("img.lazy")[i]).trigger("sporty");
						i++;
						console.log(i);
						if(i>$("img.lazy").size()){
							console.log("over");
							clearInterval(timeout);
						}
					}, 250);
				});
			});
			//初始化方法
			function init(){
				$(".pay").hide();
				initNotice();
				//alert("${eleProductGoodsInfo}");
			}
			//初始化公告栏
			function initNotice(){
				var $notice_0 = $(".notice:eq(0)");
				$notice_0.addClass("panel-default");
				var $notice_1 = $(".notice:eq(1)");
				$notice_1.addClass("panel-success");
				var $notice_2 = $(".notice:eq(2)");
				$notice_2.addClass("panel-info");
				var $notice_3 = $(".notice:eq(3)");
				$notice_3.addClass("panel-warning");
				
			}
			
			
			//换一批
			function perv_hot_goods(){
				var left = $(".long_div").css("left");
				if(left=="0px"){
					$(".long_div").animate({left:"-980px"});
				}
				if(left=="-980px"){
					$(".long_div").animate({left:"0px"});
				}
				
			}
			function next_hot_goods(){
				var left = $(".long_div").css("left");
				if(left=="-980px"){
					$(".long_div").animate({left:"0px"});
				}
				if(left=="0px"){
					$(".long_div").animate({left:"-980px"});
				}
				
			}
			
			//更多
			function moreGoods(goodsType){
				//获取点击的种类
				$("#goodsType").val(goodsType);
				
			}
		</script>
	</body>

</html>