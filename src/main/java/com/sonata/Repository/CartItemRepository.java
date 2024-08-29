package com.sonata.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sonata.Model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Custom queries can be added here if needed
}