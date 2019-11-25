/**
 * 初始化详情对话框
 */
var AuthzInfoDlg = {
    authzInfoData : {}
};

/**
 * 清除数据
 */
AuthzInfoDlg.clearData = function() {
    this.authzInfoData = {};
}

/**
 * 关闭此对话框
 */
AuthzInfoDlg.close = function() {
    parent.layer.close(window.parent.Authz.layerIndex);
}

/**
 * 收集数据
 */
AuthzInfoDlg.collectData = function() {
 this.authzInfoData= form.val("authzFrom");
 this.authzInfoData['authzId']=$("#authzId").val();
}

/**
 * 提交添加
 */
AuthzInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rock/sys/authz/add", function(data){
        Feng.success("添加成功!");
        window.parent.Authz.reload();
        AuthzInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.msg + "!");
    });
    ajax.setBody(this.authzInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AuthzInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rock/sys/authz/update", function(data){
        Feng.success("修改成功!");
        window.parent.Authz.reload();
        AuthzInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.msg + "!");
    });
    ajax.setBody(this.authzInfoData);
    ajax.start();
}

$(function() {
	form.on('submit(add)', function(data) {
		AuthzInfoDlg.addSubmit();
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	form.on('submit(edit)', function(data) {
		AuthzInfoDlg.editSubmit();
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
   form.render();
});
