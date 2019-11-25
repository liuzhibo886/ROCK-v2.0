package com.lzb.rock.system.open.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import com.lzb.rock.base.config.FeignClientCfg;
import com.lzb.rock.base.config.FeignClientFallbackCfg;
import com.lzb.rock.system.open.facade.DictFacade;

/**
 * 系统字典表(全局表) 
 * 远程调用客户端
 * @author lzb
 * @Date 2019-09-27 14:15:30
 */
@FeignClient(name= DictFacade.SERVICE_NAME,configuration = FeignClientCfg.class,fallback=FeignClientFallbackCfg.class)
public interface DictClient extends DictFacade{
}
