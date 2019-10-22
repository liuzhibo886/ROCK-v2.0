package com.lzb.rock.base.factory;


import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.model.PageReq;
/**
 * BootStrap Table
 * 
 * @author lzb
 *
 * @param <T> 2019年4月1日 下午8:00:51
 */
public class PageFactory<T> {

	public Page<T> defaultPage(PageReq req) {
		Page<T> page = new Page<>(req.getPage(), req.getLimit());
		page.setOpenSort(false);
		return page;
	}
	/**
	 * 
	 * @param pageNum 页码
	 * @param limit   每页多少条
	 * @return
	 */
	public Page<T> defaultPage(Integer pageNum, Integer limit) {
		Page<T> page = new Page<>(pageNum, limit);
		page.setOpenSort(false);
		return page;
	}
}
