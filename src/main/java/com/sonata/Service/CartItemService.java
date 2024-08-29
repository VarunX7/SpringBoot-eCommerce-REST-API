package com.sonata.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.Model.CartItem;
import com.sonata.Repository.CartItemRepository;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    // Create CartItem
    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    // Get CartItem by ID
    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    // Get all CartItems
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    // Update CartItem
    public CartItem updateCartItem(Long id, int newQuantity) {
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
