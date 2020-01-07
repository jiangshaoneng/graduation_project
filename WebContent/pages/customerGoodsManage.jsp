<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>我的商品</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fileUpload.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/fileUpload.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/iconfont.js" ></script>
		
		<style type="text/css">
			.myGoodsManage{
				width: 1024px;
				margin: 70px auto;
			}
			.addGoods,.myGoods{
				box-shadow: 5px 5px 5px #CCCCCC;
			}
			#form_goodsInfo .input-group{
				margin-bottom: 10px;
			}
		</style>
		
	</head>
	<body>
		
		<!-- 我的商品管理 -->
		<div class="myGoodsManage">
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
					            <li class="active"><a href="#">我的商品</a></li>
					            <li class="dropdown">
					                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
					                    	个人中心 <b class="caret"></b>
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
			
			
			<!--  显示我的商品  -->
			<div class="myGoods">
				<div class="panel panel-success">
					<div class="panel-heading">
						<form class="form-inline" role="form" action="${pageContext.request.contextPath}/customerGoodsManage" method="POST">
  							<div class="form-group">
					            <select class="form-control" name="goodsStatus" id="goodsStatus">
					            	<option value="">全部</option>
									<option value="出售中">出售中</option>
									<option value="已售出">已售出</option>
									<option value="已下架">已下架</option>
								</select>
					        </div>
							<button type="submit" class="btn btn-primary">查询商品</button>
							<button type="button" class="btn btn-success" onclick="showAddModel()">发布商品</button>
					    </form>
					</div>
					<div class="panel-body">
						<table class="table table-hover">
						  <!--<caption>悬停表格布局</caption>-->
						  <thead>
						    <tr>
						      <th width="5%">编号</th>
						      <th width="10%">种类</th>
						      <th width="15%">商品名称</th>
						      <th width="10%">价格</th>
						      <th width="5%">数量</th>
						      <th width="10%">交易方式</th>
						      <th width="10%">状态</th>
						      <th width="15%">上架时间</th>
						      <th width="5%">操作</th>
						    </tr>
						  </thead>
						  <tbody>
						    <c:forEach items="${goodsInfo }" var="goods" varStatus="status">
						    <tr>
						      <td>${status.index + 1}<!-- <input type="checkbox"/> --></td>
						      <td>${goods.goodsType}</td>
						      <td><a href="javascript:void(0);" onclick="showUpdateModel('${goods.goodsId}')" title="查看详情">${goods.goodsName}</a></td>
						      <td>${goods.goodsPrice}</td>
						      <td>${goods.goodsTotal}</td>
						      <td>${goods.goodsPaytype}</td>
						      <td>${goods.goodsStatus}</td>
						      <td><fmt:formatDate value="${goods.goodsAddtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						      <td>
						      		<a href="javascript:void(0);" title="刷新商品" onclick="refreshGoods('${goods.goodsId}','${goods.goodsName}',this)">
						      		<span class="glyphicon glyphicon-refresh"></span></a>
						      		<a href="javascript:void(0);" title="移除此条信息" onclick="ok_delGoods('${goods.goodsId}','${goods.goodsName}',this)">
						      		<span class="glyphicon glyphicon-trash"></span></a>
						      </td>
						    </tr>
						    </c:forEach>
						  </tbody>
						</table>
					</div>
				</div>
			</div>
			
			<!--
            	作者：offline
            	时间：2018-02-04
            	描述：模态框
            -->
			<div class="modal fade" id="addOrupdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="myModalLabel"></h4>
			            </div>
			            <div class="modal-body">
							<form id="form_goodsInfo" class="bs-example bs-example-form" role="form" enctype="multipart/form-data" method="post">
								<input type="hidden" name="goodsId" id="goodsId"/>
								<div class="input-group">
									<span class="input-group-addon">选择分类</span>
									<select class="form-control" name="classfiy" id="classfiy">
										<option value="01">书籍</option>
										<option value="02">电子设备</option>
										<option value="03">衣物</option>
										<option value="04">日用品</option>
										<option value="05">男生专区</option>
										<option value="06">女生专区</option>
									</select>
								</div>
								<div class="input-group">
									<span class="input-group-addon">交易方式</span>
									<select class="form-control" name="payType" id="payType">
										<option value="up">线上交易</option>
										<option value="down">线下交易</option>
									</select>
								</div>
								<div class="input-group">
									<span class="input-group-addon">商品名称</span>
									<input type="text" class="form-control" name="name"  id="name" maxlength="20" placeholder="请输入名称">
								</div>
								<div class="input-group">
									<span class="input-group-addon">出售价格</span>
									<input type="text" class="form-control" name="price" id="price" maxlength="7" onblur= "if(! /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='';}" placeholder="请输入价格">
								</div>
								<div class="input-group">
									<span class="input-group-addon">出售数量</span>
									<input type="number" class="form-control" name="num" id="num" oninput="if(value<1)value=1"; value="1">
								</div>
	
								<div class="input-group">
									<span class="input-group-addon">商品描述</span>
									<textarea type="text" class="form-control" name="desc" id="desc" maxlength="200" placeholder="描述一下" style="resize: none;"></textarea>
								</div>
								
								<div id="fileUploadContent" class="fileUploadContent"></div>
	        
	        					<!--<button onclick="testUpload()">提交</button>-->
								<button type="button" class="btn btn-primary btn-block" onclick="addOrUpdate()" >确认</button>
								<button type="button" class="btn btn-default btn-block" onclick="cancle_addOrupdate()" >取消</button>
							</form>
						</div>
			        </div>
			    </div>
			</div>
			
		</div>
	</body>
