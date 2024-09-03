package com.sonata.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.Model.CartItem;
import com.sonata.Model.Product;
import com.sonata.Repository.CartItemRepository;
import com.sonata.Repository.ProductRepository;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepo;

    // Create CartItem
    public CartItem saveCartItem(CartItem cartItem) {
    	Product product = productRepo.findById(cartItem.getProduct()).orElse(null);
    	if(product != null) {
    		if(product.getStockQuantity() < cartItem.getQuantity() && product.getStockQuantity() < 10) {
    			cartItem.setQuantity(product.getStockQuantity());
    		}
    		else if(cartItem.getQuantity() > 10) {
    			cartItem.setQuantity((long)10);
    		}
    	}
        return cartItemRepository.save(cartItem);
    }
    
    // Create Multiple CartItems
    public List<CartItem> saveMultipleCartItems(List<CartItem> cartItems){
    	return cartItemRepository.saveAll(cartItems);
    }

    // Get CartItem by ID
    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    // Get all CartItems of a user
    public List<CartItem> getAllCartItems(Long user) {
        return cartItemRepository.findByUser(user);
    }

    // Update CartItem
    public CartItem updateCartItem(Long id, Long newQuantity) {
        CartItem existingCartItem = cartItemRepository.findById(id).orElse(null);
        if (existingCartItem != null) {
            existingCartItem.setQuantity(newQuantity);
            return cartItemRepository.save(existingCartItem);
        }
        return null;
    }

    // Delete a CartItem by ID
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
