package com.lzb.rock.gemerator.config;

import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.lzb.rock.gemerator.model.GenQo;
import com.lzb.rock.gemerator.util.ToolUtil;

import java.io.File;

/**
 * 默认的代码生成的配置
 * 
 * @author lzb
 *
 *         Mar 16, 2019 9:50:47 PM
 */
public class WebGeneratorConfig extends AbstractGeneratorConfig {

	public WebGeneratorConfig(GenQo genQo) {
		this.genQo = genQo;
	}

	@Override
	protected void config() {
		/**
		 * 数据库配置
		 */
		dataSourceConfig.setDbType(DbType.MYSQL);
		dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
		dataSourceConfig.setUsername(genQo.getUserName());
		dataSourceConfig.setPassword(genQo.getPassword());
		dataSourceConfig.setUrl(genQo.getUrl());

		/**
		 * 全局配置
		 */
		globalConfig.setOutputDir(
				genQo.getProjectPath() + File.separator + "src" + File.separator + "main" + File.separator + "java");
		globalConfig.setFileOverride(true);
		globalConfig.setEnableCache(false);
		globalConfig.setBaseResultMap(true);
		globalConfig.setBaseColumnList(true);
		globalConfig.setOpen(false);
		globalConfig.setAuthor(genQo.getAuthor());
		contextConfig.setProPackage(genQo.getProjectPackage());

		/**
		 * 生成策略
		 */
		if (genQo.getIgnoreTabelPrefix() != null) {
			strategyConfig.setTablePrefix(new String[] { genQo.getIgnoreTabelPrefix() });
		}
		/**
		 * 需要生成的表名
		 */
		strategyConfig.setInclude(new String[] { genQo.getTableName() });
		strategyConfig.setNaming(NamingStrategy.underline_to_camel);
		/**
		 * 逻辑删除字段
		 */
		strategyConfig.setLogicDeleteFieldName("is_del");
		/**
		 * 使用Lombok
		 */
		strategyConfig.setEntityLombokModel(true);
		/**
		 * 数据库版本控制字段
		 */
		strategyConfig.setVersionFieldName("version");
		packageConfig.setParent(null);
		packageConfig.setEntity(genQo.getProjectPackage() + ".open.model");
		packageConfig.setMapper(genQo.getProjectPackage() + ".ms.mapper");
		packageConfig.setXml(genQo.getProjectPackage() + ".ms.xml");

		/**
		 * 业务代码配置
		 */
		contextConfig.setBizChName(genQo.getBizName());
		contextConfig.setModuleName(genQo.getModuleName());
		contextConfig.setProjectPath(genQo.getProjectPath());// 写自己项目的绝对路径
		contextConfig.setProjectName(genQo.getProjectName());
		if (ToolUtil.isEmpty(genQo.getIgnoreTabelPrefix())) {
			String entityName = ToolUtil.toCamelCase(genQo.getTableName());
			contextConfig.setEntityName(ToolUtil.firstCharToUpperCase(entityName));
			contextConfig.setBizEnName(ToolUtil.firstCharToLowerCase(entityName));
		} else {
			String entiyName = ToolUtil
					.toCamelCase(ToolUtil.removePrefix(genQo.getTableName(), genQo.getIgnoreTabelPrefix()));
			contextConfig.setEntityName(ToolUtil.firstCharToUpperCase(entiyName));
			contextConfig.setBizEnName(ToolUtil.firstCharToLowerCase(entiyName));
		}
		sqlConfig.setParentMenuName(genQo.getParentMenuName());// 这里写已有菜单的名称,当做父节点

		/**
		 * mybatis-plus 生成器开关
		 */
		contextConfig.setEntitySwitch(genQo.getEntitySwitch());
		contextConfig.setDaoSwitch(genQo.getDaoSwitch());
		contextConfig.setServiceSwitch(genQo.getServiceSwitch());

		/**
		 * 生成器开关
		 */
		contextConfig.setControllerSwitch(genQo.getControllerSwitch());
		contextConfig.setIndexPageSwitch(genQo.getIndexPageSwitch());
		contextConfig.setAddPageSwitch(genQo.getAddPageSwitch());
		contextConfig.setEditPageSwitch(genQo.getEditPageSwitch());
		contextConfig.setJsSwitch(genQo.getJsSwitch());
		contextConfig.setInfoJsSwitch(genQo.getInfoJsSwitch());
		contextConfig.setSqlSwitch(genQo.getSqlSwitch());
	}
}
