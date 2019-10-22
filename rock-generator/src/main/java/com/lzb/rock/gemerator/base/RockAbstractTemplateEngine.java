package com.lzb.rock.gemerator.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.lzb.rock.gemerator.util.ToolUtil;
import com.sun.javafx.PlatformUtil;

/**
 * ADI项目模板生成 引擎
 * @author lzb
 *
 * 2019年3月18日 下午2:07:58
 */
public abstract class RockAbstractTemplateEngine extends AbstractTemplateEngine {

	private GroupTemplate groupTemplate;

	public RockAbstractTemplateEngine() {
		initBeetlEngine();
	}

	protected void initBeetlEngine() {
		Properties properties = new Properties();
		properties.put("RESOURCE.root", "");
		properties.put("DELIMITER_STATEMENT_START", "<%");
		properties.put("DELIMITER_STATEMENT_END", "%>");
		properties.put("HTML_TAG_FLAG", "##");
		Configuration cfg = null;
		try {
			cfg = new Configuration(properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
		groupTemplate = new GroupTemplate(resourceLoader, cfg);
		groupTemplate.registerFunctionPackage("tool", new ToolUtil());
	}

	protected void configTemplate(Template template) {
		//查找主键 和分库字段， 不允许出现两个主键
		Integer keyCount=0;
		StringBuffer sb=new StringBuffer();
		Integer subCount=0;
		StringBuffer subSb=new StringBuffer();
		for (TableField field : super.tableInfo.getFields()) {
			//判断是否为主键
            if (field.isKeyFlag()) {
            	keyCount++;
            	super.setKeyTableField(field);
            	sb.append(field.getName()).append(",");
            //	System.out.println("=========>表"+super.tableInfo.getName()+"的主键为:"+field.getName());
            }
            //判断分库字段
		}
		if(keyCount>1) {
			throw new RuntimeException("有多个主键："+super.tableInfo.getName()+"keys="+sb.toString());
		}
		
		
		//判断是否有分库字段
		String subTreasury = super.getGenQo().getSubTreasuryMap().get(super.tableInfo.getName());
		if(StringUtils.isNoneBlank(subTreasury)) {
			for (TableField field : super.tableInfo.getFields()) {
				//判断是否为主键
	            if (field.getName().equals(subTreasury) && !super.getKeyTableField().getName().equals(subTreasury)) {
	            	super.setSubTableField(field);
	            	subSb.append(field.getName()).append(",");
	            	subCount++;
	            }
	            //判断分库字段
			}
			if(subCount>1) {
				throw new RuntimeException("有多个分库字段："+super.tableInfo.getName()+"keys="+subSb.toString());
			}
		}

		template.binding("adminControllerConfig", super.adminControllerConfig);
		template.binding("msControllerConfig", super.msControllerConfig);
		template.binding("facadeControllerConfig", super.facadeControllerConfig);
		template.binding("clientControllerConfig", super.clientControllerConfig);
		template.binding("dtoReqControllerConfig", super.dtoReqControllerConfig);
		template.binding("dtoRespControllerConfig", super.dtoRespControllerConfig);

		template.binding("context", super.contextConfig);
		template.binding("dao", super.daoConfig);
		template.binding("service", super.serviceConfig);
		template.binding("serviceImpl", super.getServiceImplConfig());
		template.binding("sqls", super.sqlConfig);
		template.binding("table", super.tableInfo);

		template.binding("genQo", super.getGenQo());
		template.binding("keyTableField", super.getKeyTableField());
		template.binding("subTableField", super.getSubTableField());
	}

	protected void generateFile(String template, String filePath) {
		Template pageTemplate = groupTemplate.getTemplate(template);
		configTemplate(pageTemplate);
		if (PlatformUtil.isWindows()) {
			filePath = filePath.replaceAll("/+|\\\\+", "\\\\");
		} else {
			filePath = filePath.replaceAll("/+|\\\\+", "/");
		}
		File file = new File(filePath);
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			pageTemplate.renderTo(fileOutputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		// 配置之间的相互依赖
		super.initConfig();

		// 生成模板
		if (super.contextConfig.getControllerSwitch()) {
			// generateController();
			generateAdminController();
			generateMsController();
			generateFacadeController();
			generateClient();
			generateDto();
		}
		if (super.contextConfig.getSqlSwitch()) {
			generateSqls();
		}
		
		//剔除 is_del
		for (int i=0;i<super.getTableInfo().getFields().size();i++) {
			TableField tableField = super.getTableInfo().getFields().get(i);
			if("isDel".equals(tableField.getPropertyName())) {
				super.getTableInfo().getFields().remove(tableField);
				i--;
			}
		}
		if (super.contextConfig.getJsSwitch()) {
			generatePageJs();
		}
		//剔除lastTime  createTime 该字段自动更新，无法修改
		for (int i=0;i<super.getTableInfo().getFields().size();i++) {
			TableField tableField = super.getTableInfo().getFields().get(i);
			if("lastTime".equals(tableField.getPropertyName())) {
				super.getTableInfo().getFields().remove(tableField);
				i--;
			}
			if("lastUser".equals(tableField.getPropertyName())) {
				super.getTableInfo().getFields().remove(tableField);
				i--;
			}
			if("createTime".equals(tableField.getPropertyName())) {
				super.getTableInfo().getFields().remove(tableField);
				i--;
			}
		}
		
		//不生成字段 
		if (super.contextConfig.getInfoJsSwitch()) {
			generatePageInfoJs();
		}

		//由于页面生成移除的主键，页面生成必须放在最后
    	super.getTableInfo().getFields().remove(super.getKeyTableField());
    	super.getTableInfo().getCommonFields().remove(super.getKeyTableField());
		if (super.contextConfig.getIndexPageSwitch()) {
			generatePageHtml();
		}
		if (super.contextConfig.getAddPageSwitch()) {
			generatePageAddHtml();
		}
		if (super.contextConfig.getEditPageSwitch()) {
			generatePageEditHtml();
		}
		
	}

	protected abstract void generatePageEditHtml();

	protected abstract void generatePageAddHtml();

	protected abstract void generatePageInfoJs();

	protected abstract void generatePageJs();

	protected abstract void generatePageHtml();


	protected abstract void generateSqls();

	/**
	 * 生成admin需要的controller
	 */
	protected abstract void generateAdminController();

	/**
	 * 生成微服务需要的controller
	 */
	protected abstract void generateMsController();

	/**
	 * 生成Facade
	 */
	protected abstract void generateFacadeController();

	/**
	 * 生成Client
	 */
	protected abstract void generateClient();
	
	/**
	 * 生成dto
	 */
	protected abstract void generateDto();

}
