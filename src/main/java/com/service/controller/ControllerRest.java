package com.service.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.exception.ProductDoesNotExistsException;
import com.service.exception.InternalServerError;
import com.service.model.ErrorModel;
import com.service.model.Product;
import com.service.service.ProductDataService;

@RestController
@RequestMapping("/product")
public class ControllerRest {

	@Autowired
	ProductDataService productDataService;
	
	@GetMapping("/health")
	public ResponseEntity<String> retrieveAllStudents() {
		return new ResponseEntity<>("running", HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody(required = true) Product product) throws InternalServerError {
		Product savedProduct = productDataService.save(product);
		if(savedProduct == null) {
			ErrorModel em = new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), "please try again, after some times");
			throw new InternalServerError(em);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
	}
	
	@GetMapping("/getByProductName/{productName}")
	public ResponseEntity<Product> getProduct(@PathVariable(required = true) String productName) throws ProductDoesNotExistsException {
		Product product = productDataService.getByProductName(productName);
		if(product == null) {
			ErrorModel em = new ErrorModel(HttpStatus.NOT_FOUND.value(), "product does not exists");
			throw new ProductDoesNotExistsException(em);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> product = productDataService.getAllProducts();
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Object> updateProduct(@RequestBody(required = true) Product product, @Valid @PathVariable(required = true) @Min(1) int id) throws ProductDoesNotExistsException {
		return productDataService.updateUser(product, id);
	}
	
	@PutMapping("/updateByProductName/{productName}")
	public ResponseEntity<Object> updateProductByName(@RequestBody(required = true) Product product, @PathVariable(required = true) String productName) throws ProductDoesNotExistsException {
		return productDataService.updateUser(product, productName);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteProduct(@Valid @PathVariable(required = true) @Min(1) int id) throws ProductDoesNotExistsException {
		return productDataService.deleteProduct(id);
	}
	
	@DeleteMapping("/deleteByProductName/{productName}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(required = true) String productName) throws ProductDoesNotExistsException {
		return productDataService.deleteByProductName(productName);
	}
	
}
