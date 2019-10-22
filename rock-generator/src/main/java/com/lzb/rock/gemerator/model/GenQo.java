package com.lzb.rock.gemerator.model;

import java.util.Map;
import java.util.Properties;

import com.lzb.rock.gemerator.base.UtilLoadProperties;

import lombok.Data;

/**
 * 代码生成的查询参数
 * 
 * @author lzb
 *
 *         Mar 16, 2019 8:28:03 PM
 */
@Data
public class GenQo {
	
	public GenQo(String proName) {
		Properties prop = UtilLoadProperties.init(proName);
		subTreasuryMap = UtilLoadProperties.getMapsKeyIsString(prop);
	}

	/**
	 * 数据库账号
	 */
	private String userName;

	/**
	 * 数据库密码
	 */
	private String password;

	/**
	 * 数据库url
	 */
	private String url;

	/**
	 * 项目地址
	 */
	private String projectPath;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 项目的包
	 */
	private String projectPackage;

	/**
	 * 核心模块的包
	 */
	private String corePackage;

	/**
	 * 表名称
	 */
	private String tableName;

	/**
	 * 忽略的表前缀
	 */
	private String ignoreTabelPrefix;

	/**
	 * 业务名称
	 */
	private String bizName;

	/**
	 * 模块名
	 */
	private String moduleName;
	/**
	 * 项目名
	 */
	private String projectName;

	/**
	 * 父级菜单名称
	 */
	private String parentMenuName;

	/**
	 * 是否生成控制器代码开关
	 */
	private Boolean controllerSwitch = true;

	/**
	 * 主页
	 */
	private Boolean indexPageSwitch = true;

	/**
	 * 添加页面
	 */
	private Boolean addPageSwitch = true;

	/**
	 * 编辑页面
	 */
	private Boolean editPageSwitch = true;

	/**
	 * 主页的js
	 */
	private Boolean jsSwitch = true;

	/**
	 * 详情页面js
	 */
	private Boolean infoJsSwitch = true;

	/**
	 * dao的开关
	 */
	private Boolean daoSwitch = true;

	/**
	 * service
	 */
	private Boolean serviceSwitch = true;

	/**
	 * 生成实体的开关
	 */
	private Boolean entitySwitch = true;

	/**
	 * 生成sql的开关
	 */
	private Boolean sqlSwitch = false;
	/**
	 * 表 分库字段配置表
	 */
	private Map<String, String> subTreasuryMap;

}
