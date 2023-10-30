package com.example.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.order.VO.ResponseTemplateVO;
import com.example.order.entity.Order;
import com.example.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/createOrder")
	public ResponseTemplateVO createOrder(@RequestBody Order order) {
		return orderService.createOrder(order);
	}

	@GetMapping("/getAllOrders")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/{orderId}")
	public Order getOrderById(@PathVariable Long orderId) {
		return orderService.getOrderById(orderId);
	}

	@PutMapping("/{orderId}")
	public Order updateOrder(@PathVariable Long orderId, @RequestBody Order updatedOrder) {
		return orderService.updateOrder(orderId, updatedOrder);
	}

	@DeleteMapping("/{orderId}")
	public void deleteOrder(@PathVariable Long orderId) {
		orderService.deleteOrder(orderId);
	}
}