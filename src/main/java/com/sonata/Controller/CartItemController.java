package com.sonata.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sonata.Model.CartItem;
import com.sonata.Service.CartItemService;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    // Create a new CartItem
    @PostMapping
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        CartItem createdCartItem = cartItemService.saveCartItem(cartItem);
        return new ResponseEntity<>(createdCartItem, HttpStatus.CREATED);
    }

    // Get a CartItem by ID
    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long id) {
        CartItem cartItem = cartItemService.getCartItemById(id);
        if (cartItem != null) {
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all CartItems
    @GetMapping("/user/{user}")
    public ResponseEntity<List<CartItem>> getAllCartItems(@PathVariable Long user) {
        List<CartItem> cartItems = cartItemService.getAllCartItems(user);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    // Update a CartItem Quantity
    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, @RequestBody int newQuantity) {
        CartItem cartItem = cartItemService.updateCartItem(id, newQuantity);
        if (cartItem != null) {
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a CartItem by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
