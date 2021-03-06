package vn.ds.study.dsi.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import vn.ds.study.dsi.entity.Product;
import vn.ds.study.dsi.infra.repository.ProductRepository;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> list(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public long count() {
        return productRepository.count();
    }

    public Product saveProduct(Product product) {
        log.info("Save product with id [{}]", product.getIdString());
        return productRepository.save(product);
    }

    public Product get(Integer productId) {
        if (productId == null) {
            return null;
        }
        Optional<Product> findById = productRepository.findById(productId);
        return findById.isPresent() ? findById.get() : null;
    }
}