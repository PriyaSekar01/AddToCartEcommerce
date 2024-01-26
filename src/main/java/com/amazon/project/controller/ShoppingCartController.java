package com.amazon.project.controller;









import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.project.dto.OrderDto;
import com.amazon.project.entity.Order;
import com.amazon.project.entity.Product;
import com.amazon.project.entity.User;
import com.amazon.project.response.APIResponse;
import com.amazon.project.response.APIresponseOrder;
import com.amazon.project.service.OrderService;
import com.amazon.project.service.ProductService;
import com.amazon.project.service.UserService;

@RestController
@RequestMapping("/cartToAdd")
public class ShoppingCartController {
	          
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	private Logger logger =  LoggerFactory.getLogger(ShoppingCartController.class);
	
	@PostMapping("/addProduct")
	public ResponseEntity<APIResponse> addProduct(@RequestBody Product product){
		APIResponse response = productService.addProduct(product);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	
	@PostMapping("/listOfProduct")
	public ResponseEntity<APIResponse> addListOfProduct(@RequestBody List<Product> product){
		APIResponse response = productService.addListOfProduct(product);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@GetMapping("/getAllProduct")
	public ResponseEntity<APIResponse> getAllProduct(){
		APIResponse response = productService.getAllProducts();
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@GetMapping("/getOrder/{id}")
	public ResponseEntity<APIResponse> getOrderDetails(Integer orderId){
		APIResponse response = orderService.getOrderDetails(orderId);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	@PostMapping("/placeOrder")
	public ResponseEntity<APIresponseOrder> placeOrder(@RequestBody OrderDto orderDto) {
	    logger.info("Received order request: " + orderDto.toString());

	    float amount = orderService.getCartAmount(orderDto.getCartItems());

	    User user = userService.getByEmail(orderDto.getEmail());

	    if (user == null) {
	        user = new User(orderDto.getName(), orderDto.getEmail());
	        user = userService.save(user);
	        logger.info("New customer saved with id: " + user.getId());
	    } else {
	        logger.info("Customer already present in the database with id: " + user.getId());
	    }

	    Order order = new Order(orderDto.getOrderDescription(), user, orderDto.getCartItems());
	    order = orderService.saveOrder(order);

	    APIresponseOrder response = new APIresponseOrder();
	    response.setAmount(amount);
	    response.setOrderId(order.getId());
	    response.setOrderDescription(orderDto.getOrderDescription());

	    logger.info("Order processed successfully.");

	    return ResponseEntity.ok(response);
	}

	
	

}

