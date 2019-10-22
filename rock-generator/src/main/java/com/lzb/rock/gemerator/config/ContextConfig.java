package com.lzb.rock.gemerator.config;

import com.lzb.rock.gemerator.util.ToolUtil;

import lombok.Data;

/**
 * 全局配置
 * @author lzb
 *
 * 2019年3月18日 下午3:18:55
 */
@Data
public class ContextConfig {

    private String templatePrefixPath = "gunsTemplate/advanced";
    private String projectPath = "D:\\ideaSpace\\guns";//模板输出的项目目录
    private String bizChName;   //业务名称
    private String bizEnName;   //业务英文名称
    private String bizEnBigName;//业务英文名称(大写)
    private String moduleName = "system";  //模块名称
    private String projectName ="";//项目名称

    private String proPackage = "com";
    private String entityName;              //实体的名称

    private Boolean controllerSwitch = true;    //是否生成控制器代码开关
    private Boolean indexPageSwitch = true;     //主页
    private Boolean addPageSwitch = true;       //添加页面
    private Boolean editPageSwitch = true;      //编辑页面
    private Boolean jsSwitch = true;            //js
    private Boolean infoJsSwitch = true;        //详情页面js
    private Boolean daoSwitch = true;           //dao
    private Boolean serviceSwitch = true;       //service
    private Boolean entitySwitch = true;        //生成实体的开关
    private Boolean sqlSwitch = false;           //生成sql的开关
    public void init() {
        if (entityName == null) {
            entityName = bizEnBigName;
        }
    }
    public void setBizEnName(String bizEnName) {
        this.bizEnName = bizEnName;
        this.bizEnBigName = ToolUtil.firstCharToUpperCase(this.bizEnName);
    }

 
}
