var Feng = {
	ctxPath : "",
	addCtx : function(ctx) {
		if (this.ctxPath == "") {
			this.ctxPath = ctx;
		}
	},
	sessionTimeoutRegistry : function() {
		$.ajaxSetup({
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			complete : function(XMLHttpRequest, textStatus) {
				// 通过XMLHttpRequest取得响应头，sessionstatus，
				var sessionstatus = XMLHttpRequest
						.getResponseHeader("sessionstatus");
				if (sessionstatus == "timeout") {
					// 如果超时就处理 ，指定要跳转的页面
					window.location = Feng.ctxPath + "/global/sessionError";
				}
			}
		});
	},
	alert : function(info, iconIndex) {
		parent.layer.msg(info, {
			icon : iconIndex
		});
	},
	info : function(info) {
		Feng.alert(info, 0);
	},
	success : function(info) {
		Feng.alert(info, 1);
	},
	error : function(info) {
		Feng.alert(info, 2);
	},
	loading : function() {
		var loading = layer.load(0, {
			shade : false,
			time : 10 * 1000
		});
		return loading;
	},
	close : function(loading) {
		layer.close(loading);
	},
	//解析page对象
	parseDataFuc : function(res) {
		return {
			"code" : res.code, // 解析接口状态
			"msg" : res.msg, // 解析提示文本
			"count" : res.data.total, // 解析数据长度
			"data" : res.data.records
		// 解析数据列表
		};
	}
};
