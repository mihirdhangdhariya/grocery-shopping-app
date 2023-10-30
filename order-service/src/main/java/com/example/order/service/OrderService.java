package com.example.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.example.order.VO.Product;
import com.example.order.VO.ResponseTemplateVO;
import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	RestTemplate restTemplate;

	@PostMapping
	public ResponseTemplateVO createOrder(@RequestBody Order order) {
		Long productId = order.getProductId();
		Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/products/{productId}", Product.class,
				productId);
		if (product == null) {
			return null;
		}
		double totalCost = product.getPrice() * order.getQuantity();
		order.setTotalCost(totalCost);
		Order createdOrder = orderRepository.save(order);
		ResponseTemplateVO vo = new ResponseTemplateVO();
		vo.setOrder(createdOrder);
		vo.setProduct(product);
		return vo;
	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Order getOrderById(Long orderId) {
		return orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
	}

	public Order updateOrder(Long orderId, Order updatedOrder) {
		Order existingOrder = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
		if (existingOrder != null) {
			existingOrder.setQuantity(updatedOrder.getQuantity());
			return orderRepository.save(existingOrder);
		}
		return null;
	}

	public void deleteOrder(Long orderId) {
		orderRepository.deleteById(orderId);
	}
}