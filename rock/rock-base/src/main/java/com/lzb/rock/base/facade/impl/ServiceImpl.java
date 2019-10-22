package com.lzb.rock.base.facade.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.common.ResultEnum;
import com.lzb.rock.base.exception.RockException;
import com.lzb.rock.base.facade.IService;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author lzb
 * @Date 2019年7月31日 下午4:58:05
 */
@Slf4j
public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {
	@Autowired
	protected M baseMapper;

	@Override
	public Integer insert(T entity) {
		return baseMapper.insert(entity);
	}

	@Override
	public Integer insertAllColumn(T entity) {
		return baseMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		return baseMapper.deleteById(id);
	}

	@Override
	public Integer delete(Wrapper<T> wrapper) {
		return baseMapper.delete(wrapper);
	}

	@Override
	public Integer updateById(T entity) {
		return baseMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(T entity) {
		return baseMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(T entity, Wrapper<T> wrapper) {
		return baseMapper.update(entity, wrapper);
	}

	@Override
	public T selectById(Serializable id) {
		return baseMapper.selectById(id);
	}

	@Override
	public T selectOne(Wrapper<T> wrapper) {
		List<T> list = baseMapper.selectList(wrapper);

		if (list == null) {
			return null;
		}
		Integer count = list.size();
		if (count < 1) {
			return null;
		} else if (count == 1) {
			return list.get(0);
		} else {
			throw new RockException(ResultEnum.SELECT_ERR, "预期1条数据,实际查询：" + count + "条数据");
		}
	}

	@Override
	public Integer selectCount(Wrapper<T> wrapper) {
		return baseMapper.selectCount(wrapper);
	}

	@Override
	public List<T> selectList(Wrapper<T> wrapper) {
		return baseMapper.selectList(wrapper);
	}

	@Override
	public Page<T> selectPage(Page<T> page) {
		return selectPage(page, Condition.EMPTY);
	}

	@Override
	public Page<T> selectPage(Page<T> page, Wrapper<T> wrapper) {
		SqlHelper.fillWrapper(page, wrapper);
		page.setRecords(baseMapper.selectPage(page, wrapper));
		return page;
	}

}
