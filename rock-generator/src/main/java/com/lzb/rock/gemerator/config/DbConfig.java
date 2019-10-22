package com.lzb.rock.gemerator.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lzb
 *
 *         2019年3月16日 上午9:40:49
 */
@Data
@AllArgsConstructor
public class DbConfig {
	/**
	 * 数据库名称
	 */
	private String dbName = "test";
	/**
	 * url
	 */
	private String url = "jdbc:mysql://127.0.0.1:3306/" + dbName + "?useUnicode=true&characterEncoding=utf-8";
	/**
	 * 用户名
	 */
	private String userName = "root";
	/**
	 * 数据库密码
	 */
	private String password = "a123";
	/**
	 * 截取前缀
	 */
	private String prefix = "zhh_";
	
}
