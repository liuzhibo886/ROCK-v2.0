/**
 * 初始化详情对话框
 */
var DictInfoDlg = {
	dictInfoData : {}
};

/**
 * 清除数据
 */
DictInfoDlg.clearData = function() {
	this.dictInfoData = {};
}

/**
 * 关闭此对话框
 */
DictInfoDlg.close = function() {
	parent.layer.close(window.parent.Dict.layerIndex);
}

/**
 * 收集数据
 */
DictInfoDlg.collectData = function() {
	this.dictInfoData = form.val("dictFrom");
	this.dictInfoData['dictId'] = $("#dictId").val();
}

/**
 * 提交添加
 */
DictInfoDlg.addSubmit = function() {

	this.clearData();
	this.collectData();

	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/rock/sys/dict/add", function(data) {
		Feng.success("添加成功!");
		window.parent.Dict.reload();
		window.parent.Dict.loadTree();
		DictInfoDlg.close();
	}, function(data) {
		Feng.error("添加失败!" + data.msg + "!");
	});
	ajax.setBody(this.dictInfoData);
	ajax.start();
}

/**
 * 提交修改
 */
DictInfoDlg.editSubmit = function() {
	this.clearData();
	this.collectData();
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/rock/sys/dict/update", function(data) {
		Feng.success("修改成功!");
		window.parent.Dict.reload();
		DictInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.msg + "!");
	});
	ajax.setBody(this.dictInfoData);
	ajax.start();
}

$(function() {
	form.on('submit(add)', function(data) {
		DictInfoDlg.addSubmit();
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	form.on('submit(edit)', function(data) {
		DictInfoDlg.editSubmit();
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	form.render();
});
