package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	

	@Query("SELECT p FROM Product p WHERE p.name LIKE %:title%")
	public List<Product> findByProductNameLike(@Param("title") String likePattern);

}
