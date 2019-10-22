package com.lzb.rock.gemerator.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Service模板生成的配置
 * @author lzb
 *
 * 2019年3月18日 下午2:59:16
 */
@Data
public class ServiceConfig {

    private ContextConfig contextConfig;

    private String packageName;

    private List<String> imports;// 所引入的包

    public void init() {
        ArrayList<String> imports = new ArrayList<String>();
        imports.add("com.lzb.rock.base.facade.IService");
        this.imports = imports;
    }



}
