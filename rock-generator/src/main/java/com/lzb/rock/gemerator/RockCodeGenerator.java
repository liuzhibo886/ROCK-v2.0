package com.lzb.rock.gemerator;

import java.util.List;

import com.lzb.rock.gemerator.config.DbConfig;
import com.lzb.rock.gemerator.config.WebGeneratorConfig;
import com.lzb.rock.gemerator.model.GenQo;
import com.lzb.rock.gemerator.util.UtilDb;

/**
 * 代码生成器启动类
 * 
 * @author lzb
 *
 *         2019年3月16日 上午9:23:54
 */
public class RockCodeGenerator {
	static String dbName = "rock-system2";
	static String url = "jdbc:mysql://mysql.liuzhibo.top:10144/" + dbName + "?useUnicode=true&characterEncoding=utf-8";

	static String userName = "root";
	static String password = "blueSKY521@";
	static String prefix = "sys_";

	public static void start() throws Exception {
		DbConfig dbConfig = new DbConfig(dbName, url, userName, password, prefix);

		List<String> tableNames = UtilDb.getTableNames(dbConfig);
		// ================
		GenQo genQo = new GenQo("subTreasury.properties");
		genQo.setUrl(url);
		genQo.setPassword(password);
		genQo.setUserName(userName);
		// 项目的包根路径
		genQo.setProjectPackage("com.lzb.rock.system");
		// 输出路径
		genQo.setProjectPath("D:/gunsDB2");
		// 作者
		genQo.setAuthor("lzb");
		// 截取表前缀
		genQo.setIgnoreTabelPrefix(prefix);
		// 项目名称
		genQo.setProjectName("rock");
		// 模块名称
		genQo.setModuleName("sys");
		for (String tableName : tableNames) {
			genQo.setTableName(tableName);
			WebGeneratorConfig webGeneratorConfig = new WebGeneratorConfig(genQo);
			webGeneratorConfig.doMpGeneration();
			webGeneratorConfig.doRockGeneration();
		}
	}

	public static void main(String[] args) throws Exception {
		RockCodeGenerator.start();
	}
}
