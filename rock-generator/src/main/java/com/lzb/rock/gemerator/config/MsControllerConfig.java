package com.lzb.rock.gemerator.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制器模板生成的配置
 *
 * @author fengshuonan
 * @date 2017-05-07 22:12
 */
public class MsControllerConfig {

	private ContextConfig contextConfig;

	private String controllerPathTemplate;
	private String packageName;// 包名称
	private List<String> imports;// 所引入的包

	public void init() {
		ArrayList<String> imports = new ArrayList<String>();
		// imports.add(contextConfig.getCoreBasePackage() +
		// ".base.controller.BaseController");
		imports.add("java.util.List");
		imports.add("org.springframework.beans.factory.annotation.Autowired");
		imports.add("org.springframework.web.bind.annotation.PostMapping");
		imports.add("org.springframework.web.bind.annotation.RequestBody");
		imports.add("org.springframework.web.bind.annotation.RequestParam");
		imports.add("org.springframework.web.bind.annotation.ResponseBody");
		imports.add("org.springframework.web.bind.annotation.RestController");

		imports.add("com.baomidou.mybatisplus.mapper.Condition");
		imports.add("com.baomidou.mybatisplus.mapper.Wrapper");
		imports.add("com.baomidou.mybatisplus.plugins.Page");
		imports.add("com.lzb.rock.base.Result");
		imports.add("com.lzb.rock.base.common.ResultEnum");
		imports.add("com.lzb.rock.base.aop.annotation.Log");
		imports.add("com.lzb.rock.base.factory.PageFactory");
//		imports.add("com.lzb.rock.base.util.UtilString");

		imports.add(contextConfig.getProPackage() + ".ms.service.I" + contextConfig.getEntityName() + "Service");
		imports.add(contextConfig.getProPackage() + ".open.dto." + contextConfig.getBizEnName() + "." + contextConfig.getEntityName() + "ListReq");
		imports.add(contextConfig.getProPackage() + ".open.facade." + contextConfig.getEntityName() + "Facade");
		imports.add(contextConfig.getProPackage() + ".open.model." + contextConfig.getEntityName());

		imports.add("io.swagger.annotations.Api");
		imports.add("io.swagger.annotations.ApiImplicitParam");
		imports.add("io.swagger.annotations.ApiImplicitParams");
		imports.add("io.swagger.annotations.ApiOperation");
		this.imports = imports;
		this.packageName = contextConfig.getProPackage() + ".ms.controller";
		this.controllerPathTemplate = "\\src\\main\\java\\" + contextConfig.getProPackage().replaceAll("\\.", "\\\\") + "\\ms\\controller\\{}Controller.java";
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<String> getImports() {
		return imports;
	}

	public void setImports(List<String> imports) {
		this.imports = imports;
	}

	public String getControllerPathTemplate() {
		return controllerPathTemplate;
	}

	public void setControllerPathTemplate(String controllerPathTemplate) {
		this.controllerPathTemplate = controllerPathTemplate;
	}

	public ContextConfig getContextConfig() {
		return contextConfig;
	}

	public void setContextConfig(ContextConfig contextConfig) {
		this.contextConfig = contextConfig;
	}

}
