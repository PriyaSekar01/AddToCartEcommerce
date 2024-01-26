package com.amazon.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.amazon.project.entity.Product;
import com.amazon.project.repository.CartRepository;
import com.amazon.project.repository.OrderRepository;
import com.amazon.project.repository.ProductRepository;
import com.amazon.project.response.APIResponse;



@SpringBootTest
public class ProductTestService {
	
	

	    @Mock
	    private ProductRepository productRepository;
	    
	    

	    @InjectMocks
	    private ProductService productService;
	    
	    
	    @Test
	    public void testAddProduct() {
	        Product product = new Product();
	        product.setName("Sample Product");
	        product.setAvailableQuatity(2);
	        product.setPrice(20.0f);

	        when(productRepository.save(any(Product.class))).thenReturn(product);

	        APIResponse result = productService.addProduct(product);

	        verify(productRepository, times(1)).save(any(Product.class));

	        assertThat(result).isNotNull();
	        assertEquals(HttpStatus.OK.value(), result.getStatus());
	        assertEquals("Save successfully", result.getMessage().trim());
	    }

       @Test
	    public void testAddListOfProduct() {
	        List<Product> productList = Arrays.asList(
	                new Product("Product1", 2, 10.0f),
	                new Product("Product2", 7, 15.0f)
	        );
	        when(productRepository.saveAll(anyList())).thenReturn(productList);
	        APIResponse result = productService.addListOfProduct(productList);
	        verify(productRepository, times(1)).saveAll(anyList());
	        assertThat(result).isNotNull();
	        assertEquals(HttpStatus.OK.value(), result.getStatus());
	        assertEquals("Products saved successfully", result.getMessage().trim());
	        List<Product> savedProducts = (List<Product>) result.getData();
	        assertNotNull(savedProducts);
	        assertEquals(2, savedProducts.size());
	      
	    }


	    @Test
	    public void testGetAllProducts() {
	        List<Product> productList = Arrays.asList(
	                new Product("Product1",2,10.0f),
	                new Product("Product1",2,10.0f)
	        );
	        when(productRepository.findAll()).thenReturn(productList);
	        APIResponse result = productService.getAllProducts();
	        verify(productRepository, times(1)).findAll();
	        assertThat(result).isNotNull();
	        assertEquals(HttpStatus.OK.value(), result.getStatus());
	        assertEquals("Products retrieved successfully", result.getMessage().trim());
	    }

	    @Test
	    public void testGetProductById() {
	        Product product = new Product("Sample Product", 8, 20.0f);
	        product.setProdectId(1);
	        when(productRepository.findById(1)).thenReturn(Optional.of(product));
	        APIResponse result = productService.getByProduct(1);
	        verify(productRepository, times(1)).findById(1);
	        assertThat(result).isNotNull();
	        assertEquals(HttpStatus.OK.value(), result.getStatus());
	        assertEquals("Product found successfully", result.getMessage().trim());
	        Optional<Product> retrievedProduct = (Optional<Product>) result.getData();
	        assertTrue(retrievedProduct.isPresent());
	        assertEquals(product, retrievedProduct.get());
	    }

	}



