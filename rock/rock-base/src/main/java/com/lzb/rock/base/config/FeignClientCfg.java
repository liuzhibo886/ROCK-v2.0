package com.lzb.rock.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lzb.rock.base.exception.FeignClientErrorDecoder;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author lzb
 * @Date 2019年7月31日 下午4:57:50
 *       Feign配置类，可以自定义Feign的Encoder、Decoder、LogLevel、Contract
 */
@Configuration
@Slf4j
public class FeignClientCfg {
	Logger log = LoggerFactory.getLogger(FeignClientCfg.class);

	@Autowired
	private ObjectFactory<HttpMessageConverters> messageConverters;

	@Bean
	@ConditionalOnMissingBean
	public Decoder feignDecoder() {
		log.debug("feignDecoder=============>");
		return new ResponseEntityDecoder(new SpringDecoder(this.messageConverters));
	}

	@Bean
	@ConditionalOnMissingBean
	public Encoder feignEncoder() {
		log.debug("feignEncoder=============>");
		return new SpringEncoder(this.messageConverters);
	}

	/**
	 * 配置错误解析器
	 *
	 * @return the error decoder
	 */
	@Bean
	public ErrorDecoder configErrorDecoder() {
		log.debug("configErrorDecoder=============>");
		return new FeignClientErrorDecoder(new ResponseEntityDecoder(new SpringDecoder(this.messageConverters)));
	}

}
