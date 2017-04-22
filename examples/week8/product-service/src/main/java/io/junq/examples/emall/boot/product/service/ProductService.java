package io.junq.examples.emall.boot.product.service;

import io.junq.examples.emall.boot.domain.Product;

/**
 * 产品服务接口
 * 
 * @author junqiangliu
 *
 */
public interface ProductService {
	/**
	 * 创建产品
	 * @param product 待创建的产品对象实例
	 * @return 创建完毕后返回用户对象 {@link Product}
	 */
	Product createProduct(final Product product);
	
	/**
	 * 通过产品id查询产品信息
	 * @param id 产品id
	 * @return {@link Product}对象
	 */
	Product findProductById(final long id);
}
