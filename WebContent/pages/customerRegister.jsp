<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
		<!-- 日期控件 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/BeatPicker.min.css"/>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demos.css"/>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/prism.css"/>
		
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<!-- 地址三级联动 -->
		<script src="${pageContext.request.contextPath}/js/distpicker.data.js"></script>
	    <script src="${pageContext.request.contextPath}/js/distpicker.js"></script>
	    <script src="${pageContext.request.contextPath}/js/main.js"></script>
	    <!-- 日期控件 -->
	    <script src="${pageContext.request.contextPath}/js/BeatPicker.min.js"></script>
    	<script src="${pageContext.request.contextPath}/js/prism.js"></script>

<style>
.main {
	width: 1200px;
	margin: 70px auto;
	/*border: 1px solid red;*/
}

.step {
	width: 80%;
	margin: 0px auto;
	box-shadow: 5px 5px 40px #AAAAAA;
	/*border: 1px solid red;*/
}

.step ul li {
	width: 33.33%;
}

.step ul li a {
	/*padding: 10px 100px;*/
	
}

.step_deail_1, .step_deail_2, .step_deail_3 {
	width: 60%;
	margin: 20px auto;
	padding: 40px;
	box-shadow: 5px 5px 40px #AAAAAA;
}
</style>

</head>

