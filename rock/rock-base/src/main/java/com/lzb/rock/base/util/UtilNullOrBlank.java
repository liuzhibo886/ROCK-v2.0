package com.lzb.rock.base.util;
/**
 * 
 * @author lzb
 * @Date 2019年7月31日 下午4:58:12
 */
public class UtilNullOrBlank {

	public static boolean character(Object str){
		if(str != null && !str.toString().equals("")){
			return true ;
		}
		
		return false;
	}
}
