<%/* %>
树结构
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
			<div class="layui-unselect layui-form-select rock-downpanel">
				<div class="layui-select-title">
					<input type="hidden" id="${id}Params" name="${id}Params" size="${size!}"  view="${view!}" isOnlyEnd="${isOnlyEnd!}" showCheckbox="${showCheckbox!}" value="${value!}" values=${values!}>
					<input type="text" class="layui-input layui-unselect" id="${id}Name" style="display: none;" name="${id}Name" value=""> 
					<input type="text" class="layui-input layui-unselect" id="${id}Names" style="display: none;" name="${id}Names" value=""> 
					<input type="hidden" id="${id}Key" name="${id}Key" value=""> 
					<input type="hidden" id="${id}Keys" name="${id}Keys" value=""> 
					<i class="layui-edge"></i>
				</div>
				<dl class="layui-anim layui-anim-upbit">
					<dd>
						<ul id="${id}Tree"></ul>
					</dd>
				</dl>
			</div>
		</div>
	</div>