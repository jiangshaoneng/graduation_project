<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
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
		

<style type="text/css">
.mian_info {
	width: 1024px;
	margin: 70px auto;
	border: 1px solid #CCCCCC;
}

.main_left {
	float: left;
	width: 20%;
}

.main_right {
	float: right;
	width: 80%;
}
/* 我的资料 */
.person_info, .person_money, .person_pwd, .person_email {
	width: 80%;
	margin: 20px auto;
	padding: 0px 200px;
	box-shadow: 5px 5px 5px #CCCCCC;
	border: 1px solid #CCCCCC;
}

.person_address {
	width: 80%;
	margin: 20px auto;
	padding: 0px 20px;
	box-shadow: 5px 5px 5px #CCCCCC;
	border: 1px solid #CCCCCC;
}

.person_info form > div {
	margin: 20px;
}

.person_pwd div {
	margin: 20px;
	text-align: center;
}

.person_money form div {
	margin: 20px;
}

.person_address div {
	margin: 20px;
	text-align: center;
}

.person_email form div {
	margin: 20px;
	text-align: center;
}

.successInfo {
	border: 1px solid green;
}

.errorInfo {
	border: 1px solid red;
}
</style>
</head>
<body>
	<!--
         	作者：offline
         	时间：2017-12-19
         	描述：1,个人信息的修改 2,地址的添加 3,
         -->
	<div class="mian_info">

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
						<li class="active"><a href="#">个人信息</a></li>
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

		<ul class="nav nav-tabs nav-justified">
			<li id="my_info"><a href="#" class="action">我的资料</a></li>
			<li id="my_pwd"><a href="#">密码管理</a></li>
			<li id="my_money"><a href="#">我的钱包</a></li>
			<li id="my_address"><a href="#">地址管理</a></li>
			<li id="my_email"><a href="#">邮箱绑定</a></li>
		</ul>

		<!-- 个人资料 -->
		<div class="person_info">
			<form class="bs-example bs-example-form" role="form"
				id="person_info_form" method="post">
				<input type="hidden" name="model" value="model_1" /> <input
					type="hidden" name="cusId" value="${cus.cusId}" />
				<div class="input-group input-group-sm">
					<span class="input-group-addon">类型</span> <input type="text"
						class="form-control" readonly="readonly" value="${cus.cusType}">
				</div>
				<div class="input-group input-group-sm">
					<span class="input-group-addon">姓名</span> <input type="text"
						class="form-control" readonly="readonly"
						value="${cus.cusRealname}">
				</div>
				<div class="input-group input-group-sm">
					<span class="input-group-addon">昵称</span> <input type="text"
						id="cus_name" name="cusName" onblur="okNickName()"
						class="form-control" value="${cus.cusName}">
				</div>
				<div class="input-group input-group-sm">
					<span class="input-group-addon">性别</span> <select
						class="form-control" name="cusGender">
						<c:if test="${cus.cusGender=='男'}" var="man">
							<option selected="selected">男</option>
							<option>女</option>
						</c:if>
						<c:if test="${!man}">
							<option>男</option>
							<option selected="selected">女</option>
						</c:if>
					</select>
				</div>
				<div class="input-group input-group-sm">
					<span class="input-group-addon">生日</span> 
					<%-- <input type="date"
						id="cus_bir" name="cusBirthday" class="form-control"
						value="${birthday}"> --%>
						<input type="text" id="cus_bir" name="cusBirthday" class="form-control" value="${birthday}" 
							data-beatpicker="true" data-beatpicker-position="['*','*']" data-beatpicker-module="today,clear">
				</div>
				<div class="input-group input-group-sm">
					<span class="input-group-addon">电话</span> <input type="text"
						id="cus_phone" name="cusPhone" class="form-control"
						value="${cus.cusPhone}">
				</div>
				<div>
					<button type="button" class="btn btn-primary btn-block"
						onclick="submit_info()">确认修改</button>
				</div>
			</form>
		</div>

		<!-- 密码管理 -->
		<div class="person_pwd">
			<form class="bs-example bs-example-form" role="form"
				id="updatePwd_from" method="post">

				<div class="input-group input-group-sm oldPassword_div">
					<span class="input-group-addon">原始密码</span> <input type="password"
						id="oldPassword" name="oldPassword" onblur="okOldpassword()"
						class="form-control">
				</div>
				<div class="input-group input-group-sm newPassword_div">
					<span class="input-group-addon">设置密码</span> <input type="password"
						id="newPassword" name="newPassword" class="form-control">
				</div>
				<div class="input-group input-group-sm newPassword_div">
					<span class="input-group-addon">确认密码</span> <input type="password"
						id="newPassword2" name="newPassword2" class="form-control">
				</div>
				<div class="newPassword_div">
					<button type="button" class="btn btn-primary btn-block"
						onclick="submit_pwd()">确认修改</button>
				</div>
			</form>
		</div>

		<!-- 我的钱包 -->
		<div class="person_money">
			<form class="bs-example bs-example-form">

				<div class="input-group input-group-sm">
					<span class="input-group-addon">余额</span> <input type="text"
						class="form-control" readonly="readonly"
						value="${cus.cusBalance}元">
				</div>
				<div>
					<a class="btn btn-primary" data-toggle="modal"
						data-target="#add_money">充值</a> <a class="btn btn-success"
						data-toggle="modal" data-target="#get_money">提现</a>
				</div>

				<div class="input-group input-group-sm">
					<span class="input-group-addon">积分</span> <input type="text"
						class="form-control" readonly="readonly" value="${cus.cusScore}">
				</div>

				<div>
					<a class="btn btn-primary" data-toggle="modal"
						data-target="#get_score">获取积分</a>
				</div>

			</form>
		</div>

		<!-- 地址管理 -->
		<div class="person_address">

			<table class="table table-hover" id="address_table">
				<caption>我的地址</caption>
				<thead>
					<tr>
						<th></th>
						<th>选择</th>
						<th>地址信息</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cus.address}" var="address" varStatus="i">
						<tr>
							<td width="0%"><input type="hidden" value="${address.addressId}"></td>
							<td width="10%"><input name="address" type="radio" value="${address.addressId}"></td>
							<td width="80%">
								<span>${address.addressProvince}</span>
								<span>${address.addressCity}</span>
								<span>${address.addressDistrict}</span>
								<span>${address.addressDescInfo}</span>
							</td>
							<td width="10%">${address.addressDefault}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<button type="button" onclick="setDefAddress()" class="btn btn-info stopBtn">设为默认</button>
				<button type="button" onclick="showUpdateAddress()" class="btn btn-primary stopBtn">修改地址</button>
				<button type="button" onclick="showAddAddress()" class="btn btn-success">添加地址</button>
				<button type="button" onclick="delAddress()" class="btn btn-danger stopBtn">删除地址</button>
			</div>
			<!-- 一个隐藏的地址表单 -->
			<form method="post" id="setAdrres_form">
				<input type="hidden" name="model" id="model" value="model_4" />
				<input type="hidden" name="addressId" id="addressId" /> 
				<input type="hidden" name="addressProvince" id="addressProvince" />
				<input type="hidden" name="addressCity" id="addressCity" /> 
				<input type="hidden" name="addressDistrict" id="addressDistrict" /> 
				<input type="hidden" name="addressDescInfo" id="addressDescInfo" />  
				<input type="hidden" name="addressDefault" id="addressDefault" />
			</form>
		</div>

		<!-- 邮箱绑定 -->
		<div class="person_email">
			<form class="bs-example bs-example-form" role="form" id="email_form"
				method="post">
				<input type="hidden" name="model" value="model_5">
				<div class="input-group input-group-sm">
					<span class="input-group-addon">绑定邮箱</span> <input type="text"
						id="cus_email" name="cus_email" class="form-control"
						value="${cus.cusEmail}">
				</div>
				<div class="form-group">
					<input class="form-control" id="cus_email_checkinfo" type="text"
						placeholder="输入验证码...">
					<button type="button" class="btn btn-success btn-block"
						id="send_email" onclick="send_timer()">发送验证码</button>
				</div>
				<div>
					<button type="button" class="btn btn-primary btn-block"
						onclick="addEmail()">确认绑定</button>
				</div>
			</form>
		</div>

		<!-- 模态框 -->
		<div class="modal fade" id="get_money" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel1" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel1">提现</h4>
					</div>
					<div class="modal-body">
						<form class="bs-example bs-example-form" role="form">
							<div class="input-group input-group-sm">
								<span class="input-group-addon">银行卡号</span> <input type="text"
									class="form-control">
							</div>
							<br>
							<div class="input-group input-group-sm">
								<span class="input-group-addon">提现金额</span> <input type="text"
									class="form-control">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary">确认</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
		</div>

		<div class="modal fade" id="add_money" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel2" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel2">充值</h4>
					</div>
					<div class="modal-body">
						<form class="bs-example bs-example-form" role="form" method="post"
							id="add_money_form">
							<input type="hidden" name="model" value="model_3">
							<div class="input-group input-group-sm">
								<span class="input-group-addon">银行卡号</span> <input type="text"
									class="form-control">
							</div>
							<br>
							<div class="input-group input-group-sm">
								<span class="input-group-addon">充值金额</span> <input type="text"
									class="form-control" id="money" name="money">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary"
							onclick="add_money()">确认</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="get_score" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel3" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel3">获取积分</h4>
					</div>
					<div class="modal-body">每完成一笔交易获取1000积分</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>

		<div class="modal fade" id="addOrUpdateAddress" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel4" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="addOrUpdate">新增地址</h4>
					</div>
					<div class="modal-body" style="height: 150px;">
						<form class="form-inline">
					      <div data-toggle="distpicker">
					        <div class="form-group">
					          <label class="sr-only" for="province1">Province</label>
					          <select class="form-control" id="province1"></select>
					        </div>
					        <div class="form-group">
					          <label class="sr-only" for="city1">City</label>
					          <select class="form-control" id="city1"></select>
					        </div>
					        <div class="form-group">
					          <label class="sr-only" for="district1">District</label>
					          <select class="form-control" id="district1"></select>
					        </div><hr/>
					        <div class="input-group" style="display: block;">
					          <input class="form-control" type="text" id="descInfo" placeholder="详细地址"/>
					        </div>
					      </div>
					    </form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="is_ok">确认</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
		</div>

	</div>

	<script type="text/javascript">
		$(function() {
			//初始化方法
			init();
		})
		function init() {
			//默认显示我的资料
			$(".person_address,.person_money,.person_pwd,.person_email,.person_info").hide();
			getModel();
			//选择模块
			change_option();

			//禁用按钮
			stopBtn();
			//点击后启用按钮
			$('input:radio[name="address"]').click(function() {
				$(".stopBtn").removeAttr('disabled');
			});
			/////////////初始化地址模块
		}
		//显示上次操作的模块
		function getModel() {
			var model = "${model}";
			if (model == "model_3") {
				$("#my_money").addClass("active");
				$(".person_money").show();
			} else if (model == "model_4") {
				$("#my_address").addClass("active");
				$(".person_address").show();
			} else if (model == "model_5") {
				$("#my_email").addClass("active");
				$(".person_email").show();
			} else {
				$("#my_info").addClass("active");
				$(".person_info").show();
			}
		}

		//选择模块
		function change_option() {
			$(".mian_info>ul>li").click(
							function() {
								$(".mian_info ul li").removeClass("active");
								$(this).addClass("active");
								//显示相应的模块
								//alert($(this).attr("id"));
								if ($(this).attr("id") == "my_info") {
									$(".person_address,.person_money,.person_pwd,.person_email").hide();
									$(".person_info").show();
									return;
								}
								if ($(this).attr("id") == "my_pwd") {
									$(".person_address,.person_money,.person_info,.person_email").hide();
									$(".person_pwd").show();
									$("#oldPassword").removeClass("errorInfo");
									$(".oldPassword_div").show();
									$(".newPassword_div").hide();
									return;
								}
								if ($(this).attr("id") == "my_money") {
									$(".person_address,.person_info,.person_pwd,.person_email").hide();
									$(".person_money").show();
									return;
								}
								if ($(this).attr("id") == "my_address") {
									$(".person_info,.person_money,.person_pwd,.person_email").hide();
									$(".person_address").show();
									return;
								}
								if ($(this).attr("id") == "my_email") {
									$(".person_info,.person_money,.person_pwd,.person_address").hide();
									$(".person_email").show();
									return;
								}
							})
		}
		///////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////
		//点击修改按钮
		function submit_info() {
			if (check_info()) {
				alert("修改成功");
				$("#person_info_form").attr("action",
						"${pageContext.request.contextPath}/updateCustomer");
				$("#person_info_form")[0].submit();
			}
		}

		//记录当前用户的昵称
		var cus_nickname = $("#cus_name").val();
		//检查用户名是否存在
		function okNickName() {
			//昵称未发生变化
			if (cus_nickname == $("#cus_name").val()) {
				return false;
			}
			//昵称发生变化,ajax校验是否存在相同的昵称
			$.ajax({
				type : "POST", //提交方式 
				url : "${pageContext.request.contextPath}/okCusName",//路径
				dateType : "text",//返回的数据类型
				data : {
					"cus_nickname" : $("#cus_name").val()
				},
				success : function(data) {
					if (data == "error") {
						//用户名已存在,将昵称栏换成原来的昵称
						$("#cus_name").val(cus_nickname);
						alert("用户名已存在！");
					}
				}
			});
		}

		//校验修改个人信息
		function check_info() {
			//获取需要验证的表单
			var cus_name = $("#cus_name");
			var cus_bir = $("#cus_bir");
			var cus_phone = $("#cus_phone");

			if (cus_name.val().length > 10 || cus_name.val() == "") {
				//昵称修改不合法
				alert("昵称修改不合法");
				return false;
			}
			var exp_phone = /1[34578]\d{9}/;
			if (!exp_phone.test(cus_phone.val())) {
				//电话号码不合法
				alert("电话号码不合法");
				return false;
			}
			if (check_date()) {
				//日期不合法
				alert("日期不合法");
				return false;
			}
			return true;
		}

		//检查日期是否合法
		function check_date() {
			if ($("#cus_bir").val() == "") {
				return true;
			}
			var currentData = new Date;
			var year = 1900 + currentData.getYear();
			var month = currentData.getMonth() + 1;
			var day = currentData.getDate();

			var bir = $("#cus_bir").val().split("-");

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
		////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////
		//校验原始密码是否输入正确
		function okOldpassword() {
			var oldPassword = $("#oldPassword").val();
			$.ajax({
				type : "POST", //提交方式 
				url : "${pageContext.request.contextPath}/okOldpassword",//路径
				dateType : "text",//返回的数据类型
				data : {
					"oldPassword" : oldPassword
				},
				success : function(data) {
					if (data == "success") {//密码正确
						$("#oldPassword").val("");
						$(".oldPassword_div").hide();
						$(".newPassword_div").slideDown();
					} else {
						//alert("密码错误");
						$("#oldPassword").addClass("errorInfo");
					}
				}
			});
		}

		//确认修改密码
		function submit_pwd() {
			var cus_password_1 = $("#newPassword");
			var cus_password_2 = $("#newPassword2");

			//校验密码是否合法
			var exp_pwd = /^[a-zA-Z0-9]{6,18}$/;

			var cus_pwd = cus_password_1.val();
			if (!exp_pwd.test(cus_pwd)) {//密码格式不合法
				alert("密码格式不合法");
				return false;
			}
			if (cus_password_1.val() != cus_password_2.val()) {//两次密码不一样
				alert("两次密码不一致");
				return false;
			}

			$("#updatePwd_from").attr("action",
					"${pageContext.request.contextPath}/updatePassword");
			$("#updatePwd_from")[0].submit();
		}

		/////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////
		//确认充值
		function add_money() {
			$("#add_money_form").attr("action",
					"${pageContext.request.contextPath}/addMoney");
			$("#add_money_form")[0].submit();
		}
		/////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////

		//获取一行的信息
		function getAddressInfo() {
			var td = $('input:radio[name="address"]:checked').parent().parent()
					.children();
			if (typeof (td[0]) == "undefined") {
				return null;
			}
			var addressInfo = $(td[0]).children()[0].value + "||"
					+ $($(td[2]).children()[0]).text() + "||" 
					+ $($(td[2]).children()[1]).text() + "||"
					+ $($(td[2]).children()[2]).text() + "||"
					+ $($(td[2]).children()[3]).text() + "||"
					+ $(td[3]).text();
			return addressInfo;
		}

		//未选择行的时候,禁用按钮
		function stopBtn() {
			$(".stopBtn").attr('disabled', "true");
		}

		//设置默认地址
		function setDefAddress() {
			var addressInfo = getAddressInfo();
			var address = addressInfo.split("||");
			$("#addressId").val(address[0]);
			
			$("#setAdrres_form").attr("action","${pageContext.request.contextPath}/setDefAddress");
			$("#setAdrres_form")[0].submit();
			//alert(addressInfo);
		}
		
		//显示修改
		function showUpdateAddress() {
			//回显在页面上
			var addressInfo = getAddressInfo();
			var address = addressInfo.split("||");
			console.log(address[1]);
			console.log(address[2]);
			console.log(address[3]);
			console.log(address[4]);
			$("#province1 option[value="+address[1]+"]").prop("selected", "selected");
			$("#city1 option[value="+address[2]+"]").prop("selected", "selected");
			$("#district1 option[value="+address[3]+"]").prop("selected", "selected");
			$("#descInfo").val(address[4]);
			
			$("#is_ok").attr("onclick","updateAddress('"+address[0]+"')");
			$("#addOrUpdateAddress").modal("show");
		}

		//修改地址
		function updateAddress(addressId) {
 			
			var province = $("#province1").val();
			var city = $("#city1").val();
			var district = $("#district1").val();
			var descInfo = $("#descInfo").val();
			
			$("#addressId").val(addressId);
			$("#addressProvince").val(province);
			$("#addressCity").val(city)
			$("#addressDistrict").val(district)
			$("#addressDescInfo").val(descInfo)
			
			$("#setAdrres_form").attr("action","${pageContext.request.contextPath}/updateAddress");
			$("#setAdrres_form")[0].submit();

		}
		
		
		//显示添加模态框
		function showAddAddress(){
			$("#addOrUpdateAddress").modal("show");
			$("#is_ok").attr("onclick","addAddress()");
		}
		
		//添加地址
		function addAddress() {
			var province = $("#province1").val();
			var city = $("#city1").val();
			var district = $("#district1").val();
			var descInfo = $("#descInfo").val();
			
			$("#addressProvince").val(province);
			$("#addressCity").val(city)
			$("#addressDistrict").val(district)
			$("#addressDescInfo").val(descInfo)
			
			//地址拼接
			var address = province1 + city1 + district1+descInfo;
			$("#setAdrres_form").attr("action","${pageContext.request.contextPath}/addAddress");
			$("#setAdrres_form")[0].submit();
		}

		//删除地址
		function delAddress() {
			var addressInfo = getAddressInfo();
			var address = addressInfo.split("||");
			if (address[5] == "默认地址") {
				alert("默认地址不能删除");
				return;
			}
			if(confirm("确认删除此地址")==true){
				$("#addressId").val(address[0]);
				$("#setAdrres_form").attr("action","${pageContext.request.contextPath}/delAddress");
				$("#setAdrres_form")[0].submit();
			}
		}

		/////////////////////////////////////////////////////////////////////////////
		//点击发送邮件信息的计时器
		function send_timer() {
			if (!checkEmail()) {
				return;
			}
			//发送验证码
			sendEmail();
			//禁止按钮的使用
			$("#send_email").attr('disabled', "true");

			var i = 60;//等待时间 60秒
			$("#send_email").text(i + "秒后重发");
			$("#cus_email").attr("readonly", "readonly")
			var timer = setInterval(function() {
				i--;
				$("#send_email").text(i + "秒后重发");
				if (i == 0) {
					$("#cus_email").removeAttr("readonly");
					$("#send_email").removeAttr('disabled');
					$("#send_email").text("发送验证码");
					clearInterval(timer);
				}
			}, 1000);
		}
		var checkNumber;//保存验证码
		//发送邮箱的请求
		function sendEmail() {
			$.ajax({
				type : "POST", //提交方式 
				url : "${pageContext.request.contextPath}/nofilter_sendEmail",//路径
				dateType : "text",//返回的数据类型
				data : {
					"cusEmail" : $("#cus_email").val()
				},
				success : function(data) {
					//返回一个验证码
					if (data != "error") {
						checkNumber = data;
					}
				}
			});
		}

		//确认添加
		function addEmail() {
			if (checkNumber == $("#cus_email_checkinfo").val()) {
				//验证码正确
				$("#email_form").attr("action",
						"${pageContext.request.contextPath}/updateEmail");
				$("#email_form")[0].submit();
			} else {
				alert("验证码错误");
			}
		}

		//检查邮箱格式
		function checkEmail() {
			var cus_email = $("#cus_email");
			var exp = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
			if (!exp.test(cus_email.val())) {
				alert("邮箱格式不正确");
				return false;
			} else {
				return true;
			}
		}
	</script>

</body>
</html>
