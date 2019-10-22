package com.lzb.rock.gemerator.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * client控制器模板生成的配置
 * 
 * @author lzb
 *
 *         2019年3月18日 下午2:52:21
 */
@Data
public class ClientControllerConfig {

	private ContextConfig contextConfig;

	private String controllerPath;
	private String packageName;// 包名称
	private List<String> imports;// 所引入的包

	public void init() {
		ArrayList<String> imports = new ArrayList<String>();
		imports.add("org.springframework.cloud.netflix.feign.FeignClient");
		imports.add("com.lzb.rock.base.config.FeignClientCfg");
		imports.add(contextConfig.getProPackage() + ".open.facade." + contextConfig.getEntityName() + "Facade");
		this.imports = imports;
		this.packageName = contextConfig.getProPackage() + ".open.client";
		this.controllerPath = "\\src\\main\\java\\" + contextConfig.getProPackage().replaceAll("\\.", "\\\\") + "\\open\\client\\{}Client.java";
	}

}
