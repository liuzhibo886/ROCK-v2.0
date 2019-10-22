package com.lzb.rock.gemerator.base;

import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.lzb.rock.gemerator.config.AdminControllerConfig;
import com.lzb.rock.gemerator.config.ClientControllerConfig;
import com.lzb.rock.gemerator.config.ContextConfig;
import com.lzb.rock.gemerator.config.DtoReqControllerConfig;
import com.lzb.rock.gemerator.config.DtoRespControllerConfig;
import com.lzb.rock.gemerator.config.FacadeControllerConfig;
import com.lzb.rock.gemerator.config.MapperConfig;
import com.lzb.rock.gemerator.config.MsControllerConfig;
import com.lzb.rock.gemerator.config.PageConfig;
import com.lzb.rock.gemerator.config.ServiceConfig;
import com.lzb.rock.gemerator.config.ServiceImplConfig;
import com.lzb.rock.gemerator.config.SqlConfig;
import com.lzb.rock.gemerator.model.GenQo;

import lombok.Data;

/**
 * 模板生成父类
 * 
 * @author lzb
 *
 *         2019年3月18日 下午2:06:18
 */
@Data
public class AbstractTemplateEngine {

	protected ContextConfig contextConfig; // 全局配置
	protected AdminControllerConfig adminControllerConfig; // admin控制器的配置
	protected MsControllerConfig msControllerConfig; // 微服务控制器的配置
	protected FacadeControllerConfig facadeControllerConfig; // facade控制器的配置
	protected ClientControllerConfig clientControllerConfig; // client控制器的配置
	protected DtoReqControllerConfig dtoReqControllerConfig; // DTO配置
	protected DtoRespControllerConfig dtoRespControllerConfig; // DTO配置
	protected PageConfig pageConfig; // 页面的控制器
	protected MapperConfig daoConfig; // Dao配置
	protected ServiceConfig serviceConfig; // Service配置
	protected ServiceImplConfig serviceImplConfig; // Service配置
	protected SqlConfig sqlConfig; // sql配置
	protected TableInfo tableInfo; // 表的信息
	protected TableField keyTableField; // 主键字段信息
	protected TableField subTableField; // 分库字段信息，当主键跟分库字段一致，分库字段为空，全局表没有分库字段

	protected GenQo genQo;

	public void initConfig() {
		if (this.contextConfig == null) {
			this.contextConfig = new ContextConfig();
		}
		if (this.adminControllerConfig == null) {
			this.adminControllerConfig = new AdminControllerConfig();
		}

		if (this.msControllerConfig == null) {
			this.msControllerConfig = new MsControllerConfig();
		}

		if (this.facadeControllerConfig == null) {
			this.facadeControllerConfig = new FacadeControllerConfig();
		}

		if (this.clientControllerConfig == null) {
			this.clientControllerConfig = new ClientControllerConfig();
		}

		if (this.dtoReqControllerConfig == null) {
			this.dtoReqControllerConfig = new DtoReqControllerConfig();
		}
		if (this.dtoRespControllerConfig == null) {
			this.dtoRespControllerConfig = new DtoRespControllerConfig();
		}
		
		if (this.pageConfig == null) {
			this.pageConfig = new PageConfig();
		}
		if (this.daoConfig == null) {
			this.daoConfig = new MapperConfig();
		}
		if (this.serviceConfig == null) {
			this.serviceConfig = new ServiceConfig();
		}
		if (this.serviceImplConfig == null) {
			this.serviceImplConfig = new ServiceImplConfig();
		}
		if (this.sqlConfig == null) {
			this.sqlConfig = new SqlConfig();
		}
		this.contextConfig.init();

		this.adminControllerConfig.setContextConfig(this.contextConfig);
		this.adminControllerConfig.init();

		this.facadeControllerConfig.setContextConfig(this.contextConfig);
		this.facadeControllerConfig.init();

		this.msControllerConfig.setContextConfig(this.contextConfig);
		this.msControllerConfig.init();

		this.clientControllerConfig.setContextConfig(this.contextConfig);
		this.clientControllerConfig.init();

		this.serviceConfig.setContextConfig(this.contextConfig);
		this.serviceConfig.init();
		
		this.serviceImplConfig.setContextConfig(this.contextConfig);
		this.serviceImplConfig.init();

		this.daoConfig.setContextConfig(this.contextConfig);
		this.daoConfig.init();

		this.pageConfig.setContextConfig(this.contextConfig);
		this.pageConfig.init();

		this.sqlConfig.setContextConfig(this.contextConfig);
		this.sqlConfig.init();

		this.dtoReqControllerConfig.setContextConfig(this.contextConfig);
		this.dtoReqControllerConfig.init();
		
		this.dtoRespControllerConfig.setContextConfig(this.contextConfig);
		this.dtoRespControllerConfig.init();
	}

}
