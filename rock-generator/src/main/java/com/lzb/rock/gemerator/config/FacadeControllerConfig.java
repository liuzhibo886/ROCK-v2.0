package com.lzb.rock.gemerator.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * facade控制器模板生成的配置
 * 
 * @author lzb
 *
 *         2019年3月18日 下午2:41:40
 */
@Data
public class FacadeControllerConfig {

	private ContextConfig contextConfig;

	private String facadeControllerPath;
	private String packageName;// 包名称
	private List<String> imports;// 所引入的包

	public void init() {
		ArrayList<String> imports = new ArrayList<String>();
		imports.add("java.util.List");
		imports.add("org.springframework.web.bind.annotation.PostMapping");
		imports.add("org.springframework.web.bind.annotation.RequestBody");
		imports.add("org.springframework.web.bind.annotation.RequestParam");
		imports.add("org.springframework.web.bind.annotation.ResponseBody");
		imports.add("com.baomidou.mybatisplus.plugins.Page");
		imports.add("com.lzb.rock.base.Result");
		imports.add(contextConfig.getProPackage() + ".open.dto." + contextConfig.getBizEnName() + "." + contextConfig.getEntityName() + "ListReq");
		imports.add(contextConfig.getProPackage() + ".open.model." + contextConfig.getEntityName());

		this.imports = imports;
		this.packageName = contextConfig.getProPackage() + ".open.facade";
		this.facadeControllerPath = "\\src\\main\\java\\" + contextConfig.getProPackage().replaceAll("\\.", "\\\\") + "\\open\\Facade\\{}Facade.java";
	}

}
