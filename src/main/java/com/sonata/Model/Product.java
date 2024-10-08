package com.sonata.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "category_id", nullable = false)
    private Long categoryId;
    private String productName;
    private String description;
    private String imageUrl;
	private Double price;
    private Long stockQuantity;
    private Double rating;


	public Product(){}

    public Product(Long categoryId, String productName, String description, Double price, Long stockQuantity, Double rating) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.rating = rating;
    }
    
    public String getImageUrl() {
    	return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
    	this.imageUrl = imageUrl;
    }
    
    public Double getRating() {
    	return rating;
    }
    
    public void setRating(Double rating) {
    	this.rating = rating;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long category) {
        this.categoryId = category;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Long getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    
}
