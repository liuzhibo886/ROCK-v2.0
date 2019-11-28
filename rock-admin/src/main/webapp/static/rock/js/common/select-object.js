(function() {
	var LYSelect = function(selectId, url) {
		this.selectId = selectId;
		this.url = url;
		this.data = null;// 表单提交参数
		this.body = null;// body提交参数

	}
	LYSelect.prototype = {

		init : function() {
			$("#"+this.selectId).html("");
			// 第一个值
			var first = $("#" + this.selectId).attr("first");
			if(first != null && first != undefined && first != ''){
				var ch = first.split(",");
				if(ch.length == 1){
					$("#"+this.selectId).append("<option value =''>"+first+"</option>");
				}else if(ch.length == 2){
					$("#"+this.selectId).append("<option value ='"+ch[0]+"'>"+ch[1]+"</option>");
				}
				
			}
			// 文本字典,生成下拉框
			var dic = $("#" + this.selectId).attr("dic");
			if (dic != null && dic != undefined && dic != '') {
				var dicArr = dic.split(";");
				for (var i = 0; i < dicArr.length; i++) {
					var str = dicArr[i];
					if (str != null && str != undefined && str != '') {
						var ch = str.split(",");
						if (ch != null && ch != undefined && ch.length == 2) {
							$("#"+this.selectId).append("<option value ='"+ch[0]+"'>"+ch[1]+"</option>");
						}
					}
				}
				this.defaultOption();
			}
			// ajax 获取数据
			if(this.url !=undefined && this.url != null && this.url !=''){
				this.load();
			}
			form.render();
		},
		// 加载数据
		load : function() {
			var em = this;
			// 提交信息
			var ajax = new $ax(em.url, function(data) {
				em.data = data.data;
				if(em.data !=null && em.data !=undefined && em.data.length >0){
					for (var i = 0; i < em.data.length; i++) {
						var obj=em.data[i];
						if(obj !=null && obj != undefined){
							/**
							 * 其他参数
							 */
							var paramsStr="";
							var params=obj.params;
							if(params !=null && params !=undefined && params !=""){
								for(var key in params){
									paramsStr=paramsStr+" "+key+"="+params[key]+" ";
								}
							}
							//是否禁用
							if(obj.disabled == true){
								paramsStr=paramsStr+" disabled='disabled' "
							}
							var option="<option value ='"+obj.key+"'"+paramsStr+">"+obj.value+"</option>";
							$("#"+em.selectId).append(option);
						}
					}
					em.defaultOption();
					form.render();
				}
			}, function(data) {
				Feng.error("数据加载失败!" + data.msg + "!");
			});
			if (em.body != null && em.body != undefined) {
				ajax.setBody(em.body);
			}
			if (em.data != null && em.data != undefined) {
				ajax.setData(em.data);
			}
			ajax.start();
		},
		// 设置表单提交参数
		setData : function(data) {
			this.data = data;
		},
		// 是在body提交参数
		setBody : function(body) {
			this.body = body;
		},
		// 默认选中
		defaultOption :function(){
			var defaultValue = $("#" + this.selectId).attr("defaultValue");
			if(defaultValue !=null && defaultValue !=undefined && defaultValue !=""){
				$("#" + this.selectId).find("option[value="+defaultValue+"]").attr("selected","selected")
			}
			form.render();
		}
	};
	window.LYSelect = LYSelect;

}());