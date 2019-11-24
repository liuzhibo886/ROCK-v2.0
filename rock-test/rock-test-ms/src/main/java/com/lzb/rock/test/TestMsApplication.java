package com.lzb.rock.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

import com.lzb.rock.base.BaseApplication;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @Date 2019年7月31日 下午5:39:18
 */
@ComponentScan(basePackages = { "com.lzb" })
@EnableFeignClients(basePackages = { "com.lzb" })
@MapperScan("com.lzb.**.mapper.**")
@EnableAutoConfiguration
@EnableEurekaClient
@RestController
@EnableScheduling
@EnableSwagger2
@Slf4j
public class TestMsApplication extends BaseApplication {
	@Override
	public ApiInfo apiInfo() {// 创建API的基本信息，这些信息会在Swagger UI中进行显示
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		// 联系人
		Contact contact = new Contact("lzb", "", "");
		// 标题
		apiInfoBuilder.title("rock系统管理微服务");
		// 描述
		apiInfoBuilder.description("");
		// 联系人
		apiInfoBuilder.contact(contact);
		// 版本
		apiInfoBuilder.version("1.0.0");

		return apiInfoBuilder.build();
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(TestMsApplication.class).web(true).run(args);
		log.warn("==================启动成功==========================");
	}
}
