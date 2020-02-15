package com.service.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.service.exception.ProductDoesNotExistsException;
import com.service.model.Category;
import com.service.model.Merchant;
import com.service.model.Product;
import com.service.repository.CategoryRepository;
import com.service.repository.MerchantRepository;
import com.service.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductDataServiceTest {

	@InjectMocks
	ProductDataService productDataService;
	
	@Mock
	ProductRepository productRepository;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Mock
	MerchantRepository merchantRepository;
	
	@Test
	public void checkGetByproductNameWithValues() throws Exception {
		String productName = "sample";
		Mockito.when(productRepository.findByProductName(productName)).thenReturn(getOptionalProduct());
		Product product = productDataService.getByProductName(productName);
		Mockito.verify(productRepository, Mockito.times(1)).findByProductName(productName);
		assertNotNull(product);
		assertEquals(product.getProductId(), product.getProductId());
	}
	
	@Test
	public void checkGetByProductNotPresent() throws Exception {
		String productName = "sample";
		Mockito.when(productRepository.findByProductName(productName)).thenReturn(Optional.ofNullable(null));
		Product product = productDataService.getByProductName(productName);
		Mockito.verify(productRepository, Mockito.times(1)).findByProductName(productName);
		assertNull(product);
	}
	
	@Test
	public void checkupdateUserwithAllValues() throws Exception {
		Mockito.when(productRepository.findById(1)).thenReturn(getOptionalProduct());
		ResponseEntity<Object> resp = productDataService.updateUser(getListOfProduct().get(0), 1);
		Mockito.verify(productRepository, Mockito.times(1)).save(getOptionalProduct().get());
		assertEquals(resp.getStatusCodeValue(), 200);
	}
	
	@Test(expected = ProductDoesNotExistsException.class)
	public void checkupdateUserwithNoValues() throws Exception {
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		productDataService.updateUser(getListOfProduct().get(0), 1);
	}
	
	@Test
	public void checkupdateUserwithProductByNameAllValues() throws Exception {
		Mockito.when(productRepository.findByProductName("sample")).thenReturn(getOptionalProduct());
		ResponseEntity<Object> resp = productDataService.updateUser(getListOfProduct().get(0), "sample");
		Mockito.verify(productRepository, Mockito.times(1)).save(getOptionalProduct().get());
		assertEquals(resp.getStatusCodeValue(), 200);
	}
	
	@Test(expected = ProductDoesNotExistsException.class)
	public void checkupdateUserwithProductByNameNoValues() throws Exception {
		Mockito.when(productRepository.findByProductName("sample")).thenReturn(Optional.ofNullable(null));
		productDataService.updateUser(getListOfProduct().get(0), "sample");
	}
	
	@Test
	public void checkDeleteProductWithAllValues() throws Exception {
		Mockito.when(productRepository.findById(1)).thenReturn(getOptionalProduct());
		Mockito.when(productRepository.getCategoryCount(Mockito.anyInt())).thenReturn(1);
		Mockito.when(productRepository.getMerchantCount(Mockito.anyInt())).thenReturn(1);
		ResponseEntity<Object> resp = productDataService.deleteProduct(1);
		Mockito.verify(productRepository, Mockito.times(1)).deleteProduct(1);
		Mockito.verify(categoryRepository, Mockito.times(1)).deleteCategory(0);
		Mockito.verify(merchantRepository, Mockito.times(1)).deleteMerchant(0);
		assertEquals(resp.getStatusCodeValue(), 200);
	}
	
	@Test(expected=ProductDoesNotExistsException.class)
	public void checkDeleteProductWithNoValues() throws Exception {
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		productDataService.deleteProduct(1);
	}
	
	
	@Test
	public void checkDeleteProductByProductNameWithAllValues() throws Exception {
		Mockito.when(productRepository.findByProductName("sample")).thenReturn(getOptionalProduct());
		Mockito.when(productRepository.getCategoryCount(Mockito.anyInt())).thenReturn(1);
		Mockito.when(productRepository.getMerchantCount(Mockito.anyInt())).thenReturn(1);
		ResponseEntity<Object> resp = productDataService.deleteByProductName("sample");
		Mockito.verify(productRepository, Mockito.times(1)).deleteProduct(1);
		Mockito.verify(categoryRepository, Mockito.times(1)).deleteCategory(0);
		Mockito.verify(merchantRepository, Mockito.times(1)).deleteMerchant(0);
		assertEquals(resp.getStatusCodeValue(), 200);
	}
	
	@Test(expected = ProductDoesNotExistsException.class)
	public void checkDeleteProductByProductNameWithNoValues() throws Exception {
		Mockito.when(productRepository.findByProductName("sample")).thenReturn(Optional.ofNullable(null));
		productDataService.deleteByProductName("sample");
	}
	
	private Optional<Product> getOptionalProduct() {
		return Optional.of(getListOfProduct().get(0));
	}
	
	private List<Product> getListOfProduct() {
		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setCategory(getCategory());
		product.setMerchant(getMerchant());
		product.setDescription("short desc");
		product.setImage("/img/");
		product.setMsrp(0.0);
		product.setPrice(10.0);
		product.setProductAvailability(true);
		product.setProductName("sample");
		product.setUrl("/product/");
		product.setProductId(1);
		productList.add(product);
		return productList;
	}

	private Merchant getMerchant() {
		Merchant merchant = new Merchant();
		merchant.setMerchantId(0);
		merchant.setMerchantName("nike");
		merchant.setDescription("merchant desc");
		return merchant;
	}
	
	private Category getCategory() {
		Category parentCategory = new Category();
		parentCategory.setCategoryId(0);
		parentCategory.setCategoryName("shoes");
		Category category = new Category();
		category.setCategoryId(0);
		category.setCategoryName("nike air");
		category.setParentCategory(parentCategory);
		return category;
	}
	
}