<body onload="init()">

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
					<li class="active"><a href="#">注册账号</a></li>
					<li><a
						href="${pageContext.request.contextPath}/pages/customerLogin.jsp">登录</a></li>
				</ul>
			</div>
		</div>
		</nav>
	</div>

	<div class="main">

		<div class="step">
			<ul class="nav nav-tabs">
				<li><a href="#">第一步：填写信息</a></li>
				<li><a href="#">第二步：邮箱绑定</a></li>
				<li><a href="#">第三步：注册成功</a></li>
			</ul>
			<!-- 注册精度条 -->
			<div class="progress">
				<div class="progress-bar" role="progressbar" aria-valuenow="60"
					aria-valuemin="0" aria-valuemax="100" style="width: 33%;">
					<span class="sr-only">33% 完成</span>
				</div>
			</div>

		</div>

		<!-- =================基本信息================= -->
		<div class="step_deail_1">
			<form class="form-horizontal" role="form">

				<div class="form-group">
					<label class="col-sm-2 control-label">用户昵称:</label>
					<div class="col-sm-10">
						<input class="form-control" id="cus_nickname" onblur="okCusName()"
							type="text" maxlength="20" placeholder="用户昵称">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">真实姓名:</label>
					<div class="col-sm-10">
						<input class="form-control" id="cus_realname" type="text"
							maxlength="20" placeholder="真实姓名...">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">设置密码:</label>
					<div class="col-sm-10">
						<input class="form-control" id="cus_password_1" type="password"
							maxlength="20" placeholder="6-20位">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">确认密码:</label>
					<div class="col-sm-10">
						<input class="form-control" id="cus_password_2" type="password"
							maxlength="20" placeholder="两次密码需要一致">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">用户性别:</label>
					<div class="col-sm-10">
						<!--<input class="form-control" id="focusedInput" type="text" value="该输入框获得焦点...">-->
						<select class="form-control" id="cus_gender">
							<option>男</option>
							<option>女</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">用户电话:</label>
					<div class="col-sm-10">
						<input class="form-control" id="cus_phone" type="text"
							maxlength="11" placeholder="联系电话...">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">出生日期:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="cus_birthday" 
							data-beatpicker="true" data-beatpicker-position="['*','*']" 
							data-beatpicker-module="today,clear">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">用户地址:</label>
					<div data-toggle="distpicker">
				        <div class="col-sm-4">
				          <label class="sr-only" for="province1">Province</label>
				          <select class="form-control" id="province1"></select>
				        </div>
				        <div class="col-sm-3">
				          <label class="sr-only" for="city1">City</label>
				          <select class="form-control" id="city1"></select>
				        </div>
				        <div class="col-sm-3">
				          <label class="sr-only" for="district1">District</label>
				          <select class="form-control" id="district1"></select>
				        </div>
				     </div>
				</div>
				<div class="form-group">
				    <label class="col-sm-2 control-label">详细地址:</label>
				    <div class="col-sm-10">
				       <input class="form-control" id="descInfo" type="text" maxlength="200" />
				    </div>
				</div>

				<div class="next_step">
					<button type="button" class="btn btn-primary btn-block"
						onclick="next_step_1()">下一步</button>
				</div>

			</form>
		</div>

		<!-- =================邮箱绑定================= -->
		<div class="step_deail_2">
		
			<div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert">
					&times;
				</a>
				<strong>温馨提示！</strong>
				已完成基本信息填写,恭喜成为平台的用户!绑定邮箱可以成为[认证用户]<br>
				<a href="customerLogin.jsp">以后绑定,先登录</a>
			</div>
			
			<form class="form-horizontal" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">输入邮箱:</label>
					<div class="col-sm-10">
						<input class="form-control" id="cus_email" type="text"
							maxlength="20" placeholder="输入邮箱...">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">验证码:</label>
					<div class="col-sm-4">
						<input class="form-control" id="cus_email_info" type="text"
							maxlength="6" placeholder="输入验证码...">
					</div>
					<button type="button" class="btn btn-success" id="send_email"
						onclick="cus_email_check()">发送验证码</button>
				</div>

				<div class="next_step">
					<button type="button" class="btn btn-primary btn-block"
						onclick="next_step_2()">下一步</button>
				</div>

			</form>
		</div>

		<!-- =================注册成功================= -->
		<div class="step_deail_3">
			<div class="alert alert-success">
				恭喜注册成功！<a href="${pageContext.request.contextPath}/showIndex">返回首页</a>
			</div>
			<div class="next_step">
				<a href="${pageContext.request.contextPath}/pages/customerLogin.jsp"
					class="btn btn-primary btn-block">立即登录</a>
			</div>
		</div>

	</div>

	<script type="text/javascript">
		//加载页面的时候，只显示填入基本信息，点击下一步显示下一步骤
		function init() {
			//隐藏步骤二，步骤三
			//alert(123);
			$(".step_deail_2").hide();
			$(".step_deail_3").hide();
		}

		//点击下一步时，隐藏当前页面显示下一页面
		function next_step_1() {
			//清除上一次的错误提示
			$(".alert-warning").remove();

			if (cus_info_check()) {//======= ==================    未了测试             if(cus_info_check())
				addCustomerInfo();
			}
		}

		var nickName = "";
		//检查用户名是否存在
		function okCusName() {
			var cus_nickname = $("#cus_nickname").val();//用户昵称
			$.ajax({
				type : "POST", //提交方式 
				url : "${pageContext.request.contextPath}/nofilter_okCusName",//路径
				dateType : "text",//返回的数据类型
				data : {
					"cus_nickname" : cus_nickname
				},
				success : function(data) {
					if (data == "error") {//用户名不可用
						$("#cus_nickname").val("");
						alert("用户名已存在！");
					} else {
						nickName = cus_nickname;
					}
				}
			});
		}

		//点击下一步完成用户的基本信息的录入
		function addCustomerInfo() {
			//得到所有表单的信息
			var cus_nickname = $("#cus_nickname").val();//用户昵称
			var cus_realname = $("#cus_realname").val();//用户真实姓名
			var cus_password_1 = $("#cus_password_1").val();//用户密码
			var cus_password_2 = $("#cus_password_2").val();//确认密码
			var cus_gender = $("#cus_gender").val();
			var cus_phone = $("#cus_phone").val();//用户电话
			var cus_birthday = $("#cus_birthday").val();//用户生日
			var cus_address_1 = $("#province1").val();//省
			var cus_address_2 = $("#city1").val();//市
			var cus_address_3 = $("#district1").val();//区
			var cus_address_4 = $("#descInfo").val();//详细地址址
			//ajax向注册基本信息发送请求
			$.ajax({
				type : "POST", //提交方式 
				url : "${pageContext.request.contextPath}/nofilter_addCustomerInfo",//路径
				dateType : "text",//返回的数据类型
				data : {
					"cus_nickname" : cus_nickname,
					"cus_realname" : cus_realname,
					"cus_password" : cus_password_1,
					"cus_gender" : cus_gender,
					"cus_phone" : cus_phone,
					"cus_birthday" : cus_birthday,
					"cus_address_1" : cus_address_1,
					"cus_address_2" : cus_address_2,
					"cus_address_3" : cus_address_3,
					"cus_address_4" : cus_address_4
				},
				success : function(data) {
					if (data = "success") {
						$(".step_deail_1").hide();
						$(".step_deail_2").show();
						//进度条增加
						$(".progress-bar")[0].style.width = "66%";
					} else {
						alert("处理异常");
					}
				}
			});

		}

		//=====================校验表单数据 step_1===========================

		//创建一个警告框
		function new_alert(parentNode, alertInfo) {
			var temp = "";
			temp = temp + "<div class='alert alert-warning'>";
			temp = temp
					+ "<a href='#' class='close' data-dismiss='alert'>&times;</a>";
			temp = temp + "<strong>警告：</strong>" + alertInfo
			temp = temp + "</div>";
			parentNode.append(temp);
		}

		//校验用户信息是否合法
		function cus_info_check() {
			var count = 0;//不合法的表单数
			//获取表单信息所有节点
			var cus_nickname = $("#cus_nickname");//用户昵称
			var cus_realname = $("#cus_realname");//用户真实姓名
			var cus_password_1 = $("#cus_password_1");//用户密码
			var cus_password_2 = $("#cus_password_2");//确认密码
			var cus_phone = $("#cus_phone");//用户电话
			var cus_birthday = $("#cus_birthday");//用户生日
			var cus_address_1 = $("#province1");//省
			var cus_address_2 = $("#city1");//市
			var cus_address_3 = $("#district1");//区
			var cus_address_4 = $("#descInfo");//详细地址
			
			if (cus_nickname.val() == "") {
				var parentNode = cus_nickname.parent().parent();
				new_alert(parentNode, "昵称为空！");
				count++;
			}
			if (cus_realname.val() == "") {//未输入真实姓名
				var parentNode = cus_realname.parent().parent();
				new_alert(parentNode, "输入真实姓名");
				count++;
			}

			var exp_pwd = /^[a-zA-Z0-9]{6,18}$/;
			var cus_pwd = cus_password_1.val();
			if (!exp_pwd.test(cus_pwd)) {//密码格式不合法
				var parentNode = cus_password_1.parent().parent();
				new_alert(parentNode, "密码格式不合法");
				count++;
			}
			if (cus_password_1.val() != cus_password_2.val()) {
				var parentNode = cus_password_2.parent().parent();
				new_alert(parentNode, "两次输入的密码不一样");
				count++;
			}

			var exp_phone = /1[34578]\d{9}/;
			var cus_phonenumber = cus_phone.val();
			if (!exp_phone.test(cus_phonenumber)) {
				var parentNode = cus_phone.parent().parent();
				new_alert(parentNode, "电话格式不合法");
				count++;
			}
			if (check_date()) {
				var parentNode = cus_birthday.parent().parent().parent();
				new_alert(parentNode, "日期不合法");
				count++;
			}
			if (cus_address_4.val() == "") {
				var parentNode = cus_address_4.parent().parent();
				new_alert(parentNode, "输入详细地址");
				count++;
			}
			//信息合法时返回true
			if (count == 0) {
				return true;
			}
			return false;
		}

		//判断输入的日期是否是未来时间或是未输入
		function check_date() {
			if ($("#cus_birthday").val() == "") {
				return true;
			}
			var currentData = new Date;
			var year = 1900 + currentData.getYear();
			var month = currentData.getMonth() + 1;
			var day = currentData.getDate();

			var bir = $("#cus_birthday").val().split("-");

			if (year > bir[0]) {
				return false;
			} else if (month > bir[1]) {
				return false;
			} else if (day > bir[2]) {
				return false;
			} else {
				//alert(3);
				return true;
			}
		}

		//=====================校验表单数据 step_2===========================
		var backCheckNumer = "";
		var emil = "";
		//点击下一步时，隐藏当前页面显示下一页面
		function next_step_2() {
			$(".alert-warning").remove();
			if (cus_email_info_check()) {
				//验证码通过通过,对用户进行绑定
				$.ajax({
					type : "POST", //提交方式 
					url : "${pageContext.request.contextPath}/nofilter_addEmail",//路径
					dateType : "text",//返回的数据类型
					data : {
						"nickName" : nickName,
						"emil" : emil
					},
					success : function(data) {
						if (data == "success") {
							$(".step_deail_2").hide();
							$(".step_deail_3").show();
							$(".progress-bar")[0].style.width = "100%";
						} else {
							alert("认证失败");
						}
					}
				});
			}
		}

		//校验邮箱格式
		function cus_email_check() {
			$(".alert-warning").remove();
			var cus_email = $("#cus_email");
			var exp = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
			if (!exp.test(cus_email.val())) {
				var parentNode = cus_email.parent().parent();
				new_alert(parentNode, "邮箱格式错误");
				return false;
			}
			//邮箱格式校验通过,发送邮箱验证码
			sendEmail();
			//发送邮件，开始计时下一次点击的时间
			send_timer();
			return true;
		}

		//邮箱格式输入正确是ajax发送验证码
		function sendEmail() {
			var cusEmail = $("#cus_email").val();
			$.ajax({
				type : "POST", //提交方式 
				url : "${pageContext.request.contextPath}/nofilter_sendEmail",//路径
				dateType : "text",//返回的数据类型
				data : {
					"cusEmail" : cusEmail
				},
				success : function(data) {
					if (data == "error") {
						alert("验证码发送失败!");
					} else {
						backCheckNumer = data;
						emil = cusEmail;
					}
				}
			});
		}

		//校验验证码是否正确
		function cus_email_info_check() {

			var cus_email_info = $("#cus_email_info");
			var info = backCheckNumer;//测试验证码信息
			if (cus_email_info.val() != info || cus_email_info.val() == "") {
				var parentNode = cus_email_info.parent().parent();
				new_alert(parentNode, "验证码不正确");
				return false;
			}
			return true;
		}

		//点击发送邮件信息的计时器
		function send_timer() {
			//禁止按钮的使用
			$("#send_email").attr('disabled', "true");
			var i = 60;//等待时间 60秒
			$("#send_email").text(i + "秒后重发");
			var timer = setInterval(function() {
				i--;
				$("#send_email").text(i + "秒后重发");
				if (i == 0) {
					$("#send_email").removeAttr('disabled');
					$("#send_email").text("发送验证码");
					clearInterval(timer);
				}
			}, 1000);
		}
	</script>

</body>

</html>