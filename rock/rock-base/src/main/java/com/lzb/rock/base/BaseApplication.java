package com.lzb.rock.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.lzb.rock.base.properties.RockProperties;

import io.swagger.annotations.Api;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 启动基类
 * 
 * @author lzb 2018年2月8日 上午11:41:55
 */
@Api(tags = { "默认接口" })
public abstract class BaseApplication extends BaseControllerExceptionAdvice {

	@Autowired
	RockProperties rockProperties;

	@GetMapping("/home")
	@ResponseBody
	public String home() {
		return "Hello world";
	}

	@GetMapping("/info")
	@ResponseBody
	public String info() {
		return "info";
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public RibbonRest initializationRibbonRest() {
		RibbonRest ribbonRest = new RibbonRest();
		return ribbonRest;
	}

	/**
	 * 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
	 * http://127.0.0.1:15027/swagger-ui.html
	 * https://blog.csdn.net/u010963948/article/details/72476854
	 * 
	 * @return
	 */
	@Bean
	public Docket createRestApi() {// 创建API基本信息
		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		docket.apiInfo(apiInfo());
		// 是否开启，false 关闭
		docket.enable(rockProperties.getSwaggerOnOff());

		ApiSelectorBuilder apiSelectorBuilder = docket.select();
		// 扫描路径
		apiSelectorBuilder.apis(apis());
		apiSelectorBuilder.paths(PathSelectors.any());

		return docket;
	}

	/**
	 * 设置扫描包路径
	 * 
	 * @return
	 */
	public Predicate<RequestHandler> apis() {
		Predicate<RequestHandler> selector = Predicates.or(RequestHandlerSelectors.basePackage("com.lzb"),
				RequestHandlerSelectors.basePackage("com.jiahong"));
		return selector;
	}

	/**
	 * 创建API的基本信息，这些信息会在Swagger UI中进行显示
	 */
	public ApiInfo apiInfo() {

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		// 联系人
		Contact contact = new Contact("联系人", "", "");
		// 标题
		apiInfoBuilder.title("API标题");
		// 描述
		apiInfoBuilder.description("API描述");
		// 联系人
		apiInfoBuilder.contact(contact);
		// 版本
		apiInfoBuilder.version("1.0.0");

		return apiInfoBuilder.build();
	}
}
