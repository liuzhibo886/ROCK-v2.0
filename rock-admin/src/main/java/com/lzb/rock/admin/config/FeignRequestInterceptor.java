/**
 * @author lzb
 *
 * 
 *2019年6月2日 上午4:50:23
 */
package com.lzb.rock.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.lzb.rock.base.properties.RockProperties;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author lzb
 * 
 *2019年6月2日 上午4:50:23
 */
/**
 * feign请求拦截器 所有用feign发出的请求的拦截器，注意是feign作为客户端发出请求的，而不是服务端
 */
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

	@Autowired
	RockProperties rockProperties;
	@Override
	public void apply(RequestTemplate template) {
		template.header("sysDataSourceKey", rockProperties.getSysDataSourceKey());
	}

}
