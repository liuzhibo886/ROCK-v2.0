var $ = layui.jquery;
var layer = layui.layer;
var tree = layui.tree;
var form = layui.form;
var layedit = layui.layedit;
var laydate = layui.laydate;
var element = layui.element;
var treetable = layui.treetable;

$(function() {

	//tree 树点击事件
	$(".rock-downpanel").on(
			"click",
			".layui-select-title",
			function(e) {
				$(".layui-form-select").not(
						$(this).parents(".layui-form-select")).removeClass(
						"layui-form-selected");
				$(this).parents(".rock-downpanel").toggleClass(
						"layui-form-selected");
				layui.stope(e);
			}).on("click", "dl i", function(e) {
		layui.stope(e);
	});
	$(".rock-downpanel").on("click", function(e) {
		$(".layui-form-select").removeClass("layui-form-selected");
	});
	//table tree 开关点击事件
    $('body').on("click",".treeTable-icon",function(){
        var treeLinkage = $(this).parents('.treeTable').attr('treeLinkage');
        if ('true' == treeLinkage) {
            treetable.toggleRows($(this), true);
        } else {
            treetable.toggleRows($(this), false);
        }
    });
});