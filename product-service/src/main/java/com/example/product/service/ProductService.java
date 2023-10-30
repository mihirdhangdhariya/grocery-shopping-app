package com.example.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
	}

	public Product updateProduct(Long productId, Product updatedProduct) {
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
		if (existingProduct != null) {
			existingProduct.setPrice(updatedProduct.getPrice());
			return productRepository.save(existingProduct);
		}
		return null;
	}

	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}
}