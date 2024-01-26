package com.amazon.project.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import com.amazon.project.entity.Product;
import com.amazon.project.repository.ProductRepository;
import com.amazon.project.response.APIResponse;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository prodectRepository;
	
	
	public APIResponse addProduct(Product product){
		APIResponse response = new APIResponse();
		if(product!=null) {
		Product productObj=prodectRepository.save(product);
		response.setStatus(HttpStatus.OK.value());
		response.setData(productObj);
		response.setMessage("Save successfully");
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Check details");
		}
		return response;
	}
	
	
	public APIResponse getAllProducts() {
		APIResponse response = new APIResponse();
		List<Product> productList = prodectRepository.findAll();
        if (!productList.isEmpty()) {
	        response.setStatus(HttpStatus.OK.value());
	        response.setData(productList);
	        response.setMessage("Products retrieved successfully");
	    } else {
	        response.setStatus(HttpStatus.NOT_FOUND.value());
	        response.setMessage("No products found");
	    }
       return response;
	}
	
	public APIResponse getByProduct(Integer id) {
		APIResponse response = new APIResponse();
		Optional<Product> product =prodectRepository.findById(id);
		if(product!=null) {
	        response.setStatus(HttpStatus.OK.value());
	        response.setData(product);
	        response.setMessage("Product found successfully");
	    } else {
	        response.setStatus(HttpStatus.NOT_FOUND.value());
	        response.setMessage("Product not found for id: " + id);
	    }
         return response;
	}
	
	public APIResponse deleteByProduct(Product product) {
		APIResponse response = new APIResponse();
		 try {
		        prodectRepository.delete(product);
		        response.setStatus(HttpStatus.OK.value());
		        response.setMessage("Product deleted successfully");
		    } catch (Exception e) {
		        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		        response.setMessage("Error deleting product: " + e.getMessage());
		    }

		    return response;
		}
	
	public APIResponse addListOfProduct(List<Product> products) {
		APIResponse response = new APIResponse();
		 try {
		        List<Product> savedProducts = prodectRepository.saveAll(products);

		        if (!savedProducts.isEmpty()) {
		            response.setStatus(HttpStatus.OK.value());
		            response.setData(savedProducts);
		            response.setMessage("Products saved successfully");
		        } else {
		            response.setStatus(HttpStatus.BAD_REQUEST.value());
		            response.setMessage("No products were saved");
		        }
		    } catch (Exception e) {
		        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		        response.setMessage("Error saving products: " + e.getMessage());
		    }

		    return response;
		
}

}


