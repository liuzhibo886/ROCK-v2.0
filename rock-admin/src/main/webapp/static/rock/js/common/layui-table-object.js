/**
 * 初始化 layui Table 的封装
 * 
 * @author lzb
 */
(function() {
	var LYTable = function(lyTableId, url, cols) {
		this.lyTableId = lyTableId;// table ID
		this.url = Feng.ctxPath + url;// 请求地址
		this.cols = cols; // 列参数
		this.title = "";// 表格标题
		this.page = true;// true 分页
		this.toolbar = null;// 头工具栏script ID
		this.toolbarFuc = null;// 头工具栏监听事件方法
		this.editFuc = null;// 监听单元格编辑方法
		this.toolFuc = null;// 监听行工具事件方法
		this.method = 'post';// 请求方法类型
		this.height = 'full-5';//高度
		this.contentType = 'application/json';
		this.table = null;
		this.cellMinWidth=100;//全局定义常规单元格的最小宽度，layui 2.2.1 新增
		this.parseDataFuc = function(res) {
			return {
				"code" : res.code, // 解析接口状态
				"msg" : res.msg, // 解析提示文本
				"count" : res.data.total, // 解析数据长度
				"data" : res.data.records
			// 解析数据列表
			};
		}
	};

	LYTable.prototype = {
		/**
		 * 初始化layui table
		 */
		init : function() {
			this.table = layui.table;
			// 初始化
			this.table.render({
				elem : '#' + this.lyTableId,
				url : this.url,
				toolbar : this.toolbar,
				title : this.title,
				cols : this.cols,
				method : this.method,
				height : 'full-100',
				page : true,
				parseData : this.parseDataFuc,
				method : this.method,
				cellMinWidth : this.cellMinWidth,
				contentType : this.contentType,
			});
			// 监听单元格编辑
			if (this.editFuc != null) {
				this.table.on('edit(' + this.lyTableId + ')', this.editFuc);
			}
			// 头工具栏事件
			if (this.toolbarFuc != null) {
				this.table.on('toolbar(' + this.lyTableId + ')', this.toolbarFuc);
			}
			// 监听行工具事件
			if (this.toolFuc != null) {
				this.table.on('tool(' + this.lyTableId + ')', this.toolFuc);
			}
			return this;
		},
		/**
		 * 监听单元格编辑
		 */
		setEditFuc : function(editFuc) {
			this.editFuc = editFuc;
		},
		/**
		 * 设置头工具栏事件
		 */
		setToolbar : function(toolbar, toolbarFuc) {
			this.toolbar = "#" + toolbar;
			this.toolbarFuc = toolbarFuc;
		},
		/**
		 * 监听行工具事件
		 */
		setToolFuc : function(toolFuc) {
			this.toolFuc = toolFuc;
		},
		/**
		 * 重新加载表格 where 参数对象 page 页码
		 */
		reload : function(where,curr){
			var options={};
			 
		    if(curr !=undefined && curr !=null && curr > 0){
		    	var page={};
		        page['curr']=curr;
		        options['page']=page;
		    }
		    if(where !=undefined && where !=null){
		    	 options['where']=where;
		    }
			this.table.reload(this.lyTableId,options);
		}
	};
	window.LYTable = LYTable;
}());