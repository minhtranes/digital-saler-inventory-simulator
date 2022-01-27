package vn.ds.study.dsi.present.controller;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
import vn.ds.study.dsi.app.service.ProductService;
import vn.ds.study.dsi.entity.Product;

@RestController
@RequestMapping("inventory")
@Slf4j
public class InventoryController {

	@Autowired
	private ProductService productService;

	@GetMapping("/list")
	public Page<Product> list(
		@RequestParam(name = "delayBeforeProcessInSecond", required = false, defaultValue = "0") int delayBeforeProcessInSecond,
		,Pageable page) {

	    log.info("List all product of page: {}, size: {}, delayBeforeProcessInSecond: {}", page.getPageNumber(), page.getPageSize(), delayBeforeProcessInSecond);

		if( delayBeforeProcessInSecond > 0){
			try {
				TimeUnit.SECONDS.sleep(delayBeforeProcessInSecond);
			}
			catch (InterruptedException e) {
			}
		}
	    
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

		return productService.saveProduct(product);
	}
	
	@GetMapping("/get/{id}")
	public Product get(@PathVariable(name = "id") Integer productId) {
		return productService.get(productId);
	}
}
