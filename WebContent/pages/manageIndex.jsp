<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理员首页</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/manageIndex.js"></script>
		<style type="text/css">
			.show_main{
				width: 1024px;
				height: 600px;
				margin: 70px auto;
				margin-bottom: 0px;
			}
			.left{
				float: left;
				width: 15%;
				height: 500px;
				position: fixed;
				/*border: 1px solid #CCCCCC;*/
				/*box-shadow: 5px 5px 5px #CCCCCC;*/
			}
			.right{
				float: right;
				width: 80%;
				border: 1px solid #CCCCCC;
				box-shadow: 5px 5px 5px #CCCCCC;
			}
			/*查询公告*/
			.filter_search_result_item{
				font-size: 8px;
				border: 1px solid #CCCCCC;
				margin-top: 10px;
				padding: 5px 10px;
			}
			.add_time{
				float: right;
				margin-right: 20px;
			}
			.morePage{
				text-align: center;
			}
			/*发布公告*/
			
		</style>
		
	</head>
	<body>
		<!--
			1，公告管理		新增||删除||查询
			2，用户管理		账号的冻结 ||查询账号
        -->
        
        <div class="show_main">
        	
        	<!-- 左浮动 -->
        	<div class="left">
        		
        		<div class="panel-group" id="accordion">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" 
								   href="#collapseOne">
									公告管理
								</a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in">
							<div class="panel-body">
								<button type="button" class="btn btn-default btn-block manager">查询公告</button>
								<button type="button" class="btn btn-default btn-block manager">新增公告</button>
							</div>
						</div>
					</div>
					
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" 
								   href="#collapseTwo">
									用户管理	
								</a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse">
							<div class="panel-body">
								<button type="button" class="btn btn-default btn-block manager">用户查询</button>
							</div>
						</div>
					</div>
					
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" 
								   href="#collapsethree">
									商品管理	
								</a>
							</h4>
						</div>
						<div id="collapsethree" class="panel-collapse collapse">
							<div class="panel-body">
								<button type="button" class="btn btn-default btn-block manager">商品查询</button>
							</div>
						</div>
					</div>
					
				</div>

        		
        	</div>
        	
        	<!-- 右浮动 -->
        	<div class="right">
        		
        		<!-- 查询公告 -->
        		<div id="search_notice" class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">查询公告</h3>
					</div>
					<div class="panel-body">
						<!-- 条件查询公告 -->
						<div class="filter_search_notice">
							<form class="bs-example bs-example-form" role="form">
						        
						        <div class="input-group">
						            <span class="input-group-addon">时间区间</span>
						            <input type="date" class="form-control" id="noticeStartTime" placeholder="起始时间">
						            <input type="date" class="form-control" id="noticeEndTime" placeholder="结束时间">
						        </div>
						        <div class="input-group">
						            <span class="input-group-addon">内容查询</span>
						            <input type="text" class="form-control" id="noeiceInfo" placeholder="输入公告的内容">
						        </div>
						        <br>
						        <button class="btn btn-primary btn-block">查询</button>
						    </form>
						</div>
						
						<!-- 查询结果 -->
						<div class="filter_search_result">
							<h5>查询结果>></h5>
							<c:forEach items="${noticePage.list}" var="notice">
							<div class="filter_search_result_item">
								<div>
									<span>${notice.noticeTitle}</span>
									<a href="#" class="add_time">删除</a>
									<span class="add_time">${notice.noticeAddtime}</span>
									
								</div>
								<hr style="margin: 5px;"/>
								<div>${notice.noticeInfo}</div>
							</div>
							</c:forEach>
							
							
							<div class="morePage">
								<div class="M-box1" style="margin: 10px auto; width: 80%;"></div>
							</div>
							
						</div>
					</div>
				
        		</div>
        		
        		<!-- 发布公告 -->
        		<div id="add_notice" class="panel panel-primary">
				    <div class="panel-heading">
				        <h3 class="panel-title">公告编辑</h3>
				    </div>
				    <div class="panel-body">
				     	<form class="bs-example bs-example-form" role="form">
						        <div class="input-group">
						            <span class="input-group-addon">公告名称</span>
						            <input type="text" class="form-control" placeholder="输入公告名称">
						        </div>
						        <br>
						        <div class="input-group">
						            <span class="input-group-addon">公告内容</span>
						            <textarea type="text" class="form-control" placeholder="输入公告名称" style="resize: none; height: 100px;"></textarea>
						        </div>
						        <br>
						        <button class="btn btn-primary btn-block">发布公告</button>
						    </form>
				    </div>
				</div>

				<!-- 用户查询 -->
				<div id="search_customer" class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">用户查询</h3>
					</div>
					<div class="panel-body">
						<!-- 查询条件 -->
						<div class="filter_search_customer">
							<form class="bs-example bs-example-form" role="form">
						        
						        <div class="input-group">
						            <span class="input-group-addon">注册时间</span>
						            <input type="text" class="form-control" placeholder="起始时间">
						            <input type="text" class="form-control" placeholder="结束时间">
						        </div>
						        <div class="input-group">
						            <span class="input-group-addon">用户名称</span>
						            <input type="text" class="form-control" placeholder="输入用户名称">
						        </div>
						        <br>
						        <button class="btn btn-primary btn-block">查询</button>
						    </form>
						</div>
						
						<!-- 查询结果 -->
						<div class="filter_search_customer_result">
							<table class="table table-hover">
							  <caption>用户查询结果表</caption>
							  <thead>
							    <tr>
							      <th>客户类型</th>
							      <th>用户名</th>
							      <th>真实姓名</th>
							      <th>联系方式</th>
							      <th>注册时间</th>
							      <th>状态</th>
							      <th>操作</th>
							    </tr>
							  </thead>
							  <tbody>
							    <tr>
							      <td>正式客户</td>
							      <td>红尘破浪</td>
							      <td>蒋少能</td>
							      <td>15673219519</td>
							      <td>2017-07-08</td>
							      <td>已激活</td>
							      <td><a>冻结</a></td>
							    </tr>
							    <tr>
							      <td>正式客户</td>
							      <td>红尘破浪</td>
							      <td>蒋少能</td>
							      <td>15673219519</td>
							      <td>2017-07-08</td>
							      <td>已激活</td>
							      <td><a>冻结</a></td>
							    </tr>
							    <tr>
							      <td>正式客户</td>
							      <td>红尘破浪</td>
							      <td>蒋少能</td>
							      <td>15673219519</td>
							      <td>2017-07-08</td>
							      <td>已激活</td>
							      <td><a>冻结</a></td>
							    </tr>
							  </tbody>
							</table>
							<div class="morePage">
								<ul class="pagination">
								    <li><a href="#">&laquo;</a></li>
								    <li class="active"><a href="#">1</a></li>
								    <li><a href="#">2</a></li>
								    <li><a href="#">3</a></li>
								    <li><a href="#">4</a></li>
								    <li><a href="#">5</a></li>
								    <li><a href="#">&raquo;</a></li>
								</ul>
							</div>
						</div>
						
					</div>
				</div>
				
				<!-- 商品管理 -->
				<div id="search_goods" class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">商品查询</h3>
					</div>
					<div class="panel-body">
						<!-- 查询条件 -->
						<div class="filter_search_goods">
							<form class="bs-example bs-example-form" role="form">
						        
						        <div class="input-group">
						            <span class="input-group-addon">上架时间</span>
						            <input type="text" class="form-control" placeholder="起始时间">
						            <input type="text" class="form-control" placeholder="结束时间">
						        </div>
						        <div class="input-group">
						            <span class="input-group-addon">查关键字</span>
						            <input type="text" class="form-control" placeholder="输入商品的关键字">
						        </div>
						        <div class="input-group">
						            <span class="input-group-addon">通过用户</span>
						            <input type="text" class="form-control" placeholder="输入用户的名称查询其商品">
						        </div>
						        <br>
						        <button class="btn btn-primary btn-block">查询</button>
						    </form>
						</div>
						
						<!-- 查询结果 -->
						<div class="filter_search_goods_result">
							
							<table class="table table-hover">
							  <caption>查询商品结果表</caption>
							  <thead>
							    <tr>
							      <th>商品名称</th>
							      <th>发布人</th>
							      <th>商品分类</th>
							      <th>价格</th>
							      <th>发布时间</th>
							    </tr>
							  </thead>
							  <tbody>
							    <tr>
							      <td>小米手机2s</td>
							      <td>红尘破浪</td>
							      <td>电子产品</td>
							      <td>999</td>
							      <td>2017-12-12</td>
							    </tr>
							    <tr>
							      <td>小米手机2s</td>
							      <td>红尘破浪</td>
							      <td>电子产品</td>
							      <td>999</td>
							      <td>2017-12-12</td>
							    </tr>
							    <tr>
							      <td>小米手机2s</td>
							      <td>红尘破浪</td>
							      <td>电子产品</td>
							      <td>999</td>
							      <td>2017-12-12</td>
							    </tr>
							  </tbody>
							</table>
							
							<div class="morePage">
								<ul class="pagination">
								    <li><a href="#">&laquo;</a></li>
								    <li class="active"><a href="#">1</a></li>
								    <li><a href="#">2</a></li>
								    <li><a href="#">3</a></li>
								    <li><a href="#">4</a></li>
								    <li><a href="#">5</a></li>
								    <li><a href="#">&raquo;</a></li>
								</ul>
							</div>
						</div>
						
					</div>
				</div>
				
        	</div>
        
        </div>
        
	</body>
</html>
