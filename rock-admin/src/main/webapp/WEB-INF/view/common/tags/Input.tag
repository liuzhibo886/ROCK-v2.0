<%/* %>
    表单中input框标签中各个参数的说明:

    hidden : input hidden框的id
    id : input框id
    name : input框名称
    readonly : readonly属性
    clickFun : 点击事件的方法名
    style : 附加的css属性
<% */ %>
<%if(isEmpty(layuiInlineStart) || layuiInlineStart != "false"){%>
<div class="layui-inline">
<%}%>
<%if(isNotEmpty(name) && isNotEmpty(layVerify)){%>
	<label class="layui-form-label">${name}<span class="badge-must">*</span></label>
<%}else if(isNotEmpty(name)){%>
<label class="layui-form-label">${name}</label>
<%}%>
	<div class="layui-input-inline">
		<input type="text" class="layui-input" id="${id}" name="${id}" value="${value!}"  lay-verify="${layVerify!}" lay-reqText="${layReqText!}" <%if(isNotEmpty(disabled)){%> disabled="${disabled}" <%}%> placeholder="${placeholder!}">
	</div>
<%if(isNotEmpty(mid)){%>
	<div class="layui-form-mid">${mid}</div>
<%}	%>

<%if(isEmpty(layuiInlineEnd) || layuiInlineEnd != "false"){%>
</div>
<%}	%>


