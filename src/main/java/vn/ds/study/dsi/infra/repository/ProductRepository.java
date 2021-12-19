package vn.ds.study.dsi.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ds.study.dsi.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}