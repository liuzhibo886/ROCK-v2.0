package com.lzb.rock.system.open.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import com.lzb.rock.base.config.FeignClientCfg;
import com.lzb.rock.system.open.facade.AuthzFacade;

/**
 * 权限表 
 * 远程调用客户端
 * @author lzb
 * @Date 2019-10-31 21:25:57
 */
@FeignClient(name= AuthzFacade.SERVICE_NAME,configuration = FeignClientCfg.class)
public interface AuthzClient extends AuthzFacade{
}
