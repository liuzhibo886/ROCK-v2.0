package com.lzb.rock.test.ms.holder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.lzb.rock.mybaits.mybatisplus.BaseDataSourceConfig;
import com.lzb.rock.mybaits.mybatisplus.IDynamicDataSource;

@Component
public class MyDataSourceContextHolder implements IDynamicDataSource {

	Integer count = 0;
	Integer index = 0;
	List<Object> dataSourceNames = new ArrayList<Object>();

	@PostConstruct
	public void init() {
		HashMap<Object, Object> dataSource = BaseDataSourceConfig.dataSourceMap;
		for (Entry<Object, Object> entry : dataSource.entrySet()) {
			dataSourceNames.add(entry.getKey());
		}
		count = dataSourceNames.size();
	}

	@Override
	public Object determineCurrentLookupKey() {

		return getDateSource();
	}

	public synchronized Object getDateSource() {
		Object source = dataSourceNames.get(index);
		index++;
		if (count == index) {
			index = 0;
		}
		return source;
	}

}