</html>

<script type="text/javascript">
     $("#fileUploadContent").initUpload({
        "uploadUrl":"${pageContext.request.contextPath}/addGoodsImgs",//上传文件信息地址
        //"size":350,//文件大小限制，单位kb,默认不限制
        "maxFileNumber":5,//文件个数限制，为整数
        "filelSavePath":"${pageContext.request.contextPath}/goodsImgs",//文件上传地址，后台设置的根目录
        "beforeUpload":beforeUploadFun,//在上传前执行的函数
        "onUpload":onUploadFun,//在上传后执行的函数
        //autoCommit:true,//文件是否自动上传
        "fileType":['png','jpg','gif','jpeg']//文件类型限制，默认不限制，注意写的是文件后缀
    });
    function beforeUploadFun(opt){
        opt.otherData =[{"name":"name","value":"zxm"}];
    } 
    function onUploadFun(opt,data){
    	$("#addOrupdate").modal("hide");
        uploadEvent.cleanFileEvent(opt);
        
        window.location.reload();
        
        uploadTools.uploadError(opt);//显示上传错误
        uploadTools.uploadSuccess(opt);//显示上传成功
    }
    
    //初始化
    $(function(){
    	//初始化方法
    	init();
    })
    
    function init(){
    	$('#addOrupdate').on('hide.bs.modal', function () {
    		//模态框消失后
            $("#goodsId").val("");
            $("#classfiy").val("01");
            $("#payType").val("up");
            $("#name").val("");
            $("#price").val("");
            $("#num").val("1");
            $("#desc").val("");
            
            var opt = uploadTools.getOpt("fileUploadContent");
            uploadEvent.cleanFileEvent(opt);
    		imgId = '';
  		})
  		
  		//回显状态
  		$("#goodsStatus option[value='${goodsStatus}']").prop("selected","selected");
    }
    
    function StringToNumberCode(str){
    	var number_info;
    	switch (str) {
		case '书籍':number_info = '01';
			break;
		case '电子设备':number_info = '02';
			break;
		case '衣物':number_info = '03';
			break;
		case '日用品':number_info = '04';
			break;
		case '男生专区':number_info = '05';
			break;
		case '女生专区':number_info = '06';
			break;
		case '线上交易':number_info = 'up';
			break;
		case '线下交易':number_info = 'down';
			break;
		default:number_info = '';
			break;
		}
    	return number_info;
    }
    
    //显示编辑页面
    function showUpdateModel(goodsId){
    	$.ajax({ 
			   type : "post", //提交方式 
			   url : "${pageContext.request.contextPath}/showGoodsInfo",//路径
			   dateType:"json",//返回的数据类型
			   data : {"goodsId":goodsId},
			   success : function(goods) {
					console.log(goods);
					$("#goodsId").val(goods.goodsId);
			        $("#classfiy option[value="+StringToNumberCode(goods.goodsType)+"]").prop("selected","selected");
			        $("#payType option[value="+StringToNumberCode(goods.goodsPaytype)+"]").prop("selected","selected");
			        $("#name").val(goods.goodsName);
			        $("#price").val(goods.goodsPrice);
			        $("#num").val(goods.goodsTotal);
			        $("#desc").val(goods.goodsInfo);
			        //将图片放入
				    var temp = "";
			        for(var index in goods.images){
				        temp += "<div class='fileItem' filecodeid="+index+">";
				        temp += "<div class='imgShow'>";
				        temp += "<img src='${pageContext.request.contextPath}/goodsImgs/"+goods.images[index].imgUrl+"'/>";
				        temp += "</div>";
				        temp += "<div class='status'>";
				        temp += "<input type='hidden'class='imgId' value='"+goods.images[index].imgId+"'/>";
				        temp += "<i class='iconfont icon-shanchu'></i>";
				        temp += "</div>";
				        temp += "<div class='fileName'>"+goods.images[index].imgUrl+"</div>";
				        temp += "</div>";			        	
			        }
			        //
			        $($(".box")[0]).html(temp);
			        
			        //添加清除图片事件
			        var opt = uploadTools.getOpt("fileUploadContent");
			        uploadTools.initWithDeleteFile(opt);
			        
			        //显示模态框
			    	$("#addOrupdate").modal("show");
			    	$("#myModalLabel").text("编辑商品");
			   },
			   error : function(){
				   alert("网络异常，稍后再试！");
			   }
			});
    }
    
 	//显示添加页面
    function showAddModel(){
    	$("#addOrupdate").modal("show");
    	$("#myModalLabel").text("添加商品");
    }
 	
 	//判断是否通过校验
 	function checkMustInput(){
        var name = $("#name").val();
        var price = $("#price").val();
        var desc = $("#desc").val();
        if(name.trim()==""||price.trim()==""||desc.trim()==""){
	        return false;
        }else{
        	 return true;
        }
 	}
    //确认添加商品
    function addOrUpdate(){
    	//判断是否有必填项未填写
    	if(!checkMustInput()){
    		alert("信息填写不完整!");
    		return false;    		
    	}
    	//判断是否超出5个范围
    	var fileItems = $(".fileItem");
    	if(fileItems.length>5){
    		alert("最多上传5张图片");
    		return false;
    	}
    	if(fileItems.length<=0){
    		alert("至少上传1张图片");
    		return false;
    	}
    	//获取表单的值
    	var goodsId = $("#goodsId").val();
        var classfiy = $("#classfiy").val();
        var payType = $("#payType").val();
        var name = $("#name").val();
        var price = $("#price").val();
        var num = $("#num").val();
        var desc = $("#desc").val();
        $.ajax({ 
			   type : "post", //提交方式 
			   url : "${pageContext.request.contextPath}/addGoodsInfo",//路径
			   dateType:"text",//返回的数据类型
			   data : { 
				  "goodsId" : goodsId,
				  "classfiy":classfiy,
				  "payType" : payType,
				  "name":name,
				  "price" : price,
				  "num":num,
				  "desc" : desc
			   },
			   success : function(goodsId) {
				   if(goodsId.length>0){
					    $("#goodsId").val(goodsId);
				   		var opt = uploadTools.getOpt("fileUploadContent");
			      	    uploadEvent.uploadFileEvent(opt);
				   }else{
					   alert("系统内部异常，稍后再试！");
				   }
			   },
			   error : function(){
				   alert("网络异常，稍后再试！");
			   }
			});
    }
    
    /*删除商品*/
    function ok_delGoods(goodsId,goodsName,_this){
    	var $this = $(_this);
    	var flag = confirm("确认删除  ["+goodsName+"] ?");
    	if(flag){//确认删除
    		$.ajax({
					type : "get",
					url : "${pageContext.request.contextPath}/delGoods",
					dateType : "text",
					data : {
						"goodsId" : goodsId,
					},
					success : function(data) {
						if(data=="success"){//删除成功
							var tr = $this.parent().parent().slideUp();
						}else{
							alert("系统发生异常，删除失败！稍后再试~");
						} 
					}
			})
    	}
    }
    /*刷新商品*/
    function refreshGoods(goodsId,goodsName,_this){
    	var $this = $(_this);
    	var flag = confirm("确认刷新  ["+goodsName+"] ?");
    	if(flag){//确认刷新
    		$.ajax({
					type : "get",
					url : "${pageContext.request.contextPath}/refreshGoods",
					dateType : "text",
					data : {
						"goodsId" : goodsId,
					},
					success : function(data) {
						$($this.parent().parent().children()[6]).html("出售中");
						var date = new Date().format("yyyy-MM-dd HH:mm:ss");
						$($this.parent().parent().children()[7]).html(date);
						alert("success");
					}
			})
    	}
    }
    function cancle_addOrupdate(){
    	$("#addOrupdate").modal('hide');
    }
  	//时间格式化函数
    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1, //month
            "d+": this.getDate(), //day
            "H+": this.getHours(), //hour
            "m+": this.getMinutes(), //minute
            "s+": this.getSeconds(), //second
            "q+": Math.floor((this.getMonth() + 3) / 3), 
            "S": this.getMilliseconds() //millisecond
        }
        if (/(y+)/.test(format)) format = format.replace(RegExp.$1,(this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o) if (new RegExp("(" + k + ")").test(format))format = format.replace(RegExp.$1,RegExp.$1.length == 1 ? o[k] :("00" + o[k]).substr(("" + o[k]).length));
        return format;
    }
</script>