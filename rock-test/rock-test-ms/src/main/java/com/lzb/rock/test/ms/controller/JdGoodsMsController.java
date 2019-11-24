package com.lzb.rock.test.ms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.base.common.ResultEnum;
import com.lzb.rock.base.aop.annotation.Log;
import com.lzb.rock.base.factory.PageFactory;
import com.lzb.rock.base.util.UtilString;
import com.lzb.rock.test.ms.service.IJdGoodsService;
import com.lzb.rock.test.open.dto.jdGoods.JdGoodsListReq;
import com.lzb.rock.test.open.facade.JdGoodsFacade;
import com.lzb.rock.test.open.model.JdGoods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 京东商品信息表控制器
 *
 * @author lzb
 * @Date 2019-11-12 20:52:13
 */
@RestController
@Api(tags={"京东商品信息表控制器"})
public class JdGoodsMsController implements JdGoodsFacade{

	@Autowired
    IJdGoodsService jdGoodsService;
    /**
	* 获取京东商品信息表列表
     */
   	@PostMapping(value = context+"/list")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取京东商品信息表列表")
    public Result<Page<JdGoods>> list(@RequestBody JdGoodsListReq jdGoodsListReq){
    	Page<JdGoods> page = new PageFactory<JdGoods>().defaultPage(jdGoodsListReq);
    	Wrapper<JdGoods> wrapper = Condition.create();
		Page<JdGoods> rs = jdGoodsService.selectPage(page,wrapper);
		return  new Result<Page<JdGoods>>(rs);
    }
        /**
	* 获取京东商品信息表列表(无分页)
     */
   	@PostMapping(value = context+"/records")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取京东商品信息表列表")
    public Result<List<JdGoods>> records(@RequestBody JdGoodsListReq jdGoodsListReq){
    	Wrapper<JdGoods> wrapper = Condition.create();
 		wrapper.last("limit "+jdGoodsListReq.getOffset()+","+jdGoodsListReq.getLimit());
		List<JdGoods> rows = jdGoodsService.selectList(wrapper);
		return  new Result<List<JdGoods>>(rows);
    }
    /**
	* 新增京东商品信息表
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    @Log(before=true,end=true,name="新增")
    @ApiOperation(value = "新增京东商品信息表")
    public Result<JdGoods> add(@RequestBody JdGoods jdGoods){
   		  jdGoods.setIsDel(0);
          jdGoodsService.insert(jdGoods);
        return new Result<JdGoods>(jdGoods);
    }
    /**
	 * 删除京东商品信息表
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    @ApiOperation(value = "删除京东商品信息表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "jdGoodsId", value = "自增主键"),
		@ApiImplicitParam(name = "jdGoodsQriginalId", value = "商品原始ID，分库字段"),
		@ApiImplicitParam(name = "lastUser", value = "最后修改人") })
    public Result<Void> delete(@RequestParam(name="jdGoodsId") Long jdGoodsId,@RequestParam(name="jdGoodsQriginalId",required=false) String jdGoodsQriginalId,@RequestParam(name="lastUser",required=false)String lastUser) {
   		 Wrapper<JdGoods> wrapper=Condition.create();
    	JdGoods entity = new JdGoods();
		entity.setIsDel(1);
		entity.setLastUser(lastUser);
		wrapper.eq("jd_goods_id", jdGoodsId);
   		if( UtilString.isNotBlank(jdGoodsQriginalId)){
  		 wrapper.eq("jd_goods_qriginal_id", jdGoodsQriginalId);
   		}
		Integer count = jdGoodsService.update(entity,wrapper);
       	if(count > 0) {
			return new Result<Void>();
		}else {
			return new Result<Void>(ResultEnum.DELETE_ERR);
		}
    }
    /**
	* 修改京东商品信息表
     */
    @PostMapping(value = context+"/update")
    @ResponseBody
    @Log(before=true,end=true,name="修改")
    @ApiOperation(value = "修改京东商品信息表")
    public Result<Void> update(@RequestBody JdGoods jdGoods) {
   		Wrapper<JdGoods> wrapper=Condition.create();
   		wrapper.eq("jd_goods_id", jdGoods.getJdGoodsId());
   		if( UtilString.isNotBlank(jdGoods.getJdGoodsQriginalId())){
  		 wrapper.eq("jd_goods_qriginal_id", jdGoods.getJdGoodsQriginalId());
   		}
   		jdGoods.setJdGoodsId(null);
   		jdGoods.setJdGoodsQriginalId(null);
      	Integer count=jdGoodsService.update(jdGoods,wrapper);
        if(count > 0) {
			return new Result<Void>();
		}else {
			return new Result<Void>(ResultEnum.UPDATE_ERR);
		}
    }
    /**
     * 京东商品信息表详情
     */
    @PostMapping(value = context+"/detail")
    @ResponseBody
    @Log(before=true,end=true,name="详情")
    @ApiOperation(value = "京东商品信息表详情,根据jdGoodsId查询")
   	@ApiImplicitParams({ @ApiImplicitParam(name = "jdGoodsId", value = "自增主键"),
	@ApiImplicitParam(name = "jdGoodsQriginalId", value = "商品原始ID，分库字段")
	 })
    public Result<JdGoods> detail(@RequestParam(name="jdGoodsId") Long jdGoodsId,@RequestParam(name="jdGoodsQriginalId",required=false) String jdGoodsQriginalId) {
    	Wrapper<JdGoods> wrapper=Condition.create();
		wrapper.eq("jd_goods_id", jdGoodsId);
   		if( UtilString.isNotBlank(jdGoodsQriginalId)){
  		 wrapper.eq("jd_goods_qriginal_id", jdGoodsQriginalId);
   		}
     	JdGoods jdGoods=jdGoodsService.selectOne(wrapper);
     	
        return new Result<JdGoods>(jdGoods);
    }
}
