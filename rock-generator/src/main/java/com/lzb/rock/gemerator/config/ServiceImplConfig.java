package com.lzb.rock.gemerator.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Service模板生成的配置
 * 
 * @author lzb
 *
 *         2019年3月18日 下午2:59:16
 */
@Data
public class ServiceImplConfig {

	private ContextConfig contextConfig;

	private String servicePathTemplate;
	private String serviceImplPathTemplate;

	private String packageName;

	private List<String> imports;

	public void init() {
		List<String> imports = new ArrayList<String>();
		imports.add("com.lzb.rock.base.facade.impl.ServiceImpl");
		this.imports = imports;
	}

}
