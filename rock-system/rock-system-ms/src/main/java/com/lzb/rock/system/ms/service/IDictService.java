package com.lzb.rock.system.ms.service;

import java.util.List;

import com.lzb.rock.base.facade.IService;
import com.lzb.rock.system.open.model.Dict;

/**
 * <p>
 * 系统字典表(全局表) 服务类
 * </p>
 *
 * @author lzb123
 * @since 2019-09-27
 */

public interface IDictService extends IService<Dict> {

	/**
	 * 根据字典编码获取字典(有缓存)
	 * 
	 * @param dictCode
	 * @return
	 */
	public List<Dict> getCacheDictsByCode(String dictCode);

	/**
	 * 根据字典编码获取字典(强制刷新缓存)
	 * 
	 * @param dictCode
	 * @return
	 */
	public List<Dict> getCacheRestDictsByCode(String dictCode);

	/**
	 * 根据字典编码获取字典
	 * 
	 * @param dictCode
	 * @return
	 */
	public List<Dict> getDictsByCode(String dictCode);

	/**
	 * 刷新全部缓存
	 */
	public void ref();

	/**
	 * 获取全部字典
	 * 
	 * @return
	 */
	public List<Dict> selectAll();
}
