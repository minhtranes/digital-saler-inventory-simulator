package vn.degitalsaler.inventory.present.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.degitalsaler.inventory.app.service.ProductService;
import vn.degitalsaler.inventory.entity.Product;

@RestController
@RequestMapping("inventory")
public class InventoryController {

	private static Logger LOGGER = LoggerFactory
	        .getLogger(InventoryController.class);

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

	@PostMapping("/add")
	public @ResponseBody Product addProduct(@RequestBody Product product) {
		LOGGER.info("Request to add new product");

		if (product == null) {
			return null;
		}
		LOGGER.info("Generate random ID String for product and clean up id");
		product.setId(null);
		product.setIdString(UUID.randomUUID().toString());
		
		return productService.importProduct(product);
	}
}
