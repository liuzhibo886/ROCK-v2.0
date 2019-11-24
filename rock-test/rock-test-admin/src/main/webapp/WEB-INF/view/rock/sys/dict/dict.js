/**
 * 管理初始化
 */
var Dict = {
    tableId: "dictTable",	// 表格id
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Dict.initColumn = function () {
    return [[
         {field: 'dictId', title: 'ID',hide:true},
         {field: 'dictCode', title: '编码', width:150},
         {field: 'dictPkey', title: 'PID',hide:true},
         {field: 'dictKey', title: 'key' , width:50},
         {field: 'dictValue', title: 'value'},
         {field: 'dictLevel', title: '级别' , width:50},
         {field: 'dictMaxLevel', title: '最大等级' , width:50},
         {field: 'dictText', title: '文本' ,hide:true},
         {field: 'sort', title: '排序',sort: true, width:50},
         {field: 'createTime', title: '创建时间' ,sort: true},
         {field: 'lastTime', title: '最后修改时间',sort: true},
         {field: 'lastUser', title: '最后修改人',templet:function(obj){
        	 return Util.getUserName(obj.lastUser);
         }},
        {fixed: 'right', title:'操作', toolbar: '#dictBar', width:120}
    ]];
};
/**
 * 初始化表格
 */
Dict.initTable =function(){
	var table = layui.table;
    var where = {};
    where['dictPkey'] = $("#dictKey").val();
    where['dictCode'] = $("#dictCode").val();
	// 初始化
	table.render({
		elem : '#' + this.tableId,// 表格ID
		title : "用户列表",// 表格标题
		url : Feng.ctxPath+"/rock/sys/dict/list",// 请求URL
		method :'post',// 请求类型
		page : true,// 是否分页
		parseData :Feng.parseDataFuc, // 解析任意格式数据
		cellMinWidth : 80,// 表格最小宽度
		contentType :'application/json',
		toolbar : "#dictToolbar",// 表格头部模板ID
		height : 'full-100',// 全屏
		cols : this.initColumn(),
		where:where
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
 * 点击添加系统字典表(全局表)
 */
Dict.openAddDict = function () {
   var dictKey = $("#dictKey").val();
   var dictCode = $("#dictCode").val();
    var index = layer.open({
        type: 2,
        title: '添加系统字典表(全局表)',
        area: ['100%', '100%'], // 宽高
        fix: false, // 不固定
        maxmin: true,
        content: Feng.ctxPath + '/rock/sys/dict/dict_add?dictKey='+dictKey+"&dictCode="+dictCode
    });
    this.layerIndex = index;
};

/**
 * 打开查看系统字典表(全局表)详情
 */

Dict.openDictDetail = function (dictId) {
        var index = layer.open({
            type: 2,
            title: '系统字典表(全局表)详情',
            area: ['100%', '100%'], // 宽高
            fix: false, // 不固定
            maxmin: true,
            content: Feng.ctxPath + '/rock/sys/dict/dict_update/?dictId=' +dictId 
        });
        this.layerIndex = index;
};

/**
 * 删除系统字典表(全局表)
 */
Dict.delete = function(dictId) {
	var ajax = new $ax(Feng.ctxPath + "/rock/sys/dict/delete", function (data) {
 	Feng.success("删除成功!");
	Dict.reload();
	Dict.loadTree();
	}, function (data) {
	Feng.error("删除失败!" + data.msg + "!");
	});
	ajax.set("dictId",dictId);
	ajax.start();
};

/**
 * 查询系统字典表(全局表)列表
 */
Dict.reload = function () {
	var options={};
    var where = {};
    where['dictPkey'] = $("#dictKey").val();
    where['dictCode'] = $("#dictCode").val();
    options['where']=where;
    this.table.reload(this.tableId,options);
};

// 监听单元格编辑
Dict.editFuc = function(obj){
    var value = obj.value // 得到修改后的值
    ,data = obj.data // 得到所在行所有键值
    ,field = obj.field; // 得到字段
    var dictData={};
    dictData['dictId']=data.dictId;
    dictData[field]=value;
    
    // 提交信息
    var ajax = new $ax(Feng.ctxPath + "/rock/sys/authz/update", function(data){
        Feng.success("修改成功!");
       Dict.reload();
    },function(data){
        Feng.error("修改失败!" + data.msg + "!");
    });
    ajax.setBody(dictData);
    ajax.start();
    
  
};
// 头工具栏事件
Dict.toolbarFuc = function(obj){
	// 查询
	if(obj.event === 'reload'){
		Dict.reload();
	}else if(obj.event === 'add'){
		Dict.openAddDict();
	}
};
// 监听行工具事件
Dict.toolFuc = function(obj){
    var data = obj.data;
    // console.log(obj)
    if(obj.event === 'del'){
        layer.confirm("确定删除吗?", function(){
        layer.close(layer.index);
        Dict.delete(data.dictId);
         });
    } else if(obj.event === 'edit'){
    	Dict.openDictDetail(data.dictId);
    }
  };
/**
 * 点击树节点点击事件
 */  
Dict.clickTree = function(clickObj){
	$("#dictKey").val(clickObj.data.id);
	$("#dictCode").val(clickObj.data.code);
	 console.log("dictKey:" + clickObj.data.id);
	 console.log("dictCode:" + clickObj.data.code);
	Dict.reload();

};

Dict.loadTree = function(){
    var ajax = new $ax(Feng.ctxPath + "/rock/sys/dict/getTree", function(data){
        var tree = layui.tree;
        // 渲染
        var inst1 = tree.render({
          elem: '#dictTree',  // 绑定元素
          onlyIconControl:true,
          click : Dict.clickTree,
          data: data.data
        });	  
    	
    },function(data){
        Feng.error("获取树数据失败!" + data.msg + "!");
    });
    ajax.start();
	

  };  
$(function () {
	Dict.initTable();
	Dict.loadTree();
});
