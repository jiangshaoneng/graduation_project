<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>

<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

<style>
.content {
	width: 100%;
	height: 500px;
}

.bg_img {
	width: 100%;
	height: 100%;
	margin-top: 100px;
	background:
		url(${pageContext.request.contextPath}/img/customerLogin_bg.jpg);
	background-size: 100%, 100%;
	background-repeat: no-repeat;
}

.customerLogin {
	margin: 30px 50px;
	background: rgba(100%, 100%, 100%, 0.6);
	width: 400px;
	border-radius: 10px;
	float: right;
	box-shadow: 10px 10px 10px;
}

.move_right {
	margin-left: 50px;
	display: inline;
}

.btn {
	padding: 5px 60px;
}

.login_reg {
	text-align: center;
}

.login_title {
	text-align: center;
}

.help-inline {
	color: red;
	font-size: 8px;
}

.help-hidden {
	visibility: hidden;
}

.check_img {
	float: right;
	height: 34px;
	width: 120px;
	margin-right: 80px;
	border-radius: 5px;
	overflow: hidden;
	display: inline;
	background: #aaa;
	color: #ffffff;
	font-size: 28px;
	font-family: "Segoe Script";
}

#get_password {
	margin: 0px;
}

#get_password_label {
	display: inline;
}

.bule_text {
	color: #0060CC;
	font-size: 6px;
}

.login_someinfo {
	margin: 20px;
	text-align: left;
	padding-left: 50px;
}

.forget_password {
	margin-left: 140px;
}

input {
	text-align: center;
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
					<li class="active"><a href="#">用户登录</a></li>
				</ul>
			</div>
		</div>
		</nav>
	</div>
	<!-- 用户登录页面   1：用户输入用户名以及密码对其进行数据库数据的校验后得得到是否登录成功的信息，
					  2：登录成功跳转回前一页面，登录失败提示相应的错误信息
					  3:登录时需要输入验证码 
					  4：当录入完整信息时，可以点击登录请求
					  5：注册入口-->

	<div class="content">
		<div class="bg_img">

			<!-- 登录表单 -->
			<div class="customerLogin">
				<form id="login_form" class="form-horizontal" method="post">

					<fieldset>

						<legend>
							<h3 class="login_title">用户登录</h3>
						</legend>


						<div class="form-group" id="cus_name_div">
							<div class="col-sm-9 move_right">
								<input class="form-control" id="cus_name" name="cusName"
									type="text" maxlength="20" placeholder="用户名"> <span
									class="help-inline help-hidden">*请输入用户名</span>
							</div>
						</div>

						<div class="form-group" id="cus_password_div">
							<div class="col-sm-9 move_right">
								<input class="form-control" id="cus_password" name="cusPassword"
									type="password" maxlength="20" placeholder="密码"> <span
									class="help-inline help-hidden">*请输入密码</span>
							</div>
						</div>

						<div class="form-group" id="check_info_div">
							<div class="col-sm-5 move_right">
								<input class="form-control" id="check_info" type="text"
									maxlength="8" placeholder="验证码"> <span
									class="help-inline help-hidden">*验证码不正确</span>
							</div>
							<div class="check_img"></div>

						</div>

					</fieldset>

					<div class="login_reg">
						<button class="btn btn-primary" id="login_btn">登录</button>
						<a href="${pageContext.request.contextPath}/pages/customerRegister.jsp" class="btn btn-success">注册</a>
					</div>

					<div class="login_someinfo">
						<input type="checkbox" id="get_password" value="记住密码？" /> <label
							id="get_password_label" for="get_password"><span
							class="bule_text">记住密码</span></label> <span
							class="forget_password bule_text"><a href="#">忘记密码？>></a></span>
					</div>

				</form>
			</div>
		</div>
	</div>

	<!-- 底部栏 -->
	<div id="fooler" style="text-align: center;">
		<div id="fooler_info">
			Copyright © 2017-2018 校园闲置物品交易平台 jiangshaoneng.com All Rights
			Reserved. 备案号：湘ICP备15012807号-1
			<hr width="80%" />
		</div>
	</div>

	<!-- javascrpt -->
	<script type="text/javascript">
		$(function() {

			//加载时在前台生成验证码
			new_number();
			//提示登录失败的信息
			showMsg();

			//点击验证码刷新
			$(".check_img").on('click', function() {
				new_number();
			});

			//单点击登录按钮时：验证表单是否输入值
			$("#login_btn").on('click',
							function() {
								//获取表单的用户名，密码，验证码是否为空
								var cus_name = $("#cus_name").val();
								var cus_password = $("#cus_password").val();
								var check_info = $("#check_info").val();

								if (cus_name == "") {//用户名为空
									$("#cus_name_div").addClass("has-error");
									$("#cus_name ~.help-inline").removeClass(
											"help-hidden");
								}
								if (cus_password.length < 6) {//密码不合法
									$("#cus_password_div")
											.addClass("has-error");
									$("#cus_password ~.help-inline")
											.removeClass("help-hidden");
								}

								//输入的信息合法时，进行登录
								if (cus_name != ""
										& cus_password.length >= 6
										& $("#check_info").val() == $(
												".check_img").text()) {
									//如果用户输入合法，发送到后台进行数据校验
									$('#login_form')
											.attr("action",
													"${pageContext.request.contextPath}/nofilter_customerLogin");
									$('#login_form').submit();
								} else {
									//如果用户输入的信息不合法,弹出提示
									alert("信息不完整！");
									return false;
								}
							});

			//输入信息后将错误提示去掉
			$("#cus_name,#cus_password").on('change', function() {
				if ($(this).val() != "") {
					//alert(123);
					$(this).parent().parent().removeClass("has-error");
					$(this).next(".help-inline").addClass("help-hidden");
				}
			});

			//判断验证码是否正确
			$("#check_info").on('change',
					function() {
						/*alert($("#check_info").val()==$(".check_img").text());*/
						if ($("#check_info").val() == $(".check_img").text()) {
							$("#check_info").parent().parent().removeClass(
									"has-error");
							$("#check_info ~.help-inline").addClass(
									"help-hidden");
						} else {
							$("#check_info_div").addClass("has-error");
							$("#check_info ~.help-inline").removeClass(
									"help-hidden");
						}
					});
		});

		//////////////////////////////////
		//生成验证码
		function new_number() {
			//随机生成4个数
			var value_number = "";
			for (var i = 0; i < 6; i++) {
				value_number = value_number + Math.round(Math.random() * 9);
			}
			//将生成的4位数放到div中
			$(".check_img").text(value_number);
		};

		//提示用户输入的用户名或密码是否合法
		function showMsg() {
			if ("${msg}" != null && "${msg}" != "") {
				alert("${msg}");
			}
		}
	</script>
</body>
</html>
