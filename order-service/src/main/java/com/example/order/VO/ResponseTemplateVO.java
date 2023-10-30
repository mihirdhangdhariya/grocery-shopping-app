package com.example.order.VO;

import com.example.order.entity.Order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
	private Product product;
	private Order order;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}