package io.junq.examples.emall.boot.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.junq.examples.emall.boot.product.domain.Product;
import io.junq.examples.emall.boot.product.domain.ProductRepository;

/**
 * 产品服务实现
 * @author junqiangliu
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	
    @Autowired
    private ProductRepository productRepo;
    
	@Override
	public Product createProduct(Product product) {
		Product.Builder builder = Product.Builder.newBuilder();
		builder.withType(product.getType());
		builder.withName(product.getName());
		builder.withSummary(product.getSummary());
		builder.withDescription(product.getDescription());
		builder.withPrice(product.getPrice());
		builder.withImages(product.getImages());
		
		Product newProduct = builder.build();
		long newProductId = productRepo.createProduct(newProduct);
		LOGGER.debug("new product created with id: " + newProductId);
		return findProductById(newProductId);
	}

	@Override
	public Product findProductById(long id) {
		return productRepo.findProductById(id);
	}

}
