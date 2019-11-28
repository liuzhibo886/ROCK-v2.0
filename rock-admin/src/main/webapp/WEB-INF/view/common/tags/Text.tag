<%/*%>
Text 框
<%*/%>
<%if(isEmpty(layuiInlineStart) || layuiInlineStart != "false"){%>
<div class="layui-inline">
<%}%>
<%if(isNotEmpty(name) && isNotEmpty(layVerify)){%>
	<label class="layui-form-label">${name}<span class="badge-must">*</span></label>
<%}else if(isNotEmpty(name)){%>
	<label class="layui-form-label">${name}</label>
<%}%>
	<div class="layui-input-inline">
		<textarea id="${id}" name="${id}" lay-verify="${layVerify!}"  placeholder="${placeholder!}" class="layui-textarea">${value!} </textarea>
	</div>
<%if(isNotEmpty(mid)){%>
	<div class="layui-form-mid">${mid}</div>
<%}%>		
<%if(isEmpty(layuiInlineEnd) || layuiInlineEnd != "false"){%>
</div>
<%}%>


