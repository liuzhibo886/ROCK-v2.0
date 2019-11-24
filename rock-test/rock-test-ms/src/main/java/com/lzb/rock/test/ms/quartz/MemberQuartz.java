package com.lzb.rock.test.ms.quartz;


import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.lzb.rock.test.ms.myThread.MemberThread;

import lombok.extern.slf4j.Slf4j;
/**
 * 生成会员定时器
 *
 * @author Administrator
 *
 * @date 2019年11月12日 下午9:00:03
 */
@Component
@Async
@Slf4j
public class MemberQuartz implements ApplicationRunner {
	
	
	@Value("classpath:name.txt")
	private Resource areaRes;
	
	@Value("${MemberThread.count}")
	private Integer count;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.warn("会员生成线程数:{}",count);
		init();
		for (int i = 0; i < count; i++) {
			MemberThread command = new MemberThread();
			TestCommon.execute(command);
		}

	}

	//@PostConstruct
	public void init() throws Exception {
		String str = IOUtils.toString(areaRes.getInputStream(), Charsets.UTF_8);
		TestCommon.namesInit(str);
	}
}
