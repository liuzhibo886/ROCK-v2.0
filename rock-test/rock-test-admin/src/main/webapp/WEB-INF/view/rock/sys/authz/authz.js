/**
 * 管理初始化
 */
var Authz = {
    tableId: "authzTable",	//表格id
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Authz.initColumn = function () {
    return [[
         {field: 'authzId', title: '权限ID'},
         {field: 'authzCode', title: '权限编码'},
         {field: 'authzParentCode', title: '权限父编码'},
         {field: 'authzName', title: '权限名称'},
         {field: 'authzIcon', title: '权限图片，菜单权限专用'},
         {field: 'authzUrl', title: '权限url地址，菜单权限专用'},
         {field: 'authzSort', title: '权限排序，升序'},
         {field: 'authzType', title: '权限类型，0菜单，1按钮;2字段'},
         {field: 'authzTips', title: '备注'},
         {field: 'authzStatus', title: '菜单状态 :  0:启用   1:不启用'},
         {field: 'authzIsOpen', title: '是否打开:    1:打开   0:不打开；菜单权限专用'},
         {field: 'createTime', title: '创建时间'},
         {field: 'lastTime', title: '最后修改时间'},
         {field: 'lastUser', title: '最后修改人',templet:function(obj){
        	 return Util.getUserName(obj.lastUser);
         }},
        {fixed: 'right', title:'操作', toolbar: '#authzBar', width:120}
    ]];
};
/**
 * 初始化表格
 */
Authz.initTable =function(){
	var table = layui.table;
	// 初始化
	table.render({
		elem : '#' + this.tableId,// 表格ID
		title : "用户列表",// 表格标题
		url : Feng.ctxPath+"/rock/sys/authz/list",// 请求URL
		method :'post',// 请求类型
		page : true,// 是否分页
		parseData :Feng.parseDataFuc, // 解析任意格式数据
		cellMinWidth : 80,// 表格最小宽度
		contentType :'application/json',
		toolbar : "#authzToolbar",// 表格头部模板ID
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
 * 点击添加权限表
 */
Authz.openAddAuthz = function () {
    var index = layer.open({
        type: 2,
        title: '添加权限表',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/rock/sys/authz/authz_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看权限表详情
 */

Authz.openAuthzDetail = function (authzId) {
        var index = layer.open({
            type: 2,
            title: '权限表详情',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/rock/sys/authz/authz_update/?authzId=' +authzId 
        });
        this.layerIndex = index;
};

/**
 * 删除权限表
 */
Authz.delete = function(authzId) {
	var ajax = new $ax(Feng.ctxPath + "/rock/sys/authz/delete", function (data) {
 	Feng.success("删除成功!");
	Authz.reload();
	}, function (data) {
	Feng.error("删除失败!" + data.msg + "!");
	});
	ajax.set("authzId",authzId);
	ajax.start();
};

/**
 * 查询权限表列表
 */
Authz.reload = function () {
	var options={};
    var where = {};
    where['authzId'] = $("#authzId").val();
    options['where']=where;
    this.table.reload(this.tableId,options);
};

// 监听单元格编辑
Authz.editFuc = function(obj){
    var value = obj.value // 得到修改后的值
    ,data = obj.data // 得到所在行所有键值
    ,field = obj.field; // 得到字段
    var authzData={};
    authzData['authzId']=data.authzId;
    authzData[field]=value;
    
    // 提交信息
    var ajax = new $ax(Feng.ctxPath + "/rock/sys/authz/update", function(data){
        Feng.success("修改成功!");
       Authz.reload();
    },function(data){
        Feng.error("修改失败!" + data.msg + "!");
    });
    ajax.setBody(authzData);
    ajax.start();
    
  
};
// 头工具栏事件
Authz.toolbarFuc = function(obj){
	// 查询
	if(obj.event === 'reload'){
		Authz.reload();
	}else if(obj.event === 'add'){
		Authz.openAddAuthz();
	}
};
// 监听行工具事件
Authz.toolFuc = function(obj){
    var data = obj.data;
    // console.log(obj)
    if(obj.event === 'del'){
        layer.confirm("确定删除吗?", function(){
        layer.close(layer.index);
        Authz.delete(data.authzId);
         });
    } else if(obj.event === 'edit'){
    	Authz.openAuthzDetail(data.authzId);
    }
  };
$(function () {
	Authz.initTable();
});
