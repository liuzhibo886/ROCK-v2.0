package com.lzb.rock.system.open.facade;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.system.open.common.SystemCommon;
import com.lzb.rock.system.open.dto.authz.AuthzListReq;
import com.lzb.rock.system.open.model.Authz;

/**
 * 权限表 Facade
 * 
 * @author lzb
 * @Date 2019-10-31 21:25:57
 */
public interface AuthzFacade {

	static String SERVICE_NAME = SystemCommon.SERVICE_NAME;
	static String context = "/authz";

	/**
	 * 获取权限表列表
	 */
	@PostMapping(value = context + "/list")
	@ResponseBody
	public Result<Page<Authz>> list(@RequestBody AuthzListReq authzListReq);

	/**
	 * 获取权限表列表(无分页)
	 */
	@PostMapping(value = context + "/records")
	@ResponseBody
	public Result<List<Authz>> records(@RequestBody AuthzListReq authzListReq);

	/**
	 * 新增权限表
	 */
	@PostMapping(value = context + "/add")
	@ResponseBody
	public Result<Authz> add(@RequestBody Authz authz);

	/**
	 * 删除权限表
	 */
	@PostMapping(value = context + "/delete")
	@ResponseBody
	public Result<Void> delete(@RequestParam(name = "authzId") Long authzId,
			@RequestParam(name = "lastUser", required = false) String lastUser);

	/**
	 * 修改权限表
	 */
	@PostMapping(value = context + "/update")
	@ResponseBody
	public Result<Void> update(@RequestBody Authz authz);

	/**
	 * 权限表详情
	 */
	@PostMapping(value = context + "/detail")
	@ResponseBody
	public Result<Authz> detail(@RequestParam(name = "authzId") Long authzId);
}
