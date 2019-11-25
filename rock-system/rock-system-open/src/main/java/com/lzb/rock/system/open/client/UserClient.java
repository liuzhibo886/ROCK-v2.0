package com.lzb.rock.system.open.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import com.lzb.rock.base.config.FeignClientCfg;
import com.lzb.rock.system.open.facade.UserFacade;

/**
 * 管理员表 
 * 远程调用客户端
 * @author lzb
 * @Date 2019-09-15 17:19:56
 */
@FeignClient(name= UserFacade.SERVICE_NAME,configuration = FeignClientCfg.class)
public interface UserClient extends UserFacade{
}
