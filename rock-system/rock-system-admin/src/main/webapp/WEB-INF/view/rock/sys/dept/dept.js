/**
 * 管理初始化
 */
var Dept = {
    tableId: "deptTable",	// 表格id
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Dept.initColumn = function () {
    return [[
         {field: 'deptId', title: '主键id'},
         {field: 'deptCode', title: '部门编码'},
         {field: 'deptSimpleName', title: '部门简称'},
         {field: 'deptFullName', title: '部门全称'},
         {field: 'remarks', title: '备注'},
         {field: 'createTime', title: '创建时间'},
         {field: 'lastTime', title: '最后修改时间'},
         {field: 'lastUser', title: '最后修改人',templet:function(obj){
        	 return Util.getUserName(obj.lastUser);
         }},
        {fixed: 'right', title:'操作', toolbar: '#deptBar', width:120}
    ]];
};
/**
 * 初始化表格
 */
Dept.initTable =function(){	
	var table = layui.treetable;
	// 初始化
	layer.load(2);
	table.render({
		elem : '#' + this.tableId,// 表格ID
		title : "用户列表",// 表格标题
		url : Feng.ctxPath+"/rock/sys/dept/list",// 请求URL
		method :'post',// 请求类型
		treeIdName: 'deptCode',// 子节点字段名
		treePidName: 'deptPcode',// 父节点字段名
		page : false,// 是否分页
		treeSpid: -2,// 最上级的父级id
		treeColIndex: 2,// 树形图标显示在第几列
		parseData : function(res){
			// 解析任意格式数据
			return {
				"code": res.code, // 解析接口状态
				"msg" : res.msg, // 解析提示文本
				"data" : res.data
				};
		}, 
		cellMinWidth : 80,// 表格最小宽度
		contentType :'application/json',
		toolbar : "#deptToolbar",// 表格头部模板ID
		height : 'full-100',// 全屏
		done: function () {
		  layer.closeAll('loading');
		},
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
 * 点击添加部门表
 */
Dept.openAddDept = function () {
    var index = layer.open({
        type: 2,
        title: '添加部门表',
        area: ['100%', '100%'], // 宽高
        fix: false, // 不固定
        maxmin: true,
        content: Feng.ctxPath + '/rock/sys/dept/dept_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看部门表详情
 */

Dept.openDeptDetail = function (deptId) {
        var index = layer.open({
            type: 2,
            title: '部门表详情',
            area: ['100%', '100%'], // 宽高
            fix: false, // 不固定
            maxmin: true,
            content: Feng.ctxPath + '/rock/sys/dept/dept_update/?deptId=' +deptId 
        });
        this.layerIndex = index;
};

/**
 * 删除部门表
 */
Dept.delete = function(deptId) {
	var ajax = new $ax(Feng.ctxPath + "/rock/sys/dept/delete", function (data) {
 	Feng.success("删除成功!");
	Dept.reload();
	}, function (data) {
	Feng.error("删除失败!" + data.msg + "!");
	});
	ajax.set("deptId",deptId);
	ajax.start();
};

/**
 * 查询部门表列表
 */
Dept.reload = function () {
	Dept.initTable();
};

// 监听单元格编辑
Dept.editFuc = function(obj){
    var value = obj.value // 得到修改后的值
    ,data = obj.data // 得到所在行所有键值
    ,field = obj.field; // 得到字段
    var deptData={};
    deptData['deptId']=data.deptId;
    deptData[field]=value;
    
    // 提交信息
    var ajax = new $ax(Feng.ctxPath + "/rock/sys/dept/update", function(data){
        Feng.success("修改成功!");
       Dept.reload();
    },function(data){
        Feng.error("修改失败!" + data.msg + "!");
    });
    ajax.setBody(deptData);
    ajax.start();
    
  
};
// 头工具栏事件
Dept.toolbarFuc = function(obj){
	// 查询
	if(obj.event === 'reload'){
		Dept.reload();
	}else if(obj.event === 'add'){
		Dept.openAddDept();
	}
};
// 监听行工具事件
Dept.toolFuc = function(obj){
    var data = obj.data;
    // console.log(obj)
    if(obj.event === 'del'){
        layer.confirm("确定删除吗?", function(){
        layer.close(layer.index);
        Dept.delete(data.deptId);
         });
    } else if(obj.event === 'edit'){
    	Dept.openDeptDetail(data.deptId);
    }
  };
  
$(function () {
	Dept.initTable();
});
