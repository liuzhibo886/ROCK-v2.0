package com.lzb.rock.gemerator.config;

import java.util.ArrayList;
import java.util.List;

/**
 * admin控制器模板生成的配置
 *
 * @author fengshuonan
 * @date 2017-05-07 22:12
 */
public class AdminControllerConfig {

	private ContextConfig contextConfig;

	private String controllerPathTemplate;
	private String packageName;// 包名称
	private List<String> imports;// 所引入的包

	public void init() {
		ArrayList<String> imports = new ArrayList<String>();
		imports.add("org.springframework.beans.factory.annotation.Autowired");
		imports.add("org.springframework.stereotype.Controller");
		imports.add("org.springframework.ui.Model");
		imports.add("org.springframework.web.bind.annotation.RequestBody");
		imports.add("org.springframework.web.bind.annotation.RequestMapping");
		imports.add("org.springframework.web.bind.annotation.RequestParam");
		imports.add("org.springframework.web.bind.annotation.ResponseBody");

		imports.add("com.baomidou.mybatisplus.plugins.Page");
		imports.add("com.lzb.rock.base.Result");
		imports.add("com.lzb.rock.base.facade.IShiro");
		imports.add("com.lzb.rock.base.common.ResultEnum");
		imports.add("com.lzb.rock.base.exception.RockException");
		imports.add("com.lzb.rock.base.util.UtilJson");
		imports.add("com.lzb.rock.base.util.UtilObject");

		imports.add(contextConfig.getProPackage() + ".open.client." + contextConfig.getEntityName() + "Client");
		imports.add(contextConfig.getProPackage() + ".open.dto." + contextConfig.getBizEnName() + "." + contextConfig.getEntityName() + "ListReq");
	//	imports.add(contextConfig.getProPackage() + ".open.dto." + contextConfig.getBizEnName() + "." + contextConfig.getEntityName() + "ListResp");
		imports.add(contextConfig.getProPackage() + ".open.model." + contextConfig.getEntityName());

		this.imports = imports;
		this.packageName = contextConfig.getProPackage() + ".admin.controller";
		this.controllerPathTemplate = "\\src\\main\\java\\" + contextConfig.getProPackage().replaceAll("\\.", "\\\\") + "\\admin\\controller\\{}Controller.java";
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
