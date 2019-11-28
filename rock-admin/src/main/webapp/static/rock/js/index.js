var Index = {};
/**
 * 0点击左边按钮，1 右边按钮，2 添加
 */
Index.refTabs = function(flag) {
	var pageWidth = $("#page-div").width();
	var btnWidth = 0;
	var btns = $("#page-div").find("*[name=page-btn]");
	for (var i = 0; i < btns.length; i++) {
		btnWidth = btnWidth + $(btns[i]).outerWidth();
	}
	var pageTabsMaxWidth = pageWidth - btnWidth;

	var tabs = $(".page-tabs").find("a");
	var leftTabs = new Array();
	var centreTabs = new Array();
	var rightTabs = new Array();

	var leftFlag = true;
	// 计算左边 中间 右边 tab的个数
	for (var i = 0; i < tabs.length; i++) {
		var tab = tabs[i];
		if ($(tab).is(':hidden')) {
			if (leftFlag) {
				leftTabs.push(tab);
			} else {
				rightTabs.push(tab);
			}
		} else {
			centreTabs.push(tab);
			leftFlag = false;
		}
	}
//	console.log(leftTabs.length + ":" + centreTabs.length + ":"+ rightTabs.length)
	// 点击左边
	if (flag == 0) {
		if (leftTabs.length > 0) {
			var leftTab = leftTabs[leftTabs.length - 1];
			$(leftTab).show();
		}
		var tabsWidth = 0;
		tabs = $(".page-tabs").find("a");
		for (var i = 0; i < tabs.length; i++) {
			var tab = tabs[i];
			if ($(tab).is(':visible')) {
				tabsWidth = tabsWidth + $(tab).outerWidth();
				if (tabsWidth > pageTabsMaxWidth) {
					$(tab).hide();
				}
			}
		}
	}
	// 点击右边
	if (flag == 1) {
		if (rightTabs.length > 0) {
			var rightTab = rightTabs[0];
			$(rightTab).show();
		}
		var tabsWidth = 0;
		tabs = $(".page-tabs").find("a");
		var maxTabsLength = tabs.length;
		for (var i = maxTabsLength; i > 0; i--) {
			var tab = tabs[i - 1];
			if ($(tab).is(':visible')) {
				tabsWidth = tabsWidth + $(tab).outerWidth();
				if (tabsWidth > pageTabsMaxWidth) {
					$(tab).hide();
				}
			}
		}
	}
}
$(function() {

	$(".menu").on('click', 'li:first', function() {
		// 多个节点事件才生效
		if ($(this).siblings().length < 1) {
			return;
		}
		if ($(this).parent().hasClass('active')) {
			$(this).parent().removeClass('active');
			$(this).find("span:last").removeClass("fa-angle-down");
			$(this).find("span:last").addClass("fa-angle-left");
		} else {
			$(this).parent().addClass('active');
			$(this).find("span:last").addClass("fa-angle-down");
			$(this).find("span:last").removeClass("fa-angle-left");
		}
	});
	// tab 点击事件
	// 删除
	$(".page-tabs").on('click', "i.fa-times-circle", function(event) {
		$(this).parent().remove();
		var dataName=$(this).parent().attr("data-name");
		//console.log("dataName:"+dataName);
		$("iframe[data-name="+dataName+"]").remove();
		//删除iframe
		event.stopPropagation();
	});
	// 选中
	$(".page-tabs").on('click', "a.page-tab", function(event) {
		$(".page-tabs").find(".page-tab").removeClass("active");
		$(this).addClass("active");
		event.stopPropagation();
	});
	// 左边点击事件
	$("#left-backward-btn").on("click", function() {
		Index.refTabs(0);
	});
	// 右边点击事件
	$("#rigth-backward-btn").on("click", function() {
		Index.refTabs(1);
	});
	// 菜单点击事件
	$(".menu").find("li > a").on("click", function() {
		var dataId=$(this).attr("data-id");
		var dataName=$(this).attr("data-name");
		var text=$(this).find("label").text();
		if(dataId ==undefined || dataId =='' || dataId ==null){
			return;
		}
		//查找是否已经存在了
		var tab=$("#page-tabs").find("a[data-name="+dataName+"]");
		//不存在，添加
		if(tab == undefined || tab.length < 1){
			var tabHtml='<a href="#" class="page-tab" data-id="'+dataId+'" data-name="'+dataName+'">'+text+'<i class="fa fa-times-circle"></i></a>';
			$("#page-tabs").append(tabHtml);
			var iframeHtml='<iframe src="'+dataId+'" data-name="'+dataName+'"></iframe>';
			$("#right-center").append(iframeHtml);
		}
		//选中点击选项
		$("#page-tabs").find("a[data-name]").removeClass("active");
		$("#page-tabs").find("a[data-name="+dataName+"]").addClass("active");
		$("#right-center").find("iframe").hide();
		$("#right-center").find("iframe[data-name="+dataName+"]").show();
		
		//console.log(tab.length);
		//console.log("dataId:"+dataId)
	//	console.log("text:"+text)
		
	});

})