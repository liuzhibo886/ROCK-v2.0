//package com.lzb.rock.system.admin.beetl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.lzb.rock.base.Result;
//import com.lzb.rock.base.aop.annotation.Beetl;
//import com.lzb.rock.system.open.client.DictClient;
//import com.lzb.rock.system.open.model.Dict;
//
///**
// * 字典常量
// * 
// * @author lzb
// *
// *         2019年1月3日 下午6:46:09
// */
//@Beetl(name = "dict")
//@Component
//public class DictBeetl {
//	@Autowired
//	DictClient dictClient;
//
//	/*
//	 * 查询当前级别以及以下的字典
//	 */
//	public String getDictByGeLevel(String dictCode, Integer dictLevel) {
//		StringBuffer sb = new StringBuffer();
//		Result<List<Dict>> rs = dictClient.getDictByGeLevel(dictCode, dictLevel);
//		if (rs.checkAndNotNull()) {
//			for (Dict dict : rs.getData()) {
//				sb.append(dict.getDictKey()).append(",").append(dict.getDictValue()).append(";");
//			}
//		}
//		return sb.toString();
//	}
//
//}
