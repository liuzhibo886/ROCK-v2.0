/**
 * 初始化详情对话框
 */
var UserInfoDlg = {
    userInfoData : {}
};

/**
 * 清除数据
 */
UserInfoDlg.clearData = function() {
    this.userInfoData = {};
}

/**
 * 关闭此对话框
 */
UserInfoDlg.close = function() {
    parent.layer.close(window.parent.User.layerIndex);
}

/**
 * 收集数据
 */
UserInfoDlg.collectData = function() {
 this.userInfoData= form.val("userFrom");
 this.userInfoData['userId']=$("#userId").val();
}

/**
 * 提交添加
 */
UserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rock/sys/user/add", function(data){
        Feng.success("添加成功!");
        window.parent.User.reload();
        UserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.msg + "!");
    });
    ajax.setBody(this.userInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rock/sys/user/update", function(data){
        Feng.success("修改成功!");
        window.parent.User.reload();
        UserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.msg + "!");
    });
    ajax.setBody(this.userInfoData);
    ajax.start();
}

$(function() {
	form.on('submit(add)', function(data) {
		UserInfoDlg.addSubmit();
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	form.on('submit(edit)', function(data) {
		UserInfoDlg.editSubmit();
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
   form.render();
});
