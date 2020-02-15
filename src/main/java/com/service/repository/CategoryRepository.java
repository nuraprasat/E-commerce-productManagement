package com.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.service.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM category u WHERE u.category_id = ?1", nativeQuery = true)
	public void deleteCategory(Integer categoryId);
}
