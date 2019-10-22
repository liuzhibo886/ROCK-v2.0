package com.lzb.rock.gemerator.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * facade控制器模板生成的配置
 * @author lzb
 *
 * 2019年3月18日 下午2:41:40
 */
@Data
public class DtoRespControllerConfig {

    private ContextConfig contextConfig;

    private String facadeControllerPath;
    private String packageName;//包名称
    private List<String> imports;//所引入的包

    public void init() {
        ArrayList<String> imports = new ArrayList<String>();
        imports.add("lombok.Data");
        imports.add(contextConfig.getProPackage() + ".open.model."+contextConfig.getEntityName());
        this.imports = imports;
        this.packageName = contextConfig.getProPackage() + ".open.dto."+contextConfig.getBizEnName();
        this.facadeControllerPath = "\\src\\main\\java\\"+contextConfig.getProPackage().replaceAll("\\.","\\\\") + "\\open\\Facade\\{}Facade.java";
    }


}
