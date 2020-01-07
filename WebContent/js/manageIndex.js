$(function() {
	// 初始化方法
	init();
})

// 初始化方法
function init() {
	$("#add_notice,#search_customer,#search_goods").hide();
	manage_choose_show();
}

function findNotice(currentPageNo){
	//获取查询条件
	var noticeStartTime = $("#noticeStartTime").val();
	var noticeEndTime = $("#noticeEndTime").val;
	var noticeInfo = $("#noticeInfo").val();
	
	$.ajax({
		type : "POST", //提交方式 
		url : "${pageContext.request.contextPath}/ajaxFindNotice",//路径
		dateType : "JSON",//返回的数据类型
		data : {
			"noticeStartTime":noticeStartTime,
			"noticeEndTime":noticeEndTime,
			"noticeInfo":noticeInfo,
			"currentPageNo" : currentPageNo
		},
		success : function(data) {
			if(data.list.size>0){
				var temp = "";
				temp = temp + "<div class='filter_search_result_item'>";
				temp = temp + "<div>";
				temp = temp + "<span>${notice.noticeTitle}</span>";
				temp = temp + "<a href='#' class='add_time'>删除</a>";
				temp = temp + "<span class='add_time'>${notice.noticeAddtime}</span>";
				temp = temp + "</div>";
				temp = temp + "<hr style='margin: 5px;'/>";
				temp = temp + "<div>${notice.noticeInfo}</div>";
				temp = temp + "</div>"
				
			}
			
		}
	})
}

function moreNoticeNum(data){
	$('.M-box1').pagination({
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
		}
	})
}

// 选择不同的管理项目显示不同的面板
function manage_choose_show() {
	// 在每个管理的选项上添加一个class样式进行选择
	$(".manager").click(function() {
		var $this = $(this);
		$(".right > div").hide();
		if ($this.text() == "查询公告") {
			$("#search_notice").show()
		}
		if ($this.text() == "新增公告") {
			$("#add_notice").show()
		}
		if ($this.text() == "用户查询") {
			$("#search_customer").show()
		}
		if ($this.text() == "商品查询") {
			$("#search_goods").show()
		}
	});
}