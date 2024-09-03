package com.sonata.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.Model.Category;
import com.sonata.Repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Create Category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    // Create multiple Categories
    public List<Category> saveMultipleCategories(List<Category> categories){
    	return categoryRepository.saveAll(categories);
    }
    
    // Get Category by ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // Get all Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Update Category
    public Category updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);
        if (existingCategory != null) {
        	
        	if(updatedCategory.getCategoryName() != null) {
        		existingCategory.setCategoryName(updatedCategory.getCategoryName());        		
        	}
        	if(updatedCategory.getDescription() != null) {
        		existingCategory.setDescription(updatedCategory.getDescription());        		
        	}
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    // Delete a Category by ID
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
