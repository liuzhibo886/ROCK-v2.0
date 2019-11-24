package com.lzb.rock.test.open.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import com.lzb.rock.base.config.FeignClientCfg;
import com.lzb.rock.test.open.facade.MemberFacade;

/**
 *  
 * 远程调用客户端
 * @author lzb
 * @Date 2019-11-12 20:38:12
 */
@FeignClient(name= MemberFacade.SERVICE_NAME,configuration = FeignClientCfg.class)
public interface MemberClient extends MemberFacade{
}
