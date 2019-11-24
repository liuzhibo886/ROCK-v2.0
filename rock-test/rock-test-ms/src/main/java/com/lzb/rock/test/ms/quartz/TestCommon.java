package com.lzb.rock.test.ms.quartz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;

public class TestCommon {
	private static ExecutorService fixedThread = Executors.newFixedThreadPool(1000);
	/**
	 * 商品最大坐标
	 */
	public static Long GOODS_MAX_INDEX = 0L;
	/**
	 * 会员最大坐标
	 */
	public static Long MEMBER_MAX_INDEX = 0L;
	/**
	 * 中文数组
	 */
	public static String[] names = null;
	/**
	 * 中文数组长度
	 */
	public static Integer namesLength = 0;

	public static void execute(Runnable command) {
		fixedThread.execute(command);
	}

	public void destroy() throws Exception {
		fixedThread.shutdown();
	}

	public static void namesInit(String str) {
		if (StringUtils.isNotBlank(str)) {
			TestCommon.names = str.split("");
			TestCommon.namesLength = names.length;
		}
	}

}
