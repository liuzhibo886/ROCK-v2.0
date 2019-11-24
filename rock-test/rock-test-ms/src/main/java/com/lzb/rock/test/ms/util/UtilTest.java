package com.lzb.rock.test.ms.util;

import java.math.BigDecimal;

import com.lzb.rock.test.ms.quartz.TestCommon;

public class UtilTest {

	/**
	 * 获取名字
	 * 
	 * @param maxLength
	 * @return
	 */
	public static String getName(Long length) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; i++) {
			sb.append(TestCommon.names[(int) (Math.random() * TestCommon.namesLength)]);
		}
		return sb.toString();
	}

	/**
	 * 获取指定单位内随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static Long getRandom(Long min, Long max) {
		Long num = -1L;
		while (num < min) {
			num = new BigDecimal(Math.random()).multiply(new BigDecimal(max)).longValue();
		}
		return num;

	}
}
