package vn.degitalsaler.inventory.present.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vn.degitalsaler.inventory.app.service.ProductService;
import vn.degitalsaler.inventory.entity.Product;

@RestController
@RequestMapping("inventory")
@Slf4j
public class InventoryController {

	@Autowired
	private ProductService productService;

	@GetMapping("/list")
	public Page<Product> list(Pageable page) {
		return productService.list(page);
	}

	@GetMapping("/count")
	public long count() {
		return productService.count();
	}

	@PostMapping("/save")
	public @ResponseBody Product addProduct(@RequestBody Product product) {
		log.info("Request to save product");

		if (product == null) {
			return null;
		}
		if (product.getId() == null || product.getId().intValue() <= 0) {
			log.info("Generate random ID String for product");
			product.setIdString(UUID.randomUUID().toString());
		}

		return productService.importProduct(product);
	}
	
	@GetMapping("/get/{id}")
	public Product get(@PathVariable(name = "id") Integer productId) {
		return productService.get(productId);
	}
}
