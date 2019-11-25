package com.lzb.rock.system.ms.quartz;

import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lzb.rock.base.properties.RockProperties;
import com.lzb.rock.mybaits.mybatisplus.BaseDataSourceConfig;
import com.lzb.rock.mybaits.mybatisplus.DataSourceContextHolder;
import com.lzb.rock.system.ms.service.IDictService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DictRefQuartz implements ApplicationRunner {

	@Autowired
	IDictService dictService;

	@Autowired
	RockProperties rockProperties;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		quartz();
	}

	@Scheduled(cron = "${DictQuartz.schedule.cron:0 */5 * * * ?}")
	@Async
	public void quartz() {
		if (rockProperties.mutiDatasourceOnOff) {// 多数据源
			for (Entry<Object, Object> entry : BaseDataSourceConfig.dataSourceMap.entrySet()) {
				DataSourceContextHolder.setDataSourceKey(entry.getKey().toString());
				dictService.ref();
			}
		}
		DataSourceContextHolder.setDataSourceKey(null);
		dictService.ref();
	}

}
