package com.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.service.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	public List<Product> findAll();
	public Optional<Product> findById(Integer id);
	
	public Optional<Product> findByProductName(String productName);
	
	public void deleteByProductName(String productName);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM product u WHERE u.product_id = ?1", nativeQuery = true)
	public void deleteProduct(Integer productId);
	
	@Query(value = "select count(*) FROM product u WHERE u.category_id = ?1", nativeQuery = true)
	public int getCategoryCount(Integer categoryId);
	
	@Query(value = "select count(*) FROM product u WHERE u.merchant_id = ?1", nativeQuery = true)
	public int getMerchantCount(Integer merchantId);
	
}
