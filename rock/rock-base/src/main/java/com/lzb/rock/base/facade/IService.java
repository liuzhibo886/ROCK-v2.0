package com.lzb.rock.base.facade;
/**
 * Copyright (c) 2011-2016, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * <p>
 * 顶级service
 * </p>
 * 
 * @author lzb
 * @Date 2019年7月24日 下午2:26:00
 */
public interface IService<T> {

	/**
	 * <p>
	 * 插入一条记录（选择字段，策略插入）
	 * </p>
	 *
	 * @param entity 实体对象
	 * @return boolean
	 */
	Integer insert(T entity);

	/**
	 * <p>
	 * 插入一条记录（全部字段）
	 * </p>
	 *
	 * @param entity 实体对象
	 * @return boolean
	 */
	Integer insertAllColumn(T entity);

	/**
	 * <p>
	 * 根据 ID 删除
	 * </p>
	 *
	 * @param id 主键ID
	 * @return boolean
	 */
	Integer deleteById(Serializable id);

	/**
	 * <p>
	 * 根据 entity 条件，删除记录
	 * </p>
	 *
	 * @param wrapper 实体包装类 {@link Wrapper}
	 * @return boolean
	 */
	Integer delete(Wrapper<T> wrapper);

	/**
	 * <p>
	 * 根据 ID 选择修改
	 * </p>
	 *
	 * @param entity 实体对象
	 * @return boolean
	 */
	Integer updateById(T entity);

	/**
	 * <p>
	 * 根据 ID 修改全部字段
	 * </p>
	 *
	 * @param entity 实体对象
	 * @return boolean
	 */
	Integer updateAllColumnById(T entity);

	/**
	 * <p>
	 * 根据 whereEntity 条件，更新记录
	 * </p>
	 *
	 * @param entity  实体对象
	 * @param wrapper 实体包装类 {@link Wrapper}
	 * @return boolean
	 */
	Integer update(T entity, Wrapper<T> wrapper);

	/**
	 * <p>
	 * 根据 ID 查询
	 * </p>
	 *
	 * @param id 主键ID
	 * @return T
	 */
	T selectById(Serializable id);

	/**
	 * <p>
	 * 根据 Wrapper，查询一条记录
	 * </p>
	 *
	 * @param wrapper 实体对象
	 * @return T
	 */
	T selectOne(Wrapper<T> wrapper);

	/**
	 * <p>
	 * 根据 Wrapper 条件，查询总记录数
	 * </p>
	 *
	 * @param wrapper 实体对象
	 * @return int
	 */
	Integer selectCount(Wrapper<T> wrapper);

	/**
	 * <p>
	 * 查询列表
	 * </p>
	 *
	 * @param wrapper 实体包装类 {@link Wrapper}
	 * @return
	 */
	List<T> selectList(Wrapper<T> wrapper);

	/**
	 * <p>
	 * 翻页查询
	 * </p>
	 *
	 * @param page 翻页对象
	 * @return
	 */
	Page<T> selectPage(Page<T> page);

	/**
	 * <p>
	 * 翻页查询
	 * </p>
	 *
	 * @param page    翻页对象
	 * @param wrapper 实体包装类 {@link Wrapper}
	 * @return
	 */
	Page<T> selectPage(Page<T> page, Wrapper<T> wrapper);

}
