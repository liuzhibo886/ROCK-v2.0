package com.lzb.rock.base.facade;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;

/**
 * 
 * @author lzb
 *         <p>
 *         facade 基类
 *         </p>
 * @date 2019年7月17日 上午11:34:05
 */
public interface IFacade<T> {


	/**
	 * 查询list对象，默认所有参数均==，连接符为and ,null,空字符串不查询
	 * 
	 * @param data   对象
	 * @param limit  取多少条
	 * @param offset 偏移量
	 * @return Result<Page<T>>
	 */
	@PostMapping(value =  "/list")
	@ResponseBody
	public Result<Page<T>> list(T req, Integer limit, Integer offset);

	/**
	 * 查询list对象，默认所有参数均==,null,空字符串不作为查询条件
	 * 
	 * @param data   对象
	 * @param limit  取多少条
	 * @param offset 偏移量
	 * @return Result<List<T>>
	 */
	@PostMapping(value =  "/records")
	@ResponseBody
	public Result<List<T>> records(T req, Integer limit, Integer offset);

	/**
	 * 新增
	 * 
	 * @param data
	 * @return
	 */
	@PostMapping(value = "/add")
	@ResponseBody
	public Result<T> add(T req);

	/**
	 * 删除，根据主键和分库字段删除，分库字段可以为空
	 * 
	 * @param data
	 * @return
	 */
	@PostMapping(value ="/delete")
	@ResponseBody
	public Result<Integer> delete(T req);

	/**
	 * 修改，根据主键和分库字段修改，分库字段可以为空
	 * 
	 * @param data
	 * @return
	 */
	@PostMapping(value = "/update")
	@ResponseBody
	public Result<Integer> update(T req);

	/**
	 * 查询对象，根据主键ID 和分库字段查询
	 * 
	 * @param data
	 * @return
	 */
	@PostMapping(value =  "/detail")
	@ResponseBody
	public Result<T> detail(T req);
}
