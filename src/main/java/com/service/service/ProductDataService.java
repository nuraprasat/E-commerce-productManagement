package com.service.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.exception.ProductDoesNotExistsException;
import com.service.model.ErrorModel;
import com.service.model.Product;
import com.service.repository.CategoryRepository;
import com.service.repository.MerchantRepository;
import com.service.repository.ProductRepository;

@Service
public class ProductDataService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	MerchantRepository merchantRepository;
	
	Predicate<Optional<Product>> isCategory = p -> p.get().getCategory() != null;
	Predicate<Optional<Product>> isMerchant = p -> p.get().getMerchant() != null;

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Product getByProductName(String productName) {
		Optional<Product> optProduct = productRepository.findByProductName(productName);
		if(optProduct.isPresent())
			return optProduct.get();
		return null;
	}

	public ResponseEntity<Object> updateUser(Product product, Integer id) throws ProductDoesNotExistsException {
		Optional<Product> userOptional = productRepository.findById(id);
		if (!userOptional.isPresent()) {
			ErrorModel em = new ErrorModel(HttpStatus.NOT_FOUND.value(), "product id-"+id+" not found");
			throw new ProductDoesNotExistsException(em);
		}
		
		product.setProductId(userOptional.get().getProductId());
		productRepository.save(product);
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Object> updateUser(Product product, String productName) throws ProductDoesNotExistsException {
		Optional<Product> userOptional = productRepository.findByProductName(productName);
		if (!userOptional.isPresent()) {
			ErrorModel em = new ErrorModel(HttpStatus.NOT_FOUND.value(), "product name - "+productName+" not found");
			throw new ProductDoesNotExistsException(em);
		}
		
		product.setProductId(userOptional.get().getProductId());
		productRepository.save(product);
		
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Object> deleteProduct(Integer id) throws ProductDoesNotExistsException {
		Optional<Product> userOptional = productRepository.findById(id);
		int categoryCount = 0;
		int merchantCount = 0;
		if (!userOptional.isPresent()) {
			ErrorModel em = new ErrorModel(HttpStatus.NOT_FOUND.value(), "product id-"+id+" not found");
			throw new ProductDoesNotExistsException(em);
		}
		
		if(isCategory.test(userOptional))
			categoryCount = productRepository.getCategoryCount(userOptional.get().getCategory().getCategoryId());
		if(isMerchant.test(userOptional))
			merchantCount = productRepository.getMerchantCount(userOptional.get().getMerchant().getMerchantId());
		productRepository.deleteProduct(id);
		if(categoryCount == 1) {
			categoryRepository.deleteCategory(userOptional.get().getCategory().getCategoryId());
		}
		if(merchantCount == 1) {
			merchantRepository.deleteMerchant(userOptional.get().getMerchant().getMerchantId());
		}
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Object> deleteByProductName(String productName) throws ProductDoesNotExistsException {
		Optional<Product> userOptional = productRepository.findByProductName(productName);
		int categoryCount = 0;
		int merchantCount = 0;
		if (!userOptional.isPresent()) {
			ErrorModel em = new ErrorModel(HttpStatus.NOT_FOUND.value(), "product name - "+productName+" not found");
			throw new ProductDoesNotExistsException(em);
		}
		
		if(isCategory.test(userOptional))
			categoryCount = productRepository.getCategoryCount(userOptional.get().getCategory().getCategoryId());
		if(isMerchant.test(userOptional))
			merchantCount = productRepository.getMerchantCount(userOptional.get().getMerchant().getMerchantId());
		productRepository.deleteProduct(userOptional.get().getProductId());
		if(categoryCount == 1) {
			categoryRepository.deleteCategory(userOptional.get().getCategory().getCategoryId());
		}
		if(merchantCount == 1) {
			merchantRepository.deleteMerchant(userOptional.get().getMerchant().getMerchantId());
		}
		
		return ResponseEntity.ok().build();
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
}
