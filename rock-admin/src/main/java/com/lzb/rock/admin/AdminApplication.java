package com.lzb.rock.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.lzb.rock.base.BaseApplication;

import lombok.extern.slf4j.Slf4j;

@EnableAutoConfiguration
@EnableEurekaClient
@EnableScheduling
@ComponentScan(basePackages = { "com.lzb.rock" })
@EnableFeignClients(basePackages = { "com.lzb.rock" })
@Slf4j
public class AdminApplication extends BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
		System.out.println("==================启动成功==========================");
		log.info("==================启动成功==========================");
	}
}
