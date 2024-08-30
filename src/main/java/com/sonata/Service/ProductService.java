package com.sonata.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.Model.Category;
import com.sonata.Model.Product;
import com.sonata.Repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Create product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get products by category
    public List<Product> getProductsByCategory(Long category) {
        return productRepository.findByCategoryId(category);
    }

    // Get Products by substring in their names
    public List<Product> getProductsByNameString(String substring){
    	return productRepository.findByProductNameContainingIgnoreCase(substring);
    }
    
    // Update product details
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
        	
        	if(updatedProduct.getCategoryId() != null) {
        		existingProduct.setCategoryId(updatedProduct.getCategoryId());        		
        	}
        	if(updatedProduct.getProductName() != null) {
        		existingProduct.setProductName(updatedProduct.getProductName());
        	}
        	if(updatedProduct.getDescription() != null) {
        		existingProduct.setDescription(updatedProduct.getDescription());
        	}
        	if(updatedProduct.getPrice() != null) {
        		existingProduct.setPrice(updatedProduct.getPrice());
        	}
        	if(updatedProduct.getStockQuantity() != null) {
        		existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
        	}
            return productRepository.save(existingProduct);
        }
        return null;
    }

    // Delete a product by ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
