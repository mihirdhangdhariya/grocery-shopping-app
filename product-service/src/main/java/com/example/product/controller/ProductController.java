package com.example.product.controller;

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

import com.example.product.entity.Product;
import com.example.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/createProduct")
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	@GetMapping("/getAllProducts")
	public List<Product> getAllOrders() {
		return productService.getAllProducts();
	}

	@GetMapping("/{productId}")
	public Product getProductById(@PathVariable Long productId) {
		return productService.getProductById(productId);
	}

	@PutMapping("/{productId}")
	public Product updateOrder(@PathVariable Long productId, @RequestBody Product updatedProduct) {
		return productService.updateProduct(productId, updatedProduct);
	}

	@DeleteMapping("/{productId}")
	public void deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
	}
}