package com.lzb.rock.gemerator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lzb.rock.gemerator.config.DbConfig;
/**
 * 数据库连接类
 * @author lzb
 *
 * 2019年3月16日 上午9:37:15
 */
public class UtilDb {

	/**
	 * 获取数据库所有表
	 * @return
	 * @throws Exception
	 */
	public static List<String> getTableNames(DbConfig dbConfig) throws Exception {
		// 获取所有表
		List<String> tableNames = new ArrayList<String>();
		Connection com = UtilDb.getConnection(dbConfig.getUrl(), dbConfig.getUserName(), dbConfig.getPassword());
		Statement stmt = com.createStatement();
		String sql = "select table_name from information_schema.tables where table_schema='" + dbConfig.getDbName()
				+ "' and table_type='base table'";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String tableName2 = rs.getString(1);
			tableNames.add(tableName2);
		}
		stmt.close();
		com.close();
		return tableNames;
	}
	/**
	 * 获取连接
	 * @param url
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(String url, String userName, String password) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connnection = DriverManager.getConnection(url, userName, password);
		return connnection;
	}
	
	
}
