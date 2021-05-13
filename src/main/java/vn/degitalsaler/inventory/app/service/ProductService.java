package vn.degitalsaler.inventory.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.degitalsaler.inventory.entity.Product;
import vn.degitalsaler.inventory.infra.repository.ProductRepository;

@Service
public class ProductService {

	private static Logger LOGGER = LoggerFactory
	        .getLogger(ProductService.class);
	@Autowired
	private ProductRepository productRepository;

	public Page<Product> list(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public long count() {
		return productRepository.count();
	}

	public Product importProduct(Product product) {
		LOGGER.info("Adding product with id [{}]", product.getIdString());
		return productRepository.save(product);
	}
}