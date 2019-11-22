package com.lzb.rock.base.exception;


import com.lzb.rock.base.common.ResultEnum;

import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;

/**
 * 
 * @author admin
 * @Date 2019年10月22日 下午5:15:33 feign 调用返回错误信息
 */
public class FeignClientErrorDecoder implements ErrorDecoder {
	private Decoder decoder;

	public FeignClientErrorDecoder(Decoder decoder) {
		this.decoder = decoder;
	}

	@Override
	public Exception decode(String methodKey, Response response) {
		RockClientException ex = null;
		try {
			RockExceptionResponse ber = (RockExceptionResponse) decoder.decode(response, RockExceptionResponse.class);
			ex = new RockClientException(ResultEnum.REST_ERR, ber.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ex = new RockClientException(ResultEnum.SYSTTEM_ERR, "Service Unavailable");
		}

		return ex;
	}

}
