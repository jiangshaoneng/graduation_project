<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta charset="UTF-8">
		<title>管理员登录</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		
		<style type="text/css">
			 .bg_img{
				margin-top: 50px;
				width: 100%;
				height: 100%;
				background: url(${pageContext.request.contextPath}/img/customerLogin_bg.jpg);
				background-size: 100%, 100%;
				background-repeat: no-repeat;
			}
			.manageLogin{
				width: 1024px;
				margin: 0px auto;
				padding: 140px 300px;
				text-align: center;
			}
			
			.manageLogin_title{
				color: #FFFFFF;
				font-size: 24px;
			}
			.my-input,.my-submit{
				background-color:rgba(225,225,225,0.1);
				width: 80%;
				height: 40px;
				color:#fff;
				margin:10px 0px;
				border: 1px solid #ccc; 
				text-align: center;
			}
			.my-input:hover{
				background-color:rgba(225,225,225,0.8);
			}
			.my-submit:hover{
				background-color:rgba(51,166,184,0.8);
			}
			input::-webkit-input-placeholder {
        		/* placeholder颜色  */
       		 	color: #FFF;
        		/* placeholder字体大小  */
         		font-size: 14px;
         		/* placeholder位置  */
         		text-align: center;
     		}
		</style>
		
	</head>
	<body>
		<div class="bg_img">
			<div class="manageLogin">
				<form action="/graduation_project/manage_login" method="post">
					<input type="text" class="my-input" id="name" name="adminName" placeholder="管理员名称">
					<input type="password" class="my-input" id="password" name="adminPassword" placeholder="管理员密码 ">
					<button type="submit" class="my-submit" onclick="login()">安全登录 &nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></button>
				</form>
			</div>
		</div>		
	</body>

<script type="text/javascript">
	$(function(){
		showMsg();

		//文本框获取焦点的时候
		$(".my-input").focus(function(){
			$(this).stop();
			$(this).animate({width:"100%"});
		})
		$(".my-input").blur(function(){
			$(this).stop();
			$(this).animate({width:"80%"});
		})
		
	})
	
	//提示用户输入的用户名或密码是否合法
	function showMsg() {
		if ("${errorMsg}" != null && "${errorMsg}" != "") {
			alert("${errorMsg}");
		}
	}
</script>

</html>