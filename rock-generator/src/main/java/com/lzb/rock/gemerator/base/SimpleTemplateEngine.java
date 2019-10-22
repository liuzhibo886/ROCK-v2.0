package com.lzb.rock.gemerator.base;

import com.lzb.rock.gemerator.util.ToolUtil;

/**
 * 通用的模板生成引擎
 *
 * @author fengshuonan
 * @date 2017-05-09 20:32
 */
public class SimpleTemplateEngine extends RockAbstractTemplateEngine {

    @Override
    protected void generatePageEditHtml() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageEditPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page_edit.html.btl", path);
        System.out.println("生成编辑页面成功!");
    }

    @Override
    protected void generatePageAddHtml() {

    	
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageAddPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page_add.html.btl", path);
        System.out.println("生成添加页面成功!");
    }

    @Override
    protected void generatePageInfoJs() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageInfoJsPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page_info.js.btl", path);
        System.out.println("生成页面详情js成功!");
    }

    @Override
    protected void generatePageJs() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageJsPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page.js.btl", path);
        System.out.println("生成页面js成功!");
    }

    @Override
    protected void generatePageHtml() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPagePathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page.html.btl", path);
        System.out.println("生成页面成功!");
    }

    @Override
    protected void generateSqls() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + super.sqlConfig.getSqlPathTemplate(),
                ToolUtil.firstCharToUpperCase(super.getContextConfig().getBizEnName()));
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/menu_sql.sql.btl", path);
        System.out.println("生成sql成功!");
    }

	@Override
	protected void generateAdminController() {
        String controllerPath = ToolUtil.format(super.getContextConfig().getProjectPath() + super.getAdminControllerConfig().getControllerPathTemplate(),
                ToolUtil.firstCharToUpperCase(super.getContextConfig().getBizEnName()));
        controllerPath=controllerPath.replaceAll("\\+", "/").replaceAll("/+", "/");
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/AdminController.java.btl", controllerPath);
        System.out.println("生成后台管理控制器成功!");
	}

	@Override
	protected void generateMsController() {
        String controllerPath = ToolUtil.format(super.getContextConfig().getProjectPath() + super.getMsControllerConfig().getControllerPathTemplate(),
                ToolUtil.firstCharToUpperCase(super.getContextConfig().getBizEnName())+"Ms");
        controllerPath=controllerPath.replaceAll("\\+", "/").replaceAll("/+", "/");
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/MsController.java.btl", controllerPath);
        System.out.println("生成微服务控制器成功!");
	}

	@Override
	protected void generateFacadeController() {
        String controllerPath = ToolUtil.format(super.getContextConfig().getProjectPath()+super.getFacadeControllerConfig().getFacadeControllerPath(),ToolUtil.firstCharToUpperCase(super.getContextConfig().getBizEnName()));
        controllerPath=controllerPath.replaceAll("\\+", "/").replaceAll("/+", "/");
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/FacadeController.java.btl", controllerPath);
        System.out.println("生成Facade控制器成功!");
		
	}

	@Override
	protected void generateClient() {
        String controllerPath = ToolUtil.format(super.getContextConfig().getProjectPath() + super.getClientControllerConfig().getControllerPath(),
        		ToolUtil.firstCharToUpperCase(super.getContextConfig().getBizEnName()));
        controllerPath=controllerPath.replaceAll("\\+", "/").replaceAll("/+", "/");
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/ClientController.java.btl", controllerPath);
        System.out.println("生成Client控制器成功!");
		
		
	}

	@Override
	protected void generateDto() {
		String projectPath=super.getContextConfig().getProjectPath()+"/src/main/java/"+ super.getContextConfig().getProPackage().replaceAll("\\.", "/");
		projectPath=projectPath+"/open/dto/"+super.getContextConfig().getBizEnName()+"/";
		projectPath=projectPath.replaceAll("//", "/");
		
		 generateFile(super.getContextConfig().getTemplatePrefixPath() + "/entityListReq.java.btl", projectPath+super.getContextConfig().getBizEnBigName()+"ListReq.java");
		 generateFile(super.getContextConfig().getTemplatePrefixPath() + "/entityListResp.java.btl", projectPath+super.getContextConfig().getBizEnBigName()+"ListResp.java");
		
	}
}
