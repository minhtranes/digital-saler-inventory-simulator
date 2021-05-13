package vn.degitalsaler.inventory.present.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.degitalsaler.inventory.app.service.ProductService;
import vn.degitalsaler.inventory.entity.Product;

@RestController
@RequestMapping("inventory")
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
	
	@PostMapping("/add")
	public Product addProduct(Product product) {
		return null;
	}
}
