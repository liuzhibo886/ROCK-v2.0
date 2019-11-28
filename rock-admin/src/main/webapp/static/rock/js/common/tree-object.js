(function() {
	var LYTree = function(treeId, url) {
		this.treeId = treeId;
		this.data = null;
		this.showCheckbox = false;// 多选框
		this.size = 10;// 文本长度大于该值显示弹框
		this.view = 0;// 显示模式0或者1
		this.isOnlyEnd = true;// 是否只允许选择跟节点，showCheckbox=false有效
		this.tipsIndex = null;
		this.data = null;// 表单提交参数
		this.body = null;// body提交参数
		this.url = url;
		this.onlyIconControl = false;// 仅允许节点左侧图标控制展开收缩
		this.isJump = false;// 是否允许点击节点时弹出新窗口跳转。默认 false，若开启，需在节点数据中设定 link
		// 参数（值为 url 格式）
		this.text = {
			defaultNodeName : '未命名', // 节点默认名称
			none : '无数据' // 数据为空时的提示文本
		};

	}
	LYTree.prototype = {

		init : function() {
			var em = this;
			// 初始化默认参数
			var sizeStr = $("#" + this.treeId + "Params").attr("size");
			if (!(sizeStr == undefined || sizeStr == null || sizeStr == "" || isNaN(sizeStr))) {
				this.size = parseInt(sizeStr);
			}

			var showCheckboxStr = $("#" + this.treeId + "Params").attr(
					"showCheckbox");
			if (!(showCheckboxStr == undefined || showCheckboxStr == null || showCheckboxStr == "")
					&& showCheckboxStr == "true") {
				this.showCheckbox = true;
			}

			var viewStr = $("#" + this.treeId + "Params").attr("view");
			if (!(viewStr == undefined || viewStr == null || viewStr == "" || isNaN(viewStr))) {
				this.view = parseInt(viewStr);
			}

			var isOnlyEndStr = $("#" + this.treeId + "Params")
					.attr("isOnlyEnd");
			// console.log("isOnlyEndStr:" + isOnlyEndStr);
			if (!(isOnlyEndStr == undefined || isOnlyEndStr == null || isOnlyEndStr == "")
					&& isOnlyEndStr == "false") {
				this.isOnlyEnd = false;
			}
			// 显示模式
			if (this.view == 0) {
				$("#" + this.treeId + "Name").show();
				$("#" + this.treeId + "Names").hide();
			}
			if (this.view == 1) {
				$("#" + this.treeId + "Name").hide();
				$("#" + this.treeId + "Names").show();
			}
			em.load();
			// 弹框点击事件
			$("#" + em.treeId + "Name").on(
					"mouseover",
					function() {
						var text = $("#" + em.treeId + "Name").val();
						if (text != undefined && text != null
								&& text.length > em.size) {
							em.tipsIndex = layer.tips(text, "#" + em.treeId
									+ "Name", {
								tips : [ 1, '#3595CC' ],
								time : 0,
								tipsMore : true,
							});
						}
					});

			$("#" + em.treeId + "Name").on("mouseout", function() {
				layer.close(em.tipsIndex);
			});
			$("#" + em.treeId + "Name").on("click", function() {
				layer.close(em.tipsIndex);
			});
			// ------------
			$("#" + em.treeId + "Names").on(
					"mouseover",
					function() {

						var text = $("#" + em.treeId + "Names").val();
						if (text != undefined && text != null
								&& text.length > em.size) {
							em.tipsIndex = layer.tips(text, "#" + em.treeId
									+ "Names", {
								tips : [ 1, '#3595CC' ],
								time : 0,
								tipsMore : true,
							});
						}
					});
			$("#" + em.treeId + "Names").on("mouseout", function() {
				layer.close(em.tipsIndex);
			});
			$("#" + em.treeId + "Names").on("click", function() {
				layer.close(em.tipsIndex);
			});
			this.render();
		},
		setShowCheckbox : function(showCheckbox) {
			this.showCheckbox = showCheckbox;
		},
		render : function() {
			tree.render({
				elem : '#' + this.treeId + "Tree",// 默认是点击节点可进行收缩
				data : this.data,
				showCheckbox : this.showCheckbox,// 开启多选
				oncheck : this.oncheck,
				click : this.click,
				id : this.treeId + "Index",
				treeId : this.treeId,
				onlyIconControl : this.onlyIconControl,
				isJump : this.isJump,
				text : this.text,
				isOnlyEnd : this.isOnlyEnd
			// 定义索引
			});
			// 默认选中
			var list = new Array()
			var value = $("#" + this.treeId + "Params").attr("value");
			var values = $("#" + this.treeId + "Params").attr("values");
			if (values != null && values != undefined && values != "") {
				var array = values.split("#");
				for (var i = 0; i < array.length; i++) {
					var obj = array[i];
					if (obj != undefined && obj != null && obj != ""
							&& !isNaN(obj)) {
						list.push(parseInt(obj));
					}
				}
			}
			if (value != null && value != undefined && value != ""
					&& list.length < 1) {
				var array = value.split("#");
				for (var i = 0; i < array.length; i++) {
					var obj = array[i];
					if (obj != undefined && obj != null && obj != ""
							&& !isNaN(obj)) {
						list.push(parseInt(obj));
					}
				}
			}
			if (this.showCheckbox) {// 多选的时候
				tree.setChecked(this.treeId + "Index", list);
			} else if ((value != null && value != undefined && value != "")
					|| (values != null && values != undefined && values != "")) {
				var keys = "#" + value + "#" + values + "#"
				var id = null;
				var title = null;
				// 查找根节点值
				var getChildren = function(node) {

					var idStr = node.id;
					if (keys.indexOf("#" + idStr + "#") > -1) {
						// 判断子节点
						var children = node.children;
						var flag = false;
						if (children != null && children != undefined
								&& children.length > 0) {
							for (var i = 0; i < children.length; i++) {
								var childrenId = children[i].id;
								if (keys.indexOf("#" + childrenId + "#") > -1) {
									flag = true;
								}
							}
							if (flag) {
								for (var i = 0; i < children.length; i++) {
									getChildren(children[i]);
								}
							}
						}
						if (!flag) {
							id = node.id;
							title = node.title;
						}

					} else {
						var children = node.children;
						for (var i = 0; i < children.length; i++) {
							getChildren(children[i]);
						}
					}
				}
				// 查找最下面节点对象
				for (var i = 0; i < this.data.length; i++) {
					getChildren(this.data[i]);
				}
				if (id != null && id != undefined && id != "" && title != null
						&& title != undefined && title != "") {
					var obj4 = {
						id : id,
						title : title,
						data : this.data
					}
					// console.log("obj4:" + JSON.stringify(obj4));
					this.click({
						data : obj4
					});
				}

				// 当单选时，模拟调用click 方法
				// tree.setChecked(this.treeId + "Index", [1]);
			}

		},
		oncheck : function(obj) {
			// console.log("oncheck：" + JSON.stringify(this));
			var name = "";
			var names = "";
			var key = "";
			var keys = "";
			var checkData = tree.getChecked(this.id);
			var getChildren = function(node) {
				var children = node.children;
				if (names == undefined || names == null || names == '') {
					names = node.title;
				} else {
					names = names + "-" + node.title;
				}

				keys = keys + "#" + node.id;
				if (children == undefined || children == null
						|| children.length < 1) {
					if (name == undefined || name == null || name == '') {
						name = node.title;
					} else {
						name = name + "-" + node.title;
					}

					key = key + "#" + node.id;
				} else {
					for (var i = 0; i < children.length; i++) {
						getChildren(children[i]);
					}
				}
			}
			for (var i = 0; i < checkData.length; i++) {
				var node = checkData[i];
				getChildren(node);
			}
			if (key != undefined && key != null && key != "") {
				key = key + "#";
			}
			if (keys != undefined && keys != null && keys != "") {
				keys = keys + "#";
			}

			// console.log("name:" + name);
			// console.log("names:" + names);
			// console.log("key:" + key);
			// console.log("keys:" + keys);
			// console.log("treeId:" + this.treeId);

			$("#" + this.treeId + "Name").val(name);
			$("#" + this.treeId + "Names").val(names);
			$("#" + this.treeId + "Key").val(key);
			$("#" + this.treeId + "Keys").val(keys);
		},

		click : function(clickObj) {
			// console.log("click.obj:" + JSON.stringify(clickObj));
			if (!this.showCheckbox) {// 是否开启多选
				// 单选
				var obj = clickObj.data;
				var name = obj.title;
				var names = name;
				var key = obj.id;
				var keys = key;
				var data = this.data;
				// 判断是否只允许选择跟节点
				// console.log("click.key:" + key);
				// console.log("click.name:" + name);
				// console.log("click.children:" + JSON.stringify(obj));
				if (this.isOnlyEnd && obj.children != undefined && obj.children.length > 0) {
					Feng.error("请选择末尾节点！"+this.isOnlyEnd);
					return;
				}
				// 查找指定节点的父节点
				var getParentNode = function(data, key) {
					var children = data.children;
					var flag = false;
					// 判断当前节点是不是指定节点父节点
					if (children != undefined && children != null) {
						for (var i = 0; i < children.length; i++) {
							if (children[i].id === key) {
								flag = true;
								break;
							}
						}
					}
					if (flag) {
						return data;
					} else {
						// 若子节点都不是父节点，继续递归
						if (children != undefined && children != null) {
							for (var i = 0; i < children.length; i++) {
								var obj2 = getParentNode(children[i], key);
								if (obj2 != null) {
									return obj2;
								}
							}
						}
					}
					return null;
				}
				var whileFlag = true;
				var childrenId = key;
				while (whileFlag) {
					whileFlag = false;
					for (var i = 0; i < data.length; i++) {
						var obj3 = getParentNode(data[i], childrenId);
						if (obj3 != null) {
							whileFlag = true;
							childrenId = obj3.id;
							names = obj3.title + "-" + names;
							keys = obj3.id + "#" + keys;
						}
					}
				}
				if (keys != undefined && keys != null && keys != "") {
					keys = "#" + keys + "#"
				}

				// console.log("key:" + key);
				// console.log("name:" + name);
				// console.log("keys:" + keys);
				// console.log("names:" + names);
				// console.log("treeId:" + this.treeId);
				$("#" + this.treeId + "Name").val(name);
				$("#" + this.treeId + "Names").val(names);
				$("#" + this.treeId + "Key").val(key);
				$("#" + this.treeId + "Keys").val(keys);
			}

		},
		// 加载数据
		load : function() {
			var em = this;
			// 提交信息
			var ajax = new $ax(em.url, function(data) {
				em.data = data.data;
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
		}
	};
	window.LYTree = LYTree;

}());