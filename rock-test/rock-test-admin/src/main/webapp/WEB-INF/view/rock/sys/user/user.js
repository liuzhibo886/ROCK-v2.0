/**
 * 管理初始化
 */
var User = {
    tableId: "userTable",	//表格id
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
User.initColumn = function () {
    return [[
         {field: 'userId', title: '用户ID'},
         {field: 'userAvatar', title: '用户头像base64格式'},
         {field: 'userAccount', title: '用户账号'},
         {field: 'userPassword', title: '用户密码'},
         {field: 'userSalt', title: '用户md5密码盐'},
         {field: 'userName', title: '用户名字'},
         {field: 'userBirthday', title: '用户生日'},
         {field: 'userSex', title: '性别（1：男 2：女）'},
         {field: 'userEmail', title: '用户电子邮件'},
         {field: 'userPhone', title: '用户电话'},
         {field: 'roleIds', title: '用户所属角色id,多个#分割,前后都要包含#'},
         {field: 'roleNames', title: '用户所属角色名称,json数组，包含字段roleId,roleName'},
         {field: 'deptIds', title: '用户所属部门id,多个#分割,前后都要包含#'},
         {field: 'deptNames', title: '用户所属部名词,json数组包含deptid,deptName'},
         {field: 'userStatus', title: '状态(1：启用  2：冻结 ）'},
         {field: 'createTime', title: '创建时间'},
         {field: 'lastTime', title: '最后修改人'},
         {field: 'lastUser', title: '最后修改人'},
        {fixed: 'right', title:'操作', toolbar: '#userBar', width:120}
    ]];
};
/**
 * 初始化表格
 */
User.initTable =function(){
	var table = layui.table;
	// 初始化
	table.render({
		elem : '#' + this.tableId,// 表格ID
		title : "用户列表",// 表格标题
		url : Feng.ctxPath+"/rock/sys/user/list",// 请求URL
		method :'post',// 请求类型
		page : true,// 是否分页
		parseData :Feng.parseDataFuc, // 解析任意格式数据
		cellMinWidth : 80,// 表格最小宽度
		contentType :'application/json',
		toolbar : "#userToolbar",// 表格头部模板ID
		height : 'full-100',// 全屏
		cols : this.initColumn()
	});
	// 监听单元格编辑
		table.on('edit(' + this.tableId + ')', this.editFuc);
	// 头工具栏事件
		table.on('toolbar(' + this.tableId + ')', this.toolbarFuc);
	// 监听行工具事件
		table.on('tool(' + this.tableId + ')', this.toolFuc);
		this.table=table;
}
/**
 * 点击添加管理员表
 */
User.openAddUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加管理员表',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/rock/sys/user/user_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看管理员表详情
 */

User.openUserDetail = function (userId) {
        var index = layer.open({
            type: 2,
            title: '管理员表详情',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/rock/sys/user/user_update/?userId=' +userId 
        });
        this.layerIndex = index;
};

/**
 * 删除管理员表
 */
User.delete = function(userId) {
	var ajax = new $ax(Feng.ctxPath + "/rock/sys/user/delete", function (data) {
 	Feng.success("删除成功!");
	User.reload();
	}, function (data) {
	Feng.error("删除失败!" + data.msg + "!");
	});
	ajax.set("userId",userId);
	ajax.start();
};

/**
 * 查询管理员表列表
 */
User.reload = function () {
	var options={};
    var where = {};
    where['userId'] = $("#userId").val();
    options['where']=where;
    this.table.reload(this.tableId,options);
};

// 监听单元格编辑
User.editFuc = function(obj){
    var value = obj.value // 得到修改后的值
    ,data = obj.data // 得到所在行所有键值
    ,field = obj.field; // 得到字段
    var userData={};
    userData['userId']=data.userId;
    userData[field]=value;
    
    // 提交信息
    var ajax = new $ax(Feng.ctxPath + "/rock/sys/authz/update", function(data){
        Feng.success("修改成功!");
       User.reload();
    },function(data){
        Feng.error("修改失败!" + data.msg + "!");
    });
    ajax.setBody(userData);
    ajax.start();
    
  
};
// 头工具栏事件
User.toolbarFuc = function(obj){
	// 查询
	if(obj.event === 'reload'){
		User.reload();
	}else if(obj.event === 'add'){
		User.openAddUser();
	}
};
// 监听行工具事件
User.toolFuc = function(obj){
    var data = obj.data;
    // console.log(obj)
    if(obj.event === 'del'){
        layer.confirm("确定删除吗?", function(){
        layer.close(layer.index);
        User.delete(data.userId);
         });
    } else if(obj.event === 'edit'){
    	User.openUserDetail(data.userId);
    }
  };
$(function () {
	User.initTable();
});
