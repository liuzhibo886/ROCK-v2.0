package com.lzb.rock.system.ms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import com.lzb.rock.base.util.UtilHttpKit;
import com.lzb.rock.base.util.UtilString;
import com.lzb.rock.mybaits.mybatisplus.DataSourceContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * 多数据源切换过滤器
 * 
 * @author lzb
 *
 */
@Component("sysDataSourceFilter")
@ServletComponentScan
@WebFilter(urlPatterns = "/*", filterName = "sysDataSourceFilter")
@Slf4j
public class SysDataSourceFilter implements Filter {

	FilterConfig filterConfig = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String dataSourceKey = UtilHttpKit.getHeader("sysDataSourceKey");
		log.debug("dataSourceKey:{}", dataSourceKey);
		if (UtilString.isNotBlank(dataSourceKey)) {
			DataSourceContextHolder.setDataSourceKey(dataSourceKey);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		this.filterConfig = null;
		DataSourceContextHolder.clearDataSourceKey();
	}

}
