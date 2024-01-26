package com.amazon.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.amazon.project.entity.Cart;
import com.amazon.project.entity.Order;
import com.amazon.project.entity.Product;
import com.amazon.project.repository.CartRepository;
import com.amazon.project.repository.OrderRepository;
import com.amazon.project.repository.ProductRepository;
import com.amazon.project.response.APIResponse;

@Service
public class OrderService {
	
	 @Autowired
	    private OrderRepository orderRepository;
	 
	 @Autowired
	 private ProductRepository productRepository;
	 
	 @Autowired
	 private CartRepository cartRepository;

	    public List<Order> getAllOrders() {
	        return orderRepository.findAll();
	    }

	    public APIResponse getOrderDetails(Integer orderId) {
	    	APIResponse response = new APIResponse();
	        Optional<Order> order =orderRepository.findById(orderId);
	         Order orders=order.isPresent()? order.get():null;
	         response.setStatus(HttpStatus.OK.value());
	         response.setData(orders);
	         response.setMessage("Oder Details");
	         return response;
	         
	    }

	    public Order placeOrder(Order order) {
	        return orderRepository.save(order);
	    }
	    
	    public float getCartAmount(List<Cart> shoppingCartList) {
	        float totalCartAmount = 0f;
	        float singleCartAmount = 0f;
	        int availableQuantity = 0;

	        for (Cart cartObj : shoppingCartList) {
	            int productId = cartObj.getProductId();
	            Optional<Product> product = productRepository.findById(productId);

	            if (product.isPresent()) {
	                Product productObj = product.get();

	                if (productObj.getAvailableQuatity() < cartObj.getQuantity()) {
	                    singleCartAmount = productObj.getPrice() * productObj.getAvailableQuatity();
	                    cartObj.setQuantity(productObj.getAvailableQuatity());
	                } else {
	                    singleCartAmount = cartObj.getQuantity() * productObj.getPrice();
	                    availableQuantity = productObj.getAvailableQuatity() - cartObj.getQuantity();
	                }

	                totalCartAmount += singleCartAmount;
	                productObj.setAvailableQuatity(availableQuantity);
	                availableQuantity = 0;
	                cartObj.setProductName(productObj.getName());
	                cartObj.setAmount(singleCartAmount);
	                productRepository.save(productObj);
	                cartRepository.save(cartObj);
	            }
	        }
            return totalCartAmount;
	    }

	    
	    public Order saveOrder(Order order) {
	    	return orderRepository.save(order);
	    }

}
