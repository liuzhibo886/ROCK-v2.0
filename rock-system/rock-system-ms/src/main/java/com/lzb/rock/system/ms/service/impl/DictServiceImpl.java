package com.lzb.rock.system.ms.service.impl;

import com.lzb.rock.system.open.model.Dict;
import com.lzb.rock.system.ms.mapper.DictMapper;
import com.lzb.rock.system.ms.service.IDictService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lzb.rock.base.facade.impl.ServiceImpl;
import com.lzb.rock.ehcache.aop.annotation.EhcacheGet;

/**
 * <p>
 * 系统字典表(全局表) 服务实现类
 * </p>
 *
 * @author lzb123
 * @since 2019-09-27
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

	@Autowired
	IDictService dictService;

	/**
	 * 根据字典编码获取字典(有缓存)
	 * 
	 * @param dictCode
	 * @return
	 */
	@Override
	@EhcacheGet(constant = "DICT", cacheName = "DICT")
	public List<Dict> getCacheDictsByCode(String dictCode) {
		return dictService.getDictsByCode(dictCode);
	}

	@Override
	@EhcacheGet(constant = "DICT", cacheName = "DICT", rest = true)
	public List<Dict> getCacheRestDictsByCode(String dictCode) {
		return dictService.getDictsByCode(dictCode);
	}

	@Override
	public List<Dict> getDictsByCode(String dictCode) {
		Integer limit = 1000;
		Long dictIdMax = -1L;
		List<Dict> rows = new ArrayList<Dict>();
		while (true) {
			Wrapper<Dict> wrapper = Condition.create();
			wrapper.eq("dict_code", dictCode);
			wrapper.eq("is_del", 0);
			wrapper.gt("dict_id", dictIdMax);
			wrapper.last(" limit " + limit);
			wrapper.orderBy("dict_id", true);
			List<Dict> list = this.baseMapper.selectList(wrapper);
			if (list != null) {
				rows.addAll(list);
			}
			if (list == null || list.size() < limit) {
				break;
			} else {
				dictIdMax = list.get(list.size() - 1).getDictId();
			}
		}
		Collections.sort(rows);
		return rows;
	}

	@Override
	public void ref() {
		// 查询所有code
		Wrapper<Dict> wrapper = Condition.create();
		wrapper.setSqlSelect("dict_code AS dictCode");
		wrapper.eq("is_del", 0);
		wrapper.last(" limit 9999");
		List<Dict> list = this.baseMapper.selectList(wrapper);
		for (Dict dict : list) {
			dictService.getCacheRestDictsByCode(dict.getDictCode());
		}
	}

	@Override
	public List<Dict> selectAll() {
		Integer limit = 1000;
		Long dictIdMax = -1L;
		List<Dict> rows = new ArrayList<Dict>();
		while (true) {
			Wrapper<Dict> wrapper = Condition.create();
			wrapper.eq("is_del", 0);
			wrapper.gt("dict_id", dictIdMax);
			wrapper.last(" limit " + limit);
			wrapper.orderBy("dict_id", true);
			List<Dict> list = this.baseMapper.selectList(wrapper);
			if (list != null) {
				rows.addAll(list);
			}
			if (list == null || list.size() < limit) {
				break;
			} else {
				dictIdMax = list.get(list.size() - 1).getDictId();
			}
		}
		return rows;
	}

}
