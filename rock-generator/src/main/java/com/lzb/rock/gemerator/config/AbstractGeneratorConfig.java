package com.lzb.rock.gemerator.config;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.lzb.rock.gemerator.base.RockAbstractTemplateEngine;
import com.lzb.rock.gemerator.base.SimpleTemplateEngine;
import com.lzb.rock.gemerator.model.GenQo;
import com.lzb.rock.gemerator.util.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成的抽象配置
 *
 * @author fengshuonan
 * @date 2017-10-28-下午8:22
 */
public abstract class AbstractGeneratorConfig {

	/**
	 * mybatis-plus代码生成器配置
	 */
	GlobalConfig globalConfig = new GlobalConfig();

	DataSourceConfig dataSourceConfig = new DataSourceConfig();
	/**
	 * 策略配置
	 */
	StrategyConfig strategyConfig = new StrategyConfig();
	/**
	 * 包名策略配置
	 */
	PackageConfig packageConfig = new PackageConfig();

	TableInfo tableInfo = null;

	GenQo genQo;

	/**
	 * 代码生成器配置
	 */
	ContextConfig contextConfig = new ContextConfig();

	SqlConfig sqlConfig = new SqlConfig();

	protected abstract void config();

	public void init() {
		config();
		packageConfig.setService(contextConfig.getProPackage() + ".ms.service");
		packageConfig.setServiceImpl(contextConfig.getProPackage() + ".ms.service.impl");

		// controller没用掉,生成之后会自动删掉
		// packageConfig.setController("TTT");

		if (!contextConfig.getEntitySwitch()) {
			packageConfig.setEntity("TTT");
		}

		if (!contextConfig.getDaoSwitch()) {
			packageConfig.setMapper("TTT");
			packageConfig.setXml("TTT");
		}

		if (!contextConfig.getServiceSwitch()) {
			packageConfig.setService("TTT");
			packageConfig.setServiceImpl("TTT");
		}
	}

	/**
	 * 删除不必要的代码
	 */
	public void destory() {
		String outputDir = globalConfig.getOutputDir() + "/web";
		outputDir = outputDir.replace("\\", "/");

		File file = new File(outputDir);
		FileUtil.deleteDir(file);

	}

	public AbstractGeneratorConfig() {
	}

	public void doMpGeneration() {
		init();
		AutoGenerator autoGenerator = new AutoGenerator();
		TemplateConfig template = new TemplateConfig();

		template.setXml("/gunsTemplate/advanced/mapper.xml.vm");
		template.setEntity("/gunsTemplate/advanced/entity.java.vm");
		template.setService("/gunsTemplate/advanced/service.java.vm");
		template.setServiceImpl("/gunsTemplate/advanced/serviceImpl.java.vm");
		autoGenerator.setTemplate(template);
		autoGenerator.setGlobalConfig(globalConfig);
		autoGenerator.setDataSource(dataSourceConfig);
		autoGenerator.setStrategy(strategyConfig);
		autoGenerator.setPackageInfo(packageConfig);
		// 注入自定义配置
		InjectionConfig injectionConfig = new InjectionConfig() {

			@Override
			public void initMap() {
				ServiceConfig service = new ServiceConfig();
				service.init();
				ServiceImplConfig serviceImpl = new ServiceImplConfig();
				serviceImpl.init();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("service", service);
				map.put("serviceImpl", serviceImpl);
				this.setMap(map);
			}

		};
		autoGenerator.setCfg(injectionConfig);
		autoGenerator.execute();
		destory();

		// 获取table信息,用于代码生成
		List<TableInfo> tableInfoList = autoGenerator.getConfig().getTableInfoList();
		if (tableInfoList != null && tableInfoList.size() > 0) {
			this.tableInfo = tableInfoList.get(0);
		}
	}

	public void doRockGeneration() {
		RockAbstractTemplateEngine RockTemplateEngine = new SimpleTemplateEngine();
		RockTemplateEngine.setContextConfig(contextConfig);
		sqlConfig.setConnection(dataSourceConfig.getConn());
		RockTemplateEngine.setSqlConfig(sqlConfig);
		RockTemplateEngine.setTableInfo(tableInfo);
		RockTemplateEngine.setGenQo(this.genQo);
		RockTemplateEngine.start();
	}

	public GenQo getGenQo() {
		return genQo;
	}

	public void setGenQo(GenQo genQo) {
		this.genQo = genQo;
	}

}
