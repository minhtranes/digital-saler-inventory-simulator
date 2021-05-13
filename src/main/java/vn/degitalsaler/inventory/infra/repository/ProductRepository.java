package vn.degitalsaler.inventory.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.degitalsaler.inventory.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}