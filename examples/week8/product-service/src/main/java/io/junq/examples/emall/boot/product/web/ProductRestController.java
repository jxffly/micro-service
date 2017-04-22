package io.junq.examples.emall.boot.product.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.junq.examples.emall.boot.domain.Product;
import io.junq.examples.emall.boot.product.service.ProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 产品API入口
 * @author junqiangliu
 *
 */
@RestController("产品相关API")
@RequestMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
@EnableDiscoveryClient
@Configuration
public class ProductRestController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

//	@Autowired
//	private EurekaClient client;
	
	@Autowired
	private ProductService productService;

	@ApiOperation(value = "创建产品", notes = "")
	@ApiImplicitParams({
		
	})
	@ApiResponses({
		@ApiResponse(code = 201,message = "产品创建成功" ),
	})
	@RequestMapping(method = RequestMethod.POST, value = "/products", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public Product createProduct(@ApiParam(name = "product", value = "待创建的产品对象json实例", required = true) @RequestBody @Validated Product product) {
		LOGGER.debug("try to create product by product: " + product);
		return productService.createProduct(product);
	}
	
	@ApiOperation(value = "查询产品", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productId", paramType = "path", value = "产品id", required = true, dataType = "Long")
		})
	@RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
	public Product findProductById(@PathVariable Long productId) {
		LOGGER.debug("try to find product by id: " + productId);
		return productService.findProductById(productId);
	}
	
//	@ApiOperation(value = "查询依赖服务的信息", notes = "")
//	@RequestMapping(method = RequestMethod.GET, value = "/system/service-dependencies")
//	public Map<String, String> findDependencyServices() {
//		LOGGER.debug("try to find dependency services.");
//		
//		Map<String, String> servicesMap = new HashMap<String, String>();
//		InstanceInfo instance = client.getNextServerFromEureka("account-service", false);
//		if (instance != null)
//			servicesMap.put("account-service", instance.getHomePageUrl());
//		
//		return servicesMap;
//	}
}
