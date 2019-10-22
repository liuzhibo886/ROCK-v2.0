package com.lzb.rock.base.filter;

import java.io.IOException;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
/**
 * 
 * @author lzb
 * @Date 2019年7月31日 下午4:58:12
 */
public class MyFilterType implements TypeFilter {

	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {

		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

		// 获取当前类注解的信息
		for (String str : annotationMetadata.getAnnotationTypes()) {
			if ("org.springframework.cloud.netflix.feign.FeignClient".equals(str)) {
				return true;
			}
		}
//		// 获取当前正在扫描类的信息
//		ClassMetadata classMetadata = metadataReader.getClassMetadata();
//		System.out.println("当前正在被扫描的类的类名" + classMetadata.getClassName());
//		// 获取当前类的资源信息（类存放的路径...）
//		Resource resource = metadataReader.getResource();
//		System.out.println("当前正在被扫描的类存放的地址" + resource.getURL());
		return false;
	}
}
