package vn.degitalsaler.inventory.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.degitalsaler.inventory.entity.Product;
import vn.degitalsaler.inventory.infra.repository.ProductRepository;

@Service
public class ProductService{

	@Autowired
	private ProductRepository productRepository;

	public Page<Product> list(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	public long count() {
		return productRepository.count();
	}
}