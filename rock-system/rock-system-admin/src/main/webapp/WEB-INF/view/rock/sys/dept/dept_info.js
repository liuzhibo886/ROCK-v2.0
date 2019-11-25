/**
 * 初始化详情对话框
 */
var DeptInfoDlg = {
    deptInfoData : {}
};

/**
 * 清除数据
 */
DeptInfoDlg.clearData = function() {
    this.deptInfoData = {};
}

/**
 * 关闭此对话框
 */
DeptInfoDlg.close = function() {
    parent.layer.close(window.parent.Dept.layerIndex);
}

/**
 * 收集数据
 */
DeptInfoDlg.collectData = function() {
 this.deptInfoData= form.val("deptFrom");
 this.deptInfoData['deptId']=$("#deptId").val();
 this.deptInfoData['deptPcode']=$("#deptPcodeKey").val();
}

/**
 * 提交添加
 */
DeptInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rock/sys/dept/add", function(data){
        Feng.success("添加成功!");
        window.parent.Dept.reload();
        DeptInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.msg + "!");
    });
    ajax.setBody(this.deptInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DeptInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rock/sys/dept/update", function(data){
        Feng.success("修改成功!");
        window.parent.Dept.reload();
        DeptInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.msg + "!");
    });
    ajax.setBody(this.deptInfoData);
    ajax.start();
}

$(function() {
	form.on('submit(add)', function(data) {
		DeptInfoDlg.addSubmit();
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	form.on('submit(edit)', function(data) {
		DeptInfoDlg.editSubmit();
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
   form.render();
   var tree=new LYTree("deptPcode",Feng.ctxPath +"/rock/sys/dept/treeNode");
   tree.init();
});
