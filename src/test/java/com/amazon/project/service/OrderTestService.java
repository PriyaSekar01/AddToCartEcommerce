package com.amazon.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.amazon.project.entity.Order;
import com.amazon.project.repository.CartRepository;
import com.amazon.project.repository.OrderRepository;
import com.amazon.project.repository.ProductRepository;
import com.amazon.project.response.APIResponse;

@SpringBootTest
public class OrderTestService {
	
	 @Mock
	    private OrderRepository orderRepository;

	    @Mock
	    private ProductRepository productRepository;

	    @Mock
	    private CartRepository cartRepository;

	    @InjectMocks
	    private OrderService orderService;

	    @Test
	    public void testGetAllOrders() {
	        
	        when(orderRepository.findAll()).thenReturn(Collections.emptyList());
            List<Order> result = orderService.getAllOrders();
            verify(orderRepository, times(1)).findAll();
            assertNotNull(result);
	        assertTrue(result.isEmpty());
	    }

	    @Test
	    public void testGetOrderDetails() {
	        
	        Order sampleOrder = new Order();
	        sampleOrder.setId(1L);
            when(orderRepository.findById(1)).thenReturn(Optional.of(sampleOrder));
            APIResponse result = orderService.getOrderDetails(1);
            verify(orderRepository, times(1)).findById(1);
            assertNotNull(result);
	        assertEquals(HttpStatus.OK.value(), result.getStatus());
	        assertEquals("Oder Details", result.getMessage().trim());
	        assertEquals(sampleOrder, result.getData());
	    }

	    @Test
	    public void testPlaceOrder() {
	       
	        Order sampleOrder = new Order();
	        when(orderRepository.save(sampleOrder)).thenReturn(sampleOrder);
	        Order result = orderService.placeOrder(sampleOrder);
	        verify(orderRepository, times(1)).save(sampleOrder);
	        assertNotNull(result);
	        assertEquals(sampleOrder, result);
	    }


	}


