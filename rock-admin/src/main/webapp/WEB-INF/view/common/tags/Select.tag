<%/* %>
下拉选择框
<% */ %>
<%if(isEmpty(layuiInlineStart) || layuiInlineStart == "true"){%>
<div class="layui-inline">
<%}%>
<%if(isNotEmpty(name) && isNotEmpty(layVerify)){%>
	<label class="layui-form-label">${name}<span class="badge-must">*</span></label>
<%}else if(isNotEmpty(name)){%>
	<label class="layui-form-label">${name}</label>
<%}%>
	<div class="layui-input-inline">
		<select name="${id}" id="${id}" ${laySearch!} dic="${dic!}" first="${first!}" defaultValue="${value!}" lay-verify="${layVerify!}">
		</select>
	</div>
<%if(isNotEmpty(mid)){%>
	<div class="layui-form-mid">${mid}</div>
<%}	%>
<%if(isEmpty(layuiInlineEnd) || layuiInlineEnd == "true"){%>
</div>
<%}	%>


