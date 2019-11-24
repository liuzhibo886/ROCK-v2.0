package com.lzb.rock.test.open.facade;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.test.open.dto.jdUrl.JdUrlListReq;
import com.lzb.rock.test.open.model.JdUrl;

/**
 * 京东url列表
 * Facade
 * @author lzb
 * @Date 2019-11-12 20:47:10
 */
public interface JdUrlFacade{

    static String SERVICE_NAME="rock-test-ms";
    static String context="/jdUrl";
    /**
   	  * 获取京东url列表列表
     */
    @PostMapping(value = context+"/list")
    @ResponseBody
    public Result<Page<JdUrl>> list(@RequestBody JdUrlListReq jdUrlListReq);
     /**
   	  * 获取京东url列表列表(无分页)
     */
    @PostMapping(value = context+"/records")
    @ResponseBody
    public Result<List<JdUrl>> records(@RequestBody JdUrlListReq jdUrlListReq);
 
    /**
	* 新增京东url列表
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    public Result<JdUrl> add(@RequestBody JdUrl jdUrl);

    /**
      * 删除京东url列表
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    public Result<Void> delete(@RequestParam(name="jdUrlId") Long jdUrlId,@RequestParam(name="lastUser",required=false)String lastUser);
    /**
     * 修改京东url列表
     */
    @PostMapping(value = context+"/update")
    @ResponseBody
    public Result<Void> update(@RequestBody JdUrl jdUrl);

    /**
     * 京东url列表详情
     */
    @PostMapping(value = context+"/detail")
    @ResponseBody
    public Result<JdUrl> detail(@RequestParam(name="jdUrlId") Long jdUrlId);
}
