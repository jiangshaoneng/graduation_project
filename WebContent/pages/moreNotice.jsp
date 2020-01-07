<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>更多公告</title>
		<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
		<link type="text/css" href="${pageContext.request.contextPath}/css/pagination.css" rel="stylesheet" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.pagination.js"></script>

		<style type="text/css">
			.main_ontice{
				width: 1024px;
				margin: 70px auto;
				
			}
			.filter_search_result{
				padding: 10px;
			}
			.filter_search_result_item{
				border: 1px solid #CCCCCC;
				margin-top: 10px;
				padding: 10px;
				
			}
			.add_time{
				float: right;
				color: #0060CC;
			}
			.morePage{
				text-align: center;
			}
		</style>
	</head>

	<body>
		<div class="main_ontice">
		
		<!-- 顶部栏 -->
			<div id="top_nav">
				<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
				    <div class="container-fluid">
					    <div class="navbar-header">
					        <a class="navbar-brand" href="${pageContext.request.contextPath}/showIndex">校园闲置物品交易平台</a>
					    </div>
					    <div>
					        <ul class="nav navbar-nav">
					            <li class="active"><a href="#">更多公告</a></li>
					        </ul>
					        <ul class="nav navbar-nav navbar-right">
						      <li><a href="${pageContext.request.contextPath}/pages/customerRegister.jsp"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
						      <li><a href="${pageContext.request.contextPath}/pages/customerLogin.jsp"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
						    </ul>
					    </div>
				    </div>
				</nav>
			</div>
		
			<div class="filter_search_result">
			<h5 id="more">更多公告>></h5>
			
			<c:forEach items="${noticePage.list}" var="notice">
			<div class="filter_search_result_item">
				<div>
					<span>${notice.noticeTitle}</span>
					<span class="add_time">${notice.noticeAddtime}</span>

				</div>
				<hr style="margin: 5px;" />
				<div>
					${notice.noticeInfo}
				</div>
			</div>
			</c:forEach>

			<div class="M-box3" style="margin: 10px auto; width: 80%;"></div>
		</div>
	
		</div>
	</body>
	
	<!-- =====================js======================== -->
	<script type="text/javascript">
	//分页显示选择的页码
	$(function(){
		showNotices(1);
	})
	
	//ajax分页查询
	function showNotices(currentPageNo){
		$.ajax({
			type : "GET", //提交方式 
			url : "${pageContext.request.contextPath}/nofilter_chooseMoreNotice",//路径
			dateType : "JSON",//返回的数据类型
			data : {
				"currentPageNo" : currentPageNo//评论的编号
			},
			success : function(data) {
				console.log(data);
				var notices = data.list;
				var temp = "";
				for(var index in notices){
					temp += "<div class='filter_search_result_item'>";
					temp += "<div>";
					temp += "<span>"+notices[index].noticeTitle+"</span>";
					temp += "<span class='add_time'>"+notices[index].noticeAddtime+"</span>";
					temp += "</div>";
					temp += "<hr style='margin: 5px;' />";
					temp += "<div>"+notices[index].noticeInfo+"</div>";
					temp += "</div>";
				}
				$(".filter_search_result_item").remove();
				$("#more").append(temp);
				choosePage(data);
			}
		});
	}
	
	//choosePage
	function choosePage(data){
		$('.M-box3').pagination({
			pageCount:data.totalPage,
			current:data.currentPageNo,
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
				showNotices(currentPageNo)
			}
		})
	}
	
	</script>
</html>